package ru.covenant.code.landing.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class GroupStartDateUpdateRqDto {

    @NotNull
    private LocalDate startDate;

    @NotBlank
    private String groupName;

    @NotNull
    @Positive
    private Integer duration;

    @NotNull
    private Boolean isActive;
}
