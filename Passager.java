package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Passager extends JFrame implements ActionListener {

    JTextField tfnom, tfID, tfprenom, tfgenre, tfnationalite, tfph_no, tfadress, tfpassport_no;
    JRadioButton rbhomme, rbfemme;
    JButton save, voirListe;

    public Passager() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("INFORMATIONS DU PASSAGER");
        heading.setBounds(540, 20, 1000, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.RED);
        add(heading);

        JLabel lblID = new JLabel("ID");
        lblID.setBounds(600, 120, 150, 25);
        lblID.setFont(new Font("TimesNewRoman", Font.PLAIN, 16));
        add(lblID);
        tfID = new JTextField();
        tfID.setBounds(800, 120, 150, 25);
        add(tfID);

        JLabel lblnom = new JLabel("NOMS");
        lblnom.setBounds(600, 180, 150, 25);
        lblnom.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnom);
        tfnom = new JTextField();
        tfnom.setBounds(800, 180, 150, 25);
        add(tfnom);

        JLabel lblprenom = new JLabel("PRENOMS");
        lblprenom.setBounds(600, 230, 150, 25);
        lblprenom.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblprenom);
        tfprenom = new JTextField();
        tfprenom.setBounds(800, 230, 150, 25);
        add(tfprenom);

        JLabel lblgenre = new JLabel("GENRE");
        lblgenre.setBounds(600, 280, 150, 25);
        lblgenre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgenre);

        ButtonGroup genregroup = new ButtonGroup();
        rbhomme = new JRadioButton("HOMME");
        rbhomme.setBounds(800, 280, 100, 25);
        rbhomme.setBackground(Color.WHITE);
        add(rbhomme);
        rbfemme = new JRadioButton("FEMME");
        rbfemme.setBounds(900, 280, 100, 25);
        rbfemme.setBackground(Color.WHITE);
        add(rbfemme);
        genregroup.add(rbhomme);
        genregroup.add(rbfemme);

        JLabel lblnationalite = new JLabel("NATIONALITE");
        lblnationalite.setBounds(600, 330, 150, 25);
        lblnationalite.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationalite);
        tfnationalite = new JTextField();
        tfnationalite.setBounds(800, 330, 150, 25);
        add(tfnationalite);

        JLabel lbladress = new JLabel("ADRESS");
        lbladress.setBounds(600, 380, 150, 25);
        lbladress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladress);
        tfadress = new JTextField();
        tfadress.setBounds(800, 380, 150, 25);
        add(tfadress);

        JLabel lblph_no = new JLabel("NUMERO DE TELEPHONE");
        lblph_no.setBounds(600, 430, 250, 25);
        lblph_no.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblph_no);
        tfph_no = new JTextField();
        tfph_no.setBounds(800, 430, 150, 25);
        add(tfph_no);

        JLabel lblpassport_no = new JLabel("NUMERO DE PASSPORT ");
        lblpassport_no.setBounds(600, 480, 250, 25);
        lblpassport_no.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpassport_no);
        tfpassport_no = new JTextField();
        tfpassport_no.setBounds(800, 480, 150, 25);
        add(tfpassport_no);

        save = new JButton("SAVE");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setBounds(700, 550, 150, 30);
        save.addActionListener(this);
        add(save);

        // BOUTON POUR AFFICHER LA LISTE
        voirListe = new JButton("VOIR LISTE PASSAGERS");
        voirListe.setBackground(Color.BLUE);
        voirListe.setForeground(Color.WHITE);
        voirListe.setBounds(700, 600, 200, 30);
        voirListe.addActionListener(this);
        add(voirListe);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/Icons/emp.png"));
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(1000, 130, 280, 400);
        add(lblimage);

        setSize(900, 700);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == save) {
            String ID = tfID.getText();
            String nom = tfnom.getText();
            String prenom = tfprenom.getText();
            String genre = null;
            String adress = tfadress.getText();
            String nationalite = tfnationalite.getText();
            String ph_no = tfph_no.getText();
            String passport_no = tfpassport_no.getText();

            if (rbhomme.isSelected()) {
                genre = "HOMME";
            } else {
                genre = "FEMME";
            }

            try {
                Database_Connection databaseConnection = new Database_Connection();
                String query = "insert into passager values('" + ID + "','" + nom + "','" + prenom + "','" + genre + "','" + nationalite + "','" + adress + "', '" + ph_no + "' ,'" + passport_no + "')";
                databaseConnection.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Information du passager ajoutée avec succès");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == voirListe) {
            afficherListePassagers();
        }
    }

    private void afficherListePassagers() {
        JFrame listeFrame = new JFrame("Liste des Passagers");
        listeFrame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nom", "Prenom", "Genre", "Nationalite", "Adresse", "Téléphone", "Passport"};
        String[][] data = null;

        try {
            Database_Connection c = new Database_Connection();
            ResultSet rs = c.s.executeQuery("SELECT * FROM passager");

            rs.last();
            int rowCount = rs.getRow();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(null, "Aucun passager enregistré dans la base de données.");
                return;
            }
            rs.beforeFirst();

            data = new String[rowCount][8];
            int i = 0;

            while (rs.next()) {
                data[i][0] = rs.getString(1);  // ID
                data[i][1] = rs.getString(2);  // nom
                data[i][2] = rs.getString(3);  // prenom
                data[i][3] = rs.getString(4);  // genre
                data[i][4] = rs.getString(5);  // nationalite
                data[i][5] = rs.getString(6);  // adress
                data[i][6] = rs.getString(7);  // ph_no
                data[i][7] = rs.getString(8);  // passport_no
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des passagers !");
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
