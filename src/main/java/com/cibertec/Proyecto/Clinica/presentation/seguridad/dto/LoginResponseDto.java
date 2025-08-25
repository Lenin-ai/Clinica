package com.cibertec.Proyecto.Clinica.presentation.seguridad.dto;

/**
 * DTO de respuesta de autenticación/refresco.
 *
 * @param accessToken  Access Token JWT
 * @param refreshToken Refresh Token JWT
 * @param expiresIn    Tiempo de expiración del access token en segundos
 */
public record LoginResponseDto(
        String accessToken,
        String refreshToken,
        Long expiresIn
) {
}

