package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Politica;
import com.proyectoIntegrador.sprint1.repository.PoliticaRepository;
import com.proyectoIntegrador.sprint1.service.PoliticaService;
import org.springframework.stereotype.Service;

@Service
public class PoliticaServiceImp implements PoliticaService {
    private final PoliticaRepository politicaRepository;

    public PoliticaServiceImp(PoliticaRepository politicaRepository) {
        this.politicaRepository = politicaRepository;
    }

    @Override
    public Politica getByIdPolitica(Long id) {
        return politicaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Politica con id " + id + " no encontrada")
        );
    }
}
