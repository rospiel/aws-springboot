package com.apiproduct.controller;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.DTO.ProductRequestDTO;
import com.apiproduct.service.product.ProductDeleteUseCase;
import com.apiproduct.service.product.ProductSaveUseCase;
import com.apiproduct.service.product.ProductSearchAllUseCase;
import com.apiproduct.utils.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Tag(name = "Products")
public class ProductControllerImpl implements ProductController {

    private final ProductSaveUseCase productSaveUseCase;
    private final ProductDeleteUseCase productDeleteUseCase;
    private final ProductSearchAllUseCase productSearchAllUseCase;

    @GetMapping(value = Constants.VERSION_1, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<ProductDTO>> searchAll() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES))
                .body(productSearchAllUseCase.searchAll());
    }


    @PostMapping(value = Constants.VERSION_1, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ProductDTO save(@RequestBody ProductRequestDTO request) {
        return productSaveUseCase.save(request);
    }


    @DeleteMapping(Constants.VERSION_1 + "/id/{productId}")
    @Override
    public void delete(@PathVariable String productId) {
        productDeleteUseCase.delete(productId);
    }
}
