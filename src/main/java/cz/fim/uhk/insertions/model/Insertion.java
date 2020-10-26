package cz.fim.uhk.insertions.model;

import javax.persistence.*;

@Entity
public class Insertion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_insertion;
    @Column(name="name_id_column")
    private String name;
    @Column(name="decsription_column")
    private String description;
    @Column(name="price_column")
    private double price;
    @ManyToOne
    @JoinColumn(name="user_id_column")
    private User user;
    @OneToOne
    @JoinColumn(name="category_id_column")
    private Category category;
    @OneToOne
    @JoinColumn(name="subCategory_id_column")
    private SubCategory subCategory;

    public Insertion() {
    }

    public Insertion(long id_insertion, String name, String description, double price, User user, Category category, SubCategory subCategory) {
        this.id_insertion = id_insertion;
        this.name = name;
        this.description = description;
        this.price = price;
        this.user = user;
        this.category = category;
        this.subCategory = subCategory;
    }

    public long getId_insertion() {
        return id_insertion;
    }

    public void setId_insertion(long id_insertion) {
        this.id_insertion = id_insertion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
