package com.apiproduct.service.impl.product;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.mapper.ProductMapper;
import com.apiproduct.repository.ProductRepository;
import com.apiproduct.service.product.ProductSearchAllUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSearchAllUseCaseImpl implements ProductSearchAllUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> searchAll() {
        return productMapper.toListProductDTOBy(productRepository.findAll());
    }
}
