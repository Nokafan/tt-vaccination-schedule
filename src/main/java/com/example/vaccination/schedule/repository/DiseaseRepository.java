package com.example.vaccination.schedule.repository;

import com.example.vaccination.schedule.entity.Disease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Page<Disease> findAllById(Long id, Pageable pageable);

    List<Disease> findAllByIdIn(Iterable<Long> ids);

//    List<Disease> findAllByIdIsNotInAndVaccinationAgeIsLessThan(Iterable<Long> ids,
//                                                                  Period period);
    void deleteAllByIdIsIn(Iterable<Long> ids);
}
