package by.sam.mvc.repository.menu;

import by.sam.mvc.models.menu.Menu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
@PropertySource("classpath:db.properties")
public class DefaultMenuDao implements MenuDao {
    //SQL
    private static final String CREATE_MENU_SQL = "INSERT INTO menu(id, name, luxury, price, appetiser_id, main_course_id, dessert_id) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

    //SQL
    private static final String DELETE_MENU_SQL = "DELETE FROM menu WHERE id = ?";


    private Connection connection;

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
//        try (PreparedStatement statement = connection.prepareStatement(CREATE_MENU_SQL)) {
//          //  private static final String CREATE_MENU_SQL = "INSERT INTO menu(id, name, luxury, price, appetiser_id, main_course_id, dessert_id) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
//
//            statement.setString(1, menu.getName());
//            statement.setObject(2, menu.getLuxury());
//            statement.setDouble(3, menu.getPrice().doubleValue());
//            statement.setInt(4, menu);
//
//        } catch (SQLException e) {
//            System.out.println("error in DefaultDishDao create");
//        }
    }

    @Override
    public Menu read(int id) {
        return null;
    }

    @Override
    public void update(Menu object) {

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MENU_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in DefaultDishDao delete");

        }
    }


    @Override
    public List<Menu> findAll() {
        return null;
    }
}
