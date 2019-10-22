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
    private static final String FIND_ALL_SQL = "SELECT * FROM DISHES";

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
    public List<Dish> findAll() {
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultset = preparedStatement.executeQuery()) {
            while (resultset.next()) {
                int id = resultset.getInt(1);
                String name = resultset.getString(2);
                String cuisine = resultset.getString(3);
                DishType dishType = DishType.valueOf(resultset.getString(4).toUpperCase());
                //Long img;
                dishes.add(new Dish(id, name, cuisine, dishType));
            }
        } catch (SQLException e) {
            //Log
        }
        return dishes;
    }

}
