package com.example.vaccination.schedule.repository;

import com.example.vaccination.schedule.entity.VaccinationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory, Long> {
}
