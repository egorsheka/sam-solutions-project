package by.sam.mvc.models.menu;

import by.sam.mvc.models.menu.DishType;

public class Dish {
    private int id;

    private String name;
    private String cuisine;
    private DishType dishType;
    private Long img;

    public Dish(int id, String name, String cuisine, DishType dishType) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.dishType = dishType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public DishType getDishType() {
        return dishType;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", dishType=" + dishType +
                '}';
    }
}
