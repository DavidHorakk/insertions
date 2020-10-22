package cz.fim.uhk.insertions.web.dto;

public class UserRegistrationDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String telnum;

    public UserRegistrationDto(){

    }

    public UserRegistrationDto(String name, String surname, String password, String email,String telnum) {
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.telnum = telnum;

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
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTelnum() {
        return telnum;
    }
    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }
}
