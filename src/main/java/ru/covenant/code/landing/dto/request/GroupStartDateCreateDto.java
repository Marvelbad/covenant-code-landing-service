package ru.covenant.code.landing.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class GroupStartDateCreateDto {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private String groupName;

    @NotNull
    @Positive
    private Integer duration;

    @NotNull
    private Boolean isActive;
}
