package entities;

public class equipement {
    private int idequip;  // Changer le nom de l'attribut id en idsalle
    private int idsalle;   // Nouvel attribut iduser
    private String categorie;

    public equipement(int idequip, int idsalle, String categorie) {
        this.idequip = idequip;
        this.idsalle = idsalle;
        this.categorie = categorie;
    }
    public equipement( int idsalle, String categorie) {

        this.idsalle = idsalle;
        this.categorie = categorie;
    }

    public equipement() {

    }

    public int getIdequip() {
        return idequip;
    }

    public int getIdsalle() {
        return idsalle;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setIdequip(int idequip) {
        this.idequip = idequip;
    }

    public void setIdsalle(int idsalle) {
        this.idsalle = idsalle;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "equipement{" +
                "idequip=" + idequip +
                ", idsalle=" + idsalle +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
