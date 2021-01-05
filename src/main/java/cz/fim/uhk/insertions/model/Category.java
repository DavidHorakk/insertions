package cz.fim.uhk.insertions.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="category_table")
public class Category {
    @Id
    @Column(name="id_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_category;
    @Column(name="name_column")
    private String name;
    @OneToMany(mappedBy="category")
    @Column(name="subcategories_column")
    private Set<SubCategory> subCategories;

    public Category() {
    }

    public Category(String name, Set<SubCategory> subCategories) {
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

    public Set<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
