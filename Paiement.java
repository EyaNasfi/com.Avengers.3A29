package entity;

public class Paiement {
    int idpaiement;
    int iduser;
    int IDformation;
    int montant;
    String modepaiement;

    public Paiement(int idpaiement, int iduser, int IDformation, int montant, String modepaiement) {
        this.idpaiement = idpaiement;
        this.iduser = iduser;
        this.IDformation = IDformation;
        this.montant = montant;
        this.modepaiement = modepaiement;
    }
    public Paiement( int iduser, int IDformation, int montant, String modepaiement) {
        this.idpaiement = idpaiement;
        this.iduser = iduser;
        this.IDformation = IDformation;
        this.montant = montant;
        this.modepaiement = modepaiement;
    }

    public Paiement(int i, String text) {
        this.montant=i;
        this.modepaiement=text;
    }

    public Paiement() {

    }

    public Paiement(String mod, int mo) {
        this.modepaiement=mod;
        this.montant=mo;
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

    public int getIDformation() {
        return IDformation;
    }

    public void setIDformation(int IDformation) {
        this.IDformation = IDformation;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getModepaiement() {
        return modepaiement;
    }

    public void setModepaiement(String modepaiement) {
        this.modepaiement = modepaiement;
    }

    @Override
    public String toString() {
        return "Paiement{" +

                " iduser=" + iduser +
                "IDformation=" + IDformation +
                ", montant=" + montant +
                ", modepaiement='" + modepaiement + '\'' +
                '}';
    }
}

