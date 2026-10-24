package com.CPTM.stock.controller;

import com.CPTM.stock.dto.StockLocation.StockLocationCreateRequest;
import com.CPTM.stock.dto.StockLocation.StockLocationResponse;
import com.CPTM.stock.service.StockLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/v1/stock-locations")
@RestController
@RequiredArgsConstructor
public class StockLocationController {

    private final StockLocationService service;

    @PostMapping
    public ResponseEntity<StockLocationResponse> register(@Valid @RequestBody StockLocationCreateRequest request) {
        var stockLocation = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockLocation);
    }

    @GetMapping
    public ResponseEntity<List<StockLocationResponse>> findAllLocations() {
        var stockLocation = service.findAllLocations();
        return ResponseEntity.ok(stockLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockLocationResponse> findStockLocationById(@PathVariable UUID id) {
        var byLocationId = service.findByLocationId(id);
        return ResponseEntity.ok(byLocationId);
    }
}
