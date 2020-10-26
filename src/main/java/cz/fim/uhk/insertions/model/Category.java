package cz.fim.uhk.insertions.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category_table")
public class Category {
    @Id
    @Column(name="id_category")
    private int id_category;
    @Column(name="name_column")
    private String name;
    @OneToMany
    @Column(name="subcategories_column")
    private List<SubCategory> subCategories;

    public Category() {
    }

    public Category(int id_category, String name, List<SubCategory> subCategories) {
        this.id_category = id_category;
        this.name = name;
        this.subCategories=subCategories;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
