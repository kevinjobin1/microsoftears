package ca.ulaval.glo2004.gui.barres;

import ca.ulaval.glo2004.gui.FenetrePrincipale;
import org.kordamp.ikonli.bootstrapicons.BootstrapIcons;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarreBoutons extends JPanel {
    private JButton nouveauButton,
            chargerButton,
            undoButton,
            redoButton,
            saveButton,
            deleteButton,
            exportButton;
    private FenetrePrincipale parent;

    public BarreBoutons(FenetrePrincipale parent) {
        this.parent = parent;
       this.initialiser();
    }

    private void initialiser() {
        nouveauButton = creerBouton(BootstrapIcons.FILE_EARMARK_PLUS_FILL, 30, Color.WHITE);
        chargerButton = creerBouton(BootstrapIcons.FOLDER2_OPEN, 30, Color.WHITE);
        undoButton = creerBouton(BootstrapIcons.ARROW_LEFT, 30, Color.WHITE);
        redoButton = creerBouton(BootstrapIcons.ARROW_RIGHT, 30, Color.WHITE);
        saveButton = creerBouton(BootstrapIcons.SAVE, 30, Color.WHITE);
        deleteButton = creerBouton(BootstrapIcons.TRASH_FILL, 30, Color.WHITE);
        exportButton = creerBouton(BootstrapIcons.ARROW_BAR_RIGHT, 30, Color.WHITE);

        // ==== Bouton nouveau projet =======
        nouveauButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nouveauButtonActionPerformed(e);
            }
        });

        // ==== Bouton charger un projet =======
        chargerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chargerButtonActionPerformed(e);
            }
        });

        // ==== Bouton revenir en arrière =======
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undoButtonActionPerformed(e);
            }
        });

        // ==== Bouton refaire une action =======
        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redoButtonActionPerformed(e);
            }
        });

        // ==== Bouton enregistrer un projet =======
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(e);
            }
        });

        // ==== Bouton supprimer un projet  =======
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);
            }
        });

        // ==== Bouton exporter un projet  =======
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportButtonActionPerformed(e);
            }
        });
    }

    private JButton creerBouton(BootstrapIcons icone, int taille, Color couleur){
        JButton bouton = new javax.swing.JButton();
        FontIcon fontIcon = FontIcon.of(icone, taille, couleur);
        bouton.setIcon(fontIcon);
        bouton.setPreferredSize(new Dimension(32,32));
        bouton.setBackground(null);
        bouton.setBorder(null);
        this.add(bouton);
        return bouton;
    }

    private void exportButtonActionPerformed(ActionEvent e) {
    }

    private void deleteButtonActionPerformed(ActionEvent e) {
    }

    private void saveButtonActionPerformed(ActionEvent e) {
    }

    private void redoButtonActionPerformed(ActionEvent e) {
    }

    private void undoButtonActionPerformed(ActionEvent e) {
    }

    private void chargerButtonActionPerformed(ActionEvent e) {
    }

    private void nouveauButtonActionPerformed(ActionEvent e) {
    }

}
