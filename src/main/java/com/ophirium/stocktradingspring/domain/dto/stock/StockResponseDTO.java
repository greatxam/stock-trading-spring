package com.ophirium.stocktradingspring.domain.dto.stock;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class StockResponseDTO {
    private UUID id;

    private String code;

    private String name;

    private LocalDateTime created;

    private LocalDateTime modified;
}
