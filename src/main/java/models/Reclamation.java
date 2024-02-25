package models;

public class Reclamation {
    private int id;
    private String titre;
    private String description;
    public Reclamation(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    public Reclamation(String titre, String description) {
        this.titre=titre;
        this.description=description;
    }

    public int getId() {
        return id;
    }
    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }
    public void setId(int id) {
        this.id = id;}
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setDescription(String des) {
        this.description = des;
    }
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    public Reclamation(){}
    }