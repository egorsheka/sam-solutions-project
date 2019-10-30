package by.sam.mvc.models.wrappers;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.menu.MenuLuxury;

import java.math.BigDecimal;
import java.util.List;

public class HtmlMenuWrapper {




    private boolean isDishesShow;
    private int id;
    private String name;
    private BigDecimal price;
    private MenuLuxury luxury;
    private List<Dish> dishes;


    public HtmlMenuWrapper(){}

    public HtmlMenuWrapper(int id, String name, BigDecimal price, MenuLuxury luxury, List<Dish> dishes, boolean isDishesShow) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.luxury = luxury;
        this.dishes = dishes;
        this.isDishesShow = isDishesShow;
    }

    public HtmlMenuWrapper(Menu menu){
        this.setId(menu.getId());
        this.setName(menu.getName());
        this.setLuxury(menu.getLuxury());
        this.setPrice(menu.getPrice());
        this.setDishes(menu.getDishes());
    }


    public boolean getDishesShow() {
        return isDishesShow;
    }

    public void setDishesShow(boolean dishesShow) {
        isDishesShow = dishesShow;
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

    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public MenuLuxury getLuxury() {
        return luxury;
    }


    public void setLuxury(MenuLuxury luxury) {
        this.luxury = luxury;
    }


    public List<Dish> getDishes() {
        return dishes;
    }


    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "HtmlMenuWrapper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", luxury=" + luxury +
                ", dishes=" + dishes +
                ", isDishesShow=" + isDishesShow +
                '}';
    }
}
