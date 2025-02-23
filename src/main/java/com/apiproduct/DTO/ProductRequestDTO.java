package com.apiproduct.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {

    @Schema(example = "Wood table")
    @JsonProperty("name")
    private String name;

    @Schema(example = "500.5")
    @JsonProperty("price")
    private BigDecimal price;

    @Schema(example = "Solid wood table")
    @JsonProperty("description")
    private String description;

}
