package com.example.vaccination.schedule.service;

import com.example.vaccination.schedule.dto.UserRequestDto;
import com.example.vaccination.schedule.entity.User;
public interface UserService extends GeneralService<User> {
    User update(Long id, UserRequestDto requestDto);
}
