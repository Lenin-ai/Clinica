package com.cibertec.Proyecto.Clinica.domain.service;

import com.cibertec.Proyecto.Clinica.domain.model.Paciente;

import java.util.List;

public interface PacienteService {
    List<Paciente> listar();
    Paciente obtener(Integer id);
    Paciente agregar(Paciente reservacion);
    Paciente actualizar(Paciente paciente);
    void eliminar(Integer id);

}
