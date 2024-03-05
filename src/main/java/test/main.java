package test;

import entity.Remise;
import javafx.collections.ObservableList;
import services.ServciceRemise;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException, SQLException {
        ServciceRemise rs=new ServciceRemise();
        ObservableList<Remise> reponses = rs.afficher();
        System.out.println(reponses);
        //  System.out.println(rs.getAll());


    }
}
