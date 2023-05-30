package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.category.CategoryFullDto;
import com.dh.digitalbooking.entity.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {
    CategoryFullDto categoryToCategoryFullDto(Category category);
}
