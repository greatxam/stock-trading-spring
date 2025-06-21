package com.ophirium.stocktradingspring.domain.services;

import com.ophirium.stocktradingspring.domain.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IStockRepository
    extends JpaRepository<Stock, UUID>
{
    boolean existsByCode(String code);
    Stock findByCode(String code);
    Stock findByName(String name);
}
