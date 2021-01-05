package cz.fim.uhk.insertions.hibernate;

import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.SubCategory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticDataDB {


    public static void createCategories(DatabaseManager databaseManager){
        Category cat1, cat2, cat3, cat4, cat5;
        Set<SubCategory> sub1, sub2, sub3, sub4, sub5;

        cat1 = new Category("Auto-Moto", null);
        SubCategory subAuto1 = new SubCategory( "Auta", cat1);
        SubCategory subAuto2 = new SubCategory( "Motocykly", cat1);
        SubCategory subAuto3 = new SubCategory( "Náhradní díly", cat1);
        sub1 = new HashSet<>();
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
        sub2 = new HashSet<>();
        sub2.add(subDum1);
        sub2.add(subDum2);
        sub2.add(subDum3);
        sub2.add(subDum4);
        cat2.setSubCategories(sub2);

        databaseManager.saveCategory(cat2);
        databaseManager.saveSubCategory(subDum1);
        databaseManager.saveSubCategory(subDum2);
        databaseManager.saveSubCategory(subDum3);
        databaseManager.saveSubCategory(subDum4);

    }

    public static void createSubCategories(DatabaseManager databaseManager){
    }
}