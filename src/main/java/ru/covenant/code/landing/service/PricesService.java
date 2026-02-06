package ru.covenant.code.landing.service;

import ru.covenant.code.landing.dto.request.PricesCreateRqDto;
import ru.covenant.code.landing.dto.request.PricesUpdateRqDto;
import ru.covenant.code.landing.dto.response.PricesRsDto;
import ru.covenant.code.landing.entity.enumerated.Tariff;

import java.util.List;
import java.util.UUID;

public interface PricesService {

    PricesRsDto create(PricesCreateRqDto dto);

    PricesRsDto update(UUID id, PricesUpdateRqDto dto);

    void delete(UUID id);

    PricesRsDto getById(UUID id);

    List<PricesRsDto> getAll(Tariff tariff, Boolean isVisible);

}
