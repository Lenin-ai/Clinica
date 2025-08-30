package com.cibertec.Proyecto.Clinica.domain.repository;

import com.cibertec.Proyecto.Clinica.domain.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository
{
    Paciente save(Paciente paciente);
    Optional<Paciente> findById(Integer id);
    List<Paciente> findAll();
    @Transactional
    Paciente update(Paciente paciente);
    void deleteById(Integer id);
    Page<Paciente> findAllPaginado(Pageable pageable);
}
