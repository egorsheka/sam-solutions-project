package by.sam.mvc.models;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.MenuLuxury;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menu2")
public class Menu{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    private BigDecimal price;
    private MenuLuxury luxury;
    @Transient
    private List<Dish> dishes;



    private boolean isDishShow;

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

    public boolean isDishShow() {
        return isDishShow;
    }

    public void setDishShow(boolean dishShow) {
        isDishShow = dishShow;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", luxury=" + luxury +
                ", dishes=" + dishes +
                '}';
    }
}
