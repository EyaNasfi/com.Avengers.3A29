package test;

import model.categorie;
import model.cours;
import model.formation;
import services.serviceFormation;
import services.servicecategorie;
import services.servicecours;
import util.MyDB;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {


        MyDB db1 = new MyDB();
        MyDB db2 = new MyDB();


        // Date de début spécifique
        Calendar calDebut = Calendar.getInstance();
        calDebut.set(2022, Calendar.JANUARY, 1); // Année, Mois (indexé à partir de 0), Jour
        Date Date_Deb = calDebut.getTime();

        // Date de fin spécifique
        Calendar calFin = Calendar.getInstance();
        calFin.set(2023, Calendar.DECEMBER, 31); // Année, Mois (indexé à partir de 0), Jour
        Date Date_Fin = calFin.getTime();

        Connection connection = null; // Initialize your connection object here

        // Read the PDF file and convert it to a byte array for the course description
        byte[] descriptionBytes = null;
        try {
            File pdfFile = new File("C:/Users/HP/Downloads/poly2013.pdf"); // Update with the actual path to your PDF file
            descriptionBytes = Files.readAllBytes(pdfFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit the method if an error occurs
        }

        // Create a new course instance
        cours cr = new cours(3, "7assen", descriptionBytes, 9);
        servicecours serviceC = new servicecours();

        // Call the method to add the course to the database
      // try {
          // serviceC.ajouter(cr);
           // System.out.println("Course added successfully!");
         //  } catch (SQLException e) {
          // System.out.println("Error adding course: " + e.getMessage());
     //  }
        // Modify the course in the database
       // try {
          //  serviceC.modifier(cr);
           // System.out.println("Course modified successfully!");
       // } catch (SQLException e) {
           // System.out.println("Error modifying course: " + e.getMessage());
      //  }
        // supprimer un cour
      //  try {
           // serviceC.supprimer(cr);
          //  } catch (SQLException e) {
         //  System.out.println(e.getMessage());
         //  }
       // try {
         //  List<cours> coursList = serviceC.afficher();
         // System.out.println("Cours list:");
          // for (cours cours : coursList) {
              // System.out.println(cours);
          // }
     // } catch (SQLException e) {
          // System.out.println("Error retrieving courses: " + e.getMessage());
     // }

        categorie c1 = new categorie(7, "c++");
       // categorie c2 = new categorie(5, "react");
       // categorie c3 = new categorie(8, ".NET");
       servicecategorie services = new servicecategorie();
        //ajouter une categorie
          try {
           services.ajouter(c1);

          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }
        //modifier une categorie
      //  try {
          //  services.modifier(c1);
      //  } catch (SQLException e) {
          //  System.out.println(e.getMessage());
       // }
        //suprimer une categorie
        //try {
          //  services.supprimer(c1);
           //  } catch (SQLException e) {
          //  System.out.println(e.getMessage());
           // }


        //Affichage categorie

       //  try {
       //  System.out.println(services.afficher());
      // } catch (SQLException e) {
        // System.out.println(e.getMessage());
//}

      //  serviceFormation services = new serviceFormation();
      //  formation f1 = new formation(10, "java", "javaFX", "xxxxx", "2moins", "2eme", Date_Deb, Date_Fin, "9000DT");
      //  formation f2 = new formation(5, "symfony", "dev", "uyyyyy", "2moins", "2eme", Date_Deb, Date_Fin, "6000DT");

//ajouter une formation
        // try {
        //    services.ajouter(f2);

        //  } catch (SQLException e) {
        //  System.out.println(e.getMessage());
        // }

//modifier une formation

        //   try {
        //   services.modifier(f1);
        //  } catch (SQLException e) {
        //  System.out.println(e.getMessage());
        //  }

        // supprimer une formation
       // try {
          //  services.supprimer(f1);
          //  System.out.println("Formation supprimée avec succès.");
        //} catch (SQLException e) {
         //   System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }

      //  try {
         //   System.out.println(services.afficher());
       // } catch C
       // }


  }








