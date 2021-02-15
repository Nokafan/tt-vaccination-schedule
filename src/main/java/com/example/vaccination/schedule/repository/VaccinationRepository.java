package com.example.vaccination.schedule.repository;

import com.example.vaccination.schedule.entity.Vaccination;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
    Page<Vaccination> findAllByUser_Id(Long id, Pageable pageable);

    void deleteAllByIdIsIn(Iterable<Long> ids);

    List<Vaccination> findAllByUser_IdAndDisease_DiseaseName(Long userId, String diseaseName);
}
