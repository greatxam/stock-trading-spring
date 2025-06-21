package com.ophirium.stocktradingspring.controllers;

//import jakarta.validation.Valid;
import com.ophirium.stocktradingspring.domain.services.IStockRepository;
import com.ophirium.stocktradingspring.domain.models.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private IStockRepository stockRepository;

    @GetMapping("/")
    public ResponseEntity<?> listStocks() {
        return ResponseEntity.ok(stockRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createStock(@RequestBody Stock stock) {
        if (stockRepository.existsByCode(stock.getCode())) {
            return ResponseEntity.badRequest().body("Stock code already exists.");
        }
        Stock saved = stockRepository.save(stock);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStock(@PathVariable("id") UUID id) {
        return stockRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStock(@PathVariable("id") UUID id,
                                         @RequestBody Stock stock) {
        if (!stockRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        stock.setId(id);
        Stock updated = stockRepository.save(stock);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable("id") UUID id) {
        if (!stockRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        stockRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
