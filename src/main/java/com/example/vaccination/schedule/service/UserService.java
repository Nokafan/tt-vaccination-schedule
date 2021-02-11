package com.example.vaccination.schedule.service;

import com.example.vaccination.schedule.dto.UserRequestDto;
import com.example.vaccination.schedule.entity.User;

import java.util.Optional;

public interface UserService extends GeneralService<User> {
    User update(Long id, UserRequestDto requestDto);

    User findByEmail(String email);

}
