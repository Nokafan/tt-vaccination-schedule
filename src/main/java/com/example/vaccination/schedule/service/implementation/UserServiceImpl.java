package com.example.vaccination.schedule.service.implementation;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.dto.UserRequestDto;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.exception.DataProcessingException;
import com.example.vaccination.schedule.repository.UserRepository;
import com.example.vaccination.schedule.service.UserService;
import com.example.vaccination.schedule.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final VaccinationService vaccinationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, VaccinationService vaccinationService) {
        this.userRepository = userRepository;
        this.vaccinationService = vaccinationService;
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public List<User> saveAll(Iterable<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new DataProcessingException("Not found User with id: " + id));
    }

    @Transactional
    @Override
    public User update(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new DataProcessingException("User id: " + id + " not found"));
        user.setName(requestDto.getName());
        user.setFamilyName(requestDto.getFamilyName());
        user.setDateOfBirth(LocalDate.parse(requestDto.getDateOfBirth(),
                DateTimeFormatter.ofPattern(Constants.PATTERN_DATE)));
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAllById(Long id, Pageable pageable) {
        return userRepository.findAllById(id, pageable);
    }

    @Transactional
    @Override
    public void deleteAllByIds(Iterable<Long> ids) {
        userRepository.deleteAllByIdIsIn(ids);
    }
}
