package com.example.vaccination.schedule.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GeneralService<T> {
    T save(T entity);

    List<T> saveAll(Iterable<T> iterableEntities);

    Page<T> getAllByPage(Pageable pageable);

    List<T> getAll();

    T get(Long entityId);

    void delete(Long id);

    void deleteAllByIds(Iterable<Long> ids);
}
