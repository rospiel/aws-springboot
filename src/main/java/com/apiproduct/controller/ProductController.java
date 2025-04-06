package com.apiproduct.controller;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.DTO.ProductRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ProductController {

    @Operation(summary = "All products", description = "Search all products", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
            })
    })
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    ResponseEntity<List<ProductDTO>> searchAll();

    @Operation(summary = "Save product", description = "Save a new product if not exist")
    ProductDTO save(@Valid @RequestBody(description = "Properties of a product to persist", required = true) ProductRequestDTO request);

    /**
     * content will ensure that don't duplicate the response in documentation
     * @param productId
     */
    @Operation(summary = "Remove product", description = "Remove a product if exist", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid productId", content = @Content(schema = @Schema))
    })
    void delete(@Parameter(description = "Id of a product", example = "1", required = true)
                @Pattern(regexp = "^[0-9]*$", message = "productId must be numerical") String productId);
}
