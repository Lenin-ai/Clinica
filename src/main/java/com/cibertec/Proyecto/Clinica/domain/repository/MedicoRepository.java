package com.cibertec.Proyecto.Clinica.domain.repository;

import com.cibertec.Proyecto.Clinica.domain.model.Medico;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository {
    Medico save(Medico medico);
    Optional<Medico> findById(Integer id);
    List<Medico> findAll();
    void deleteById(Integer id);
    @Transactional
    Medico update(Medico medico);
}
