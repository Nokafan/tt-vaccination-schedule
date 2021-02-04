package com.example.vaccination.schedule.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralService<T> {
    T save(T entity);

    List<T> saveAll(Iterable<T> iterableEntities);

    Page<T> getAll(Pageable pageable);

    T get(Long entityId);

    void delete(Long id);

    Page<T> findAllById(Long id, Pageable pageable);

    void deleteAllByIds(Iterable<Long> ids);
}
