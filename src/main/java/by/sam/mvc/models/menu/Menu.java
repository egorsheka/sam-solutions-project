package by.sam.mvc.models.menu;

import java.math.BigDecimal;
import java.util.List;

public class Menu {

    private int id;
    private String name;
    private Dish appetiser;
    private Dish main_course;
    private Dish desert;

    private List<Dish> dishes;
    private BigDecimal price;
    private MenuLuxury luxury;


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
}
