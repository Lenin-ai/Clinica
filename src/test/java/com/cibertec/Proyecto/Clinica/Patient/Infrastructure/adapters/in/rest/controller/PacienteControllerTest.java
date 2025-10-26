package com.cibertec.Proyecto.Clinica.Patient.Infrastructure.adapters.in.rest.controller;

import com.cibertec.Proyecto.Clinica.Patient.domain.Model.Paciente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("mysql-test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PacienteControllerTest {
    @Container
    @ServiceConnection
    protected static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("clinica_test")
            .withUsername("root")
            .withPassword("root");
    protected String accessToken;
    protected HttpHeaders headers;

    @Autowired
    protected TestRestTemplate restTemplate;
    @BeforeEach
    void resetDatabase() {

        // 🧩 Login inicial
        var loginRequest = Map.of(
                "username", "admin",
                "password", "admin123"
        );
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/public/api/auth/login",
                loginRequest,
                Map.class
        );
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        accessToken = (String) response.getBody().get("accessToken");
        Assertions.assertThat(accessToken).isNotBlank();

        headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    @Test
    void testAgregarYObtenerPaciente() {

        // Crear paciente
        Paciente nuevo = new Paciente();
        nuevo.setNombres("PACIENTE TEST");
        nuevo.setApellidos("Pérez");
        nuevo.setDni("94852134");
        nuevo.setDireccion("Av. Siempre Viva 123");
        nuevo.setTelefono("987654321");
        nuevo.setEmail("test_" + System.currentTimeMillis() + "@example.com");
        nuevo.setFechaNacimiento(LocalDate.of(1999, 5, 15));

        HttpEntity<Paciente> request = new HttpEntity<>(nuevo, headers);

        ResponseEntity<Paciente> response = restTemplate.postForEntity(
                "/api/pacientes",
                request,
                Paciente.class
        );


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Paciente creado = response.getBody();
        assertThat(creado).isNotNull();
        assertThat(creado.getId()).isNotNull();
        assertThat(creado.getNombres()).isEqualTo("PACIENTE TEST");

        // Obtener por ID
        ResponseEntity<Paciente> getResponse = restTemplate.exchange(
                "/api/pacientes/" + creado.getId(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Paciente.class
        );


        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Paciente obtenido = getResponse.getBody();
        assertThat(obtenido).isNotNull();
        assertThat(obtenido.getDni()).isEqualTo("94852134");
    }
    @Test
    void testListarPacientes() {
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/pacientes?page=0&size=5",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("content");
    }
    @Test
    void testActualizarPaciente() {
        // Crear paciente primero
        Paciente nuevo = new Paciente();
        nuevo.setNombres("JUAN");
        nuevo.setApellidos("Pérez");
        nuevo.setDni("87651474");
        nuevo.setDireccion("Av. Siempre Viva 123");
        nuevo.setTelefono("987654321");
        nuevo.setEmail("test_" + System.currentTimeMillis() + "@example.com");
        nuevo.setFechaNacimiento(LocalDate.of(2000, 1, 1));

        HttpEntity<Paciente> createRequest = new HttpEntity<>(nuevo, headers);
        Paciente creado = restTemplate.postForEntity(
                "/api/pacientes",
                createRequest,
                Paciente.class
        ).getBody();

        // Modificar paciente
        creado.setNombres("JUAN ACTUALIZADO");

        HttpEntity<Paciente> updateRequest = new HttpEntity<>(creado, headers);

        ResponseEntity<Paciente> response = restTemplate.exchange(
                "/api/pacientes/" + creado.getId(),
                HttpMethod.PUT,
                updateRequest,
                Paciente.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getNombres()).isEqualTo("JUAN ACTUALIZADO");
    }
    @Test
    void testEliminarPaciente() {
        // Crear paciente
        Paciente nuevo = new Paciente();
        nuevo.setNombres("Eliminar");
        nuevo.setApellidos("Paciente");
        nuevo.setDni("99999999");
        nuevo.setDireccion("Av. Siempre Viva 123");
        nuevo.setTelefono("987654321");
        nuevo.setEmail("test_" + System.currentTimeMillis() + "@example.com");
        nuevo.setFechaNacimiento(LocalDate.of(1995, 10, 10));

        HttpEntity<Paciente> createRequest = new HttpEntity<>(nuevo, headers);
        Paciente creado = restTemplate.postForEntity(
                "/api/pacientes",
                createRequest,
                Paciente.class
        ).getBody();

        // Eliminar paciente
        ResponseEntity<String> deleteResponse = restTemplate.exchange(
                "/api/pacientes/" + creado.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(headers),
                String.class
        );

        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(deleteResponse.getBody()).contains("eliminado");

    }
}