package ru.covenant.code.landing.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.covenant.code.landing.dto.request.PricesCreateRqDto;
import ru.covenant.code.landing.dto.request.PricesUpdateRqDto;
import ru.covenant.code.landing.dto.response.PricesRsDto;
import ru.covenant.code.landing.dto.response.ResponseWrapper;
import ru.covenant.code.landing.entity.enumerated.Tariff;
import ru.covenant.code.landing.service.PricesService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/prices")
public class AdminPricesController {

    private final PricesService pricesService;

    @PostMapping
    public ResponseWrapper<PricesRsDto> create(@Valid @RequestBody PricesCreateRqDto dto) {
        return ResponseWrapper.success(pricesService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseWrapper<PricesRsDto> update(
            @PathVariable UUID id,
            @Valid @RequestBody PricesUpdateRqDto dto
    ) {
        return ResponseWrapper.success(pricesService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseWrapper<Void> delete(@PathVariable UUID id) {
        pricesService.delete(id);
        return ResponseWrapper.success();
    }

    @GetMapping("/{id}")
    public ResponseWrapper<PricesRsDto> getById(@PathVariable UUID id) {
        return ResponseWrapper.success(pricesService.getById(id));
    }

    @GetMapping
    public ResponseWrapper<List<PricesRsDto>> getAll(
            @RequestParam(required = false) Tariff tariff,
            @RequestParam(required = false) Boolean isVisible
    ) {
        return ResponseWrapper.success(pricesService.getAll(tariff, isVisible));
    }
}
