package cz.fim.uhk.insertions;

import cz.fim.uhk.insertions.model.User;
import cz.fim.uhk.insertions.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsertionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsertionsApplication.class, args);
//        User user = new User("Jan","Novák","test","novakja@uhk.cz","123456789",1);
//        UserService us = new UserService(user);
//        us.passwordEncoder();

        User user = new User("Jan","Novák","test","novakja@uhk.cz","123456789",1);

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
         tx.commit();



    }



}
