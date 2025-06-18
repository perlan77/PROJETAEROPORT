package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CarteEmbarquement extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfnom, tfnationalite, lbldepart, lblarrivee, labelfnom, labelfcode, labeldate;
    JButton allerButton,btnCarteComplete;


    public CarteEmbarquement() {
        setTitle("Carte d'Embarquement - AIR CAMEROUN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null); // centrer la fenêtre

        JLabel background = new JLabel(new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/Icons/aa.png")));
        background.setLayout(new BorderLayout());
        add(background);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // transparence
        background.add(panel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // marges
        gbc.anchor = GridBagConstraints.WEST;

        // Titre
        JLabel subheading = new JLabel("CARTE D'EMBARQUEMENT");
        subheading.setFont(new Font("Arial Black", Font.BOLD, 28));
        subheading.setForeground(Color.BLACK);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(subheading, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(createLabel("PNR DETAILS:"), gbc);

        tfpnr = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfpnr, gbc);

        allerButton = new JButton("Afficher la Carte");
        allerButton.setBackground(Color.BLACK);
        allerButton.setForeground(Color.WHITE);
        allerButton.setFocusPainted(false);
        allerButton.addActionListener(this);
        gbc.gridx = 2;
        panel.add(allerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Nom:"), gbc);
        tfnom = createDataLabel();
        gbc.gridx = 1;
        panel.add(tfnom, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Nationalité:"), gbc);
        tfnationalite = createDataLabel();
        gbc.gridx = 1;
        panel.add(tfnationalite, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Départ:"), gbc);
        lbldepart = createDataLabel();
        gbc.gridx = 1;
        panel.add(lbldepart, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Arrivée:"), gbc);
        lblarrivee = createDataLabel();
        gbc.gridx = 1;
        panel.add(lblarrivee, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Nom du vol:"), gbc);
        labelfnom = createDataLabel();
        gbc.gridx = 1;
        panel.add(labelfnom, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Code de vol:"), gbc);
        labelfcode = createDataLabel();
        gbc.gridx = 1;
        panel.add(labelfcode, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Date:"), gbc);
        labeldate = createDataLabel();
        gbc.gridx = 1;
        panel.add(labeldate, gbc);

        // Nouveau bouton pour voir la carte complète
        btnCarteComplete = new JButton("Visualiser carte complète");
        btnCarteComplete.setBackground(new Color(0, 102, 204));
        btnCarteComplete.setForeground(Color.BLACK);
        btnCarteComplete.setFocusPainted(false);
        btnCarteComplete.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy++;
        panel.add(btnCarteComplete, gbc);

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JLabel createDataLabel() {
        JLabel label = new JLabel(" - ");
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText().trim();

        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un PNR.");
            return;
        }

        try {
            Database_Connection databaseConnection = new Database_Connection();

            String query = "SELECT * FROM reservation WHERE PNR = ?";
            PreparedStatement pst = databaseConnection.c.prepareStatement(query);
            pst.setString(1, pnr);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                tfnom.setText(rs.getString("nom"));
                tfnationalite.setText(rs.getString("nationalite"));
                lbldepart.setText(rs.getString("depart"));
                lblarrivee.setText(rs.getString("arrivee"));
                labelfnom.setText(rs.getString("nomvol"));
                labelfcode.setText(rs.getString("codevol"));
                labeldate.setText(rs.getString("ddate"));
            } else {
                JOptionPane.showMessageDialog(this, "PNR incorrect ou non trouvé.");
            }

            if (ae.getSource() == btnCarteComplete) {


                if (pnr.isEmpty() || tfnom.getText().equals(" - ")) {
                    JOptionPane.showMessageDialog(this, "Veuillez d'abord rechercher un PNR valide.");
                    return;
                }

                String contenuCarte =
                        "---------------------------\n\n" +
                                "  AIR CAMEROUN - CARTE D'EMBARQUEMENT\n\n" +
                                "---------------------------\n" +
                                "PNR : " + pnr + "\n" +
                                "Nom du passager : " + tfnom.getText() + "\n" +
                                "Nationalité : " + tfnationalite.getText() + "\n" +
                                "Départ : " + lbldepart.getText() + "\n" +
                                "Arrivée : " + lblarrivee.getText() + "\n" +
                                "Vol : " + labelfnom.getText() + " (" + labelfcode.getText() + ")\n" +
                                "Date de vol : " + labeldate.getText() + "\n" +
                                "---------------------------\n\n";

                JTextArea textArea = new JTextArea(contenuCarte);
                textArea.setEditable(false);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(500, 300));

                JOptionPane.showMessageDialog(this, scrollPane, "Carte d'Embarquement", JOptionPane.INFORMATION_MESSAGE);
            }


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données.");
        }
    }

}
