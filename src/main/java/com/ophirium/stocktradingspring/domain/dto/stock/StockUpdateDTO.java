package com.ophirium.stocktradingspring.domain.dto.stock;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.DecimalMin;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockUpdateDTO {
    @Size(max = 4)
    private String code;

    private String name;

    @DecimalMin("0.00")
    @Digits(integer = 13, fraction = 2)
    private BigDecimal price;
}
