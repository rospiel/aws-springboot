package com.apiproduct.service.impl.product;

import com.apiproduct.repository.ProductRepository;
import com.apiproduct.service.product.ProductDeleteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDeleteUseCaseImpl implements ProductDeleteUseCase {

    private final ProductRepository productRepository;

    @Override
    public void delete(String id) {
        productRepository.deleteById(Long.valueOf(id));
    }
}
