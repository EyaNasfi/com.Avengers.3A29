package models;

public class Reponses {
    private  int idrep;
    private String texte;
    private boolean correcte;

    public int getIdrep() {
        return idrep;
    }

    public Reponses(String texte, boolean correcte) {
        this.texte = texte;
        this.correcte = correcte;
    }

    public String getTexte() {
        return texte;
    }

    public boolean estCorrecte() {
        return correcte;
    }
}
