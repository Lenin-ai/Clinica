package com.cibertec.Proyecto.Clinica.domain.repository;

import com.cibertec.Proyecto.Clinica.domain.model.CitaMedica;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CitaMedicaRepository {
    CitaMedica save(CitaMedica citaMedica);
    Optional<CitaMedica> findById(Integer id);
    List<CitaMedica> findAll();
    void deleteById(Integer id);
    @Transactional
    CitaMedica update(CitaMedica citaMedica);
}
