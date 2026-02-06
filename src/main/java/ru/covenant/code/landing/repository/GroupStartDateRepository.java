package ru.covenant.code.landing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.covenant.code.landing.entity.GroupStartDate;

import java.util.List;

public interface GroupStartDateRepository extends JpaRepository<GroupStartDate, Integer> {

    List<GroupStartDate> findAllByIsActive(boolean isActive);
}
