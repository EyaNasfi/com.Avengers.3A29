package models;

import java.util.List;

public class quiz {
    int idquiz;

   String nom;
    int nbrquest;
    int ID_de_Formation;
    int iduser;

    public void setID_de_Formation(int ID_de_Formation) {
        this.ID_de_Formation = ID_de_Formation;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getID_de_Formation() {
        return ID_de_Formation;
    }

    public int getIduser() {
        return iduser;
    }

    //private List<Questions> questions;
    public quiz(String text) {
        this.nom=text;
    }

    public quiz(String t, int n) {
        this.nom = t;
        this.nbrquest=n;
    }

    public void setNbrquest(int nbrquest) {
        this.nbrquest = nbrquest;
    }

    public int getNbrquest() {
        return nbrquest;
    }

    public quiz() {

    }


    public int getIdquiz() {
        return idquiz;
    }

    public void setId(int id) {
        this.idquiz = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIdquiz(int idquiz) {
        this.idquiz = idquiz;
    }
}


