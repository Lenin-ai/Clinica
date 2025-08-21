package com.cibertec.Proyecto.Clinica.domain.repository;

import com.cibertec.Proyecto.Clinica.domain.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository
{
    Paciente save(Paciente paciente);
    Optional<Paciente> findById(Integer id);
    List<Paciente> findAll();
    void deleteById(Integer id);
}
