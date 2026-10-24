package com.CPTM.stock.mapper;

import com.CPTM.stock.domain.StockLocation;
import com.CPTM.stock.dto.StockLocation.StockLocationCreateRequest;
import com.CPTM.stock.dto.StockLocation.StockLocationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockLocationMapper {
    StockLocation toStockLocation(StockLocationCreateRequest request);

    StockLocationResponse toStockLocationResponse(StockLocation stockLocation);


}
