package com.apiproduct.service.impl.product;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.DTO.ProductRequestDTO;
import com.apiproduct.entity.ProductEntity;
import com.apiproduct.mapper.ProductMapper;
import com.apiproduct.repository.ProductRepository;
import com.apiproduct.service.product.ProductSaveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSaveUseCaseImpl implements ProductSaveUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO save(ProductRequestDTO dto) {
        return CompletableFuture.supplyAsync(() -> productRepository.findFirstByName(dto.getName()))
                .thenCompose((previous) ->  persist(dto, previous))
                .thenApply(productMapper::toProductDTOBy)
                .join();
    }

    private String get() {
        return "RELO";
    }

    private CompletableFuture<ProductEntity> persist(ProductRequestDTO dto, Optional<ProductEntity> entity) {
        if (entity.isPresent()) {
            log.info("Product of name [{}] already registered", dto.getName());
            return CompletableFuture.supplyAsync(() -> entity.get());
        }

        return CompletableFuture.supplyAsync(() -> {
            log.info("Registering product of name [{}]", dto.getName());
            ProductEntity newProduct = productMapper.toProductEntityBy(dto);
            productRepository.saveAndFlush(newProduct);

            return newProduct;
        });
    }
}
