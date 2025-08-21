package com.cibertec.Proyecto.Clinica.infrastructure.persitence.jpa;

import com.cibertec.Proyecto.Clinica.domain.model.Paciente;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepositoryJpa extends JpaRepository<PacienteEntity, Integer> {
    Optional<PacienteEntity> findById(Integer id);
}
