package test;

import entities.salleee;
import services.ServiceSalle2;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
/* MyDB db1 =  MyDB.getInstance();
        MyDB db2 =  MyDB.getInstance();

        System.out.println(db1.hashCode());
        System.out.println(db2.hashCode()); */
        salleee s1 = new salleee(1,5,"samir");
        salleee s2 = new salleee(6,"peseron");
        ServiceSalle2 services = new ServiceSalle2();

       /* try {
            services.ajouter(s1);
           // services.ajouter(s2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

        try {
            System.out.println(services.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            services.modifier(s1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
