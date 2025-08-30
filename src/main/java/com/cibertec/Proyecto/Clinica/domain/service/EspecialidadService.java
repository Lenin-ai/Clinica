package com.cibertec.Proyecto.Clinica.domain.service;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    List<Especialidad> listar();
    Especialidad guardar(Especialidad especialidad);
    Especialidad obtenerPorId(Integer id);
    Especialidad actualizar(Integer id, Especialidad especialidad);
    void eliminar(Integer id);
    Page<Especialidad> listarPaginado(int page, int size);
}
