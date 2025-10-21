package com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.in.rest.controller;

import com.cibertec.Proyecto.Clinica.Doctor.domain.Model.Medico;
import com.cibertec.Proyecto.Clinica.Doctor.application.ports.in.MedicoServicePort;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    private final MedicoServicePort medicoServicePort;

    public MedicoController(MedicoServicePort medicoServicePort) {
        this.medicoServicePort = medicoServicePort;
    }
    @GetMapping()
    public Page<Medico> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return medicoServicePort.listarPaginado(page, size);
    }

    // Listar todos
 /*   @GetMapping
    public List<Medico> listar() {
        return medicoService.listar();
    }*/

    // Obtener por ID
    @GetMapping("/{id}")
    public Medico obtener(@PathVariable Integer id) {
       return medicoServicePort.obtenerPorId(id);
    }

    // Agregar nuevo
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Medico medico) {
        try {
            return ResponseEntity.ok(medicoServicePort.guardar(medico));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Actualizar (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable Integer id, @RequestBody Medico medico) {
        medico.setId(id);
        Medico actualizado = medicoServicePort.actualizar(medico);
        return  ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        medicoServicePort.eliminar(id);
        return ResponseEntity.ok("Médico eliminado correctamente");
    }


}
