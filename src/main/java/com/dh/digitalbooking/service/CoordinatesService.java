package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Coordinates;
import org.springframework.stereotype.Service;

@Service
public interface CoordinatesService {
    Coordinates getCoordinatesById(Long id);
}
