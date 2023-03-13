package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Caracteristica;
import com.dh.digitalbooking.model.Producto;
import com.dh.digitalbooking.repository.CaracteristicaRepository;
import com.dh.digitalbooking.service.CaracteristicaService;
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
}
