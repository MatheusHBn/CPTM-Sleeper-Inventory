package com.CPTM.stock.controller;

import com.CPTM.stock.dto.Sleeper.SleeperCreateRequest;
import com.CPTM.stock.dto.Sleeper.SleeperResponse;
import com.CPTM.stock.service.SleeperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/sleepers")
@RequiredArgsConstructor
public class SleeperController {

    private final SleeperService service;

    @PostMapping
    public ResponseEntity<SleeperResponse> register(@Valid @RequestBody SleeperCreateRequest request){
        var sleeper = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sleeper);
    }

    @GetMapping
    public ResponseEntity<List<SleeperResponse>> findAllSleepers(){
        var allSleepers = service.findAllSleepers();
        return ResponseEntity.ok(allSleepers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SleeperResponse> findBySleeperId(@PathVariable UUID id){
        var bySleeperId = service.findBySleeperId(id);
        return ResponseEntity.ok(bySleeperId);
    }

    @GetMapping("/identifier/{identifier}")
    public ResponseEntity<SleeperResponse> findByIdentifier(@PathVariable String identifier){
        var byIdentifier = service.findByIdentifier(identifier);
        return ResponseEntity.ok(byIdentifier);
    }
}
