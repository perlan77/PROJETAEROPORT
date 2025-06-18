package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TicketAnnulation extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfnom, lblid, lblPass, lblfcode, lblannulationdate;
    JButton btnAfficherDetails, btnAnnuler;

    public TicketAnnulation() {
        setTitle("Annulation de Ticket - AIR CAMEROUN");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("ANNULATION DE TICKET");
        heading.setBounds(550, 20, 600, 35);
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/ee.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 120, 250, 250);
        add(image);

        JLabel lblnum = new JLabel("NUMERO PNR");
        lblnum.setBounds(400, 80, 150, 25);
        lblnum.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnum);

        tfpnr = new JTextField();
        tfpnr.setBounds(600, 80, 200, 25);
        add(tfpnr);

        btnAfficherDetails = new JButton("Afficher détails");
        btnAfficherDetails.setBackground(Color.BLACK);
        btnAfficherDetails.setForeground(Color.WHITE);
        btnAfficherDetails.setBounds(820, 80, 150, 25);
        btnAfficherDetails.addActionListener(this);
        add(btnAfficherDetails);

        JLabel lblnom = new JLabel("Nom");
        lblnom.setBounds(400, 130, 150, 25);
        lblnom.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnom);

        tfnom = new JLabel();
        tfnom.setBounds(600, 130, 250, 25);
        add(tfnom);

        JLabel lblpass = new JLabel("NUMERO DE TICKET");
        lblpass.setBounds(400, 180, 250, 25);
        lblpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpass);

        lblPass = new JLabel();
        lblPass.setBounds(600, 180, 250, 25);
        add(lblPass);

        JLabel lblcod = new JLabel("Code de Vol");
        lblcod.setBounds(400, 230, 150, 25);
        lblcod.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcod);

        lblfcode = new JLabel();
        lblfcode.setBounds(600, 230, 250, 25);
        add(lblfcode);

        JLabel lblgender = new JLabel("Date du Vol");
        lblgender.setBounds(400, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        lblannulationdate = new JLabel();
        lblannulationdate.setBounds(600, 280, 250, 25);
        add(lblannulationdate);

        JLabel lblidentifiant = new JLabel("ID Passager");
        lblidentifiant.setBounds(400, 330, 150, 25);
        lblidentifiant.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblidentifiant);

        lblid = new JLabel();
        lblid.setBounds(600, 330, 250, 25);
        add(lblid);

        btnAnnuler = new JButton("Annuler le ticket");
        btnAnnuler.setBackground(Color.BLACK);
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setBounds(600, 400, 200, 30);
        btnAnnuler.addActionListener(this);
        add(btnAnnuler);

        setSize(1100, 550);
        setLocation(200, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnAfficherDetails) {
            String pnr = tfpnr.getText().trim();

            if (pnr.equals("")) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer le numéro PNR !");
                return;
            }

            try {
                Database_Connection databaseConnection = new Database_Connection();
                String query = "SELECT * FROM reservation WHERE PNR = '" + pnr + "'";
                ResultSet rs = databaseConnection.s.executeQuery(query);

                if (rs.next()) {
                    tfnom.setText(rs.getString("nom"));
                    lblfcode.setText(rs.getString("codevol"));
                    lblannulationdate.setText(rs.getString("ddate"));
                    lblid.setText(rs.getString("ID"));
                    lblPass.setText(rs.getString("TICKET"));
                } else {
                    JOptionPane.showMessageDialog(null, "PNR non trouvé !");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == btnAnnuler) {
            String pnr = tfpnr.getText().trim();
            String nom = tfnom.getText();
            String ticketNo = lblPass.getText();
            String idPassager = lblid.getText();
            String codeVol = lblfcode.getText();
            String dateVol = lblannulationdate.getText();

            if (pnr.equals("") || nom.equals("")) {
                JOptionPane.showMessageDialog(null, "Veuillez d'abord rechercher le PNR !");
                return;
            }

            try {
                Database_Connection databaseConnection = new Database_Connection();

                // Insérer l'annulation
                String queryInsert = "INSERT INTO annulation VALUES ('" + pnr + "', '" + nom + "', '" + ticketNo + "', '" + codeVol + "', '" + idPassager + "', '" + dateVol + "')";
                databaseConnection.s.executeUpdate(queryInsert);

                // Supprimer de réservation
                String queryDelete = "DELETE FROM reservation WHERE PNR = '" + pnr + "'";
                databaseConnection.s.executeUpdate(queryDelete);

                JOptionPane.showMessageDialog(null, "Le ticket a été annulé avec succès.");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de l'annulation !");
            }
        }
    }
}
