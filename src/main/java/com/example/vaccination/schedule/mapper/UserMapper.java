package com.example.vaccination.schedule.mapper;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.dto.UserRequestDto;
import com.example.vaccination.schedule.dto.UserResponseDto;
import com.example.vaccination.schedule.entity.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userDtoToEntity(UserRequestDto requestDto) {
        return User.builder()
                .name(requestDto.getName())
                .familyName(requestDto.getFamilyName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .dateOfBirth(LocalDate.parse(requestDto.getDateOfBirth(),
                        DateTimeFormatter.ofPattern(Constants.PATTERN_DATE)))
                .build();
    }

    public UserResponseDto entityToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .familyName(user.getFamilyName())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
