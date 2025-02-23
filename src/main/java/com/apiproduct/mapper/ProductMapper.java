package com.apiproduct.mapper;

import com.apiproduct.DTO.ProductDTO;
import com.apiproduct.DTO.ProductRequestDTO;
import com.apiproduct.decorator.decorators.DecimalFormatDecorator;
import com.apiproduct.entity.ProductEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper extends DecimalFormatDecorator {

    public ProductEntity toProductEntityBy(ProductRequestDTO dto) {
        return ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();
    }

    public ProductDTO toProductDTOBy(ProductEntity entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(toAmericanMoney(entity.getPrice()))
                .description(entity.getDescription())
                .build();
    }

    public List<ProductDTO> toListProductDTOBy(List<ProductEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.parallelStream()
                .map(this::toProductDTOBy)
                .collect(Collectors.toList());

    }
}
