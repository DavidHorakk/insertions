//package cz.fim.uhk.insertions.controller;
//
//import cz.fim.uhk.insertions.model.User;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RegistrationResource {
//
//    private final User userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public RegistrationResource(User user, PasswordEncoder passwordEncoder) {
//        this.userRepository = user;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostMapping("/registration")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public void register(@RequestBody UserCredentialsDto userCredentialsDto) {
//        UserCredentials user = UserCredentials.builder()
//                .enabled(true)
//                .username(userCredentialsDto.getUsername())
//                .password(passwordEncoder.encode(userCredentialsDto.getPassword()))
//                .roles(Set.of("USER"))
//                .build();
//        userRepository.save(user);
//    }
//}
