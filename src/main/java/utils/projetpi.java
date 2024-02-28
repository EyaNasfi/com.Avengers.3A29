package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class projetpi {

    private final String URL = "jdbc:mysql://localhost:3306/projetpi";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection connection;
    private static projetpi instance;

    private projetpi() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static projetpi getInstance() {
        if(instance == null)
            instance = new projetpi();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
