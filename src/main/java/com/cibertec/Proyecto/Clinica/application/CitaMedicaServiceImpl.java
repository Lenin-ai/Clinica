package com.cibertec.Proyecto.Clinica.application;

import com.cibertec.Proyecto.Clinica.application.dto.CitaMedicaDTO;
import com.cibertec.Proyecto.Clinica.domain.model.CitaMedica;
import com.cibertec.Proyecto.Clinica.domain.repository.CitaMedicaRepository;
import com.cibertec.Proyecto.Clinica.domain.service.CitaMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaMedicaServiceImpl implements CitaMedicaService {

    private final CitaMedicaRepository citaMedicaRepository;

    @Override
    public CitaMedica registrarCita(CitaMedica citaMedica) {
        citaMedica.setFechaCreacion(LocalDateTime.now());
        citaMedica.setUsuarioCreacion("admin");
        return citaMedicaRepository.save(citaMedica);
    }

    @Override
    public Optional<CitaMedica> obtenerCitaPorId(Integer id) {
        return citaMedicaRepository.findById(id);
    }

   /* @Override
    public List<CitaMedica> listarCitas() {
        return citaMedicaRepository.findAll();
    }*/

    @Override
    public void eliminarCita(Integer id) {
        CitaMedica citaMedica= citaMedicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
        citaMedicaRepository.deleteById(id);
    }

    @Override
    public CitaMedica actualizarCita(CitaMedica citaMedica) {
        citaMedica.setFechaActualizacion(LocalDateTime.now());
        citaMedica.setUsuarioActualizacion("admin_update");
        return citaMedicaRepository.update(citaMedica);
    }

    @Override
    public Page<CitaMedicaDTO> listarCitasPaginadas(Pageable pageable) {
        return citaMedicaRepository.findAllPaginado(pageable);
    }
}
