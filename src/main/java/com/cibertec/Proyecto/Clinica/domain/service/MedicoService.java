package com.cibertec.Proyecto.Clinica.domain.service;

import com.cibertec.Proyecto.Clinica.domain.model.Medico;
import java.util.List;

public interface MedicoService {
    List<Medico> listar();
    Medico obtenerPorId(Integer id);
    Medico guardar(Medico medico);
    Medico actualizar(Medico medico);
    void eliminar(Integer id);
}
