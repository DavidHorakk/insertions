package cz.fim.uhk.insertions.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subcategory_table")
public class SubCategory {
    @Id
    @Column(name="id_subcategory")
    private int id_subcategory;
    @Column(name="name_column")
    private String name;

    public SubCategory() {
    }

    public SubCategory(int id_subcategory, String name) {
        this.id_subcategory = id_subcategory;
        this.name = name;
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
}
