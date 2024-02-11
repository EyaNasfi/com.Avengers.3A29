package tn.esprit.crud.test;

import tn.esprit.crud.models.User;
import tn.esprit.crud.services.UserService;
import tn.esprit.crud.utils.MyDatabase;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService us = new UserService();



        //AJOUTER
        try{
            //us.ajouter(new User("Azer", "Amor", "Tounes" , "azber@gmail.com" , "12dasf"));
            System.out.println(us.recupperer());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }





        ///MODIFIER
/*        try {
            us.modifier(new User(2,"Sofien", "Abidi", "KSA", "sofabidi@gmail.com",
            "testtss12"));
        } catch (SQLException e) {
System.err.println(e.getMessage());
        }
*/



        ////SUPRIMER
  /*        try {
            us.supprimer(2);
            System.out.println("ID supprimer avec succes");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
*/





        }
    }


