package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.entity.Image;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.repository.ImageRepository;
import com.dh.digitalbooking.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImp implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImp(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id " + id + " not found")
        );
    }
}
