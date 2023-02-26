package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Caracteristica;
import com.proyectoIntegrador.sprint1.model.Pais;
import com.proyectoIntegrador.sprint1.model.Producto;
import com.proyectoIntegrador.sprint1.repository.CaracteristicaRepository;
import com.proyectoIntegrador.sprint1.service.CaracteristicaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristicaServiceImp implements CaracteristicaService {
    private final CaracteristicaRepository caracteristicaRepository;

    public CaracteristicaServiceImp(CaracteristicaRepository caracteristicaRepository) {
        this.caracteristicaRepository = caracteristicaRepository;
    }

    @Override
    public List<Caracteristica> getAllCaracteristica() {
        return caracteristicaRepository.findAll();
    }

    @Override
    public Caracteristica getByIdCaracteristica(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Caracteristica saveCaracteristica(Caracteristica caracteristica) {
        String nombre = caracteristica.getNombre();
        emptyNombreValidation(nombre);
        if(caracteristicaRepository.findByNombre(nombre).isPresent())
            throw new BadRequestException("Ya existe una caracteristica con el nombre '" + nombre + "'");

        return caracteristicaRepository.save(caracteristica);
    }

    @Override
    public void deleteCaracteristica(Long id) {
        Caracteristica caracteristica = existByIdValidation(id);
        for (Producto producto : caracteristica.getProductos()) {
            producto.getCaracteristicas().remove(caracteristica);
        }
        caracteristicaRepository.deleteById(id);
    }

    @Override
    public Caracteristica updateCaracteristica(Caracteristica caracteristica) {
        Long id = caracteristica.getId();
        String nombre = caracteristica.getNombre();

        emptyNombreValidation(nombre);
        existByIdValidation(id);

        Caracteristica caracteristicaByNombre = caracteristicaRepository.findByNombre(nombre).orElse(null);
        if(caracteristicaByNombre != null && !(caracteristicaByNombre.getId().equals(id)))
            throw new BadRequestException("Ya existe una caracteristica con el nombre '" + nombre + "'");

        return caracteristicaRepository.save(caracteristica);
    }

    public Caracteristica existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la caracteristica");
        return caracteristicaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Caracteristica con id " + id + " no encontrada"));
    }

    private void emptyNombreValidation(String nombre) {
        if(nombre == null || nombre.equals(""))
            throw new BadRequestException("La caracteristica debe contener un nombre");
    }
}
