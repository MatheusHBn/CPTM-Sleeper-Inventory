package com.CPTM.stock.service;

import com.CPTM.stock.domain.SleeperStatus;
import com.CPTM.stock.dto.Sleeper.SleeperCreateRequest;
import com.CPTM.stock.dto.Sleeper.SleeperResponse;
import com.CPTM.stock.exception.BusinessRuleException;
import com.CPTM.stock.exception.DuplicateResourceException;
import com.CPTM.stock.exception.InvalidRequestException;
import com.CPTM.stock.exception.ResourceNotFoundException;
import com.CPTM.stock.mapper.SleeperMapper;
import com.CPTM.stock.repository.SleeperRepository;
import com.CPTM.stock.repository.StockLocationRepository;
import com.CPTM.stock.repository.WoodTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SleeperService {
    private final SleeperRepository repository;
    private final SleeperMapper mapper;
    private final WoodTypeRepository woodTypeRepository;
    private final StockLocationRepository stockLocationRepository;

    public SleeperResponse register(SleeperCreateRequest request) {
        if (request.identifier() == null || request.identifier().isBlank()) {
            throw new InvalidRequestException("Write a valid identifier");
        }

        var typeWood = woodTypeRepository.findById(request.woodTypeId()).orElseThrow(
                () -> new ResourceNotFoundException("This woodType id not exists, try other id"));
        var stockLocation = stockLocationRepository.findById(request.stockLocationId()).orElseThrow(
                () -> new ResourceNotFoundException("This stockLocation id not exists, try other id"));

        if (!typeWood.isActive()) {
            throw new BusinessRuleException("This wood is inactive");
        }

        if (!stockLocation.isActive()) {
            throw new BusinessRuleException("This location is inactive");
        }

        String identifier = request.identifier().trim();

        if (repository.existsByIdentifier(identifier)) {
            throw new DuplicateResourceException("This identifier exists, try other");
        }

        var sleeperEntity = mapper.toSleeper(request);
        sleeperEntity.setIdentifier(identifier);
        sleeperEntity.setWoodType(typeWood);
        sleeperEntity.setStockLocation(stockLocation);
        sleeperEntity.setStatus(SleeperStatus.IN_STOCK);

        var sleeperSaved = repository.save(sleeperEntity);

        return mapper.toSleeperResponse(sleeperSaved);
    }

    public SleeperResponse findBySleeperId(UUID id) {
        return repository.findById(id).map(mapper::toSleeperResponse).orElseThrow(
                () -> new ResourceNotFoundException("SleeperId not Found"));
    }

    public List<SleeperResponse> findAllSleepers() {
        return repository.findAll().stream().map(mapper::toSleeperResponse).toList();
    }

    public SleeperResponse findByIdentifier(String identifier) {
        if (identifier == null || identifier.isBlank()){
            throw new ResourceNotFoundException("This identifier is invalid");
        }

        var normalizedIdentifier = identifier.trim();

        return repository.findByIdentifier(normalizedIdentifier).map(mapper::toSleeperResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Sleeper not found"));
    }
}
