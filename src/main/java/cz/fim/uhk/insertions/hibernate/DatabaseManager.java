package cz.fim.uhk.insertions.hibernate;

import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.Insertion;
import cz.fim.uhk.insertions.model.SubCategory;
import cz.fim.uhk.insertions.model.User;
import cz.fim.uhk.insertions.util.Utilities;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class DatabaseManager {
    private final Session session;

    public DatabaseManager(Session session) {
        if(session==null){
            this.session=SessionMaker.openSession();
        }else{
            this.session=session;
        }
    }

    public boolean isCategoryTableEmpty(){
        List<Category> cat = findAllCategories();
        if (cat.size() > 0){
            return false;
        }else{
            return true;
        }
    }
    public boolean isSubCategoryTableEmpty(){
        List<SubCategory> subcat = findAllSubCategories();
        if (subcat.size() > 0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Deletes all expired insertions from database
     */
    public void checkForExpiredInsertions(){
        List<Insertion> insertions = findAllInsertions();
        Date today = Utilities.getCurrentDate();
        if(insertions.size()>0){
            for (Insertion insertion: insertions) {
                System.out.println(insertion.getExpired().compareTo(today));
                if(insertion.getExpired().compareTo(today)<=0){
                    deleteInsertion(insertion);
                }
            }
        }
    }


    //==============================================================================================
    //
    //MODEL USER.CLASS
    //
    //==============================================================================================

    /**
     * Najde pomocí ID konkrétního uživatele
     * @param id primární klíč integer
     * @return Pokud uživatel nebyl nelezen, atribut name="notfound"
     */
    public User findUserByID(int id){
        User user = session.load(User.class, id);
        if(user != null){
            return user;
        }
        user = new User();
        user.setName("notfound");
        return user;
    }

//    public User findUserByMail(String mail){
//        String mailprep = "\'"+mail+"\'";
//       return session.createQuery("SELECT a FROM User a WHERE a.email = " +"'mail'", User.class).getSingleResult();
//    }

    public User findUserByName(String name){
        User user = session.load(User.class, name);
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
        Transaction tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
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
        Transaction tx = session.beginTransaction();
        session.merge(subCategory);
        tx.commit();
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

    //==============================================================================================
    //
    //MODEL INSERTION.CLASS
    //
    //==============================================================================================

    /**
     * najde inzerát podle ID
     * @param id integer klíč
     * @return Vrací nalezený inzerát, pokud nenajde, inzerát se jmenuje notfound
     */
    public Insertion findInsertionByID(Long id){
        Insertion insertion = session.load(Insertion.class, id);
        if(insertion != null){
            return insertion;
        }
        insertion = new Insertion();
        insertion.setName("notfound");
        return insertion;
    }

    /**
     * Uloží instanci inzerátu do db
     * @param insertion daný inzerát
     * @return informace o úspěchu/neúspěchu
     */
    public String saveInsertion(Insertion insertion){
        if(insertion != null){
            Transaction tx = session.beginTransaction();
            session.save(insertion);
            tx.commit();
            return "insertion "+insertion.getName()+"added to DB.";
        }else{
            return "Instance of insertion is empty (null)";
        }
    }


    /**
     * odstraní inzeráty pomocí jejího instance
     * @param insertion instance inzerátu ke smazání
     */
    public void deleteInsertion(Insertion insertion){
        Transaction tx = session.beginTransaction();
        session.delete(insertion);
        tx.commit();
    }
    /**
     * aktualizuje záznam inzerátu, který se shoduje s instancí
     * @param insertion instance inzerátu
     */
    public void updateInsertion(Insertion insertion){
            Transaction tx = session.beginTransaction();
            session.merge(insertion);
            tx.commit();
    }

    public List<Insertion> findAllInsertions() {
        return session.createQuery("SELECT a FROM Insertion a", Insertion.class).getResultList();
    }

    public List<Insertion> findFilteredInsertions(int category) {
        return session.createQuery("SELECT a FROM Insertion a WHERE (a.category.id_category =" + "'"+category+"')", Insertion.class).getResultList();
    }

    public List<Insertion> findAllInsertionsByUser(String email) {
        return session.createQuery("SELECT a FROM Insertion a WHERE a.user.email =" + "'"+email+"'", Insertion.class).getResultList();
    }

    /**
     * smaže všechny inzeráty
     */
    public void deleteAllInsertions(){
        List<Insertion> insertions = findAllInsertions();
        for (int i = 0; i < insertions.size(); i++){
            deleteInsertion(insertions.get(i));
        }
    }

}
