package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    public Home() {
        setLayout(null);
        setTitle("Accueil - AIR CAMEROUN");
        setSize(400, 250);
        setLocation(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Chargement de l'image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/Icons/mm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1600, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1600, 800);
        add(image);

        // Titre
        JLabel heading = new JLabel("BIENVENUE SUR AIR CAMEROUN");
        heading.setBounds(420, 50, 1000, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 40));
        image.add(heading);

        // Barre de menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu tickets = new JMenu("Tickets");
        menuBar.add(tickets);

        JMenu details = new JMenu("Détails");
        menuBar.add(details);

        JMenu personnelMenu = new JMenu("Personnel");
        menuBar.add(personnelMenu);

        // Items
        JMenuItem volDetails = new JMenuItem("Informations sur le Vol");
        volDetails.setActionCommand("vol");
        volDetails.addActionListener(this);
        details.add(volDetails);

        JMenuItem passagerDetails = new JMenuItem("Ajouter des informations sur le passager");
        passagerDetails.setActionCommand("passager");
        passagerDetails.addActionListener(this);
        details.add(passagerDetails);

        JMenuItem ticketannulation = new JMenuItem("Tickets annulé");
        ticketannulation.setActionCommand("annulation");
        ticketannulation.addActionListener(this);
        tickets.add(ticketannulation);

        JMenuItem carnetvol = new JMenuItem("Carnet de Vol");
        carnetvol.setActionCommand("carnet");
        carnetvol.addActionListener(this);
        details.add(carnetvol);

        JMenuItem journeeDetails = new JMenuItem("Informations sur la journée");
        journeeDetails.setActionCommand("journee");
        journeeDetails.addActionListener(this);
        details.add(journeeDetails);

        JMenuItem carteembarquement = new JMenuItem("Carte d'embarquement");
        carteembarquement.setActionCommand("carte");
        carteembarquement.addActionListener(this);
        tickets.add(carteembarquement);

        // -------- NOUVEAU MENU POUR LE PERSONNEL --------
        JMenuItem personnelGestion = new JMenuItem("Gérer le Personnel");
        personnelGestion.setActionCommand("personnel");
        personnelGestion.addActionListener(this);
        personnelMenu.add(personnelGestion);
        // -------------------------------------------------

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        switch (text) {
            case "passager":
                new Passager();
                break;
            case "vol":
                new InfoVol();
                break;
            case "journee":
                new JourneeDetails();
                break;
            case "carnet":
                new CarnetVol();
                break;
            case "annulation":
                new TicketAnnulation();
                break;
            case "carte":
                new CarteEmbarquement();
                break;
            case "personnel":
                // On lance la gestion du personnel
                GestionPersonnel gestion = new GestionPersonnel();
                SwingUtilities.invokeLater(() -> {
                    RegistrePersonnel ui = new RegistrePersonnel(gestion);
                    ui.setVisible(true);
                });
                break;
            default:
                break;
        }
    }
}

