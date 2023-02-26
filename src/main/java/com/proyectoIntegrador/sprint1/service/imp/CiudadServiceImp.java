package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Ciudad;
import com.proyectoIntegrador.sprint1.model.Pais;
import com.proyectoIntegrador.sprint1.repository.CiudadRepository;
import com.proyectoIntegrador.sprint1.service.CiudadService;
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
        emptyNombreValidation(ciudad.getNombre());
        emptyPaisValidation(ciudad);
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
        Long id = ciudad.getId();

        emptyNombreValidation(ciudad.getNombre());
        existByIdValidation(id);
        emptyPaisValidation(ciudad);
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

    private void emptyNombreValidation(String nombre) {
        if(nombre == null || nombre.equals(""))
            throw new BadRequestException("Las ciudad debe contener un nombre");
    }

    private void emptyPaisValidation(Ciudad ciudad) {
        if(ciudad.getPais() == null)
            throw new BadRequestException("La ciudad debe contener un pais");
    }
}
