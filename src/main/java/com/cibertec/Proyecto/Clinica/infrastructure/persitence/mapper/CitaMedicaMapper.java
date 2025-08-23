package com.cibertec.Proyecto.Clinica.infrastructure.persitence.mapper;

import com.cibertec.Proyecto.Clinica.domain.model.CitaMedica;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.entity.CitaMedicaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PacienteMapper.class, MedicoMapper.class})
public interface CitaMedicaMapper {

    CitaMedicaMapper INSTANCE = Mappers.getMapper(CitaMedicaMapper.class);

    @Mapping(target = "paciente", source = "paciente")
    @Mapping(target = "medico", source = "medico")
    CitaMedica toDomain(CitaMedicaEntity entity);

    @Mapping(target = "paciente", source = "paciente")
    @Mapping(target = "medico", source = "medico")
    CitaMedicaEntity toEntity(CitaMedica model);
}
