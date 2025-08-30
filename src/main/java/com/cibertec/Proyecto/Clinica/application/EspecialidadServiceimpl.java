package com.cibertec.Proyecto.Clinica.application;

import com.cibertec.Proyecto.Clinica.domain.model.Especialidad;
import com.cibertec.Proyecto.Clinica.domain.repository.EspecialidadRepository;
import com.cibertec.Proyecto.Clinica.domain.service.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceimpl {
    @Service
    @RequiredArgsConstructor
    public class EspecialidadServiceImpl implements EspecialidadService {

        private final EspecialidadRepository especialidadRepository;

        @Override
        public Especialidad guardar(Especialidad especialidad) {
            return especialidadRepository.save(especialidad);
        }

        @Override
        public Especialidad obtenerPorId(Integer id) {
            return especialidadRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Paciente no Encontrado"));
        }

        @Override
        public List<Especialidad> listar() {
            return especialidadRepository.findAll();
        }

        @Override
        public Especialidad actualizar(Integer id, Especialidad especialidad) {
            return especialidadRepository.actualizar(id, especialidad);
        }


        @Override
        public void eliminar(Integer id) {
            especialidadRepository.deleteById(id);
        }

        @Override
        public Page<Especialidad> listarPaginado(int page, int size) {
            return especialidadRepository.findAllPaginado(page, size);
        }
    }
}
