package com.apiproduct.service.product;

import com.apiproduct.DTO.ProductDTO;

import java.util.List;

public interface ProductSearchAllUseCase {
    List<ProductDTO> searchAll();
}
