package com.cibertec.Proyecto.Clinica.infrastructure.persitence.jpa;

import com.cibertec.Proyecto.Clinica.infrastructure.persitence.entity.CitaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaMedicaRepositoryJpa extends JpaRepository<CitaMedicaEntity, Integer> {
}
