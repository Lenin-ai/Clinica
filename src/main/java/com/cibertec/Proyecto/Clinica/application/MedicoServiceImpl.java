package com.cibertec.Proyecto.Clinica.application;
import com.cibertec.Proyecto.Clinica.domain.model.Medico;
import com.cibertec.Proyecto.Clinica.domain.repository.MedicoRepository;
import com.cibertec.Proyecto.Clinica.domain.service.MedicoService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    @Override
    public Medico guardar(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public Medico obtenerPorId(Integer id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con id: " + id));
    }

    @Override
    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico actualizar(Medico medico) {
        return  medicoRepository.update(medico);
    }

    @Override
    public void eliminar(Integer id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado con id: " + id));
        medicoRepository.deleteById(id);
    }

    @Override
    public Page<Medico> listarPaginado(int page, int size) {
        return medicoRepository.listarPaginado(PageRequest.of(page, size, Sort.by("apellidos").ascending()));
    }



}
