package com.cibertec.Proyecto.Clinica.Authentication.domain.Model;

import lombok.Builder;
import lombok.Data;

/**
 * Rol de usuario dentro del dominio de seguridad.
 * - nombre: Identificador del rol (p. ej. ROLE_ADMIN).
 * - descripcion: Texto descriptivo del rol.
 */
@Data
@Builder
public class RolModel {
    private String nombre;
    private String descripcion;
}
