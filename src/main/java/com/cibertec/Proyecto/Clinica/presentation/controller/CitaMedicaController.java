package com.cibertec.Proyecto.Clinica.presentation.controller;
import com.cibertec.Proyecto.Clinica.application.dto.CitaMedicaDTO;
import com.cibertec.Proyecto.Clinica.domain.model.CitaMedica;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaMedicaController {

    private final com.cibertec.Proyecto.Clinica.domain.service.CitaMedicaService citaMedicaService;

    // 🔹 Crear cita médica.
    @PostMapping
    public ResponseEntity<CitaMedica> crearCita(@RequestBody CitaMedica citaMedica) {
        return ResponseEntity.ok(citaMedicaService.registrarCita(citaMedica));
    }

   // 🔹 Obtener todas las citas médicas.
   @GetMapping
   public Page<CitaMedicaDTO> listarCitas(Pageable pageable) {
       return citaMedicaService.listarCitasPaginadas(pageable);
   }

    // 🔹 Buscar cita médica por ID.
    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> obtenerCita(@PathVariable Integer id) {
        return citaMedicaService.obtenerCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Actualizar cita médica.
    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> actualizarCita(@PathVariable Integer id, @RequestBody CitaMedica citaMedica) {
        citaMedica.setId(id); // Aseguramos que actualice la correcta
        return ResponseEntity.ok(citaMedicaService.actualizarCita(citaMedica));
    }

    // 🔹 Eliminar cita médica.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Integer id) {
        citaMedicaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

}
