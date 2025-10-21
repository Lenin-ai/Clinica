package com.cibertec.Proyecto.Clinica.Authentication.domain.exception;

/**
 * Excepción base para errores de dominio.
 * Debe utilizarse para representar fallos de reglas de negocio u
 * operaciones del dominio independientes de frameworks.
 */
public class DominioException extends RuntimeException {
    public DominioException(String message) {
        super(message);
    }

    public DominioException(String message, Throwable cause) {
        super(message, cause);
    }
}
