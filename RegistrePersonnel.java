package airlinemanagementsystem;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrePersonnel extends JFrame {
    private GestionPersonnel gestion;

    public RegistrePersonnel(GestionPersonnel gestion) {
        this.gestion = gestion;
        initUI();
    }

    private void initUI() {
        setTitle("AIR CAMEROUN - Gestion du Personnel");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Couleur de fond générale
        getContentPane().setBackground(new Color(210, 230, 250));

        // Panel pour les champs
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 220), 2),
                "Formulaire d'Enregistrement",
                0, 0, new Font("Tahoma", Font.BOLD, 16), new Color(50, 90, 160)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField nomField = new JTextField(15);
        JTextField prenomField = new JTextField(15);
        JTextField infoField = new JTextField(15);

        String[] types = { "Personnel Naviguant", "Personnel au Sol", "Personnel Administratif" };
        JComboBox<String> typeCombo = new JComboBox<>(types);

        JButton addButton = new JButton("Ajouter");
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 14));

        JTextArea displayArea = new JTextArea(10, 40);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 220)));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste du Personnel"));

        // Ajout des composants avec GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nom :"), gbc);
        gbc.gridx = 1;
        panel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Prénom :"), gbc);
        gbc.gridx = 1;
        panel.add(prenomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Fonction :"), gbc);
        gbc.gridx = 1;
        panel.add(typeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Info spécifique :"), gbc);
        gbc.gridx = 1;
        panel.add(infoField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, gbc);
        panel.add(new JLabel(""));
        panel.add(addButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String info = infoField.getText();

                String selectedType = (String) typeCombo.getSelectedItem();
                Personnel p = null;

                switch (selectedType) {
                    case "Personnel Naviguant":
                        p = new PersonnelNaviguant( nom, prenom, info);
                        break;
                    case "Personnel au Sol":
                        p = new PersonnelSol(nom, prenom, info);
                        break;
                    case "Personnel Administratif":
                        p = new PersonnelAdministratif(nom, prenom, info);
                        break;
                }

                if (p != null) {
                    gestion.ajouterPersonnel(p);
                    displayArea.append(p.toString() + "\n");
                    clearFields(nomField, prenomField, infoField);
                }
            }
        });
    }

    private void clearFields(JTextField nom, JTextField prenom, JTextField info) {
        nom.setText("");
        prenom.setText("");
        info.setText("");
    }

    public static void main(String[] args) {
        GestionPersonnel gestion = new GestionPersonnel();

        SwingUtilities.invokeLater(() -> {
            RegistrePersonnel ui = new RegistrePersonnel(gestion);
            ui.setVisible(true);
        });
    }
}