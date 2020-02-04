package by.sam.mvc.entity.menu;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "menus")
public class Menu{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotEmpty
    private String name;
    @NotNull
    @Digits(integer=3, fraction=2)
    private BigDecimal price;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();


    public Menu() {}

    public Menu(String name, BigDecimal price, List<Dish> dishes) {
        this.name = name;
        this.price = price;
        this.dishes = dishes;
    }

    public Menu(int id, String name, BigDecimal price, List<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.price = price;

        this.dishes = dishes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id &&
                Objects.equals(name, menu.name) &&
                Objects.equals(price, menu.price) &&
                Objects.equals(dishes, menu.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, dishes);
    }


}
