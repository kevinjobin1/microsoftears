package ca.ulaval.glo2004.gui;

import com.formdev.flatlaf.icons.*;
import org.kordamp.ikonli.swing.*;
import org.kordamp.ikonli.bootstrapicons.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class BarreMenu extends JMenuBar
{

    public FenetrePrincipale parent;
    public JMenu fichierMenu,
            editionMenu,
            outilsMenu,
            affichageMenu,
            aideMenu,
            selectionSubMenu,
            ajouterSubMenu;

    public JMenuItem nouveauMenuItem,
            ouvrirMenuItem,
            recentMenuItem,
            sauvegarderMenuItem,
            quitterMenuItem,
            revenirMenuItem,
            retablirMenuItem,
            supprimerMenuItem,
            agrandirMenuItem,
            reduireMenuItem,
            optionsMenuItem,
            aboutMenuItem,
            exportMenuItem;

    public JCheckBoxMenuItem  showHayonMenuItem,
            showPlancherMenuItem,
            showMurIntMenuItem;

    public ButtonGroup selectionButtonGroup;

    public BarreMenu(FenetrePrincipale parent)
    {
        this.parent = parent;
        this.initialiser();
    }

    public void initialiser()
    {
        //Panel de la barre de Menu
        fichierMenu = new JMenu("Fichier");
        editionMenu = new JMenu("Edition");
        outilsMenu = new JMenu("Outils");
        aideMenu = new JMenu("Aide");
        affichageMenu = new JMenu("Affichage");
        selectionSubMenu = new JMenu("Sélectionner...");
        ajouterSubMenu = new JMenu("Ajouter");

        selectionButtonGroup = new ButtonGroup();

        fichierMenu.getAccessibleContext().setAccessibleDescription("Options du fichier");
        editionMenu.getAccessibleContext().setAccessibleDescription("Édition");
        outilsMenu.getAccessibleContext().setAccessibleDescription("Outils");
        aideMenu.getAccessibleContext().setAccessibleDescription("Aide");
        affichageMenu.getAccessibleContext().setAccessibleDescription("Affichage");
        add(fichierMenu);
        add(editionMenu);
        add(affichageMenu);
        add(outilsMenu);
        add(aideMenu);

        //Création des sous-item dans le Menu Fichier
        nouveauMenuItem = new JMenuItem("Nouveau...", new FlatFileViewFileIcon());
        nouveauMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        nouveauMenuItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            parent.nouveauProjetActionPerformed(e);
        }
    });

        ouvrirMenuItem = new JMenuItem("Ouvrir...", new FlatTreeOpenIcon());
        ouvrirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        ouvrirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.ouvrirProjetActionPerformed(e);
            }
        });

        recentMenuItem = new JMenuItem("Récent");
        recentMenuItem.setMnemonic(KeyEvent.VK_R);

        sauvegarderMenuItem = new JMenuItem("Sauvegarder",new FlatFileViewFloppyDriveIcon());
        sauvegarderMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        sauvegarderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.sauvegarderProjetActionPerformed(e);
            }
        });

        FontIcon icon = FontIcon.of(BootstrapIcons.ARROW_BAR_RIGHT, 15, Color.white);

        exportMenuItem = new JMenuItem("Exporter", icon);
        exportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.exporterProjetActionPerformed(e);
            }
        });

        quitterMenuItem = new JMenuItem("Quitter", new FlatInternalFrameCloseIcon());
        quitterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quitterMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.quitterActionPerformed(e);
            }
        });

        JSeparator separator1 = new JSeparator();
        JSeparator separator2 = new JSeparator();

        separator1.setForeground(new Color(201,199, 205, 150));
        separator2.setForeground(new Color(201,199, 205, 150));

        fichierMenu.add(nouveauMenuItem);
        fichierMenu.add(ouvrirMenuItem);
        fichierMenu.add(separator1);
        fichierMenu.add(sauvegarderMenuItem);
        fichierMenu.add(exportMenuItem);
        fichierMenu.add(separator2);
        fichierMenu.add(quitterMenuItem);

        //Création des sous-item dans le Menu Edition
        revenirMenuItem = new JMenuItem("Revenir");
        revenirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        revenirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.undoActionPerformed(e);
            }
        });

        retablirMenuItem = new JMenuItem("Rétablir");
        retablirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        retablirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.redoActionPerformed(e);
            }
        });


        supprimerMenuItem = new JMenuItem("Supprimer");
        supprimerMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.ALT_MASK));
        retablirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.supprimerActionPerformed(e);
            }
        });

        JSeparator separator3 = new JSeparator();

        editionMenu.add(revenirMenuItem);
        editionMenu.add(retablirMenuItem);
        editionMenu.add(separator3);
        editionMenu.add(supprimerMenuItem);

        //Création des sous-item dans le menu Affichage
        agrandirMenuItem = new JMenuItem("Aggrandir", new FlatInternalFrameMaximizeIcon());
        agrandirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK));
        agrandirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.zoomInActionPerformed(e);
            }
        });

        reduireMenuItem = new JMenuItem("Réduire", new FlatInternalFrameRestoreIcon());
        reduireMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
        reduireMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.zoomOutActionPerformed(e);
            }
        });

        showHayonMenuItem = new JCheckBoxMenuItem("Hayon");
        showHayonMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK));
        showHayonMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showHayonActionPerformed(e);
            }
        });

        showPlancherMenuItem = new JCheckBoxMenuItem("Plancher");
        showPlancherMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        showPlancherMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showPlancherActionPerformed(e);
            }
        });

        showMurIntMenuItem = new JCheckBoxMenuItem("Mur Intérieur");
        showMurIntMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
        showMurIntMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showMurIntActionPerformed(e);
            }
        });

        // TODO : ajouter ici les autres JCheckBoxMenuItem pour chacune des composantes et aide au design

        JSeparator separator4 = new JSeparator();
        
        affichageMenu.add(agrandirMenuItem);
        affichageMenu.add(reduireMenuItem);
        affichageMenu.add(separator4);
        affichageMenu.add(showHayonMenuItem);
        affichageMenu.add(showPlancherMenuItem);
        affichageMenu.add(showMurIntMenuItem);

        //Création des sous-item dans le Menu Outils
        optionsMenuItem = new JMenuItem("Options");
        optionsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        optionsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.optionsActionPerformed(e);
            }
        });

        JRadioButtonMenuItem selectMurInt = new JRadioButtonMenuItem("Mur Intérieur");
        //bselection.addActionListener(new ActionListener() {
        //            public void actionPerformed(ActionEvent e) {
        //                parent.murIntActionPerformed(e);
        //            }
        //        });

        JRadioButtonMenuItem selectMurExt = new JRadioButtonMenuItem("Mur Extérieur");
        //bselection.addActionListener(new ActionListener() {
        //            public void actionPerformed(ActionEvent e) {
        //                parent.murExtActionPerformed(e);
        //            }
        //        });

        JRadioButtonMenuItem selectPlancher = new JRadioButtonMenuItem("Plancher");
        //bselection.addActionListener(new ActionListener() {
        //            public void actionPerformed(ActionEvent e) {
        //                parent.plancherActionPerformed(e);
        //            }
        //        });

        ButtonGroup selectionButtonGroup = new ButtonGroup();
        selectionButtonGroup.add(selectMurInt);
        selectionButtonGroup.add(selectMurExt);
        selectionButtonGroup.add(selectPlancher);
        selectionSubMenu.add(selectMurInt);
        selectionSubMenu.add(selectMurExt);
        selectionSubMenu.add(selectPlancher);


        outilsMenu.add(ajouterSubMenu);
        outilsMenu.add(selectionSubMenu);
        outilsMenu.add(optionsMenuItem);


        aboutMenuItem = new JMenuItem("À propos");
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
        aboutMenuItem.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                           parent.aboutActionPerformed(e);
                       }});
        aideMenu.add(aboutMenuItem);

    }

}
