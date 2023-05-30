package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.amenity.AmenityFullDto;
import com.dh.digitalbooking.dto.amenity.AmenityRequest;
import com.dh.digitalbooking.entity.Amenity;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.mapper.AmenityMapper;
import com.dh.digitalbooking.mapper.AmenityMapperOld;
import com.dh.digitalbooking.repository.AmenityRepository;
import com.dh.digitalbooking.service.AmenityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;
    private final AmenityMapper amenityMapper;

    public AmenityServiceImpl(AmenityRepository amenityRepository, AmenityMapper amenityMapper) {
        this.amenityRepository = amenityRepository;
        this.amenityMapper = amenityMapper;
    }

    @Override
    public List<AmenityFullDto> getAllAmenities() {
        return amenityRepository.findAll().stream().map(amenityMapper::amenityToAmenityFullDto).toList();
    }

    @Override
    public AmenityFullDto getAmenityById(Long id) {
        return amenityMapper.amenityToAmenityFullDto(existByIdValidation(id));
    }

    @Override
    @Transactional
    public AmenityFullDto saveAmenity(AmenityRequest amenityRequest) {
        String name = amenityRequest.name();
        if(amenityRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already an amenity with the name'" + name + "'");
        Amenity amenity = amenityRepository.save(Amenity.builder().name(name).build());
        return amenityMapper.amenityToAmenityFullDto(amenity);
    }

    @Override
    @Transactional
    public void deleteAmenity(Long id) {
        Amenity amenity = existByIdValidation(id);
        for (Producto producto : amenity.getProducts()) {
            producto.getCaracteristicas().remove(amenity);
        }
        amenityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AmenityFullDto updateAmenity(Long id, AmenityRequest amenityRequest) {
        String name = amenityRequest.name();
        Amenity amenity = existByIdValidation(id);
        Amenity amenityByNombre = amenityRepository.findByName(name).orElse(null);
        if(amenityByNombre != null && !(amenityByNombre.getId().equals(id)))
            throw new BadRequestException("There is already an amenity with the name '" + name + "'");
        amenity.setName(name);
        return amenityMapper.amenityToAmenityFullDto(amenity);
    }

    @Override
    public Amenity existByIdValidation(Long id) {
        return amenityRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Amenity with id " + id + " not found"));
    }
}
