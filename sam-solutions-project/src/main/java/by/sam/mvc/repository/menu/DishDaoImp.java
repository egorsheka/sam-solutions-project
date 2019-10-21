package by.sam.mvc.repository.menu;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.DishType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDaoImp implements DishDao {

    //SQL
    private final String FIND_ALL_SQL = "SELECT * FROM DISHES";

    private final static DishDao dishDao = DishConnector.getDishDao();
    private Connection connection = DishConnector.getConnection();

    public DishDaoImp(Connection connection) {
        this.connection = connection;
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
            e.printStackTrace();
        }
        return dishes;
    }

}
