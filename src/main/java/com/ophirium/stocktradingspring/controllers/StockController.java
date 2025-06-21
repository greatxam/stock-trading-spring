package com.ophirium.stocktradingspring.controllers;

import com.ophirium.stocktradingspring.domain.dao.StockRepository;
import com.ophirium.stocktradingspring.domain.dto.stock.StockCreateDTO;
import com.ophirium.stocktradingspring.domain.dto.stock.StockResponseDTO;
import com.ophirium.stocktradingspring.domain.dto.stock.StockUpdateDTO;
import com.ophirium.stocktradingspring.domain.mappers.StockMapper;
import com.ophirium.stocktradingspring.domain.models.Stock;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @GetMapping
    public List<StockResponseDTO> listStocks() {
        return stockRepository.findAll().stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/")
    public ResponseEntity<StockResponseDTO> getStockById(
            @PathVariable UUID id
    ) {
        return stockRepository.findById(id)
                .map(stockMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(
            @RequestBody StockCreateDTO dto
    ) {
        if (stockRepository.existsByCode(dto.getCode())) {
            return ResponseEntity.badRequest().body(null);
        }

        Stock stock = stockMapper.toStock(dto);
        Stock savedStock = stockRepository.save(stock);
        return ResponseEntity.ok(stockMapper.toDTO(savedStock));
    }

    @PatchMapping("/{id}/")
    public ResponseEntity<StockResponseDTO> updateStock(
            @PathVariable UUID id,
            @Valid @RequestBody StockUpdateDTO dto
    ) {
        return stockRepository.findById(id)
                .map(existingStock -> {
                    stockMapper.updateStockFromDto(dto, existingStock);
                    Stock updated = stockRepository.save(existingStock);
                    return ResponseEntity.ok(stockMapper.toDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> deleteStock(@PathVariable UUID id) {
        return stockRepository.findById(id)
                .map(existingStock -> {
                    stockRepository.delete(existingStock);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
