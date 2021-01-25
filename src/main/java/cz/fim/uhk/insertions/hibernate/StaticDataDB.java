package cz.fim.uhk.insertions.hibernate;

import cz.fim.uhk.insertions.model.Category;
import cz.fim.uhk.insertions.model.SubCategory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticDataDB {


    public static void createCategories(DatabaseManager databaseManager){
        Category cat1, cat2, cat3, cat4, cat5, cat6;
        Set<SubCategory> sub1, sub2, sub3, sub4, sub5, sub6;

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

        cat3 = new Category("Elektro a počítače", null);
        SubCategory subElektro1 = new SubCategory("Audio, Video", cat3);
        SubCategory subElektro2 = new SubCategory("Foto bazar", cat3);
        SubCategory subElektro3 = new SubCategory("PC, Notebooky", cat3);
        SubCategory subElektro4 = new SubCategory("Herní konzole", cat3);
        SubCategory subElektro5 = new SubCategory("Mobily, telefony", cat3);
        sub3 = new HashSet<>();
        sub3.add(subElektro1);
        sub3.add(subElektro2);
        sub3.add(subElektro3);
        sub3.add(subElektro4);
        sub3.add(subElektro5);
        cat3.setSubCategories(sub3);

        databaseManager.saveCategory(cat3);
        databaseManager.saveSubCategory(subElektro1);
        databaseManager.saveSubCategory(subElektro2);
        databaseManager.saveSubCategory(subElektro3);
        databaseManager.saveSubCategory(subElektro4);
        databaseManager.saveSubCategory(subElektro5);

        cat4 = new Category("Sporty", null);
        SubCategory subSport1 = new SubCategory("Letní sporty", cat4);
        SubCategory subSport2 = new SubCategory("Zimní sporty", cat4);
        SubCategory subSport3 = new SubCategory("Ostatní vybavení", cat4);
        SubCategory subSport4 = new SubCategory("Služby, kurzy", cat4);
        SubCategory subSport5 = new SubCategory("Půjčovny, opravny", cat4);
        sub4 = new HashSet<>();
        sub4.add(subSport1);
        sub4.add(subSport2);
        sub4.add(subSport3);
        sub4.add(subSport4);
        sub4.add(subSport5);
        cat4.setSubCategories(sub4);

        databaseManager.saveCategory(cat4);
        databaseManager.saveSubCategory(subSport1);
        databaseManager.saveSubCategory(subSport2);
        databaseManager.saveSubCategory(subSport3);
        databaseManager.saveSubCategory(subSport4);
        databaseManager.saveSubCategory(subSport5);

        cat5 = new Category("Hudba, knihy a hry", null);
        SubCategory subHry1 = new SubCategory("Blu-Ray a DVD filmy", cat5);
        SubCategory subHry2 = new SubCategory("Hry a hračky", cat5);
        SubCategory subHry3 = new SubCategory("Hudba a CD", cat5);
        SubCategory subHry4 = new SubCategory("Hudební nástroje", cat5);
        SubCategory subHry5 = new SubCategory("Knihy a literatura", cat5);
        sub5 = new HashSet<>();
        sub5.add(subHry1);
        sub5.add(subHry2);
        sub5.add(subHry3);
        sub5.add(subHry4);
        sub5.add(subHry5);
        cat5.setSubCategories(sub5);

        databaseManager.saveCategory(cat5);
        databaseManager.saveSubCategory(subHry1);
        databaseManager.saveSubCategory(subHry2);
        databaseManager.saveSubCategory(subHry3);
        databaseManager.saveSubCategory(subHry4);
        databaseManager.saveSubCategory(subHry5);

        cat6 = new Category("Oblečení a doplňky", null);
        SubCategory subObleceni1 = new SubCategory("Boty", cat6);
        SubCategory subObleceni2 = new SubCategory("Bundy, kabáty", cat6);
        SubCategory subObleceni3 = new SubCategory("Čepice, klobouky", cat6);
        SubCategory subObleceni4 = new SubCategory("Kalhoty a šortky", cat6);
        SubCategory subObleceni5 = new SubCategory("Galanterie a doplňky", cat6);
        sub6 = new HashSet<>();
        sub6.add(subObleceni1);
        sub6.add(subObleceni2);
        sub6.add(subObleceni3);
        sub6.add(subObleceni4);
        sub6.add(subObleceni5);
        cat6.setSubCategories(sub6);

        databaseManager.saveCategory(cat6);
        databaseManager.saveSubCategory(subObleceni1);
        databaseManager.saveSubCategory(subObleceni2);
        databaseManager.saveSubCategory(subObleceni3);
        databaseManager.saveSubCategory(subObleceni4);
        databaseManager.saveSubCategory(subObleceni5);



    }

    public static void createSubCategories(DatabaseManager databaseManager){
    }
}