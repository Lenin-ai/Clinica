package com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.repository;

import com.cibertec.Proyecto.Clinica.Doctor.domain.Model.Medico;
import com.cibertec.Proyecto.Clinica.Doctor.application.ports.out.MedicoPersistence;
import com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.entity.MedicoEntity;
import com.cibertec.Proyecto.Clinica.Doctor.Infrastructure.adapters.out.persistence.mapper.MedicoMapper;
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
public class MedicoPersistenceAdapterImpl implements MedicoPersistence {

    private final MedicoRepositoryJpa medicoRepositoryJpa;
    private final MedicoMapper medicoMapper;

    @Override
    public Medico save(Medico medico) {
        MedicoEntity medicoEntity = medicoMapper.toEntity(medico);
        return medicoMapper.toDomain(medicoRepositoryJpa.save(medicoEntity));
    }

    @Override
    public Optional<Medico> findById(Integer id) {
        return medicoRepositoryJpa.findById(id).map(medicoMapper::toDomain);
    }

    @Override
    public List<Medico> findAll() {
        return medicoRepositoryJpa.findAll()
                .stream()
                .map(medicoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        medicoRepositoryJpa.deleteById(id);
    }

    @Transactional
    @Override
    public Medico update(Medico medico) {
        if (!medicoRepositoryJpa.existsById(medico.getId())) {
            throw new RuntimeException("Medico no encontrado");
        }
        MedicoEntity medicoEntity = medicoMapper.toEntity(medico);
        return medicoMapper.toDomain(medicoRepositoryJpa.save(medicoEntity));
    }

    @Override
    public Page<Medico> listarPaginado(Pageable pageable) {
        return medicoRepositoryJpa.findAllMedicosConEspecialidad(pageable)
                .map(medicoMapper::toDomain);
    }
}
