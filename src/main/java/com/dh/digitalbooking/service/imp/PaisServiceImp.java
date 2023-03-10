package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Pais;
import com.dh.digitalbooking.repository.PaisRepository;
import com.dh.digitalbooking.service.PaisService;
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
        Pais pais = existByIdValidation(id);
        if(!(pais.getCiudades().isEmpty()))
            throw new BadRequestException("No puede eliminar el pais con id " + id + " ya que hay ciudades registradas en dicho pais");

        paisRepository.deleteById(id);
    }

    @Override
    public Pais updatePais(Pais updatePais) {
        Long id = updatePais.getId();
        String nombre = updatePais.getNombre();

        emptyNombreValidation(nombre);
        existByIdValidation(id);

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

    private void emptyNombreValidation(String nombre) {
        if(nombre == null || nombre.equals(""))
            throw new BadRequestException("El pais debe contener un nombre");
    }
}
