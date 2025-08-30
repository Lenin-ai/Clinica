package com.cibertec.Proyecto.Clinica.presentation.controller;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;
import com.cibertec.Proyecto.Clinica.domain.service.EspecialidadService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService service) {
        this.especialidadService = service;
    }

    // ✅ Listar todas
    @GetMapping
    public ResponseEntity<Page<Especialidad>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(especialidadService.listarPaginado(page, size));
    }

    // ✅ Obtener por ID 
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtener(@PathVariable Integer id) {
        Especialidad especialidad = especialidadService.obtenerPorId(id);
        return ResponseEntity.ok(especialidad);
    }

    // ✅ Registrar nueva
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Especialidad especialidad) {
        try {
            Especialidad nueva = especialidadService.guardar(especialidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Especialidad especialidad) {
        try {
            return ResponseEntity.ok(especialidadService.actualizar(id, especialidad));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        especialidadService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 en vez de 200 con texto
    }
}
