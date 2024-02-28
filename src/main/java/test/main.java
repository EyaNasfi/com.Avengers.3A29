package test;
import Controllers.ajouterreclamation;
import Services.questionservice;
import Services.quizservice;
import Services.reponseservice;
import javafx.collections.ObservableList;
import models.Reclamation;
import models.Reponses;
import models.quiz;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        reponseservice rs=new reponseservice();
        ObservableList<Reponses> reponses = rs.getAll();
        for (Reponses r : reponses) {
            System.out.println("ID: " + r.getIdrep());
            System.out.println("Contenu: " + r.getContenu());
            System.out.println("Titre: " + r.getTitre());
            System.out.println("Description: " + r.getDescription());
            System.out.println("ID Reclamation: " + r.getIdrec());
            System.out.println();
        }
      //  System.out.println(rs.getAll());


    }
}
