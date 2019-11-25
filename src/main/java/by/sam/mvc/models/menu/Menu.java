package by.sam.mvc.models.menu;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "menus")
public class Menu{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "luxury_type")
    private MenuLuxury luxury;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Dish> dishes = new ArrayList<>();


    public Menu() {}

    public Menu(String name, BigDecimal price, MenuLuxury luxury, List<Dish> dishes) {
        this.name = name;
        this.price = price;
        this.luxury = luxury;
        this.dishes = dishes;
    }

    public Menu(int id, String name, BigDecimal price, MenuLuxury luxury, List<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.luxury = luxury;
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

    public MenuLuxury getLuxury() {
        return luxury;
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

    public void setLuxury(MenuLuxury luxury) {
        this.luxury = luxury;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id &&
                Objects.equals(name, menu.name) &&
                Objects.equals(price, menu.price) &&
                luxury == menu.luxury &&
                Objects.equals(dishes, menu.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, luxury, dishes);
    }


}
