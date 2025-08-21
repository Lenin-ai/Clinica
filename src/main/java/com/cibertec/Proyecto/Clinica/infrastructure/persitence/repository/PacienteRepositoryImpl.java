package com.cibertec.Proyecto.Clinica.infrastructure.persitence.repository;

import com.cibertec.Proyecto.Clinica.domain.model.Paciente;
import com.cibertec.Proyecto.Clinica.domain.repository.PacienteRepository;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.entity.PacienteEntity;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.jpa.PacienteRepositoryJpa;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PacienteRepositoryImpl implements PacienteRepository {

    private final PacienteRepositoryJpa pacienteRepositoryJpa;
    private final PacienteMapper pacienteMapper;


    @Override
    public Paciente save(Paciente paciente) {
        return pacienteMapper.toDomain(
                pacienteRepositoryJpa.save(
                        pacienteMapper.toEntity(paciente)
                )
           );
    }

    @Override
    public Optional<Paciente> findById(Integer id) {
        return pacienteRepositoryJpa.findById(id)
                .map(pacienteMapper::toDomain);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepositoryJpa.findAll()
                .stream()
                .map(pacienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        pacienteRepositoryJpa.deleteById(id);
    }
}
