package ru.covenant.code.landing.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.covenant.code.landing.dto.request.GroupStartDateCreateDto;
import ru.covenant.code.landing.dto.request.GroupStartDateUpdateRqDto;
import ru.covenant.code.landing.dto.response.GroupStartDateRsDto;
import ru.covenant.code.landing.dto.response.ResponseWrapper;
import ru.covenant.code.landing.service.GroupStartDateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/group-start-dates")
public class AdminGroupStartDateController {

    private final GroupStartDateService service;

    @PostMapping
    public ResponseWrapper<GroupStartDateRsDto> create(
            @Valid @RequestBody GroupStartDateCreateDto dto
    ) {
        return ResponseWrapper.success(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseWrapper<GroupStartDateRsDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody GroupStartDateUpdateRqDto dto
    ) {
        return ResponseWrapper.success(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseWrapper<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseWrapper.success();
    }

    @GetMapping
    public ResponseWrapper<List<GroupStartDateRsDto>> getList(
            @RequestParam(required = false) Boolean isActive
    ) {
        return ResponseWrapper.success(service.getList(isActive));
    }
}