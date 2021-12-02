package ca.ulaval.glo2004.gui.barres;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import com.formdev.flatlaf.icons.*;
import org.kordamp.ikonli.swing.*;
import org.kordamp.ikonli.bootstrapicons.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

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

    public ButtonGroup selectionButtonGroup;

    public BarreMenu(FenetrePrincipale parent)
    {
        this.parent = parent;
        this.setBorder(null);
        this.initialiser();
    }

    public void initialiser()
    {
        // Créer les différents menus
        fichierMenu = creerMenu("Fichier", "Options du fichier");
        editionMenu = creerMenu("Edition","Édition");
        outilsMenu = creerMenu("Outils","Outils");
        aideMenu = creerMenu("Aide","Aide");
        affichageMenu = creerMenu("Affichage","Affichage");
        selectionSubMenu = new JMenu("Sélectionner...");
        ajouterSubMenu = new JMenu("Ajouter");

        // Groupe qui contient les différentes composantes à sélectionner
        selectionButtonGroup = new ButtonGroup();

        // Menu Fichier
        initialiserMenuFichier();

        // Menu Edition
        initialiserMenuEdition();

       // Menu Affichage
        initialiserMenuAffichage();

        // Menu Outils
        initialiserMenuOutils();

        // Menu Aide
        initialiserMenuAide();

    }

    private void initialiserMenuFichier(){

        //Création des sous-item dans le Menu Fichier
        nouveauMenuItem = new JMenuItem("Nouveau...", new FlatFileViewFileIcon());
        nouveauMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        nouveauMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nouveauProjetActionPerformed(e);
            }
        });

        ouvrirMenuItem = new JMenuItem("Ouvrir...", new FlatTreeOpenIcon());
        ouvrirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        ouvrirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ouvrirProjetActionPerformed(e);
            }
        });

        recentMenuItem = new JMenuItem("Récent");
        recentMenuItem.setMnemonic(KeyEvent.VK_R);

        sauvegarderMenuItem = new JMenuItem("Sauvegarder",new FlatFileViewFloppyDriveIcon());
        sauvegarderMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        sauvegarderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sauvegarderProjetActionPerformed(e);
            }
        });

        FontIcon icon = FontIcon.of(BootstrapIcons.ARROW_BAR_RIGHT, 15, Color.white);

        exportMenuItem = new JMenuItem("Exporter", icon);
        exportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exporterProjetActionPerformed(e);
            }
        });

        quitterMenuItem = new JMenuItem("Quitter", new FlatInternalFrameCloseIcon());
        quitterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quitterMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quitterMenuItemActionPerformed(e);
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
    }

    private void initialiserMenuEdition(){

        //Création des sous-item dans le Menu Edition
        revenirMenuItem = new JMenuItem("Revenir");
        revenirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        revenirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undoActionPerformed(e);
            }
        });

        retablirMenuItem = new JMenuItem("Rétablir");
        retablirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        retablirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redoActionPerformed(e);
            }
        });


        supprimerMenuItem = new JMenuItem("Supprimer");
        supprimerMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.ALT_MASK));
        retablirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supprimerActionPerformed(e);
            }
        });

        JSeparator separator3 = new JSeparator();

        editionMenu.add(revenirMenuItem);
        editionMenu.add(retablirMenuItem);
        editionMenu.add(separator3);
        editionMenu.add(supprimerMenuItem);
    }

    private void initialiserMenuAffichage(){
        //Création des sous-item dans le menu Affichage
        FontIcon agrandirIcon = FontIcon.of(BootstrapIcons.PLUS, 20, Color.WHITE);
        agrandirMenuItem = new JMenuItem("Agrandir", agrandirIcon);
        agrandirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK));
        agrandirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zoomInActionPerformed(e);
            }
        });

        FontIcon reduireIcon = FontIcon.of(BootstrapIcons.DASH, 20, Color.WHITE);
        reduireMenuItem = new JMenuItem("Réduire", reduireIcon);
        reduireMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
        reduireMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zoomOutActionPerformed(e);
            }
        });

        JSeparator separator = new JSeparator();

        affichageMenu.add(agrandirMenuItem);
        affichageMenu.add(reduireMenuItem);
        affichageMenu.add(separator);


        if (!parent.controller.getListeComposantes().isEmpty()){
            creerCheckBoxMenuItem("Afficher/masquer tout", true);
        for (IComposante composante : parent.controller.getListeIComposantes())
        {
                creerCheckBoxMenuItem(composante.toString(), composante.estVisible());
            if ((composante.getType() == TypeComposante.MUR_PROFILE)){
                if (composante.getMode()){
                    creerCheckBoxMenuItem("Afficher/masquer ellipses", composante.estVisible());
                }
                else {
                    creerCheckBoxMenuItem("Afficher/masquer points de contrôles", composante.estVisible());
                }
            }
        }
        }

    }

    private void initialiserMenuOutils(){

        //Création des sous-item dans le Menu Outils
        optionsMenuItem = new JMenuItem("Options");
        optionsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        optionsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionsActionPerformed(e);
            }
        });

        JRadioButtonMenuItem selectMurInt = new JRadioButtonMenuItem("Mur Intérieur");
        //bselection.addActionListener(new ActionListener() {
        //            public void actionPerformed(ActionEvent e) {
        //                murIntActionPerformed(e);
        //            }
        //        });

        JRadioButtonMenuItem selectMurExt = new JRadioButtonMenuItem("Mur Extérieur");
        //bselection.addActionListener(new ActionListener() {
        //            public void actionPerformed(ActionEvent e) {
        //                murExtActionPerformed(e);
        //            }
        //        });

        JRadioButtonMenuItem selectPlancher = new JRadioButtonMenuItem("Plancher");
        //bselection.addActionListener(new ActionListener() {
        //            public void actionPerformed(ActionEvent e) {
        //                plancherActionPerformed(e);
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
    }

    private void initialiserMenuAide(){
        aboutMenuItem = new JMenuItem("À propos");
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboutActionPerformed(e);
            }});
        aideMenu.add(aboutMenuItem);
    }

    private JMenu creerMenu(String titreMenu, String description){
        JMenu menu = new JMenu(titreMenu);
        menu.getAccessibleContext().setAccessibleDescription(description);
        this.add(menu);
        return menu;
    }

    private void creerCheckBoxMenuItem(String description, boolean estSelectionne){
        JCheckBoxMenuItem checkbox = new JCheckBoxMenuItem(description);
        checkbox.setSelected(estSelectionne);
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showComposanteActionPerformed(e);
            }
        });
        affichageMenu.add(checkbox);
    }

    protected void nouveauProjetActionPerformed(ActionEvent e) {
    }

    protected void sauvegarderProjetActionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int valeur = chooser.showOpenDialog(null);
        if(valeur == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            try {
                FileOutputStream fos = new FileOutputStream(selectedFile.getAbsolutePath());
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(parent.controller);
                oos.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    protected void ouvrirProjetActionPerformed(ActionEvent e) {
        RoulotteController roulotte;
        JFileChooser chooser = new JFileChooser();
        int valeur = chooser.showOpenDialog(null);
        if(valeur == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                FileInputStream fileIn = new FileInputStream(selectedFile.getAbsolutePath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                roulotte = (RoulotteController) in.readObject();
                parent.controller = roulotte;
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("RoulotteController class not found");
                c.printStackTrace();
            }
            parent.repaint();
        }
    }

    protected void exporterProjetActionPerformed(ActionEvent e) {
    }

    protected void undoActionPerformed(ActionEvent e) {
        // TODO: à coder au livrable 4, undo action
    }

    protected void redoActionPerformed(ActionEvent e) {
        // TODO: à coder au livrable 4, redo action
    }

    protected void supprimerActionPerformed(ActionEvent e) {
        // TODO: à coder
    }

    protected void zoomInActionPerformed(ActionEvent e) {
        parent.controller.setScale(1);
        parent.repaint();
    }

    protected void zoomOutActionPerformed(ActionEvent e) {
        parent.controller.setScale(-1);
        parent.repaint();
    }

    protected void optionsActionPerformed(ActionEvent e) {
        // TODO: à coder
    }

    protected void aboutActionPerformed(ActionEvent e) {
        // TODO: à coder
    }

    protected void showComposanteActionPerformed(ActionEvent e) {
        Object objet = e.getSource();

        if (objet instanceof JCheckBoxMenuItem) {
            JCheckBoxMenuItem checkbox = (JCheckBoxMenuItem) objet;

            // nom de la composante et son état (affiché ou non)
            String composante = checkbox.getText();
            boolean estAffiche = checkbox.getState();

            if (estAffiche){
                // si la composante n'est pas affichée, on la réaffiche
                // on va appeler une fonction du controleur qui invalide l'affichage
                // et qui repaint les composantes
                if (composante == "Afficher/masquer tout"){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            ((JCheckBoxMenuItem) item).setState(estAffiche);
                        }
                    }
                }
                else if (composante == "Afficher/masquer ellipses"){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                            if (checkBox.getText() == "Ellipse 1" ||
                                    checkBox.getText() == "Ellipse 2" ||
                                    checkBox.getText() == "Ellipse 3" ||
                                    checkBox.getText() == "Ellipse 4"){
                                checkBox.setState(estAffiche);
                            }
                        }
                    }
                }
                else if (composante == "Afficher/masquer points de contrôles"){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                            if (checkBox.getText() == "Point de contrôle 1" ||
                                    checkBox.getText() == "Point de contrôle 2" ||
                                    checkBox.getText() == "Point de contrôle 3" ||
                                    checkBox.getText() == "Point de contrôle 4"){
                                checkBox.setState(estAffiche);
                            }
                        }
                    }
                }
                parent.controller.setComposanteVisible(true, composante);
            }

            else {
                // sinon, on la fait disparaitre
                // on va appeler une fonction du controleur qui invalide l'affichage
                // et qui repaint les composantes
                if (composante == "Afficher/masquer tout"){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            ((JCheckBoxMenuItem) item).setState(estAffiche);
                        }
                    }
                }
                else if (composante == "Afficher/masquer ellipses"){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                            if (checkBox.getText() == "Ellipse 1" ||
                                    checkBox.getText() == "Ellipse 2" ||
                                    checkBox.getText() == "Ellipse 3" ||
                                    checkBox.getText() == "Ellipse 4"){
                                checkBox.setState(estAffiche);
                            }
                        }
                    }
                }
                else if (composante == "Afficher/masquer points de contrôles"){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                            if (checkBox.getText() == "Point de contrôle 1" ||
                                    checkBox.getText() == "Point de contrôle 2" ||
                                    checkBox.getText() == "Point de contrôle 3" ||
                                    checkBox.getText() == "Point de contrôle 4"){
                                checkBox.setState(estAffiche);
                            }
                        }
                    }
                }
                parent.controller.setComposanteVisible(false, composante);
            }

            parent.repaint();
        }
    }

    private void quitterMenuItemActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public void update() {

    }
}
