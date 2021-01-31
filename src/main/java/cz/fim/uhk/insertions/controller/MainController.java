package cz.fim.uhk.insertions.controller;

import cz.fim.uhk.insertions.hibernate.DatabaseManager;
import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.Insertion;
import cz.fim.uhk.insertions.model.Role;
import cz.fim.uhk.insertions.model.SubCategory;
import cz.fim.uhk.insertions.repository.UserRepository;
import cz.fim.uhk.insertions.util.Utilities;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;
import java.util.Collection;


@Controller
public class MainController {
    DatabaseManager dbm = new DatabaseManager(null);
    private final UserRepository userRepository;




    public MainController(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    /*============================================================================
     * USER - LOGIN, HOME PAGE
     ============================================================================*/

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        dbm.checkForExpiredInsertions();
        model.addAttribute("insertions", dbm.findAllInsertions());
        model.addAttribute("categories", dbm.findAllCategories());
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
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<Role> roles = userRepository.findByEmail(userEmail).getRoles();
        for (Role role:roles) {
            if(role.getName().equals("ROLE_ADMIN")){
                model.addAttribute("insertions", dbm.findAllInsertions());
                model.addAttribute("categories", dbm.findAllCategories());
                break;
            }else{
                model.addAttribute("insertions", dbm.findAllInsertionsByUser(userEmail));
                model.addAttribute("categories", dbm.findAllCategories());
            }
        }

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
     * Method that redirects to page where you can create insertion
     * @param model of Insertion
     * @return page where you can create new insertion
     */
    @GetMapping("/Insertion/createInsertion")
    public String createInsertionForm(Model model) {
        model.addAttribute("insertion", new Insertion());
        model.addAttribute("categories", dbm.findAllCategories());
        model.addAttribute("subCategories", dbm.findAllSubCategories());
        return "./Insertion/createInsertion";
    }

    /**
     * Method that redirects to page where you can see detail of insertion
     * @param model of Insertion
     * @return page where you can see details insertion
     */
    @GetMapping("/Insertion/detailInsertion")
    public String detailInsertionForm(Model model,
                                      @RequestParam("id")long id) {
        Insertion insertion = dbm.findInsertionByID(id);
        String format = insertion.getFormat();
        byte[] photo = Base64.getEncoder().encode(insertion.getPhoto());
        String photoStr = new String(photo);
        String src = "data:image/"+format+";base64,"+photoStr;
        model.addAttribute("insertion", insertion);
        model.addAttribute("source", src);
        System.out.println(src);
        return "./Insertion/detailInsertion";
    }

    /**
     * Method that redirects to page where you can see edit page of insertion
     * @param model of Insertion
     * @return page where you can edit insertion
     */
    @GetMapping("/Insertion/editInsertion")
    public String editInsertionForm(Model model,
                                      @RequestParam("id")long id) {
        model.addAttribute("insertion", dbm.findInsertionByID(id));
        model.addAttribute("categories", dbm.findAllCategories());
        model.addAttribute("subCategories", dbm.findAllSubCategories());
        return "./Insertion/editInsertion";
    }
    /**
     * Method that redirects to page where you can see edit page of insertion
     * @param model of Insertion
     * @return page where you can edit insertion
     */
    @PostMapping("/Insertion/editInsertion")
    public String editInsertion(@ModelAttribute Insertion insertion,Model model,
                                    @RequestParam("id")long id,
                                    @RequestParam("id_category")int id_category,
                                    @RequestParam("id_subcategory")int id_subcategory) {
        model.addAttribute("insertion", dbm.findInsertionByID(id));
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        insertion.setId_insertion(id);
        insertion.setUser(userRepository.findByEmail(userEmail));
        insertion.setCategory(dbm.findCategoryByID(id_category));
        insertion.setSubCategory(dbm.findSubCategoryByID(id_subcategory));
        dbm.updateInsertion(insertion);
        return "redirect:/Insertion/detailInsertion?id="+id;
    }

    /**
     * Redirects to method that creates Insertion
     * @param insertion instance of Insertion
     * @param model model of Insertion
     * @return creates new Insertion in dbs
     */
    @PostMapping("/Insertion/createInsertion")
    public String createInsertion(@ModelAttribute Insertion insertion,Model model,
                                  @RequestParam("id_category")int id_category,
                                  @RequestParam("id_subcategory")int id_subcategory,
                                  @RequestParam(value="file", required = false) MultipartFile multiPartFile
    ) {
        model.addAttribute("insertion", insertion);
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        insertion.setUser(userRepository.findByEmail(userEmail));
        insertion.setCategory(dbm.findCategoryByID(id_category));
        insertion.setSubCategory(dbm.findSubCategoryByID(id_subcategory));
        insertion.setCreated(Utilities.getCurrentDate());
        insertion.setExpired(Utilities.getExpiredDate());

        try {
            insertion.setPhoto(multiPartFile.getBytes());
            insertion.setFormat(multiPartFile.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbm.saveInsertion(insertion);
        return "redirect:/";
    }

    /**
     * Deletes insertion and redirects back to insertions made by specific user,
     * @param id insertion id
     * @return to userInsertion page
     */
    @GetMapping("/Insertion/deleteInsertion")
    public String deleteInsertion(@RequestParam("id") long id){
          Insertion deleted = dbm.findInsertionByID(id);
            dbm.deleteInsertion(deleted);
        return "redirect:/userInsertions";
    }

    /**
     * Method that redirects to list of filtered Insertions
     * @param model of all Insertions
     * @return redirects tp page where you can see filtered Insertions
     */
    @PostMapping("/")
    public String filterInsertion(Model model, @RequestParam("id_category")int id_category) {
        model.addAttribute("insertions", dbm.findFilteredInsertions(id_category));
        model.addAttribute("categories", dbm.findAllCategories());
        return "index";
    }
    /**
     * Redirects to send email page
     * @param
     * @return to userInsertion page
     */
    @GetMapping("/Mail/sendMail")
    public String sendEmailForm(
            Model model, @RequestParam("id")long id){
        model.addAttribute("insertion", dbm.findInsertionByID(id));
        return "/Mail/sendMail";
    }
    /**
     * Redirects to send email page
     * @param
     * @return to userInsertion page
     */
    @PostMapping("/Mail/sendMail")
    public String sendMail(
            Model model,
            @RequestParam("id") long id,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message
    ){
        Utilities.sendEmail(from, to, subject, message);
        model.addAttribute("insertion", dbm.findInsertionByID(id));
        return "redirect:/Insertion/detailInsertion?id="+id;
    }

}