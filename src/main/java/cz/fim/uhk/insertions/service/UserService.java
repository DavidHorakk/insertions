package cz.fim.uhk.insertions.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import cz.fim.uhk.insertions.model.User;
import cz.fim.uhk.insertions.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}
