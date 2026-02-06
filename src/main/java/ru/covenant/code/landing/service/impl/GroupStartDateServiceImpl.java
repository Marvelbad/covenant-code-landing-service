package ru.covenant.code.landing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.covenant.code.landing.dto.request.GroupStartDateCreateDto;
import ru.covenant.code.landing.dto.request.GroupStartDateUpdateRqDto;
import ru.covenant.code.landing.dto.response.GroupStartDateRsDto;
import ru.covenant.code.landing.entity.GroupStartDate;
import ru.covenant.code.landing.mapper.GroupStartDateMapper;
import ru.covenant.code.landing.repository.GroupStartDateRepository;
import ru.covenant.code.landing.service.GroupStartDateService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupStartDateServiceImpl implements GroupStartDateService {

    private final GroupStartDateRepository repository;
    private final GroupStartDateMapper mapper;


    @Override
    public GroupStartDateRsDto create(GroupStartDateCreateDto dto) {
        GroupStartDate entity = mapper.toEntity(dto);
        return mapper.toRsDto(repository.save(entity));
    }

    @Override
    public GroupStartDateRsDto update(Integer id, GroupStartDateUpdateRqDto dto) {
        GroupStartDate entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Группа не найдена"));

        mapper.updateEntity(entity, dto);
        return mapper.toRsDto(repository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<GroupStartDateRsDto> getList(Boolean isActive) {
        List<GroupStartDate> list = (isActive == null)
                ? repository.findAll()
                : repository.findAllByIsActive(isActive);

        return list.stream()
                .map(mapper::toRsDto)
                .toList();
    }
}
