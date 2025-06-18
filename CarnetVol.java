package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;

public class CarnetVol extends JFrame implements ActionListener {

    JTextField tfID;
    JLabel tfnom, tfprenom, tfgenre, tfnationalite, tfadress, labelgenre, labelfnom, labelfcode;
    JButton btnRechercherPassager, btnRechercherVol, btnEnregistrerReservation, btnVoirReservations;
    Choice depart, arrivee, Escale;
    JDateChooser dcdate;

    public CarnetVol() {

        setTitle("Carnet de Vol - AIR CAMEROUN");
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(null);

        JLabel heading = new JLabel(" CARNET DE VOL - AIR CAMEROUN");
        heading.setBounds(350, 20, 800, 40);
        heading.setFont(new Font("Verdana", Font.BOLD, 28));
        heading.setForeground(new Color(0, 102, 204));
        add(heading);

        // Champ ID Passager
        JLabel lblID = new JLabel("ID Passager");
        lblID.setBounds(60, 80, 150, 25);
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblID);

        tfID = new JTextField();
        tfID.setBounds(220, 80, 150, 25);
        add(tfID);

        btnRechercherPassager = new JButton("🔍 Rechercher Passager");
        btnRechercherPassager.setBackground(Color.BLACK);
        btnRechercherPassager.setForeground(Color.WHITE);
        btnRechercherPassager.setBounds(480, 80, 220, 25);
        btnRechercherPassager.addActionListener(this);
        add(btnRechercherPassager);

        // Informations Passager
        ajouterLabelChamp("Nom", 130, tfnom = new JLabel());
        ajouterLabelChamp("Prénom", 180, tfprenom = new JLabel());
        ajouterLabelChamp("Genre", 230, labelgenre = new JLabel());
        ajouterLabelChamp("Nationalité", 280, tfnationalite = new JLabel());
        ajouterLabelChamp("Adresse", 330, tfadress = new JLabel());

