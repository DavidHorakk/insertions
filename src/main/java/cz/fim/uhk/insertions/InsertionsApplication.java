package cz.fim.uhk.insertions;

import cz.fim.uhk.insertions.hibernate.DatabaseManager;
import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.SubCategory;
import cz.fim.uhk.insertions.model.User;
import cz.fim.uhk.insertions.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class InsertionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsertionsApplication.class, args);
//        User user = new User("Jan","Novák","test","novakja@uhk.cz","123456789",1);
//        UserService us = new UserService(user);
//        us.passwordEncoder();

//        User user = new User("Jan","Novák","test","novakja@uhk.cz","123456789",1);

//        Transaction tx = session.beginTransaction();
//        session.save(user);
//        DatabaseManager dbm = new DatabaseManager(session);
//        List<User> honzove = dbm.findAllUsers();
//        System.out.println(honzove.get(0).getId_user());
//        String honza = "c5687b60-ac82-4c7c-811d-14ec55749141";
//        byte[] test;
//        test = honza.getBytes();
//        UUID uuid = UUID.fromString("c5687b60-ac82-4c7c-811d-14ec55749141");
//        User hledanyhonza = session.load(User.class, uuid);
//        System.out.println(hledanyhonza.getName());
//         tx.commit();



    }



}
