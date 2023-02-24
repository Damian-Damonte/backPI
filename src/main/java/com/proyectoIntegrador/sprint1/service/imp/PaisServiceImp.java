package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Pais;
import com.proyectoIntegrador.sprint1.repository.PaisRepository;
import com.proyectoIntegrador.sprint1.service.PaisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImp implements PaisService {

    private final PaisRepository paisRepository;

    public PaisServiceImp(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @Override
    public List<Pais> allPais() {
        return paisRepository.findAll();
    }

    @Override
    public Pais getByIdPais(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Pais savePais(Pais pais) {
        String nombre = pais.getNombre();
        emptyNombreValidation(nombre);
        if(paisRepository.findByNombre(nombre).isPresent())
            throw new BadRequestException("Ya existe un pais con el nombre '" + nombre + "'");

        return paisRepository.save(pais);
    }

    @Override
    public void deletePais(Long id) {
        existByIdValidation(id);
        paisRepository.deleteById(id);
    }

    @Override
    public Pais updatePais(Pais updatePais) {
        Long id = updatePais.getId();
        String nombre = updatePais.getNombre();

        existByIdValidation(id);
        emptyNombreValidation(nombre);

        Pais paisByNombre = paisRepository.findByNombre(nombre).orElse(null);
        if(paisByNombre != null && !(paisByNombre.getId().equals(id)))
            throw new BadRequestException("Ya existe un pais con el nombre '" + nombre + "'");

        return paisRepository.save(updatePais);
    }

    public Pais existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id del pais");
        return paisRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Pais con id " + id + " no encontrado"));
    }

    public void emptyNombreValidation(String nombre) {
        if(nombre == null || nombre.equals(""))
            throw new BadRequestException("El pais debe contener un nombre");
    }
}
