package com.cibertec.Proyecto.Clinica.Authentication.application.services;

import com.cibertec.Proyecto.Clinica.Authentication.domain.Model.SeguridadModel;
import com.cibertec.Proyecto.Clinica.Authentication.application.ports.in.SeguridadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SeguridadServiceImpl implements SeguridadService {

    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;
    private final RefrescarTokenUseCase refrescarTokenUseCase;

    @Override
    public SeguridadModel autenticacion(String username, String password) {
        return autenticarUsuarioUseCase.ejecutar(username, password);
    }

    @Override
    public SeguridadModel refrescar(String refreshToken) {
        return refrescarTokenUseCase.ejecutar(refreshToken);
    }
}
