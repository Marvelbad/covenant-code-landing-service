package ru.covenant.code.landing.service;

import ru.covenant.code.landing.dto.request.GroupStartDateCreateDto;
import ru.covenant.code.landing.dto.request.GroupStartDateUpdateRqDto;
import ru.covenant.code.landing.dto.response.GroupStartDateRsDto;

import java.util.List;

public interface GroupStartDateService {

    GroupStartDateRsDto create(GroupStartDateCreateDto dto);

    GroupStartDateRsDto update(Integer id, GroupStartDateUpdateRqDto dto);

    void delete(Integer id);

    List<GroupStartDateRsDto> getList(Boolean isActive);
}
