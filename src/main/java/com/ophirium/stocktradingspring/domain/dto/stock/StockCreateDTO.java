package com.ophirium.stocktradingspring.domain.dto.stock;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockCreateDTO {

    @NotBlank
    @Size(max = 4)
    private String code;

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 13, fraction = 2)
    private BigDecimal price;
}
