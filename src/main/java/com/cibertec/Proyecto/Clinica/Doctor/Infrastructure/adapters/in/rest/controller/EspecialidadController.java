package com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.in.rest.controller;

import com.cibertec.Proyecto.Clinica.Doctor.domain.Model.Especialidad;
import com.cibertec.Proyecto.Clinica.Doctor.application.ports.in.EspecialidadServicePort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final EspecialidadServicePort especialidadServicePort;

    public EspecialidadController(EspecialidadServicePort service) {
        this.especialidadServicePort = service;
    }

    // ✅ Listar todas
    @GetMapping
    public ResponseEntity<Page<Especialidad>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(especialidadServicePort.listarPaginado(page, size));
    }

    // ✅ Obtener por ID 
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtener(@PathVariable Integer id) {
        Especialidad especialidad = especialidadServicePort.obtenerPorId(id);
        return ResponseEntity.ok(especialidad);
    }

    // ✅ Registrar nueva
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Especialidad especialidad) {
        try {
            Especialidad nueva = especialidadServicePort.guardar(especialidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Especialidad especialidad) {
        try {
            return ResponseEntity.ok(especialidadServicePort.actualizar(id, especialidad));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        especialidadServicePort.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 en vez de 200 con texto
    }
}
