package by.sam.mvc.repository.menu;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.DishType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:db.properties")
public class DefaultDishDao implements DishDao {

    //SQL
    private static final String FIND_ALL_SQL = "SELECT * FROM dishes";

    //SQL
    private static final String CREATE_DISH_SQL = "INSERT INTO dishes(id, name, cuisine, dish_type) VALUES (DEFAULT, ?, ?, ?)";
    private static final String CREATE_DISH_SQL = "INSERT INTO dishes(id, name, cuisine, dish_type) VALUES (DEFAULT, ?, ?, ?)";

    private Connection connection;


    public DefaultDishDao(
        @Value("${db.url}") String url,
        @Value("${db.login}") String login,
        @Value("${db.password}") String password,
        @Value("${db.driverClassName}") String driverClassName
    ) throws ClassNotFoundException, SQLException {
            Class.forName(driverClassName);
            connection =  DriverManager.getConnection(url, login, password);
    }

    @Override
    public void create(Dish object) {
        //try(PreparedStatement statement = connection.prepareStatement(CREATE_DISH_SQL))
    }

    @Override
    public Dish read(int id) {
        return null;
    }

    @Override
    public Dish update(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Dish> findAll() {
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultset = statement.executeQuery()) {
            while (resultset.next()) {
                int id = resultset.getInt(1);
                String name = resultset.getString(2);
                int cuisine_id = resultset.getInt(3);
                DishType dishType = DishType.valueOf(resultset.getString(4).toUpperCase());
                //Long img;
                dishes.add(new Dish(id, name, cuisine, dishType));
                System.out.println();
            }
        } catch (SQLException e) {
            //Log
        }
        return dishes;
    }

    private String getCuisineById(int id){

    }

}
