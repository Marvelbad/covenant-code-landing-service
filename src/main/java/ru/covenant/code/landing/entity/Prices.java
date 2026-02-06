package ru.covenant.code.landing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.covenant.code.landing.entity.enumerated.Tariff;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "prices_db")
public class Prices {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String direction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tariff tariff;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean isVisible = true;

    private LocalDate validFrom;

    private LocalDate validTo;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}
