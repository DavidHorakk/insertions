package cz.fim.uhk.insertions.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @Column(name="role_column")
    private int role;

    public User() {
    }

    public User(String name, String surname, String password, String email, String telnum, int role) {
        this.id_user=UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.telnum = telnum;
        this.role = role;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
