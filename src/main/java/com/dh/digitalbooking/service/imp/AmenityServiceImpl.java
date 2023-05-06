package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.entity.Amenity;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.repository.AmenityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AmenityServiceImpl implements com.dh.digitalbooking.service.AmenityService {
    private final AmenityRepository amenityRepository;

    public AmenityServiceImpl(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    @Override
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    @Override
    public Amenity getAmenityById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Amenity saveAmenity(Amenity amenity) {
        String name = amenity.getName();
        if(amenityRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already an amenity with the name'" + name + "'");

        return amenityRepository.save(amenity);
    }

    @Override
    public void deleteAmenity(Long id) {
        Amenity amenity = existByIdValidation(id);
        for (Producto producto : amenity.getProducts()) {
            producto.getCaracteristicas().remove(amenity);
        }
        amenityRepository.deleteById(id);
    }

    @Override
    public Amenity updateAmenity(Amenity amenity) {
        Long id = amenity.getId();
        String name = amenity.getName();

        existByIdValidation(id);

        Amenity amenityByNombre = amenityRepository.findByName(name).orElse(null);
        if(amenityByNombre != null && !(amenityByNombre.getId().equals(id)))
            throw new BadRequestException("There is already an amenity with the name '" + name + "'");

        return amenityRepository.save(amenity);
    }

    public Amenity existByIdValidation(Long id) {
//        if(id == null)
//            throw new BadRequestException("Debe enviar el id de la caracteristica");
        return amenityRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Amenity with id " + id + " not found"));
    }
}
