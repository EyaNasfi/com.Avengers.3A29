package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mydb {
    final String URL = "jdbc:mysql://localhost:3306/pi_avengers";
    final String USER = "root";
    final String PWD="";
    private static  mydb instance;
    Connection cnx;
    private mydb(){
        try {
            cnx= DriverManager.getConnection(URL,USER,PWD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static mydb getInstance(){
        if (instance==null){
            instance =new mydb();
        }
        return instance;
    }

    public Connection getCnx(){
        return  cnx;
    }
}
