package com.cibertec.Proyecto.Clinica.infrastructure.persitence.mapper;

import com.cibertec.Proyecto.Clinica.domain.model.Paciente;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.entity.PacienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombres", source = "nombres")
    @Mapping(target = "apellidos", source = "apellidos")
    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "email", source = "email")
    Paciente  toDomain(PacienteEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombres", source = "nombres")
    @Mapping(target = "apellidos", source = "apellidos")
    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "email", source = "email")
    PacienteEntity toEntity(Paciente model);
}
