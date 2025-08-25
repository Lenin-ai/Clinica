package com.cibertec.Proyecto.Clinica.domain.seguridad.service;

import com.cibertec.Proyecto.Clinica.domain.seguridad.model.SeguridadModel;
public interface SeguridadService {
    SeguridadModel autenticacion(String username, String password);

    SeguridadModel refrescar(String token);
}
