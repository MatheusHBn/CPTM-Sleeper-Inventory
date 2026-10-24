package com.CPTM.stock.service;

import com.CPTM.stock.domain.StockLocation;
import com.CPTM.stock.dto.StockLocation.StockLocationCreateRequest;
import com.CPTM.stock.dto.StockLocation.StockLocationResponse;
import com.CPTM.stock.exception.DuplicateResourceException;
import com.CPTM.stock.exception.InvalidRequestException;
import com.CPTM.stock.exception.ResourceNotFoundException;
import com.CPTM.stock.mapper.StockLocationMapper;
import com.CPTM.stock.repository.StockLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockLocationService {
    private final StockLocationRepository repository;
    private final StockLocationMapper mapper;

    public StockLocationResponse register(StockLocationCreateRequest request){
        if (request.name() == null || request.name().isBlank()){
            throw new InvalidRequestException("This name is invalid, try again");
        }

        if (request.code() == null || request.code().isBlank()){
            throw new InvalidRequestException("This code is invalid, try again");
        }

        String normalizedCode = request.code().trim();
        String normalizedName = request.name().trim();

        if (repository.existsByCodeIgnoreCase(normalizedCode)){
            throw new DuplicateResourceException("This code already exists, try again");
        }

        var stockLocationEntity = mapper.toStockLocation(request);
        stockLocationEntity.setActive(true);
        stockLocationEntity.setCode(normalizedCode);
        stockLocationEntity.setName(normalizedName);
        StockLocation locationSaved = repository.save(stockLocationEntity);

        return mapper.toStockLocationResponse(locationSaved);
    }

    public List<StockLocationResponse> findAllLocations(){
        return repository.findAllByActiveTrue().stream().map(mapper::toStockLocationResponse).toList();
    }

    public StockLocationResponse findByLocationId(UUID id){
         return repository.findById(id).map(mapper::toStockLocationResponse).orElseThrow(() -> new ResourceNotFoundException("StockLocation not found"));
    }
}
