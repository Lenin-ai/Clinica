package com.cibertec.Proyecto.Clinica.Appointment.Infrastructure.adapters.out.persistence.mapper;

import com.cibertec.Proyecto.Clinica.Appointment.Infrastructure.adapters.out.persistence.entity.CitaMedicaEntity;
import com.cibertec.Proyecto.Clinica.Appointment.domain.Model.CitaMedica;
import com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.entity.MedicoEntity;
import com.cibertec.Proyecto.Clinica.Doctor.domain.Model.Medico;
import com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.out.persistence.entity.PacienteEntity;
import com.cibertec.Proyecto.Clinica.Patient.domain.Model.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CitaMedicaMapperTest {

    @Autowired
    private CitaMedicaMapper mapper;

    @Test
    void testToDomain() {
        // --- Paciente ---
        PacienteEntity pacienteEntity = new PacienteEntity();
        pacienteEntity.setId(1);
        pacienteEntity.setNombres("Carlos");
        pacienteEntity.setApellidos("Pérez");

        // --- Medico ---
        MedicoEntity medicoEntity = new MedicoEntity();
        medicoEntity.setId(10);
        medicoEntity.setNombres("Dr. José");
        medicoEntity.setApellidos("Ramírez");

        // --- Cita médica ---
        CitaMedicaEntity citaEntity = new CitaMedicaEntity();
        citaEntity.setId(100);
        citaEntity.setFecha(LocalDate.of(2025, 8, 21));
        citaEntity.setHora(LocalTime.of(9, 30));
        citaEntity.setMotivo("Chequeo general");
        citaEntity.setPaciente(pacienteEntity);
        citaEntity.setMedico(medicoEntity);

        // --- When ---
        CitaMedica domain = mapper.toDomain(citaEntity);

        // --- Then ---
        assertNotNull(domain);
        assertEquals(100, domain.getId());
        assertEquals("Chequeo general", domain.getMotivo());
        assertEquals(LocalDate.of(2025, 8, 21), domain.getFecha());
        assertEquals(LocalTime.of(9,30),domain.getHora());
        assertNotNull(domain.getPaciente());
        assertEquals("Carlos", domain.getPaciente().getNombres());
        assertNotNull(domain.getMedico());
        assertEquals("Dr. José", domain.getMedico().getNombres());
    }

    @Test
    void testToEntity() {
        // --- Paciente ---
        Paciente paciente = new Paciente();
        paciente.setId(1);
        paciente.setNombres("Carlos");
        paciente.setApellidos("Pérez");

        // --- Medico ---
        Medico medico = new Medico();
        medico.setId(10);
        medico.setNombres("Dr. José");
        medico.setApellidos("Ramírez");

        // --- Cita médica ---
        CitaMedica cita = new CitaMedica();
        cita.setId(100);
        cita.setFecha(LocalDate.of(2025, 10, 24));
        cita.setHora(LocalTime.of(9, 35));
        cita.setMotivo("Chequeo general");
        cita.setPaciente(paciente);
        cita.setMedico(medico);

        // --- When ---
        CitaMedicaEntity entity = mapper.toEntity(cita);

        // --- Then ---
        assertNotNull(entity);
        assertEquals(100, entity.getId());
        assertEquals("Chequeo general", entity.getMotivo());
        assertEquals(LocalDate.of(2025, 10, 24), entity.getFecha());
        assertEquals(LocalTime.of(9,35),entity.getHora());
        assertNotNull(entity.getPaciente());
        assertEquals("Carlos", entity.getPaciente().getNombres());
        assertNotNull(entity.getMedico());
        assertEquals("Dr. José", entity.getMedico().getNombres());
    }
}