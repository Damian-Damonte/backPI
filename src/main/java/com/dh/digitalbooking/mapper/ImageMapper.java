package com.dh.digitalbooking.mapper;


import com.dh.digitalbooking.dto.image.ImageFullDto;
import com.dh.digitalbooking.dto.image.ImageRequest;
import com.dh.digitalbooking.entity.Image;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ImageMapper {
    ImageFullDto imageToImageFullDto(Image image);
    Image imageRequestToImage(ImageRequest imageRequest);
}
