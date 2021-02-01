package cz.fim.uhk.insertions.unit.testing;

import cz.fim.uhk.insertions.hibernate.DatabaseManager;
import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.Insertion;
import cz.fim.uhk.insertions.model.User;
import org.junit.Test;


import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestDatabaseManager {

    /**
     * Check if user was added to database.
     * dbm saveUser(User user) method returns String report for success/denial
     */
    @Test
    public void  testSaveUser(){
        User user = new User("Jan", "Novák", "12345", "jon.novak@gmail.com", null, null);
        DatabaseManager dbm = new DatabaseManager(null);
        String report = dbm.saveUser(user);
        assertEquals("User "+user.getName()+" "+user.getSurname()+"added to DB.", report);
    }

    /**
     * If we filter insertions by user's email, we want to make sure, that only user's insertions are in the list
     */
    @Test
    public void testfindAllInsertionsByUser(){
        String email = "jon.novak@gmail.com";
        DatabaseManager dbm = new DatabaseManager(null);
        List<Insertion> userInsertions = dbm.findAllInsertionsByUser(email);
        if(userInsertions.size()>0){  // If user with email jon.novak@gmail has some insertions, then we can check them
            for (Insertion insertion: userInsertions) {
                assertEquals(email, insertion.getUser().getEmail());
            }
        }
    }

    /**
     * Project contains 6 static categories, if you ask for some category, that does not exist,
     * instance OF Category with name "notfound" should be returned
     */
    @Test
    public void testFindCategoryById(){
        String expected = "notfound";
        DatabaseManager dbm = new DatabaseManager(null);
        Category category = dbm.findCategoryByID(500);
        assertEquals(expected, category.getName());

    }

}
