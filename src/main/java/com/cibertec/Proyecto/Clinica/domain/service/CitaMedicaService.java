package com.cibertec.Proyecto.Clinica.domain.service;

import com.cibertec.Proyecto.Clinica.domain.model.CitaMedica;

import java.util.List;
import java.util.Optional;

public interface CitaMedicaService {
    List<CitaMedica> listarCitas();
    Optional<CitaMedica> obtenerCitaPorId(Integer id);
    CitaMedica registrarCita(CitaMedica citaMedica);
    CitaMedica actualizarCita(CitaMedica citaMedica);
    void eliminarCita(Integer id);
}
