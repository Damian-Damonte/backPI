package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Politica;
import com.dh.digitalbooking.repository.PoliticaRepository;
import com.dh.digitalbooking.service.PoliticaService;
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
