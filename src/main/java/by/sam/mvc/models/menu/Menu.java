package by.sam.mvc.models.menu;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menu2")
public class Menu{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "menu_id")
    private int id;


    private String name;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "luxury_type")
    private MenuLuxury luxury;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "menu_dishes",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
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
