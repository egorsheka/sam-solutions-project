package by.sam.mvc.repository.menu.dish;

import by.sam.mvc.exceptions.dao.menu.DefaultDishDaoException;
import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.DishType;
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
    private static final String FIND_ALL_SQL = "SELECT dishes.id, name, country, dish_type FROM dishes INNER JOIN cuisine c on dishes.cuisine_id = c.id";
    //SQL
    private static final String FIND_DISH_SQL = "SELECT name, country,dish_type FROM dishes INNER JOIN cuisine c on dishes.cuisine_id = c.id WHERE dishes.id = ?";
    //SQL
    private static final String CREATE_DISH_SQL = "INSERT INTO dishes(id, name, cuisine_id, dish_type) VALUES (DEFAULT, ?, ?, ?)";
    //SQL
    private static final String UPDATE_DISH_SQL = "UPDATE  dishes SET name = ?, cuisine_id = ?, dish_type = ? WHERE id = ?";
    //SQL
    private static final String DELETE_DISH_SQL = "DELETE FROM dishes WHERE id = ?";
    //SQL
    private static final String IS_EXIST_SQL = "SELECT dishes.id FROM dishes INNER JOIN cuisine c on dishes.cuisine_id = c.id WHERE name = ? AND country = ?  AND dish_type = ?";
    //SQL
    private static final String GET_ID_CUISINE_BY_STRING_SQL = "SELECT id FROM cuisine WHERE country = ?";
    //SQL
    private static final String CREATE_CUISINE_SQL = "INSERT INTO cuisine(id, country) values(DEFAULT, ?)";
    //SQL
    private static final String GET_LAST_ID = "SELECT MAX(id) FROM dishes";



    private Connection connection;


    public DefaultDishDao(
            @Value("${db.url}") String url,
            @Value("${db.login}") String login,
            @Value("${db.password}") String password,
            @Value("${db.driverClassName}") String driverClassName
    ) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(url, login, password);
    }

    //TODO
    //класс CuisineDao со всем CRUD create
    @Override
    public void create(Dish object) {
        int id = isExist(object);
        if (id != 0) {
            object.setId(id);
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(CREATE_DISH_SQL)) {
            statement.setString(1, object.getName());
            int id_cuisine = getIdCuisineByString(object.getCuisine());
            if(id_cuisine != 0){
                statement.setInt(2, id_cuisine);
            }
            statement.setString(3, object.getDishType().toString().toUpperCase());
            statement.executeUpdate();
            object.setId(getLastTableId());
        } catch (SQLException e) {
            throw new DefaultDishDaoException("SQLException in method create.");
        }


    }

    @Override
    public Dish read(int id) {
        Dish dish = new Dish();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_DISH_SQL)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                dish.setId(id);
                dish.setName(resultSet.getString(1));
                dish.setCuisine(resultSet.getString(2));
                dish.setDishType(DishType.valueOf(resultSet.getString(3).toUpperCase()));
            }
        } catch (SQLException e) {
            throw new DefaultDishDaoException("SQLException in method read.");
        }finally {
            closeResultSet(resultSet);
        }
        return dish;
    }

    // TODO
    //класс CuisineDao со всем CRUD update
    @Override
    public void update(Dish object) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_DISH_SQL)) {
            statement.setString(1, object.getName());
            int id_cuisine = getIdCuisineByString(object.getCuisine());
            if(id_cuisine != 0){
                statement.setInt(2, id_cuisine);
            }
            statement.setString(3, object.getDishType().toString().toUpperCase());
            statement.setInt(4, object.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DefaultDishDaoException("SQLException in method update.");

        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_DISH_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DefaultDishDaoException("SQLException in method delete.");
        }
    }

    @Override
    public List<Dish> findAll() {
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultset = statement.executeQuery()) {
            while (resultset.next()) {
                int id = resultset.getInt(1);
                String name = resultset.getString(2);
                String cuisineString = resultset.getString(3);
                DishType dishType = DishType.valueOf(resultset.getString(4).toUpperCase());
                //Long img;
                dishes.add(new Dish(id, name, cuisineString, dishType));
            }
        } catch (SQLException e) {
            throw new DefaultDishDaoException("SQLException in method findAll.");
        }
        return dishes;
    }

    @Override
    public int isExist(Dish dish) {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(IS_EXIST_SQL)) {
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getCuisine());
            statement.setObject(3, dish.getDishType().toString().toUpperCase());
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DefaultDishDaoException("SQLException in method isExist.");
        } finally {
            closeResultSet(resultSet);
        }
        return 0;
    }

    private int getIdCuisineByString(String cuisine){
        int id = 0;
        ResultSet resultSetGet = null;
        try (PreparedStatement statementGet = connection.prepareStatement(GET_ID_CUISINE_BY_STRING_SQL);
             PreparedStatement statementCreate = connection.prepareStatement(CREATE_CUISINE_SQL)) {
            statementGet.setString(1, cuisine);
            resultSetGet = statementGet.executeQuery();
            if(resultSetGet.next()) {
                id = resultSetGet.getInt(1);
            }else{
                statementCreate.setString(1,cuisine);
                statementCreate.executeUpdate();
                id = getIdCuisineByString(cuisine);
            }
        } catch (SQLException e) {
            throw new DefaultDishDaoException("SQLException in method getIdCuisineByString.");
        } finally {
            closeResultSet(resultSetGet);
        }
        return id;
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DefaultDishDaoException("SQLException in method closeResultSet.");
            }
        }
    }

        private int getLastTableId() {
        try (PreparedStatement statement = connection.prepareStatement(GET_LAST_ID);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new DefaultDishDaoException("SQLException in method getLastTableId");
        }
        return 0;
    }

}
