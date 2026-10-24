package com.CPTM.stock.service;

import com.CPTM.stock.domain.WoodType;
import com.CPTM.stock.dto.WoodType.WoodTypeCreateRequest;
import com.CPTM.stock.dto.WoodType.WoodTypeResponse;
import com.CPTM.stock.exception.DuplicateResourceException;
import com.CPTM.stock.exception.InvalidRequestException;
import com.CPTM.stock.exception.ResourceNotFoundException;
import com.CPTM.stock.mapper.WoodTypeMapper;
import com.CPTM.stock.repository.WoodTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WoodTypeService {
    private final WoodTypeRepository repository;
    private final WoodTypeMapper mapper;

    public WoodTypeResponse register(WoodTypeCreateRequest request) {

        if (request.name() == null || request.name().isBlank()){
            throw new InvalidRequestException("This name is invalid, try again");
        }

        String normalizedName = request.name().trim();

        if (repository.existsByNameIgnoreCase(normalizedName)) {
            throw new DuplicateResourceException("This name already exists, try again");
        }

        var woodTypeEntity = mapper.toWoodType(request);
        woodTypeEntity.setActive(true);
        woodTypeEntity.setName(normalizedName);

        var woodTypeSaved = repository.save(woodTypeEntity);

        return mapper.toWoodTypeResponse(woodTypeSaved);
    }

    public List<WoodTypeResponse> findAllWood(){
        return repository.findByActiveTrue().stream().map(mapper::toWoodTypeResponse).toList();
    }

    public WoodTypeResponse findByWoodTypeId(UUID id) {
        return repository.findById(id).map(mapper::toWoodTypeResponse).orElseThrow(() -> new ResourceNotFoundException("TypeWood id not found"));
    }
}
