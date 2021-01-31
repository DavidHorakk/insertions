package cz.fim.uhk.insertions;

import cz.fim.uhk.insertions.hibernate.DatabaseManager;
import cz.fim.uhk.insertions.hibernate.StaticDataDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InsertionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsertionsApplication.class, args);

          DatabaseManager dbm = new DatabaseManager(null);

          if(dbm.isCategoryTableEmpty() && dbm.isSubCategoryTableEmpty()){ // if static data are not inserted in tables, create them
              StaticDataDB.createCategories(dbm);
          }
    }
}
