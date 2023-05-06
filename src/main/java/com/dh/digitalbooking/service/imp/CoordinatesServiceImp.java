package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Coordinates;
import com.dh.digitalbooking.repository.CoordinatesRepository;
import com.dh.digitalbooking.service.CoordinatesService;
import org.springframework.stereotype.Service;

@Service
public class CoordinatesServiceImp implements CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;

    public CoordinatesServiceImp(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    @Override
    public Coordinates getCoordinatesById(Long id) {
        return coordinatesRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Coordinates with id " + id + " not found")
        );
    }
}
