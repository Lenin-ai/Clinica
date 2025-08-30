package com.cibertec.Proyecto.Clinica.infrastructure.persitence.repository;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;
import com.cibertec.Proyecto.Clinica.domain.repository.EspecialidadRepository;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.entity.EspecialidadEntity;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.jpa.EspecialidadRepositoryJpa;
import com.cibertec.Proyecto.Clinica.infrastructure.persitence.mapper.EspecialidadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EspecialidadRepositoryImpl implements EspecialidadRepository {

    private final EspecialidadRepositoryJpa especialidadRepositoryJpa;
    private final EspecialidadMapper especialidadMapper;

    @Override
    public Especialidad save(Especialidad especialidad) {
        EspecialidadEntity entity = especialidadMapper.toEntity(especialidad);
        return especialidadMapper.toDomain(especialidadRepositoryJpa.save(entity));
    }

    @Override
    public Optional<Especialidad> findById(Integer id) {
        return especialidadRepositoryJpa.findById(id)
                .map(especialidadMapper::toDomain);
    }

    @Override
    public List<Especialidad> findAll() {
        return especialidadRepositoryJpa.findAll()
                .stream()
                .map(especialidadMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Especialidad actualizar(Integer id, Especialidad especialidad) {
        int rows = especialidadRepositoryJpa.actualizarEspecialidad(id, especialidad.getNombre(), especialidad.getDescripcion());
        if (rows == 0) {
            throw new RuntimeException("Especialidad con ID " + id + " no encontrada");
        }
        // Vuelvo a consultar para devolver la versión actualizada
        return especialidadRepositoryJpa.findById(id)
                .map(especialidadMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Especialidad con ID " + id + " no encontrada tras actualizar"));
    }

    @Override
    public void deleteById(Integer id) {
        especialidadRepositoryJpa.deleteById(id);
    }

    @Override
    public Page<Especialidad> findAllPaginado(int page, int size) {
        return especialidadRepositoryJpa.findAllPaginado(PageRequest.of(page, size))
                .map(especialidadMapper::toDomain);
    }
}