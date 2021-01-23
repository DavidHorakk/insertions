package cz.fim.uhk.insertions.controller;

import cz.fim.uhk.insertions.hibernate.DatabaseManager;
import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.Insertion;
import cz.fim.uhk.insertions.model.SubCategory;
import cz.fim.uhk.insertions.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    DatabaseManager dbm = new DatabaseManager(null);
    /*============================================================================
     * USER - LOGIN, HOME PAGE
     ============================================================================*/

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", dbm.findAllUsers());
        return "index";
    }
    /*============================================================================
     * INSERTIONS
     ============================================================================*/
    /**
     * Method that redirects to user insertions
     * @param model of user insertions
     * @return return page with user insertions
     */
    @GetMapping("/userInsertions")
    public String userInsertions(Model model) {
        model.addAttribute("users", dbm.findAllUsers());
        return "userInsertions";
    }
    /*============================================================================
     * CATEGORY
     ============================================================================*/

    /**
     * Method that redirects to list of all categories
     * @param model of all categories and subcategories
     * @return redirects tp page where you can see all categories
     */
    @GetMapping("/Category/listCategory")
    public String categories(Model model) {
        model.addAttribute("categories", dbm.findAllCategories());
        model.addAttribute("subCategories", dbm.findAllSubCategories());
        return "./Category/listCategory";
    }

    /**
     * Method that redirects to page where you can create category
     * @param model of category
     * @return page where you can create new category
     */
    @GetMapping("/Category/createCategory")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("subCategory", new SubCategory());
        return "./Category/createCategory";
    }

    /**
     * Redirects to method that creates category
     * @param category instance of category
     * @param model model of category
     * @return creates new category in dbs
     */
    @PostMapping("/Category/createCategory")
    public String createCategory(@ModelAttribute Category category,@ModelAttribute SubCategory subCategory, Model model) {
        model.addAttribute("category", category);
        model.addAttribute("subCategory", subCategory);
        dbm.saveCategory(category);
        dbm.saveSubCategory(subCategory);
        return "./Category/listCategory";
    }

    /*============================================================================
     * INSERTION
     ============================================================================*/
    /**
     * Method that redirects to list of all Insertion
     * @param model of all Insertions
     * @return redirects tp page where you can see all Insertions
     */
    @GetMapping("/Insertion/listInsertion")
    public String insertions(Model model) {
        model.addAttribute("insertion", dbm.findAllInsertions());
        return "./Insertion/listInsertion";
    }

    /**
     * Method that redirects to page where you can create insertion
     * @param model of Insertion
     * @return page where you can create new insertion
     */
    @GetMapping("/Insertion/createInsertion")
    public String createInsertionForm(Model model) {
        model.addAttribute("insertion", new Insertion());
        return "./Insertion/createInsertion";
    }

    /**
     * Redirects to method that creates Insertion
     * @param insertion instance of Insertion
     * @param model model of Insertion
     * @return creates new Insertion in dbs
     */
    @PostMapping("/Insertion/createInsertion")
    public String createInsertion(@ModelAttribute Insertion insertion, @ModelAttribute User user, Model model) {
        model.addAttribute("insertion", insertion);
        model.addAttribute("users", dbm.findAllUsers());
        dbm.saveInsertion(insertion);
        return "./Insertion/listInsertion";
    }


}