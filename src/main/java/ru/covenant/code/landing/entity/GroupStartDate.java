package ru.covenant.code.landing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "group_start_dates")
public class GroupStartDate {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Column(nullable = false, name = "group_name")
    private String groupName;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false, name = "is_active")
    private boolean isActive;
}
