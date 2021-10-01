package ca.ulaval.glo2004;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ca.ulaval.glo2004.gui.MainWindow;

public class Main {

    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();
        mainWindow.setExtendedState(mainWindow.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        mainWindow.setVisible(true);

        /*
        // Création du main window
        JFrame mainWindow = new JFrame();

        // Création de la barre de menu
        JMenuBar menuBar = new JMenuBar();

        // Menu Nouveau
        JMenu menuNew = new JMenu("Nouveau");
        JMenuItem itemNewProject = new JMenuItem("Nouveau Projet");
        menuNew.add(itemNewProject);
        menuBar.add(menuNew);

        // Menu Charger
        JMenu menuLoad = new JMenu("Charger");
        menuBar.add(menuLoad);

        // Menu Revenir
        JMenu menuUndo = new JMenu("Revenir");
        menuBar.add(menuUndo);

        // Menu Refaire
        JMenu menuRedo = new JMenu("Refaire");
        menuBar.add(menuRedo);

        // Menu Enregistrer
        JMenu menuSave = new JMenu("Enregistrer");
        menuBar.add(menuSave);

        // Menu Supprimer
        JMenu menuDelete = new JMenu("Supprimer");
        menuBar.add(menuDelete);

        // Menu Exporter
        JMenu menuExport = new JMenu("Exporter");
        menuBar.add(menuExport);


        // Exemple tres simple de comment ajouter un evenement lors de la fermeture de l'application avec le X
        mainWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Creation d'un bouton
        JButton button = new JButton("cliquez ici pour fermer");

        // On place le bouton a un endroit dans la fenetre
        button.setBounds(130, 100, 100, 40);

        // On lui ajouter un action listener qui déclanche une methode qui possède le code devant être
        // executer lorsque le bouton est cliqué
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


        // On ajoute le bouton a notre fenetre principal
        mainWindow.add(button);

        // Ici on ne fait que changer les propriétées du Main Window
        mainWindow.setJMenuBar(menuBar);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainWindow.setSize(screenSize.width, screenSize.height);
        mainWindow.setLayout(null);
        mainWindow.setVisible(true);


        // Simple commande permettant d'ecrire sur la console
        System.out.println("hello world");*/
    }
}

