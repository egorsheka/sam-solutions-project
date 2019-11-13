package by.sam.mvc.models.menu;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "cuisine")
public class Cuisine {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cuisine_id")
    private int id;
    private String name;

    @OneToMany(mappedBy = "cuisine", fetch = FetchType.EAGER)
    private List<Dish> dishes;


    public Cuisine() {

    }

    public Cuisine(String name, List<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    public Cuisine(String name) {
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuisine cuisine = (Cuisine) o;
        return id == cuisine.id &&
                Objects.equals(name, cuisine.name) &&
                Objects.equals(dishes, cuisine.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishes);
    }
}
