package airlinemanagementsystem;

public class PersonnelAdministratif extends Personnel {
    public PersonnelAdministratif(String nom, String prenom, String infoSpecifique) {
        super(nom, prenom, infoSpecifique);
        this.type = "Administratif";
    }
}
