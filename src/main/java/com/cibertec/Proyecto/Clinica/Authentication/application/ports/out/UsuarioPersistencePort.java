package com.cibertec.Proyecto.Clinica.Authentication.application.ports.out;

import com.cibertec.Proyecto.Clinica.Authentication.domain.Model.UsuarioModel;

import java.util.Optional;

/**
 * Contrato de repositorio del agregado Usuario.
 * Responsabilidades:
 * - Proveer acceso a los usuarios por criterios de consulta del dominio.
 * - Gestionar datos auxiliares del agregado (p. ej. cache de tokens si aplica al dominio).
 *
 * Este contrato NO debe exponer detalles de infraestructura.
 */
public interface UsuarioPersistencePort {
    Optional<UsuarioModel> usuarioPorUserName(String username);

    void guardarToken(String token);

    String obtenerTokenCache(String username);
}
