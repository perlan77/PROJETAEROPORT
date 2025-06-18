package airlinemanagementsystem;

public abstract class Personnel {
    protected String nom;
    protected String prenom;
    protected String type;
    protected String infoSpecifique;

    public Personnel(String nom, String prenom, String infoSpecifique) {
        this.nom = nom;
        this.prenom = prenom;
        this.infoSpecifique = infoSpecifique;
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getType() { return type; }
    public String getInfoSpecifique() { return infoSpecifique; }

    @Override
    public String toString() {
        return type + " : " + nom + " " + prenom + " - " + infoSpecifique;
    }
}