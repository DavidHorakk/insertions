package cz.fim.uhk.insertions.model;


import javax.persistence.*;

@Entity
@Table(name="subcategory_table")
public class SubCategory {
    @Id
    @Column(name="id_subcategory")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_subcategory;
    @Column(name="name_column")
    private String name;
    @ManyToOne()
    @JoinColumn(name="category_id_column")
    private Category category;

    public SubCategory() {
    }

    public SubCategory(String name, Category category) {
        this.id_subcategory = id_subcategory;
        this.name = name;
        this.category = category;
    }

    public int getId_subcategory() {
        return id_subcategory;
    }

    public void setId_subcategory(int id_subcategory) {
        this.id_subcategory = id_subcategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}