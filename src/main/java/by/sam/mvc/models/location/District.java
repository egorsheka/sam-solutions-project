package by.sam.mvc.models.location;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.user.Cook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;




}
