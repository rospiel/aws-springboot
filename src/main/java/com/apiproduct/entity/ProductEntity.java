package com.apiproduct.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private String description;
}
