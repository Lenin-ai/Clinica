package com.cibertec.Proyecto.Clinica.domain.repository;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadRepository {
    Especialidad save(Especialidad especialidad);
    Optional<Especialidad> findById(Integer id);
    List<Especialidad> findAll();
    void deleteById(Integer id);
}
