package com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.repository;

import com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.entity.EspecialidadEntity;
import com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.mapper.EspecialidadMapperImpl;
import com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.mapper.MedicoMapperImpl;
import com.cibertec.Proyecto.Clinica.Doctor.domain.Model.Especialidad;
import com.cibertec.Proyecto.Clinica.Doctor.domain.Model.Medico;
import com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.out.persistence.mapper.PacienteMapperImpl;
import com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.out.persistence.repository.PacientePersistenceAdapterImpl;
import com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.out.persistence.repository.PacienteRepositoryJpa;
import com.cibertec.Proyecto.Clinica.Patient.domain.Model.Paciente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import({MedicoPersistenceAdapterImpl.class, MedicoMapperImpl.class, EspecialidadMapperImpl.class})
@ActiveProfiles("h2-test")
class MedicoPersistenceAdapterImplTest {

    @Autowired
    private MedicoPersistenceAdapterImpl adapter;

    @Autowired
    private MedicoRepositoryJpa repository;

    @Autowired
    private EspecialidadRepositoryJpa especialidadRepository;

    @Autowired
    private EntityManager entityManager;

    private Medico medico;
    private Especialidad especialidad;

    @BeforeEach
    void setUp() {
        EspecialidadEntity especialidadEntity = new EspecialidadEntity();
        especialidadEntity.setNombre("Cardiología");
        especialidadEntity.setDescripcion("Description Test");

        especialidadEntity = especialidadRepository.save(especialidadEntity);
        especialidad = new Especialidad(especialidadEntity.getId(), "Cardiología", "Description Test");

        medico = new Medico();
        medico.setNombres("José");
        medico.setApellidos("Martínez López");
        medico.setCmp("CMP1234");
        medico.setEspecialidad(especialidad);
        medico.setFechaCreacion(LocalDateTime.now());
        medico.setUsuarioCreacion("admin");
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
        especialidadRepository.deleteAll();
    }
    @Test
    void testSaveAndFindById() {
        Medico guardado = adapter.save(medico);

        Optional<Medico> encontrado = adapter.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("José", encontrado.get().getNombres());
        assertEquals("Cardiología", encontrado.get().getEspecialidad().getNombre());
    }

    @Test
    void testUpdate() {
        Medico guardado = adapter.save(medico);

        guardado.setNombres("Juan");
        guardado.setCmp("CMP9999");

        Medico actualizado = adapter.update(guardado);

        entityManager.flush();
        entityManager.clear();

        Optional<Medico> encontrado = adapter.findById(actualizado.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Juan", encontrado.get().getNombres());
        assertEquals("CMP9999", encontrado.get().getCmp());
    }

    @Test
    void testListarPaginado() {
        adapter.save(medico);

        Page<Medico> pagina = adapter.listarPaginado(PageRequest.of(0, 5));

        assertFalse(pagina.isEmpty());
        assertEquals("José", pagina.getContent().get(0).getNombres());
    }

    @Test
    void testDeleteById() {
        Medico guardado = adapter.save(medico);

        adapter.deleteById(guardado.getId());

        assertTrue(repository.findAll().isEmpty());
    }
}