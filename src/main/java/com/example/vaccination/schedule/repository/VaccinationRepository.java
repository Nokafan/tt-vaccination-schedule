package com.example.vaccination.schedule.repository;

import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.entity.Vaccination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
    Page<Vaccination> findAllByUser_Id(Long id, Pageable pageable);

//    Page<Vaccination> findAllByDisease_IdAndVaccinationDateTimeBefore(Iterable<Long> ids,
//                                          LocalDateTime localDateTime,
//                                          Pageable pageable);

//    List<Vaccination> findAllByUser_Id(Long id);

    void deleteAllByIdIsIn(Iterable<Long> ids);

}
