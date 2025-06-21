package com.ophirium.stocktradingspring.controllers;

//import jakarta.validation.Valid;
import com.ophirium.stocktradingspring.domain.services.IStockRepository;
import com.ophirium.stocktradingspring.domain.models.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
