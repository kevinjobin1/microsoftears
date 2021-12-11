package ca.ulaval.glo2004.gui.actions;

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
    private final FormatFichier format;

    public ExporterProjet(FenetrePrincipale parent, FormatFichier format) {
        this.parent = parent;
        this.format = format;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        exporter(format);
        }

    private void exporter(FormatFichier format) {

        JFileChooser choix = new JFileChooser();
        FileFilter filtre = new FileNameExtensionFilter("Plans Microsoftears (*." + format + ")", format.toString());
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
            if(nom_sfichier.contains("." + format))
            {
                nom_fichier = nom_sfichier;
            }
            else if(nom_sfichier.contains("..."))
            {
                nom_fichier = path + "\\" + "defaut." + format;
            }
            else
            {
                nom_fichier = nom_sfichier + "." + format;
            }
            parent.panelConception.panneauAffichage.exporter(nom_fichier, format);

            FontIcon icon = FontIcon.of(BootstrapIcons.ARROW_UP_RIGHT_SQUARE, 30, Color.WHITE);

            JOptionPane.showMessageDialog(null, "Vous exportez le fichier " + nom_fichier, "Exportation des plans", JOptionPane.INFORMATION_MESSAGE,icon);
        }
    }

    public enum FormatFichier {
            JPEG {
                public String toString(){ return "jpg"; }
            },
            SVG {
                public String toString(){ return "svg"; }
            }
    }
    }
