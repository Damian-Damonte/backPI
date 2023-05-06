package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.amenity.AmenityFullDTO;
import com.dh.digitalbooking.dto.amenity.AmenityOnlyNameDTO;
import com.dh.digitalbooking.entity.Amenity;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.mapper.AmenityMapper;
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
    public List<AmenityFullDTO> getAllAmenities() {
        return amenityRepository.findAll().stream().map(amenityMapper::amenityToAmenityFullDTO).toList();
    }

    @Override
    public AmenityFullDTO getAmenityById(Long id) {
        return amenityMapper.amenityToAmenityFullDTO(existByIdValidation(id));
    }

    @Override
    @Transactional
    public AmenityFullDTO saveAmenity(AmenityOnlyNameDTO amenityOnlyNameDTO) {
        String name = amenityOnlyNameDTO.name();
        if(amenityRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already an amenity with the name'" + name + "'");

        Amenity amenity = amenityMapper.amenityOnlyNameDTOToAmenity(amenityOnlyNameDTO);
        return amenityMapper.amenityToAmenityFullDTO(amenityRepository.save(amenity));
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
    public AmenityFullDTO updateAmenity(AmenityFullDTO amenityFullDTO) {
        Long id = amenityFullDTO.id();
        String name = amenityFullDTO.name();
        Amenity amenity = existByIdValidation(id);
        Amenity amenityByNombre = amenityRepository.findByName(name).orElse(null);
        if(amenityByNombre != null && !(amenityByNombre.getId().equals(id)))
            throw new BadRequestException("There is already an amenity with the name '" + name + "'");

        amenity.setName(name);
        return amenityFullDTO;
    }

    public Amenity existByIdValidation(Long id) {
        return amenityRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Amenity with id " + id + " not found"));
    }
}
