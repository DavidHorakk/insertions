package cz.fim.uhk.insertions.controller;

import cz.fim.uhk.insertions.hibernate.DatabaseManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    DatabaseManager dbm = new DatabaseManager(null);

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", dbm.findAllUsers());
        return "index";
    }

    @GetMapping("/userInsertions")
    public String userInsertions(Model model) {
        model.addAttribute("users", dbm.findAllUsers());
        return "userInsertions";
    }

}