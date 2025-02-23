package com.apiproduct.controller;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.DTO.ProductRequestDTO;
import com.apiproduct.service.product.ProductDeleteUseCase;
import com.apiproduct.service.product.ProductSaveUseCase;
import com.apiproduct.service.product.ProductSearchAllUseCase;
import com.apiproduct.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Tag(name = "Products")
public class ProductController {

    private final ProductSaveUseCase productSaveUseCase;
    private final ProductDeleteUseCase productDeleteUseCase;
    private final ProductSearchAllUseCase productSearchAllUseCase;

    @Operation(summary = "All products", description = "Search all products", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
            })
    })
    @GetMapping(value = Constants.VERSION_1, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> searchAll() {
        return productSearchAllUseCase.searchAll();
    }

    @Operation(summary = "Save product", description = "Save a new product if not exist")
    @PostMapping(value = Constants.VERSION_1, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Properties of a product to persist", required = true) @RequestBody ProductRequestDTO request) {
        return productSaveUseCase.save(request);
    }


    /**
     * content will ensure that don't duplicate the response in documentation
     * @param productId
     */
    @Operation(summary = "Remove product", description = "Remove a product if exist", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid productId", content = @Content(schema = @Schema))

    })
    @DeleteMapping(Constants.VERSION_1 + "/id/{productId}")
    public void delete(@Parameter(description = "Id of a product", example = "1", required = true) @PathVariable String productId) {
        productDeleteUseCase.delete(productId);
    }
}
