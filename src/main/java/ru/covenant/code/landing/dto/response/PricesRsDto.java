package ru.covenant.code.landing.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.covenant.code.landing.entity.enumerated.Tariff;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PricesRsDto {

    private String direction;
    private Tariff tariff;
    private BigDecimal price;
    private Boolean isVisible;
    private LocalDate validFrom;
    private LocalDate validTo;
}
