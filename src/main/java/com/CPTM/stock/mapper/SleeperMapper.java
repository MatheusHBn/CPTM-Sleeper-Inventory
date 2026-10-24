package com.CPTM.stock.mapper;

import com.CPTM.stock.domain.Sleeper;
import com.CPTM.stock.dto.Sleeper.SleeperCreateRequest;
import com.CPTM.stock.dto.Sleeper.SleeperResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SleeperMapper {

    @Mapping(target = "woodType", ignore = true)
    @Mapping(target = "stockLocation", ignore = true)
    Sleeper toSleeper(SleeperCreateRequest request);

    @Mapping(target = "woodTypeId", source = "woodType.id")
    @Mapping(target = "stockLocationId", source = "stockLocation.id")
    SleeperResponse toSleeperResponse(Sleeper sleeper);

}
