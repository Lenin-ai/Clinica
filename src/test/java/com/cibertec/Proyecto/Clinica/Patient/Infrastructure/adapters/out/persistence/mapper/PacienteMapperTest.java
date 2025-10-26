package com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.out.persistence.mapper;

import com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.out.persistence.entity.PacienteEntity;
import com.cibertec.Proyecto.Clinica.Patient.domain.Model.Paciente;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class PacienteMapperTest {


    private final PacienteMapper mapper = Mappers.getMapper(PacienteMapper.class);
    @Test
    void testToDomain() {
        // Dado un entity
        PacienteEntity entity = new PacienteEntity();
        entity.setId(1);
        entity.setNombres("Juan");
        entity.setApellidos("Perez");
        entity.setFechaNacimiento(LocalDate.of(1990, 5, 10));
        entity.setDni("12345678");
        entity.setDireccion("Av. Lima 123");
        entity.setTelefono("987654321");
        entity.setEmail("juan.perez@gmail.com");

        // Cuando se mapea a dominio
        Paciente domain = mapper.toDomain(entity);

        // Entonces los campos deben coincidir
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getNombres(), domain.getNombres());
        assertEquals(entity.getApellidos(), domain.getApellidos());
        assertEquals(entity.getFechaNacimiento(), domain.getFechaNacimiento());
        assertEquals(entity.getDni(), domain.getDni());
        assertEquals(entity.getDireccion(), domain.getDireccion());
        assertEquals(entity.getTelefono(), domain.getTelefono());
        assertEquals(entity.getEmail(), domain.getEmail());
    }

    @Test
    void testToEntity() {
        // Dado un modelo de dominio
        Paciente domain = new Paciente();
        domain.setId(2);
        domain.setNombres("Maria");
        domain.setApellidos("Lopez");
        domain.setFechaNacimiento(LocalDate.of(1995, 3, 15));
        domain.setDni("87654321");
        domain.setDireccion("Calle Sol 456");
        domain.setTelefono("999888777");
        domain.setEmail("maria.lopez@gmail.com");

        // Cuando se mapea a entity
        PacienteEntity entity = mapper.toEntity(domain);

        // Entonces los campos deben coincidir
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getNombres(), entity.getNombres());
        assertEquals(domain.getApellidos(), entity.getApellidos());
        assertEquals(domain.getFechaNacimiento(), entity.getFechaNacimiento());
        assertEquals(domain.getDni(), entity.getDni());
        assertEquals(domain.getDireccion(), entity.getDireccion());
        assertEquals(domain.getTelefono(), entity.getTelefono());
        assertEquals(domain.getEmail(), entity.getEmail());
    }
}