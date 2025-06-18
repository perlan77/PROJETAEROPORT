package airlinemanagementsystem;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionPersonnel {
    private List<Personnel> listePersonnel;

    public GestionPersonnel() {
        listePersonnel = new ArrayList<>();
        createTableIfNotExists();
    }

    public void ajouterPersonnel(Personnel p) {
        listePersonnel.add(p);
        saveToDatabase(p);
    }

    private void saveToDatabase(Personnel p) {
        try {
            Database_Connection db = new Database_Connection();
            String sql = "INSERT INTO personnel (nom, prenom, type, info_specifique) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = db.getConnection().prepareStatement(sql);

            pstmt.setString(1, p.getNom());
            pstmt.setString(2, p.getPrenom());
            pstmt.setString(3, p.getType());
            pstmt.setString(4, p.getInfoSpecifique());

            pstmt.executeUpdate();

            System.out.println("Personnel sauvegardé en base : " + p.toString());
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    private void createTableIfNotExists() {
        try {
            Database_Connection db = new Database_Connection();

            String sql = "CREATE TABLE IF NOT EXISTS personnel (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nom VARCHAR(100), " +
                    "prenom VARCHAR(100), " +
                    "type VARCHAR(100), " +
                    "info_specifique VARCHAR(255))";

            db.s.execute(sql);
            System.out.println("Table 'personnel' vérifiée/créée.");
        } catch (Exception e) {
            System.err.println("Erreur création de table : " + e.getMessage());
        }
    }

    public List<Personnel> getListePersonnel() {
        return listePersonnel;
    }
}
