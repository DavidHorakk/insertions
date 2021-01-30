package cz.fim.uhk.insertions.service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import cz.fim.uhk.insertions.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cz.fim.uhk.insertions.model.User;
import cz.fim.uhk.insertions.repository.UserRepository;
import cz.fim.uhk.insertions.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getName(),
                registrationDto.getSurname(),passwordEncoder.encode(registrationDto.getPassword()),
                registrationDto.getEmail(), registrationDto.getTelnum(), Collections.singletonList(new Role("ROLE_USER")), null);
        User checkUser = userRepository.findByEmail(registrationDto.getEmail());
        if(checkUser == null){
            return userRepository.save(user);
        }else{
            throw new UsernameNotFoundException("User with this email already exists");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
