package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.product.ProductFullDto;
import com.dh.digitalbooking.dto.product.ProductRequest;
import com.dh.digitalbooking.dto.product.ProductResponse;
import com.dh.digitalbooking.dto.product.ProductUpdate;
import com.dh.digitalbooking.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {
    ProductResponse productToProductResponse(Product product);
    Product productRequestToProduct(ProductRequest productRequest);
    Product productUpdateToProduct(ProductUpdate productUpdate);
    ProductFullDto productToProductFullDto(Product product);
}
