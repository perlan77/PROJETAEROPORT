package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class InfoVol extends JFrame {

    public InfoVol() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("Informations des Vols");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable table = new JTable();

        try {
            Database_Connection databaseConnection = new Database_Connection();
            ResultSet rs = databaseConnection.s.executeQuery("SELECT * FROM vol");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);

        setSize(800, 500);
        setLocationRelativeTo(null); // Centre la fenêtre
        setVisible(true);
    }

}
