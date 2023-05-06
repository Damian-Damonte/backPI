package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.image.ImageFullDTO;
import com.dh.digitalbooking.dto.image.ImageNoIdDTO;
import com.dh.digitalbooking.entity.Image;
import org.springframework.stereotype.Component;

// USAR EN PRODUCTO MAPPER
@Component
public class ImageMapper {
    public ImageFullDTO imageToImageFullDTO(Image image) {
        return new ImageFullDTO(image.getId(), image.getTitle(), image.getUrl(), image.getOrder());
    }

    public Image imageFullDTOToImage(ImageFullDTO imageFullDTO) {
        Image image = new Image();
        image.setId(imageFullDTO.id());
        image.setTitle(imageFullDTO.title());
        image.setUrl(imageFullDTO.url());
        image.setOrder(imageFullDTO.order());
        return image;
    }

    public Image imageToImageNoIdDTO(ImageNoIdDTO imageNoIdDTO) {
        Image image = new Image();
        image.setTitle(imageNoIdDTO.title());
        image.setUrl(imageNoIdDTO.url());
        image.setOrder(imageNoIdDTO.order());
        return image;
    }
}
