package by.sam.mvc.repository.menu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
public class DbConfiguration {

    @Bean
    public Connection connection(
            @Value("${db.url}") String url,
            @Value("${db.login}") String login,
            @Value("${db.password}") String password,
            @Value("${db.driverClassName}") String driverClassName
    ) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, login, password);
    }
}