package by.sam.mvc.models.location;

import by.sam.mvc.models.user.Cook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "town", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<District> districts = new ArrayList<>();

    public void addDistrict(District district) {
        districts.add(district);
        district.setTown(this);
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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return id == town.id &&
                Objects.equals(name, town.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