        // Choix Départ / Arrivée
        JLabel lbldepart = new JLabel("Départ");
        lbldepart.setBounds(60, 380, 150, 25);
        lbldepart.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldepart);

        depart = new Choice();
        depart.setBounds(220, 380, 180, 25);
        depart.add(" ");
        add(depart);

        JLabel lblarrivee = new JLabel("Arrivée");
        lblarrivee.setBounds(60, 430, 150, 25);
        lblarrivee.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblarrivee);

        arrivee = new Choice();
        arrivee.setBounds(220, 430, 180, 25);
        arrivee.add(" ");
        add(arrivee);

        JLabel lblEscale = new JLabel("Escale");
        lblEscale.setBounds(60, 580, 150, 25);
        lblEscale.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblEscale);

        Escale = new Choice();
        Escale.setBounds(220, 580, 180, 25);
        Escale.add(" "); // premier élément vide
        add(Escale);

        try {
            Database_Connection c = new Database_Connection();
            String query = "SELECT * FROM vol";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                depart.add(rs.getString("depart"));
                arrivee.add(rs.getString("arrivee"));
                Escale.add(rs.getString("escale"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnRechercherVol = new JButton("🔍 Rechercher Vol");
        btnRechercherVol.setBackground(Color.BLACK);
        btnRechercherVol.setForeground(Color.WHITE);
        btnRechercherVol.setBounds(480, 480, 220, 25);
        btnRechercherVol.addActionListener(this);
        add(btnRechercherVol);

        // Détails du vol
        ajouterLabelChamp("Nom du vol", 480, labelfnom = new JLabel());
        ajouterLabelChamp("Code du vol", 530, labelfcode = new JLabel());

        // Date du vol
        JLabel lbldate = new JLabel("Date du vol");
        lbldate.setBounds(60, 660, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220, 660, 150, 25);
        add(dcdate);

        // Bouton Enregistrement
        btnEnregistrerReservation = new JButton(" Enregistrer réservation");
        btnEnregistrerReservation.setBackground(new Color(0, 153, 51));
        btnEnregistrerReservation.setForeground(Color.WHITE);
        btnEnregistrerReservation.setBounds(450, 660, 300, 35);
        btnEnregistrerReservation.addActionListener(this);
        add(btnEnregistrerReservation);

        // Bouton Voir les réservations
        btnVoirReservations = new JButton("📋 Voir Liste des Réservations");
        btnVoirReservations.setBackground(new Color(0, 102, 204));
        btnVoirReservations.setForeground(Color.WHITE);
        btnVoirReservations.setBounds(450, 710, 300, 35);
        btnVoirReservations.addActionListener(this);
        add(btnVoirReservations);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/ae.png"));
        Image i2 = i1.getImage().getScaledInstance(350, 400, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(800, 150, 350, 400);
        add(lblimage);

        setSize(1250, 850);
        setLocation(100, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btnRechercherPassager) {
            String ID = tfID.getText();
            try {
                Database_Connection c = new Database_Connection();
                String query = "SELECT * FROM passager WHERE ID = '" + ID + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    tfnom.setText(rs.getString("nom"));
                    tfprenom.setText(rs.getString("prenom"));
                    labelgenre.setText(rs.getString("genre"));
                    tfnationalite.setText(rs.getString("nationalite"));
                    tfadress.setText(rs.getString("adress"));
                } else {
                    JOptionPane.showMessageDialog(null, "Passager non trouvé !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnRechercherVol) {
            String src = depart.getSelectedItem();
            String dest = arrivee.getSelectedItem();
            try {
                Database_Connection c = new Database_Connection();
                String query = "SELECT * FROM vol WHERE depart = '" + src + "' AND arrivee = '" + dest + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    labelfnom.setText(rs.getString("v_nom"));
                    labelfcode.setText(rs.getString("v_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun vol trouvé !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnEnregistrerReservation) {
            try {
                Random random = new Random();

                String PNR = "PNR-" + random.nextInt(999999);
                String ticketNo = "TIC-" + random.nextInt(99999);

                String ID = tfID.getText();
                String nom = tfnom.getText();
                String nationalite = tfnationalite.getText();
                String nomvol = labelfnom.getText();
                String codevol = labelfcode.getText();
                String src = depart.getSelectedItem();
                String dest = arrivee.getSelectedItem();
                String esc = Escale.getSelectedItem();
                String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();

                Database_Connection c = new Database_Connection();

                String query = "INSERT INTO reservation VALUES('" + PNR + "', '" + ticketNo + "', '" + ID + "', '" + nom + "', '" + nationalite + "', '" + nomvol + "', '" + codevol + "', '" + src + "', '" + dest + "', '" + ddate + "' ,'" + esc + "')";

                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Réservation enregistrée avec succès !");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnVoirReservations) {
            afficherListeReservations();
        }
    }

    private void ajouterLabelChamp(String titre, int y, JLabel label) {
        JLabel lbl = new JLabel(titre);
        lbl.setBounds(60, y, 150, 25);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbl);

        label.setBounds(220, y, 300, 25);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(label);
    }

    private void afficherListeReservations() {
        JFrame listeFrame = new JFrame("Liste des Réservations");
        listeFrame.setLayout(new BorderLayout());

        String[] columnNames = {"PNR", "Ticket No", "ID Passager", "Nom", "Nationalité", "Nom Vol", "Code Vol", "Départ", "Arrivée", "Date", "Escale"};
        String[][] data = null;

        try {
            Database_Connection c = new Database_Connection();
            ResultSet rs = c.s.executeQuery("SELECT * FROM reservation");

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            if (rowCount == 0) {
                JOptionPane.showMessageDialog(null, "Aucune réservation trouvée !");
                return;
            }

            data = new String[rowCount][11];
            int i = 0;

            while (rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                data[i][4] = rs.getString(5);
                data[i][5] = rs.getString(6);
                data[i][6] = rs.getString(7);
                data[i][7] = rs.getString(8);
                data[i][8] = rs.getString(9);
                data[i][9] = rs.getString(10);
                data[i][10] = rs.getString(11);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des réservations !");
            return;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        listeFrame.add(scrollPane, BorderLayout.CENTER);

        listeFrame.setSize(1000, 500);
        listeFrame.setLocation(300, 200);
        listeFrame.setVisible(true);
    }
}
