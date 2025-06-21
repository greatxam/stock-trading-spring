package com.ophirium.stocktradingspring.domain.mappers;

import com.ophirium.stocktradingspring.domain.dto.stock.StockCreateDTO;
import com.ophirium.stocktradingspring.domain.dto.stock.StockResponseDTO;
import com.ophirium.stocktradingspring.domain.dto.stock.StockUpdateDTO;
import com.ophirium.stocktradingspring.domain.models.Stock;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface StockMapper {
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    Stock toStock(StockCreateDTO dto);
    Stock toStock(StockUpdateDTO dto);
    StockResponseDTO toDTO(Stock stock);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStockFromDto(StockUpdateDTO dto, @MappingTarget Stock entity);
}
