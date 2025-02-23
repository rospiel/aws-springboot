package com.apiproduct.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @Schema(example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(example = "Wood table")
    @JsonProperty("name")
    private String name;

    @Schema(example = "1,500.00")
    @JsonProperty("price")
    private String price;

    @Schema(example = "Solid wood table")
    @JsonProperty("description")
    private String description;

    
}
