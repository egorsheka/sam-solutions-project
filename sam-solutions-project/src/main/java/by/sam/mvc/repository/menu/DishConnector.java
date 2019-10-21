package by.sam.mvc.repository.menu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DishConnector {

    private static Connection connection;
    private static DishDao dishDao;
    private static final DishConnector connector = new DishConnector();


    private DishConnector(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader( "D:\\Sheka\\sam-solutions-project\\personal-cook\\src\\main\\resources\\db.properties"));
            //properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            String URL = properties.getProperty("db.url");
            String login = properties.getProperty("db.login");
            String password = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            Class.forName(driverClassName);
            connection = DriverManager.getConnection(URL, login, password);
            dishDao = new DishDaoImp(connection);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
    public static DishDao getDishDao() {
        return dishDao;
    }


}