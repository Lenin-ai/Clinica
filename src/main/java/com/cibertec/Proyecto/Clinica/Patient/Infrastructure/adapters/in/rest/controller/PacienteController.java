package com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.in.rest.controller;

import com.cibertec.Proyecto.Clinica.Patient.domain.Model.Paciente;
import com.cibertec.Proyecto.Clinica.Patient.application.ports.in.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;}

    @GetMapping
    public Page<Paciente> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.listarPaginado(page, size);
    }

 /*   @GetMapping
    public List<Paciente> listar() {
        return service.listar();
    }*/

    @GetMapping("/{id}")
    public Paciente obtener(@PathVariable Integer id) {return service.obtener(id);}

    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Paciente paciente){
        try{
            return ResponseEntity.ok(service.agregar(paciente));
        } catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Paciente paciente) {
        try {
            paciente.setId(id);
            return ResponseEntity.ok(service.actualizar(paciente));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> eliminar(@PathVariable Integer id){
        service.eliminar(id);
        return ResponseEntity.ok("Paciente eliminado");
    }

}
