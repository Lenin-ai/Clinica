package com.cibertec.Proyecto.Clinica.domain.seguridad.exception;

/**
 * Excepción de dominio para credenciales inválidas.
 */
public class CredencialesInvalidasException extends DominioException {
    public CredencialesInvalidasException(String mensaje) {
        super(mensaje);
    }
}
