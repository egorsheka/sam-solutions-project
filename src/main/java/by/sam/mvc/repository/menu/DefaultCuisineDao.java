package by.sam.mvc.repository.menu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@PropertySource("classpath:db.properties")
public class DefaultCuisineDao {

    private Connection connection;


    DefaultCuisineDao(
    @Value("${db.url}") String url,
    @Value("${db.login}") String login,
    @Value("${db.password}") String password,
    @Value("${db.driverClassName}") String driverClassName
    ) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        connection =  DriverManager.getConnection(url, login, password);
    }





}
