package com.example.vaccination.schedule.repository;

import com.example.vaccination.schedule.entity.Disease;
import java.time.Period;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    void deleteAllByIdIsIn(Iterable<Long> ids);

    @Query(value = "SELECT d FROM Disease d WHERE d.id NOT IN "
                        + "(SELECT v.disease.id FROM Vaccination v WHERE v.user.id = :userId)")
    Page<Disease> findAllSkipped(@Param("userId") Long userId, Pageable pageable);

    List<Disease> findAllByDiseaseNameAndVaccinationAgeIsNotIn(String diseaseName,
                                                               List<Period> periods);
}
