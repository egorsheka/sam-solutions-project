package by.sam.mvc.models.menu;


public class Dish {
    private int id;

    private String name;
    private String cuisine;
    private DishType dishType;
    private Long img;

    public Dish(){}

    public Dish(String name, String cuisine, DishType dishType) {
        this.name = name;
        this.cuisine = cuisine;
        this.dishType = dishType;
    }

    public Dish(int id, String name, String cuisine, DishType dishType) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.dishType = dishType;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }
    public String getCuisine() {
        return cuisine;
    }
    public DishType getDishType() {
        return dishType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public void setImg(Long img) {
        this.img = img;
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
