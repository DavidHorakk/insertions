package cz.fim.uhk.insertions.hibernate;

import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class StaticDataDB {


    public static void createCategories(DatabaseManager databaseManager){
        Category cat1, cat2, cat3, cat4, cat5;
        List<SubCategory> sub1, sub2, sub3, sub4, sub5;

        cat1 = new Category("Auto-Moto", null);
        SubCategory subAuto1 = new SubCategory( "Auta", cat1);
        SubCategory subAuto2 = new SubCategory( "Motocykly", cat1);
        SubCategory subAuto3 = new SubCategory( "Náhradní díly", cat1);
        sub1 = new ArrayList<>();
        sub1.add(subAuto1);
        sub1.add(subAuto2);
        sub1.add(subAuto3);
        cat1.setSubCategories(sub1);

        databaseManager.saveCategory(cat1);
        databaseManager.saveSubCategory(subAuto1);
        databaseManager.saveSubCategory(subAuto2);
        databaseManager.saveSubCategory(subAuto3);

        cat2 = new Category("Dům a zahrada", null);
        SubCategory subDum1 = new SubCategory("Bytové doplňky", cat2);
        SubCategory subDum2 = new SubCategory("Domácí spotřebiče", cat2);
        SubCategory subDum3 = new SubCategory("Zahrada", cat2);
        SubCategory subDum4 = new SubCategory("Rostliny, sazenice", cat2);
        sub2 = new ArrayList<>();
        sub2.add(subAuto1);
        sub2.add(subAuto2);
        sub2.add(subAuto3);
        cat2.setSubCategories(sub2);

    }

    public static void createSubCategories(DatabaseManager databaseManager){
    }
}