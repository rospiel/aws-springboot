package com.apiproduct.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {

    @Schema(example = "Wood table", minLength = 1, maxLength = 50)
    @JsonProperty("name")
    @NotBlank(message = "name must be informed and can't be empty")
    @Size(max = 50, message = "name should be at most 50 characters")
    private String name;

    @Schema(example = "500.5", minimum = "1", maximum = "99999999999.99")
    @JsonProperty("price")
    @Positive(message = "price should be higher than zero")
    @Digits(integer = 11, fraction = 2, message = "price should be at most 99999999999.99")
    @NotNull(message = "price must be informed")
    private BigDecimal price;

    @Schema(example = "Solid wood table", minLength = 1, maxLength = 200)
    @JsonProperty("description")
    @NotBlank(message = "description must be informed and can't be empty")
    @Size(max = 200, message = "description should be at most 200 characters")
    private String description;

}
