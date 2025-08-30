package com.cibertec.Proyecto.Clinica.infrastructure.persitence.repository;

import com.cibertec.Proyecto.Clinica.domain.model.Paciente;
import com.cibertec.Proyecto.Clinica.domain.repository.PacienteRepository;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.jpa.PacienteRepositoryJpa;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Paciente update(Paciente paciente) {
        int updated = pacienteRepositoryJpa.actualizarPaciente(
                paciente.getId(),
                paciente.getNombres(),
                paciente.getApellidos(),
                paciente.getFechaNacimiento(),
                paciente.getDni(),
                paciente.getDireccion(),
                paciente.getTelefono(),
                paciente.getEmail(),
                "admin" //  pasar el usuario logueado
        );

        if (updated == 0) {
            throw new RuntimeException("Paciente con ID " + paciente.getId() + " no encontrado");
        }
        return paciente;
    }

    @Override
    public void deleteById(Integer id) {
        pacienteRepositoryJpa.deleteById(id);
    }

    @Override
    public Page<Paciente> findAllPaginado(Pageable pageable) {
        return pacienteRepositoryJpa.findAllPaginado(pageable)
                .map(pacienteMapper::toDomain);
    }
}
