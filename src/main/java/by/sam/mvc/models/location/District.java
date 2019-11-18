package by.sam.mvc.models.location;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.user.Cook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private Town town;

    public District() {}

    public District(String name, Town town) {
        this.name = name;
        this.town = town;
    }

    public District(int id, String name, Town town) {
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

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
