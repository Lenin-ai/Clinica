package com.cibertec.Proyecto.Clinica.infrastructure.persitence.jpa;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.entity.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadRepositoryJpa extends JpaRepository<EspecialidadEntity,Integer> {

}
