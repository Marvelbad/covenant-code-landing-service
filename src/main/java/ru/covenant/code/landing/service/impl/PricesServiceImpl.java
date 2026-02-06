package ru.covenant.code.landing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.covenant.code.landing.dto.request.PricesCreateRqDto;
import ru.covenant.code.landing.dto.request.PricesUpdateRqDto;
import ru.covenant.code.landing.dto.response.PricesRsDto;
import ru.covenant.code.landing.entity.Prices;
import ru.covenant.code.landing.entity.enumerated.Tariff;
import ru.covenant.code.landing.mapper.PricesMapper;
import ru.covenant.code.landing.repository.PricesRepository;
import ru.covenant.code.landing.service.PricesService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PricesServiceImpl implements PricesService {

    private final PricesRepository pricesRepository;
    private final PricesMapper pricesMapper;

    @Override
    public PricesRsDto create(PricesCreateRqDto dto) {
        Prices entity = pricesMapper.toEntity(dto);
        return pricesMapper.toDto(pricesRepository.save(entity));
    }

    @Override
    public PricesRsDto update(UUID id, PricesUpdateRqDto dto) {
        Prices entity = pricesRepository.findById(id)
                .orElseThrow(); // handler потом

        pricesMapper.updateEntityFromDto(dto, entity);
        return pricesMapper.toDto(pricesRepository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        pricesRepository.deleteById(id);
    }

    @Override
    public PricesRsDto getById(UUID id) {
        return pricesRepository.findById(id)
                .map(pricesMapper::toDto)
                .orElseThrow();
    }

    @Override
    public List<PricesRsDto> getAll(Tariff tariff, Boolean isVisible) {
        if (tariff != null && isVisible != null) {
            return pricesMapper.toDtoList(
                    pricesRepository.findAllByTariffAndIsVisible(tariff, isVisible)
            );
        }

        if (tariff != null) {
            return pricesMapper.toDtoList(pricesRepository.findAllByTariff(tariff));
        }

        if (isVisible != null) {
            return pricesMapper.toDtoList(pricesRepository.findAllByIsVisible(isVisible));
        }

        return pricesMapper.toDtoList(pricesRepository.findAll());
    }

}
