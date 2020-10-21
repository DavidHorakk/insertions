package cz.fim.uhk.insertions.service;

import cz.fim.uhk.insertions.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    private User user;
    private String password;

    public UserService(User user) {
        this.user = user;
        this.password=user.getPassword();
    }

    @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

}
