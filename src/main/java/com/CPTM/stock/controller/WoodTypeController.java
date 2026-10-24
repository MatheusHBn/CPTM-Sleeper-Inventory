package com.CPTM.stock.controller;

import com.CPTM.stock.dto.WoodType.WoodTypeCreateRequest;
import com.CPTM.stock.dto.WoodType.WoodTypeResponse;
import com.CPTM.stock.service.WoodTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/wood-types")
@RequiredArgsConstructor
public class WoodTypeController {

    private final WoodTypeService service;

    @PostMapping
    public ResponseEntity<WoodTypeResponse> register(@Valid @RequestBody WoodTypeCreateRequest request) {
        var woodType = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(woodType);
    }

    @GetMapping
    public ResponseEntity<List<WoodTypeResponse>> findAllWood() {
        var allWoods = service.findAllWood();
        return ResponseEntity.ok(allWoods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WoodTypeResponse> findByWoodTypeId(@PathVariable UUID id) {
        var woodType = service.findByWoodTypeId(id);
        return ResponseEntity.ok(woodType);
    }
}
