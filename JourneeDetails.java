package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class JourneeDetails extends JFrame implements ActionListener {

    JTable table;
    JTextField pnr;
    JButton show;

    public JourneeDetails() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 50, 120, 25);
        add(lblpnr);

        pnr = new JTextField();
        pnr.setBounds(180, 50, 150, 25);
        add(pnr);

        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(350, 50, 140, 25);
        show.addActionListener(this);
        add(show);

        table = new JTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 100, 740, 400);
        jsp.setBackground(Color.WHITE);
        add(jsp);

        setTitle("Détails de la Journée - AIR CAMEROUN");
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Database_Connection databaseConnection = new Database_Connection();
            String pnrValue = pnr.getText();

            String query = "SELECT PNR, ticket, ID, nom, nationalite, nomvol, codevol, depart, arrivee, escale, ddate " +
                    "FROM reservation WHERE PNR = '" + pnrValue + "'";

            ResultSet rs = databaseConnection.s.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "AUCUNE INFORMATION TROUVEE");
                return;
            }

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des données !");
        }
    }

}
