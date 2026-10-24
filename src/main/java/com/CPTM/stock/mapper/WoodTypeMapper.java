package com.CPTM.stock.mapper;

import com.CPTM.stock.domain.WoodType;
import com.CPTM.stock.dto.WoodType.WoodTypeCreateRequest;
import com.CPTM.stock.dto.WoodType.WoodTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WoodTypeMapper {

    WoodType toWoodType(WoodTypeCreateRequest request);

    WoodTypeResponse toWoodTypeResponse(WoodType woodType);

}
