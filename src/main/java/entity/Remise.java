package entity;

public class Remise {
    int idremise;
    int idpaiement;
    int iduser;
    int pourcentage;
    int montantaprespourcentage;

    public Remise(int idremise, int idpaiement, int iduser, int pourcentage, int montantaprespourcentage) {
        this.idremise = idremise;
        this.idpaiement = idpaiement;
        this.iduser = iduser;
        this.pourcentage = pourcentage;
        this.montantaprespourcentage = montantaprespourcentage;
    }
    public Remise(int idpaiement, int iduser, int pourcentage, int montantaprespourcentage) {
        this.idpaiement = idpaiement;
        this.iduser = iduser;
        this.pourcentage = pourcentage;
        this.montantaprespourcentage = montantaprespourcentage;
    }

    public Remise(int i, int i1) {
        this.pourcentage = i;
        this.montantaprespourcentage = i1;


    }

    public Remise() {

    }


    public int getIdremise() {
        return idremise;
    }

    public void setIdremise(int idremise) {
        this.idremise = idremise;
    }

    public int getIdpaiement() {
        return idpaiement;
    }

    public void setIdpaiement(int idpaiement) {
        this.idpaiement = idpaiement;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    @Override
    public String toString() {
        return "Remise{" +
                " idpaiement=" + idpaiement +
                ", iduser=" + iduser +
                ", pourcentage=" + pourcentage +
                ", montantaprespourcentage=" + montantaprespourcentage +
                '}';
    }

    public int getMontantaprespourcentage() {
        return montantaprespourcentage;
    }

    public void setMontantaprespourcentage(int montantaprespourcentage) {
        this.montantaprespourcentage = montantaprespourcentage;
    }
}
