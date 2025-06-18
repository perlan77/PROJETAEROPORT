package airlinemanagementsystem;

import java.sql.*;

public class Database_Connection {
    public Connection c;
    public Statement s;

    public Database_Connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinemanagementsystem", "root", "loulouperlan"); // adapter ton mdp
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //s = c.createStatement();
        } catch (Exception e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return c;
    }
}
