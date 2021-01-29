package cz.fim.uhk.insertions.controller;

import cz.fim.uhk.insertions.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.fim.uhk.insertions.service.UserService;
import cz.fim.uhk.insertions.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserRegistrationController(UserService userService, UserRepository userRepository) {
        super();
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        if(userRepository.findByEmail(registrationDto.getEmail()) == null) {
            userService.save(registrationDto);
            return "redirect:/login";
        }
            return "redirect:/registration";
    }
}
