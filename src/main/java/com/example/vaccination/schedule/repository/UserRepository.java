package com.example.vaccination.schedule.repository;

import com.example.vaccination.schedule.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllById(Long id, Pageable pageable);

    void deleteAllByIdIsIn(Iterable<Long> ids);
}
