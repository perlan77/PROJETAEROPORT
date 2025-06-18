package airlinemanagementsystem;

public class PersonnelSol extends Personnel {
    public PersonnelSol(String nom, String prenom, String infoSpecifique) {
        super(nom, prenom, infoSpecifique);
        this.type = "Au Sol";
    }
}

