package by.sam.mvc.models.menu;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name = "dish_id", insertable=false, updatable=false)
    private int id;

    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cuisine_id" , insertable=false, updatable=false)
    private Cuisine cuisine;

    @Enumerated(EnumType.STRING)
    @Column(name = "dish_type")
    private DishType dishType;

//    @ManyToMany(mappedBy = "dishes")
//    private List<Menu> menu;



    @Transient
    private Long img;

    public Dish(){}

    public Dish(String name, Cuisine cuisine, DishType dishType, List<Menu> menu, Long img) {
        this.name = name;
        this.cuisine = cuisine;
        this.dishType = dishType;
        //this.menu = menu;
        this.img = img;
    }

    public Dish(String name, Cuisine cuisine, DishType dishType) {
        this.name = name;
        this.cuisine = cuisine;
        this.dishType = dishType;
        //this.menu = menu;
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

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

//    public List<Menu> getMenu() {
//        return menu;
//    }
//
//    public void setMenu(List<Menu> menu) {
//        this.menu = menu;
//    }

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
                Objects.equals(cuisine, dish.cuisine) &&
                dishType == dish.dishType &&

                Objects.equals(img, dish.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cuisine, dishType, img);
    }




}
