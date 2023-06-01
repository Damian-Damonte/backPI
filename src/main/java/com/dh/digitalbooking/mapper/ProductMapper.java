package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.product.ProductResponse;
import com.dh.digitalbooking.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {
    ProductResponse productToProductResponse(Product product);
}
