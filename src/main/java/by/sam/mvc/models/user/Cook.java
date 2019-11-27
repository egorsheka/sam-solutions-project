package by.sam.mvc.models.user;

import by.sam.mvc.models.WeekDay;
import by.sam.mvc.models.WorkTime;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.menu.Menu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cooks")
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


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<District> districts = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Menu> menu = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    private List<WorkTime> workTime = new ArrayList<>(7);

    private String status;

    public Cook(){}

    public Cook(String name, List<District> districts) {
        this.name = name;
        this.districts = districts;
    }

    public Cook(int id, String name, List<District> districts) {
        this.id = id;
        this.name = name;
        this.districts = districts;
    }

    public Cook(int id, String name) {
        this.id = id;
        this.name = name;
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

    public List<WorkTime> getWorkTime() {return workTime; }

    public void setWorkTime(List<WorkTime> workTime) { this.workTime = workTime; }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
}
