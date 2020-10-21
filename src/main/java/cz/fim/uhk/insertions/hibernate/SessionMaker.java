package cz.fim.uhk.insertions.hibernate;


import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.SubCategory;
import cz.fim.uhk.insertions.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionMaker {

    public SessionMaker() {
    }

    /**
     *
     * @return Vrátí otevřenou instanci session třídy
     */
    public static Session openSession(){
        Configuration con = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(SubCategory.class);
        SessionFactory sf = con.buildSessionFactory();
        return sf.openSession();
    }

}
