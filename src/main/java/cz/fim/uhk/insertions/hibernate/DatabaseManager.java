package cz.fim.uhk.insertions.hibernate;

import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.SubCategory;
import cz.fim.uhk.insertions.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DatabaseManager {
    private Session session;

    public DatabaseManager(Session session) {
        if(session==null){
            this.session=SessionMaker.openSession();
        }else{
            this.session=session;
        }
    }

    //==============================================================================================
    //
    //MODEL USER.CLASS
    //
    //==============================================================================================

    /**
     * Najde pomocí UUID konkrétního uživatele
     * @param uuid kód ve tvaru "c5687b60-ac82-4c7c-811d-14ec55749141"
     * @return Pokud uživatel nebyl nelezen, atribut name="notfound"
     */
    public User findUserByUUID(String uuid){
        User user = session.load(User.class, uuid);
        if(user != null){
            return user;
        }
        user = new User();
        user.setName("notfound");
        return user;
    }

    /**
     * Uloží instanci uživatele do databáze
     * @param user ukládaný uživatel
     * @return informace o úspěchu/neúspěchu
     */
    public String saveUser(User user){
        if(user != null){
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return "User "+user.getName()+" "+user.getSurname()+"added to DB.";
        }else{
            return "Instance of user is empty (null)";
        }
    }

    /**
     * odstraní uživatele pomocí jeho instance
     * @param user instance uživatele ke smazání
     */
    public void deleteUser(User user){
        session.delete(user);
    }

    /**
     * aktualizuje záznam uživatele, který se shoduje s instancí
     * @param user instance uživatele
     */
    public void updateUser(User user){
        session.update(user);
    }

    /**
     * jpqL příkaz
     * @return Vrací seznam všech uživatelů
     */
    public List<User> findAllUsers() {
        return session.createQuery("SELECT a FROM User a", User.class).getResultList();
    }
    public void deleteAllUsers(){
        List<User> users = findAllUsers();
        for (int i = 0; i < users.size(); i++){
            deleteUser(users.get(i));
        }
    }

    //==============================================================================================
    //
    //MODEL CATEGORY.CLASS
    //
    //==============================================================================================

    /**
     * najde kategorii podle ID
     * @param id integer klíč
     * @return Vrací nalezenou kategorii, pokud nenajde, kategorie se jmenuje notfound
     */
    public Category findCategoryByID(int id){
        Category category = session.load(Category.class, id);
        if(category != null){
            return category;
        }
        category = new Category();
        category.setName("notfound");
        return category;
    }

    /**
     * Uloží instanci kategorie do db
     * @param category daná kategorie
     * @return informace o úspěchu/neúspěchu
     */
    public String saveCategory(Category category){
        if(category != null){
            Transaction tx = session.beginTransaction();
            session.save(category);
            tx.commit();
            return "Category "+category.getName()+"added to DB.";
        }else{
            return "Instance of category is empty (null)";
        }
    }

    /**
     * odstraní kategorie pomocí jejího instance
     * @param category instance kategorie ke smazání
     */
    public void deleteCategory(Category category){
        session.delete(category);
    }

    /**
     * aktualizuje záznam uživatele, který se shoduje s instancí
     * @param category instance uživatele
     */
    public void updateCategory(Category category){
        session.update(category);
    }
    public List<Category> findAllCategories() {
        return session.createQuery("SELECT a FROM Category a", Category.class).getResultList();
    }
    public void deleteAllCategories(){
        List<Category> categories = findAllCategories();
        for (int i = 0; i < categories.size(); i++){
            deleteCategory(categories.get(i));
        }
    }

    //==============================================================================================
    //
    //MODEL SUBCATEGORY.CLASS
    //
    //==============================================================================================

    /**
     * najde subkategorii podle ID
     * @param id integer klíč
     * @return Vrací nalezenou subkategorii, pokud nenajde, subkategorie se jmenuje notfound
     */
    public SubCategory findSubCategoryByID(int id){
        SubCategory subCategory = session.load(SubCategory.class, id);
        if(subCategory != null){
            return subCategory;
        }
        subCategory = new SubCategory();
        subCategory.setName("notfound");
        return subCategory;
    }

    /**
     * Uloží instanci kategorie do db
     * @param subCategory daná kategorie
     * @return informace o úspěchu/neúspěchu
     */
    public String saveSubCategory(SubCategory subCategory){
        if(subCategory != null){
            Transaction tx = session.beginTransaction();
            session.save(subCategory);
            tx.commit();
            return "SubCategory "+subCategory.getName()+"added to DB.";
        }else{
            return "Instance of category is empty (null)";
        }
    }

    /**
     * odstraní kategorie pomocí jejího instance
     * @param subCategory instance kategorie ke smazání
     */
    public void deleteCategory(SubCategory subCategory){
        session.delete(subCategory);
    }
    /**
     * aktualizuje záznam uživatele, který se shoduje s instancí
     * @param subCategory instance uživatele
     */
    public void updateSubCategory(SubCategory subCategory){
        session.update(subCategory);
    }
    public List<SubCategory> findAllSubCategories() {
        return session.createQuery("SELECT a FROM SubCategory a", SubCategory.class).getResultList();
    }

    /**
     * smaže všechny subkategorie
     */
    public void deleteAllSubCategories(){
        List<SubCategory> subcategories = findAllSubCategories();
        for (int i = 0; i < subcategories.size(); i++){
            deleteCategory(subcategories.get(i));
        }
    }

}
