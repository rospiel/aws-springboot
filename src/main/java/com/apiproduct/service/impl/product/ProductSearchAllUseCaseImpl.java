package com.apiproduct.service.impl.product;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.mapper.ProductMapper;
import com.apiproduct.repository.ProductRepository;
import com.apiproduct.service.product.ProductSearchAllUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSearchAllUseCaseImpl implements ProductSearchAllUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Cacheable(value = "products")
    public List<ProductDTO> searchAll() {
        log.info("Searching all products");
        return productMapper.toListProductDTOBy(productRepository.findAll());
    }

    @CacheEvict(value = "products", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.productListTTL}")
    public void emptyHotelsCache() {
        log.info("emptying Products cache");
    }
}
