package com.cibertec.Proyecto.Clinica.domain.repository;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EspecialidadRepository {
    Especialidad save(Especialidad especialidad);
    Optional<Especialidad> findById(Integer id);
    List<Especialidad> findAll();
    Especialidad actualizar(Integer id, Especialidad especialidad);
    void deleteById(Integer id);
    Page<Especialidad> findAllPaginado(int page, int size);
}
