package com.cibertec.Proyecto.Clinica.application.dto;

import com.cibertec.Proyecto.Clinica.domain.enums.EstadoCita;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class CitaMedicaDTO {
    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private String pacienteNombreCompleto;
    private String medicoNombreCompleto;
    private String motivo;
    private EstadoCita estado;

    public CitaMedicaDTO(Integer id, LocalDate fecha, LocalTime hora,
                         String pacienteNombreCompleto, String medicoNombreCompleto,
                         String motivo, EstadoCita estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.pacienteNombreCompleto = pacienteNombreCompleto;
        this.medicoNombreCompleto = medicoNombreCompleto;
        this.motivo = motivo;
        this.estado = estado;
    }
}
