package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Ciudad;
import com.dh.digitalbooking.model.Pais;
import com.dh.digitalbooking.repository.CiudadRepository;
import com.dh.digitalbooking.service.CiudadService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CiudadServiceImp implements CiudadService {
    private final CiudadRepository ciudadRepository;
    private final PaisServiceImp paisServiceImp;

    public CiudadServiceImp(CiudadRepository ciudadRepository, PaisServiceImp paisServiceImp) {
        this.ciudadRepository = ciudadRepository;
        this.paisServiceImp = paisServiceImp;
    }

    @Override
    public List<Ciudad> allCiudad() {
        return ciudadRepository.findAll();
    }

    @Override
    public Ciudad getByIdCiudad(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Ciudad saveCiudad(Ciudad ciudad) {
        Pais pais = paisServiceImp.existByIdValidation(ciudad.getPais().getId());
        ciudad.setPais(pais);

        return ciudadRepository.save(ciudad);
    }

    @Override
    public void deleteCiudad(Long id) {
        Ciudad ciudad = existByIdValidation(id);
        if(!(ciudad.getProductos().isEmpty()))
            throw new BadRequestException("No puede eliminar la ciudad con id " + id + " ya que hay productos que en dicha ciudad");

        ciudadRepository.deleteById(id);
    }

    @Override
    public Ciudad updateCiudad(Ciudad ciudad) {
        existByIdValidation(ciudad.getId());
        Pais pais = paisServiceImp.existByIdValidation(ciudad.getPais().getId());
        ciudad.setPais(pais);

        return ciudadRepository.save(ciudad);
    }

    public Ciudad existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la ciudad");
        return ciudadRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Ciudad con id " + id + " no encontrada"));
    }
}
