package com.apiproduct.controller;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.DTO.ProductRequestDTO;
import com.apiproduct.service.product.ProductDeleteUseCase;
import com.apiproduct.service.product.ProductSaveUseCase;
import com.apiproduct.service.product.ProductSearchAllUseCase;
import com.apiproduct.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductSaveUseCase productSaveUseCase;
    private final ProductDeleteUseCase productDeleteUseCase;
    private final ProductSearchAllUseCase productSearchAllUseCase;

    @GetMapping(Constants.VERSION_1)
    public List<ProductDTO> searchAll() {
        return productSearchAllUseCase.searchAll();
    }

    @PostMapping(Constants.VERSION_1)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@RequestBody ProductRequestDTO request) {
        return productSaveUseCase.save(request);
    }

    @DeleteMapping(Constants.VERSION_1 + "/id/{productId}")
    public void delete(@PathVariable String productId) {
        productDeleteUseCase.delete(productId);
    }
}
