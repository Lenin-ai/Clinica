package com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.repository;

import com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.entity.EspecialidadEntity;
import com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.entity.MedicoEntity;
import com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.out.persistence.entity.PacienteEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("h2-test")
class MedicoRepositoryJpaTest {
    @Autowired
    private MedicoRepositoryJpa repository;

    @Autowired
    private EntityManager entityManager;

    private EspecialidadEntity especialidadGuardada;
    private MedicoEntity medicoGuardado;

    @BeforeEach
    void setUp() {
        // Primero guardamos la especialidad
        EspecialidadEntity especialidadEntity = new EspecialidadEntity();
        especialidadEntity.setNombre("Cardiología");
        especialidadEntity.setDescripcion("Descripción Test");
        entityManager.persist(especialidadEntity);
        especialidadGuardada = especialidadEntity;

        // Luego creamos el médico asociado a la especialidad guardada
        MedicoEntity medico = new MedicoEntity();
        medico.setNombres("José");
        medico.setApellidos("Martínez López");
        medico.setCmp("CMP1234");
        medico.setEspecialidad(especialidadGuardada);
        medico.setFechaCreacion(LocalDateTime.now());
        medico.setUsuarioCreacion("admin");

        medicoGuardado = repository.save(medico);
    }

    @Test
    void testFindAllMedicosConEspecialidad() {
        // Limpiar el contexto de persistencia para forzar nueva consulta SQL
        entityManager.flush();
        entityManager.clear();

        Pageable pageable = PageRequest.of(0, 10);
        Page<MedicoEntity> page = repository.findAllMedicosConEspecialidad(pageable);

        assertNotNull(page);
        assertFalse(page.isEmpty());
        MedicoEntity medico = page.getContent().get(0);

        assertEquals("José", medico.getNombres());
        assertEquals("CMP1234", medico.getCmp());
        assertNotNull(medico.getEspecialidad());
        assertEquals("Cardiología", medico.getEspecialidad().getNombre());
    }

}