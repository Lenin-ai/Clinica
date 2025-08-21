package com.cibertec.Proyecto.Clinica.domain.service;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    Especialidad guardar(Especialidad especialidad);
    Especialidad obtenerPorId(Integer id);
    List<Especialidad> listar();
    void eliminar(Integer id);
}
