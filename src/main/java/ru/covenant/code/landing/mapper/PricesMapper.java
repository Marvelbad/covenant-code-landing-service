package ru.covenant.code.landing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.covenant.code.landing.dto.request.PricesCreateRqDto;
import ru.covenant.code.landing.dto.request.PricesUpdateRqDto;
import ru.covenant.code.landing.dto.response.PricesRsDto;
import ru.covenant.code.landing.entity.Prices;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricesMapper {

    Prices toEntity(PricesCreateRqDto dto);

    PricesRsDto toDto(Prices entity);

    List<PricesRsDto> toDtoList(List<Prices> entities);

    void updateEntityFromDto(PricesUpdateRqDto dto, @MappingTarget Prices entity);
}
