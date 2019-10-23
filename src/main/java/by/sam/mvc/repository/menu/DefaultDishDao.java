package by.sam.mvc.repository.menu;

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
    private static final String FIND_ALL_SQL = "SELECT * FROM dishes";
    //SQL
    private static final String FIND_DISH_SQL = "SELECT name, cuisine_id, dish_type FROM dishes WHERE id = ?";
    //SQL
    private static final String CREATE_DISH_SQL = "INSERT INTO dishes(id, name, cuisine_id, dish_type) VALUES (DEFAULT, ?, ?, ?)";
    //SQL
    private static final String UPDATE_DISH_SQL = "UPDATE  dishes SET name = ?, cuisine_id = ?, dish_type = ? WHERE id = ?";
    //SQL
    private static final String DELETE_DISH_SQL = "DELETE FROM dishes WHERE id = ?";
    //SQL
    private static final String GET_CUISINE_BY_ID_SQL = "SELECT country FROM cuisine WHERE id = ?";
    //SQL
    private static final String IS_EXIST_SQL = "SELECT id, cuisine_id FROM dishes WHERE name = ? AND dish_type = ?";
    //SQL
    //private static final String GET_LAST_ID = "SELECT MAX(id) FROM dishes";
    //SQL
    private static final String GET_ID_CUISINE_BY_STRING_SQL = "SELECT id FROM cuisine WHERE country = ?";




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
        } catch (SQLException e) {
            System.out.println("error in DefaultDishDao create");
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
                dish.setCuisine(getCuisineById(resultSet.getInt(2)));
                dish.setDishType(DishType.valueOf(resultSet.getString(3).toUpperCase()));
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println("error in DefaultDishDao read");
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
            System.out.println("error in DefaultDishDao update");


        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_DISH_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in DefaultDishDao delete");

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
                int cuisineId = resultset.getInt(3);
                String cuisineString = getCuisineById(cuisineId);
                DishType dishType = DishType.valueOf(resultset.getString(4).toUpperCase());
                //Long img;
                dishes.add(new Dish(id, name, cuisineString, dishType));
            }
        } catch (SQLException e) {
            System.out.println("error in DefaultDishDao findAll");

        }
        return dishes;
    }

    @Override
    public int isExist(Dish dish) {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(IS_EXIST_SQL)) {
            statement.setString(1, dish.getName());
            statement.setObject(2, dish.getDishType().toString().toUpperCase());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int cuisineID = resultSet.getInt(2);
                if (dish.getCuisine().equals(getCuisineById(cuisineID))) {
                    return id;
                }
            }
        } catch (SQLException e) {
            System.out.println("error in DefaultDishDao isExist");

        } finally {
            closeResultSet(resultSet);
        }
        return 0;
    }

    private String getCuisineById(int id) {
        String cuisine = "";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_CUISINE_BY_ID_SQL)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            cuisine = resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println("error in DefaultDishDao getCuisineById");

        } finally {
            closeResultSet(resultSet);
        }
        return cuisine;
    }

    private int getIdCuisineByString(String cuisine){
        int id = 0;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_ID_CUISINE_BY_STRING_SQL)) {
            statement.setString(1, cuisine);
            resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println("error in DefaultDishDao getIdCuisineByString");

        } finally {
            closeResultSet(resultSet);
        }
        return id;
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("error in DefaultDishDao closeResultSet");

            }
        }
    }

//    private int getLastTableId() {
//        try (PreparedStatement statement = connection.prepareStatement(GET_LAST_ID);
//             ResultSet resultSet = statement.executeQuery()) {
//            if (resultSet.next()) {
//                return resultSet.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }


}
