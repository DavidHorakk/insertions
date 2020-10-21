package cz.fim.uhk.insertions.hibernate;

import cz.fim.uhk.insertions.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DatabaseManager {
    private Session session;

    public DatabaseManager(Session session) {
        if(session==null){
            this.session=SessionMaker.openSession();
        }
        this.session=session;
    }

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
     *
     * @return Vrací seznam všech uživatelů
     */
    public List<User> findAllUsers() {
        return session.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

}
