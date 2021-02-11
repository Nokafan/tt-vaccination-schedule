package com.example.vaccination.schedule.repository;

import com.example.vaccination.schedule.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    void deleteAllByIdIsIn(Iterable<Long> ids);

    Optional<User> findByEmail(String email);
}
