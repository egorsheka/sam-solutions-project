package by.sam.mvc.models.user;

import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.menu.Menu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;
    private String surname;
    private String email;
    private String password;
    private String birthday;
    private String mobile;

    @ManyToOne
    private Town town;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<District> districts = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Menu> menu = new ArrayList<>();

    private String weekForm;
    private String status;

    public Cook(){}

    public Cook(String name, Town town) {
        this.name = name;
        this.town = town;
    }

    public Cook(int id, String name, Town town) {
        this.id = id;
        this.name = name;
        this.town = town;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }



    public String getWeekForm() {
        return weekForm;
    }

    public void setWeekForm(String weekForm) {
        this.weekForm = weekForm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
