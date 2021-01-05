package cz.fim.uhk.insertions.controller;

import cz.fim.uhk.insertions.hibernate.DatabaseManager;
import cz.fim.uhk.insertions.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/Category/listCategory")
    public String categories(Model model) {
        model.addAttribute("categories", dbm.findAllCategories());
        model.addAttribute("subCategories", dbm.findAllSubCategories());
        return "./Category/listCategory";
    }

    @GetMapping("/Category/createCategory")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "./Category/createCategory";
    }

    @PostMapping("/Category/createCategory")
    public String createCategory(@ModelAttribute Category category, Model model) {
        model.addAttribute("category", category);
        dbm.saveCategory(category);
        return "./Category/listCategory";
    }


}