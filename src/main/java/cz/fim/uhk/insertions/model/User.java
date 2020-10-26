package cz.fim.uhk.insertions.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user_table")
public class User {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id_user;
    @Column(name="name_column")
    private String name;
    @Column(name="surname_column")
    private String surname;
    @Column(name="password_column")
    private String password;
    @Column(name="email_column")
    private String email;
    @Column(name="telnum_column")
    private String telnum;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "id_user", referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_role", referencedColumnName = "id_role"))

    private Collection<Role> roles;
    @OneToMany
    private List<Insertion> insertions;


    public User() {
    }

    public User(String name, String surname, String password, String email, String telnum, Collection<Role> roles, List<Insertion> insertions) {
        this.id_user=UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.telnum = telnum;
        this.roles = roles;
        this.insertions=insertions;
    }

    public String validateTelNum(String telnum){
        if(!telnum.equals("")){
            return telnum.trim();
        }
        return telnum;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
