package ca.ulaval.glo2004.gui.actions;

import ca.ulaval.glo2004.Microsoftears;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import org.kordamp.ikonli.bootstrapicons.BootstrapIcons;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExporterProjet implements ActionListener  {
    private final FenetrePrincipale parent;

    public ExporterProjet(FenetrePrincipale parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            JFileChooser choix = new JFileChooser();
            FileFilter filtre = new FileNameExtensionFilter("Plans Microsoftears (*.jpg)", "jpeg");
            choix.addChoosableFileFilter(filtre);
            choix.setDialogTitle("Exportation des plans");
            File f = choix.getCurrentDirectory();
            String path = f.toString();
            System.out.println(f.getAbsolutePath());

            int returnVal2 = choix.showSaveDialog(null);
            if(returnVal2 == JFileChooser.APPROVE_OPTION)
            {
                String nom_fichier;
                java.io.File sfichier = choix.getSelectedFile();
                String nom_sfichier = sfichier.toString();
                if(nom_sfichier.contains(".jpg"))
                {
                    nom_fichier = nom_sfichier;
                }
                else if(nom_sfichier.contains("..."))
                {
                    nom_fichier = path + "\\" + "defaut.jpg";
                }
                else
                {
                    nom_fichier = nom_sfichier + ".jpg";
                }
                parent.panelConception.panneauAffichage.exporter(nom_fichier);

                FontIcon icon = FontIcon.of(BootstrapIcons.ARROW_UP_RIGHT_SQUARE, 30, Color.WHITE);

                JOptionPane.showMessageDialog(null, "Vous exportez le fichier " + nom_fichier, "Exportation des plans", JOptionPane.INFORMATION_MESSAGE,icon);
            }
        }
    }
