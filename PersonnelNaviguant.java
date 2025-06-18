package airlinemanagementsystem;

public class PersonnelNaviguant extends Personnel {
    public PersonnelNaviguant(String nom, String prenom, String infoSpecifique) {
        super(nom, prenom, infoSpecifique);
        this.type = "Naviguant";
    }
}


