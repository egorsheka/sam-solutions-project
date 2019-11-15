package by.sam.mvc.models.menu;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "dish_type")
    private DishType dishType;

    @ManyToOne
    private Cuisine cuisine;


    @Transient
    private Long img;

    public Dish(){}

    public Dish(String name, DishType dishType, Cuisine cuisine) {
        this.name = name;
        this.dishType = dishType;
        this.cuisine = cuisine;
        this.img = img;
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

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Long getImg() {
        return img;
    }

    public void setImg(Long img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id &&
                Objects.equals(name, dish.name) &&
                dishType == dish.dishType &&
                Objects.equals(cuisine, dish.cuisine) &&
                Objects.equals(img, dish.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishType, cuisine);
    }
}
