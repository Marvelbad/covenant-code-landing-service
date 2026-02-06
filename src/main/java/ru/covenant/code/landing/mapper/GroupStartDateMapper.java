package ru.covenant.code.landing.mapper;

import org.mapstruct.*;
import ru.covenant.code.landing.dto.request.GroupStartDateCreateDto;
import ru.covenant.code.landing.dto.request.GroupStartDateUpdateRqDto;
import ru.covenant.code.landing.dto.response.GroupStartDateRsDto;
import ru.covenant.code.landing.entity.GroupStartDate;

@Mapper(componentModel = "spring")
public interface GroupStartDateMapper {

    GroupStartDate toEntity(GroupStartDateCreateDto dto);

    GroupStartDateRsDto toRsDto(GroupStartDate entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget GroupStartDate entity, GroupStartDateUpdateRqDto dto);
}
