package com.ophirium.stocktradingspring.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock
    extends BaseEntity
{
    @Column(length = 4, unique = true, nullable = false)
    private String code;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal price;
}
