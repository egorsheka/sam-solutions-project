package by.sam.mvc.models.location;

import by.sam.mvc.models.user.Cook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    @OneToMany(mappedBy = "town", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Cook> cooks = new ArrayList<>();

    public void addCook(Cook cook) {
        cooks.add(cook);
        cook.setTown(this);
    }


    public Town() {}

    public Town(String name) {
        this.name = name;
    }

    public Town(int id, String name) {
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

    public List<Cook> getCooks() {
        return cooks;
    }

    public void setCooks(List<Cook> cooks) {
        this.cooks = cooks;
    }
}
