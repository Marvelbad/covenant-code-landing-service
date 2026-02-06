package ru.covenant.code.landing.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import ru.covenant.code.landing.entity.enumerated.Tariff;
import ru.covenant.code.landing.validation.annotation.PriceChecker;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PricesCreateRqDto {

    @NotBlank
    private String direction;

    @NotNull
    private Tariff tariff;

    @NotNull
    @PriceChecker
    private BigDecimal price;

    @NotNull
    private Boolean isVisible;

    private LocalDate validFrom;
    private LocalDate validTo;
}
