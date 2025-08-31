package com.cibertec.Proyecto.Clinica.infrastructure.persitence.jpa;

import com.cibertec.Proyecto.Clinica.application.dto.CitaMedicaDTO;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.entity.CitaMedicaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaMedicaRepositoryJpa extends JpaRepository<CitaMedicaEntity, Integer> {

    @Query(
            value = "SELECT new com.cibertec.Proyecto.Clinica.application.dto.CitaMedicaDTO(" +
                    "c.id, c.fecha, c.hora, " +
                    "p.id, m.id, " +
                    "CONCAT(p.apellidos, ', ', p.nombres), " +
                    "CONCAT(m.apellidos, ', ', m.nombres), " +
                    "m.especialidad.nombre, " +
                    "c.motivo, c.estado) " +
                    "FROM CitaMedicaEntity c " +
                    "JOIN c.paciente p " +
                    "JOIN c.medico m " +
                    "ORDER BY c.id DESC",
            countQuery = "SELECT COUNT(c) FROM CitaMedicaEntity c"
    )
    Page<CitaMedicaDTO> findAllCitasConNombres(Pageable pageable);

}
