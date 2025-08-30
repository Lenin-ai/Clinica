package com.cibertec.Proyecto.Clinica.presentation.controller;

import com.cibertec.Proyecto.Clinica.domain.model.Medico;
import com.cibertec.Proyecto.Clinica.domain.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }
    @GetMapping()
    public Page<Medico> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return medicoService.listarPaginado(page, size);
    }

    // Listar todos
 /*   @GetMapping
    public List<Medico> listar() {
        return medicoService.listar();
    }*/

    // Obtener por ID
    @GetMapping("/{id}")
    public Medico obtener(@PathVariable Integer id) {
       return medicoService.obtenerPorId(id);
    }

    // Agregar nuevo
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Medico medico) {
        try {
            return ResponseEntity.ok(medicoService.guardar(medico));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Actualizar (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable Integer id, @RequestBody Medico medico) {
        medico.setId(id);
        Medico actualizado = medicoService.actualizar(medico);
        return  ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        medicoService.eliminar(id);
        return ResponseEntity.ok("Médico eliminado correctamente");
    }


}
