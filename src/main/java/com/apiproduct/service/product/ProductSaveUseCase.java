package com.apiproduct.service.product;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.DTO.ProductRequestDTO;

public interface ProductSaveUseCase {
    ProductDTO save(ProductRequestDTO dto);
}
