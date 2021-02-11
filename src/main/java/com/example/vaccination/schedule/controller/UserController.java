package com.example.vaccination.schedule.controller;

import com.example.vaccination.schedule.dto.UserRequestDto;
import com.example.vaccination.schedule.dto.UserResponseDto;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.mapper.UserMapper;
import com.example.vaccination.schedule.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable(name = "id") Long id) {
        return userMapper.entityToDto(userService.get(id));
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto) {
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = userService.save(userMapper.userDtoToEntity(requestDto));
        log.info("User created id: " + user.getId() + " email: " + user.getEmail());
        return userMapper.entityToDto(user);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable(name = "id") Long id,
                                      @Valid @RequestBody UserRequestDto requestDto) {
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = userService.update(id, requestDto);
        log.info("User id: " + id + " updated.");
        return userMapper.entityToDto(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        log.info("User id: " + id + " deleted.");
    }

    @GetMapping("/page")
    public List<UserResponseDto> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable).getContent()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
