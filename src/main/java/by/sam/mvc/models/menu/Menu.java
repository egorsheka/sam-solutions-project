package by.sam.mvc.models.menu;

import java.math.BigDecimal;
import java.util.List;

public class Menu{

    private int id;
    private String name;
    private BigDecimal price;
    private MenuLuxury luxury;
    private List<Dish> dishes;

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
