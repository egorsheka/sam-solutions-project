package by.sam.mvc.repository.menu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
public class DbConfiguration {

    @Bean
    public Connection connection() throws Exception {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/db.properties"));

        //properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

        String URL = properties.getProperty("db.url");
        String login = properties.getProperty("db.login");
        String password = properties.getProperty("db.password");
        String driverClassName = properties.getProperty("db.driverClassName");

        Class.forName(driverClassName);
        return DriverManager.getConnection(URL, login, password);
    }

}