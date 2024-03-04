package entities;

public class  salleee {
    private int idsalle;  // Changer le nom de l'attribut id en idsalle
    private int iduser;   // Nouvel attribut iduser
    private String matiere;
    private String email;


    // Constructeurs, getters et setters

    public salleee() {
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Constructeur avec idsalle, iduser et matiere
    public salleee(int idsalle, int iduser, String matiere,String email) {
        this.idsalle = idsalle;
        this.iduser = iduser;
        this.matiere = matiere;
        this.email = email;

    }

    // Constructeur sans idsalle (utilisé lors de l'insertion en base de données)
    public salleee(int iduser, String matiere, String email) {
        this.iduser = iduser;
        this.matiere = matiere;
        this.email = email;

    }

    // Getters et setters

    // Getter and Setter for password




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
        // Customize the string representation of salleee object
        return
                "\tMatiere: " + getMatiere() +
                "\tID User: " + getIduser() +
                "\tEmail: " + getEmail() + "\n";
    }
}
