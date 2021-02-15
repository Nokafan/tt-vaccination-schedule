package com.example.vaccination.schedule.security;

import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.service.UserService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        User repositoryUser = userService.findByEmail(userEmail);

        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(userEmail);
        builder.password(repositoryUser.getPassword());
        builder.authorities(Collections.emptyList());
        return builder.build();
    }
}
