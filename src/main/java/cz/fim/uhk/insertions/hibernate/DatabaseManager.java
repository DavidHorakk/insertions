package cz.fim.uhk.insertions.hibernate;

import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.Insertion;
import cz.fim.uhk.insertions.model.SubCategory;
import cz.fim.uhk.insertions.model.User;
import cz.fim.uhk.insertions.util.Utilities;
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
    /**
     * Check if CategoryTable is empty
     * @return true - is empty = true, not empty = false
     */
    public boolean isCategoryTableEmpty(){
        List<Category> cat = findAllCategories();
        return cat.size() <= 0;
    }

    /**
     * Check if SubCategoryTable is empty
     * @return true - is empty = true, not empty = false
     */
    public boolean isSubCategoryTableEmpty(){
        List<SubCategory> subcat = findAllSubCategories();
        return subcat.size() <= 0;
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
     * Saves instance of the user into the database
     * @param user user that is going to be saved
     * @return Information about success/denial
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
     *Deletes User instance from table
     * @param user Instance of the user, that is going to be deleted.
     */
    public void deleteUser(User user){
        session.delete(user);
    }

    /**
     * Updates record of specific user, that is in parameter
     * @param user Instance of User
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
        for (User user : users) {
            deleteUser(user);
        }
    }

    //==============================================================================================
    //
    //MODEL CATEGORY.CLASS
    //
    //==============================================================================================

    /**
     * Finds category by ID
     * @param id integer primary key
     * @return Returns category, if it does not find any, method will return instance called "notfound"
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
     * Saves instance of Category
     * @param category Instance of Category
     * @return Returns information about success/denial
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
     * Deletes category from database
     * @param category instance that is going to be deleted
     */
    public void deleteCategory(Category category){
        session.delete(category);
    }

    /**
     * Updates record in Category table
     * @param category Instance of the category, that is going to be updated
     */
    public void updateCategory(Category category){
        session.update(category);
    }

    /**
     * Finds all categories
     * @return returns List of Category instances
     */
    public List<Category> findAllCategories() {
        return session.createQuery("SELECT a FROM Category a", Category.class).getResultList();
    }

    /**
     * Deletes all categories in the dabase
     */
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
     * Finds the subcategory by ID
     * @param id integer primary key
     * @return Returns SubCategory, if it does not find any, method will return instance called "notfound"
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
     * Saves instance of SubCategory into the database
     * @param subCategory Instance of the SubCategory
     * @return Returns information about success/denial
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
     * Deletes SubCategory by instance of Category
     * @param subCategory instance of SubCategory
     */
    public void deleteCategory(SubCategory subCategory){
        session.delete(subCategory);
    }
    /**
     * Updates the record of SubCategory record
     * @param subCategory instance of SubCategory that is going to be updated
     */
    public void updateSubCategory(SubCategory subCategory){
        Transaction tx = session.beginTransaction();
        session.merge(subCategory);
        tx.commit();
    }

    /**
     * Finds all SubCategories
     * @return Returns all SubCategories in List
     */
    public List<SubCategory> findAllSubCategories() {
        return session.createQuery("SELECT a FROM SubCategory a", SubCategory.class).getResultList();
    }

    /**
     * Deletes all SubCategories
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
     * Finds the insertion by ID
     * @param id integer primary key
     * @return Returns SubCategory, if it does not find any, method will return instance called "notfound"
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
     * Saves the instance of Insertion into the database
     * @param insertion Instance of insertion that is going to be saved
     * @return Information about success/denial
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
     * Deletes insertion by Insertion instance
     * @param insertion Instance of Insertion that is going to be deleted
     */
    public void deleteInsertion(Insertion insertion){
        Transaction tx = session.beginTransaction();
        session.delete(insertion);
        tx.commit();
    }
    /**
     * Updates record of the insertion
     * @param insertion Instance of Insertion
     */
    public void updateInsertion(Insertion insertion){
            Transaction tx = session.beginTransaction();
            session.merge(insertion);
            tx.commit();
    }

    /**
     * Finds all insertions
     * @return Returns insertions in List
     */
    public List<Insertion> findAllInsertions() {
        return session.createQuery("SELECT a FROM Insertion a", Insertion.class).getResultList();
    }

    /**
     * Filters all insertions by their Category
     * @param category filter category
     * @return returns List of filtered insertions
     */
    public List<Insertion> findFilteredInsertions(int category) {
        return session.createQuery("SELECT a FROM Insertion a WHERE (a.category.id_category =" + "'"+category+"')", Insertion.class).getResultList();
    }

    /**
     * Filters insertions made only by the user
     * @param email mail address of the user, that you want to filter
     * @return returns List of filtered insertions
     */
    public List<Insertion> findAllInsertionsByUser(String email) {
        return session.createQuery("SELECT a FROM Insertion a WHERE a.user.email =" + "'"+email+"'", Insertion.class).getResultList();
    }

    /**
     * Deletes all Insertions from the database
     */
    public void deleteAllInsertions(){
        List<Insertion> insertions = findAllInsertions();
        for (int i = 0; i < insertions.size(); i++){
            deleteInsertion(insertions.get(i));
        }
    }

}
