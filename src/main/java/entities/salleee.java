package entities;

public class salleee {
    private int idsalle;  // Changer le nom de l'attribut id en idsalle
    private int iduser;   // Nouvel attribut iduser
    private String matiere;

    // Constructeurs, getters et setters

    public salleee() {
    }

    // Constructeur avec idsalle, iduser et matiere
    public salleee(int idsalle, int iduser, String matiere) {
        this.idsalle = idsalle;
        this.iduser = iduser;
        this.matiere = matiere;
    }

    // Constructeur sans idsalle (utilisé lors de l'insertion en base de données)
    public salleee(int iduser, String matiere) {
        this.iduser = iduser;
        this.matiere = matiere;
    }

    // Getters et setters

    public int getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(int idsalle) {
        this.idsalle = idsalle;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    // Reste du code inchangé

    @Override
    public String toString() {
        return "salle{" +
                "idsalle=" + idsalle +
                ", iduser=" + iduser +
                ", matiere='" + matiere + '\'' +
                '}';
    }
}
