package ru.covenant.code.landing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.covenant.code.landing.entity.Prices;
import ru.covenant.code.landing.entity.enumerated.Tariff;

import java.util.List;
import java.util.UUID;

@Repository
public interface PricesRepository extends JpaRepository<Prices, UUID> {

    List<Prices> findAllByTariff(Tariff tariff);

    List<Prices> findAllByIsVisible(Boolean isVisible);

    List<Prices> findAllByTariffAndIsVisible(Tariff tariff, Boolean isVisible);
}
