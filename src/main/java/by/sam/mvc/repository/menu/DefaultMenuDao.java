package by.sam.mvc.repository.menu;

import by.sam.mvc.exceptions.dao.menu.DefaultDishDaoException;
import by.sam.mvc.exceptions.dao.menu.DefaultMenuDaoException;
import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.menu.MenuLuxury;
import by.sam.mvc.repository.menu.dish.DishDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
@PropertySource("classpath:db.properties")
public class DefaultMenuDao implements MenuDao {

    Logger logger = LoggerFactory.getLogger(DefaultMenuDao.class);


    //SQL
    private static final String CREATE_MENU_SQL = "INSERT INTO menu(id, name, luxury, price) VALUES (DEFAULT, ?, ?, ?)";
    //SQL
    private static final String INSERT_IN_MENU_DISHES = "INSERT INTO menu_dishes (menu_id, dish_id) VALUES (?, ?);";
    //SQL
    private static final String READ_MENU_SQL = "SELECT name, luxury, price FROM menu WHERE id = ?;";
    //SQL
    private static final String READ_MENU_DISHES_SQL = "SELECT dishes.id FROM menu INNER JOIN menu_dishes md on md.menu_id = menu.id INNER JOIN dishes  on dishes.id = md.dish_id WHERE menu.id = ?;";
    //SQL
    private static final String UPDATE_MENU_SQL = "UPDATE menu SET name = ?, luxury = ?, price = ? WHERE id = ?";
    //SQL
    private static final String UPDATE_MENU_DISHES = "UPDATE menu_dishes SET  dish_id = ? WHERE menu_id = ? AND dish_id = ?;";
    //SQL
    private static final String ACCESSORY_SELECT_FOR_UPDATE_MENU_DISHES = "SELECT dish_id FROM menu_dishes WHERE menu_id = ?;";
    //SQL
    private static final String DELETE_MENU_SQL = "DELETE FROM menu WHERE id = ?";
    //SQL
    private static final String DELETE_IN_MENU_DISHES = "DELETE FROM menu_dishes WHERE menu_id = ?";
    //SQL
    private static final String GET_LAST_ID = "SELECT MAX(id) FROM menu";
    //SQL
    private static final String FIND_ALL_SQL = "SELECT * FROM menu";



    private Connection connection;

    private DishDao dishDao;

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public DefaultMenuDao(
            @Value("${db.url}") String url,
            @Value("${db.login}") String login,
            @Value("${db.password}") String password,
            @Value("${db.driverClassName}") String driverClassName
    ) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(url, login, password);
    }

    @Override
    public void create(Menu menu) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_MENU_SQL)) {
            statement.setString(1, menu.getName());
            statement.setString(2, menu.getLuxury().toString().toUpperCase());
            statement.setDouble(3, menu.getPrice().doubleValue());
            statement.executeUpdate();
            menu.setId(getLastTableId());

            createDishes(menu);


            throw new SQLException();

        } catch (SQLException e) {
            logger.error("SQLException in method create", e);
            throw new DefaultMenuDaoException("SQLException in method create");
        }
    }


    @Override
    public Menu read(int id) {
        Menu menu = new Menu();
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(READ_MENU_SQL)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                menu.setId(id);
                menu.setName(resultSet.getString(1));
                menu.setLuxury(MenuLuxury.valueOf(resultSet.getString(2).toUpperCase()));
                menu.setPrice(resultSet.getBigDecimal(3));
            }

            menu.setDishes(getDishes(id));
            menu.setId(id);

        } catch (SQLException e) {
            throw new DefaultMenuDaoException("SQLException in method read");
        } finally {
            closeResultSet(resultSet);
        }
        return menu;
    }

    @Override
    public void update(Menu menu) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_MENU_SQL)) {

            statement.setString(1, menu.getName());
            statement.setString(2, menu.getLuxury().toString().toUpperCase());
            statement.setDouble(3, menu.getPrice().doubleValue());
            statement.setInt(4, menu.getId());
            statement.executeUpdate();


            List<Dish> dishes = getDishes(menu.getId());
            Collections.sort(dishes, Comparator.comparing(Dish::getName));

            Collections.sort(menu.getDishes(), Comparator.comparing(Dish::getName));

            if(!dishes.equals(menu.getDishes())){
                deleteDishes(menu.getId());
                createDishes(menu);
            }

        } catch (SQLException e) {
            throw new DefaultMenuDaoException("SQLException in method update");
        }
    }

    @Override
    public void delete(int id) {
        deleteDishes(id);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MENU_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DefaultMenuDaoException("SQLException in method delete");

        }
    }


    @Override
    public List<Menu> findAll() {
        List<Menu> menu = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                MenuLuxury menuLuxury = MenuLuxury.valueOf(resultSet.getString(3).toUpperCase());
                BigDecimal bigDecimal = resultSet.getBigDecimal(4);
                List<Dish> dishes = getDishes(id);

                menu.add(new Menu(id, name, bigDecimal, menuLuxury, dishes));
            }
        } catch (SQLException e) {
            throw new DefaultMenuDaoException("SQLException in method findAll");
        }
        return menu;
    }

    private int getLastTableId() {
        try (PreparedStatement statement = connection.prepareStatement(GET_LAST_ID);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new DefaultMenuDaoException("SQLException in method getLastTableId");
        }
        return 0;
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


    private List<Dish> getDishes(int id) {
        ResultSet resultSetMenuDish = null;
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statementMenuDish = connection.prepareStatement(READ_MENU_DISHES_SQL)) {

            statementMenuDish.setInt(1, id);
            resultSetMenuDish = statementMenuDish.executeQuery();

            while (resultSetMenuDish.next()) {
                dishes.add(dishDao.read(resultSetMenuDish.getInt(1)));
            }

        } catch (SQLException e) {
            throw new DefaultMenuDaoException("SQLException in method getDishes");
        }finally {
            closeResultSet(resultSetMenuDish);
        }
        return dishes;
    }

    private List<Dish> deleteDishes(int id) {
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statementMenuDish = connection.prepareStatement(DELETE_IN_MENU_DISHES)) {

            statementMenuDish.setInt(1, id);
            statementMenuDish.executeUpdate();
        } catch (SQLException e) {
            throw new DefaultMenuDaoException("SQLException in method deleteDishes");
        }
        return dishes;
    }

    private List<Dish> createDishes(Menu menu) {
        List<Dish> dishes = null;
        try (PreparedStatement statementMenuDish = connection.prepareStatement(INSERT_IN_MENU_DISHES)) {
            dishes = menu.getDishes();

            for (Dish dish : dishes) {
                if (dish.getId() == 0) {
                    dishDao.create(dish);
                }else{
                    dishDao.update(dish);
                }

                statementMenuDish.setInt(1, menu.getId());
                statementMenuDish.setInt(2, dish.getId());

                statementMenuDish.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DefaultMenuDaoException("SQLException in method createDishes");
        }
        return dishes;
    }

}
