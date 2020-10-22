package cz.fim.uhk.insertions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;
    private String name;

    public Role() {

    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id_role;
    }
    public void setId(Long id_role) {
        this.id_role = id_role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
