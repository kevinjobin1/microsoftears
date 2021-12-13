package ca.ulaval.glo2004.gui.barres;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import ca.ulaval.glo2004.gui.actions.ExporterProjet;
import com.formdev.flatlaf.icons.*;
import org.kordamp.ikonli.swing.*;
import org.kordamp.ikonli.bootstrapicons.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Objects;

public class BarreMenu extends JMenuBar
{

    public FenetrePrincipale parent;

    public JMenu fichierMenu,
            editionMenu,
            exporterMenu,
            affichageMenu,
            aideMenu,
            contreplaqueSubMenu;

    public JMenuItem
            ouvrirMenuItem,
            sauvegarderMenuItem,
            quitterMenuItem,
            revenirMenuItem,
            retablirMenuItem,
            agrandirMenuItem,
            reduireMenuItem,
            aboutMenuItem,
            exporterSVGMenuItem,
            exporterJPEGMenuItem;

    public ButtonGroup exporterButtonGroup;
    public ButtonGroup selectionContreplaque;

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
        exporterMenu = creerMenu("Exporter","Exportation");
        aideMenu = creerMenu("Aide","Aide");
        affichageMenu = creerMenu("Affichage","Affichage");
        contreplaqueSubMenu = new JMenu("Affichage des contreplaqués");

        // Groupe qui contient les différentes composantes à sélectionner
        exporterButtonGroup = new ButtonGroup();

        selectionContreplaque = new ButtonGroup();

        // Menu Fichier
        initialiserMenuFichier();

        // Menu Edition
        initialiserMenuEdition();

       // Menu Affichage
        initialiserMenuAffichage();

        // Menu Outils
        initialiserMenuExporter();

        // Menu Aide
        initialiserMenuAide();

    }

    private void initialiserMenuFichier(){

        ouvrirMenuItem = new JMenuItem("Ouvrir...", new FlatTreeOpenIcon());
        ouvrirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        ouvrirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ouvrirProjetActionPerformed(e);
            }
        });

        sauvegarderMenuItem = new JMenuItem("Sauvegarder",new FlatFileViewFloppyDriveIcon());
        sauvegarderMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        sauvegarderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sauvegarderProjetActionPerformed(e);
            }
        });

        quitterMenuItem = new JMenuItem("Quitter", new FlatInternalFrameCloseIcon());
        quitterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quitterMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quitterMenuItemActionPerformed(e);
            }
        });

        fichierMenu.add(ouvrirMenuItem);
        fichierMenu.add(new JSeparator());
        fichierMenu.add(sauvegarderMenuItem);
        fichierMenu.add(new JSeparator());
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

        editionMenu.add(revenirMenuItem);
        editionMenu.add(new JSeparator());
        editionMenu.add(retablirMenuItem);

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

        affichageMenu.add(agrandirMenuItem);
        affichageMenu.add(new JSeparator());
        affichageMenu.add(reduireMenuItem);
        affichageMenu.add(new JSeparator());


        if (!parent.controller.getListeComposantes().isEmpty()){
            creerCheckBoxMenuItem("Afficher/masquer tout", true, true, affichageMenu);
        for (IComposante composante : parent.controller.getListeIComposantes())
        {
            creerCheckBoxMenuItem(composante.toString(), composante.estVisible(), composante.estAjoute(), affichageMenu);

            if ((composante.getType() == TypeComposante.MUR_PROFILE)){
                JRadioButtonMenuItem profilComplet = new JRadioButtonMenuItem("Profil complet");
                profilComplet.setSelected(true);
                profilComplet.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showComposanteActionPerformed(e);
                    }
                });

                JRadioButtonMenuItem contreplaqueExterieur = new JRadioButtonMenuItem("Contreplaqué extérieur");
                contreplaqueExterieur.setSelected(false);
                contreplaqueExterieur.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showComposanteActionPerformed(e);
                    }
                });
                JRadioButtonMenuItem contreplaqueInterieur = new JRadioButtonMenuItem("Contreplaqué intérieur");
                contreplaqueInterieur.setSelected(false);
                contreplaqueInterieur.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showComposanteActionPerformed(e);
                    }
                });
                ButtonGroup selectionContreplaque = new ButtonGroup();
                selectionContreplaque.add(profilComplet);
                selectionContreplaque.add(contreplaqueExterieur);
                selectionContreplaque.add(contreplaqueInterieur);

                contreplaqueSubMenu.add(profilComplet);
                contreplaqueSubMenu.add(contreplaqueExterieur);
                contreplaqueSubMenu.add(contreplaqueInterieur);

                affichageMenu.add(contreplaqueSubMenu);

                if ((boolean) composante.getModes()[0]){
                    creerCheckBoxMenuItem("Afficher/masquer ellipses", composante.estVisible(), composante.estAjoute(), affichageMenu);
                }
                else {
                    creerCheckBoxMenuItem("Afficher/masquer points de contrôles", composante.estVisible(),composante.estAjoute(), affichageMenu);
                }

            }

        }
        }

    }

    private void initialiserMenuExporter(){
        
        exporterJPEGMenuItem = new JMenuItem("Exporter en image (.jpg)");
        exporterSVGMenuItem = new JMenuItem("Exporter en ficher de découpe (.svg)");
        exporterMenu.add(exporterJPEGMenuItem);
        exporterMenu.add(exporterSVGMenuItem);
        exporterMenu.add( new JSeparator());

        exporterSVGMenuItem.addActionListener(new ExporterProjet(parent, ExporterProjet.FormatFichier.SVG));
        exporterJPEGMenuItem.addActionListener(new ExporterProjet(parent, ExporterProjet.FormatFichier.JPEG));

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

    private void creerCheckBoxMenuItem(String description, boolean estSelectionne, boolean estAjoute, JMenu container){
        JCheckBoxMenuItem checkbox = new JCheckBoxMenuItem(description);
        checkbox.setSelected(estSelectionne);
        if (!estAjoute){
            checkbox.setEnabled(false);
        }
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showComposanteActionPerformed(e);
            }
        });
        container.add(checkbox);
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
        if(parent.controller.getUndoController() != null) {
            RoulotteController controller = parent.controller;
            parent.controller = parent.controller.getUndoController();
            parent.controller.setRedoController(controller.deepCopy());
        }
    }

    protected void redoActionPerformed(ActionEvent e) {
        if(parent.controller.getUndoController() != null) {
            RoulotteController controller = parent.controller;
            parent.controller = parent.controller.getRedoController();
            parent.controller.setUndoController(controller.deepCopy());
        }
    }


    protected void zoomInActionPerformed(ActionEvent e) {
        parent.controller.setScale(1);
        parent.repaint();
    }

    protected void zoomOutActionPerformed(ActionEvent e) {
        parent.controller.setScale(-1);
        parent.repaint();
    }


    protected void aboutActionPerformed(ActionEvent e) {
        JDialog d = new JDialog(parent, "À propos");
        d.setLayout(new BoxLayout(d.getContentPane(),BoxLayout.Y_AXIS));
        // create a label
        JLabel espace1 = new JLabel("================================================");
        JLabel espace2 = new JLabel("================================================");
        JLabel espace3 = new JLabel("================================================");
        JLabel espace4 = new JLabel("================================================");
        JLabel label1 = new JLabel("....................//** 2021 Projet Microsoftears **\\\\....................");
        JLabel label2 = new JLabel("....................//** Conception et code réalisé par **\\\\....................");
        JLabel label3 = new JLabel("....................//** Kevin Jobin, Lucas Niquet et Lydia Lelièvre **\\\\....................");
        d.add(espace1);
        d.add(label1);
        d.add(espace2);
        d.add(label2);
        d.add(espace3);
        d.add(label3);
        d.add(espace4);

        // setsize of dialog
        d.setSize(400, 150);

        // set visibility of dialog
        d.setVisible(true);
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
                if (Objects.equals(composante, "Afficher/masquer tout")){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            ((JCheckBoxMenuItem) item).setState(estAffiche);
                        }
                    }
                }
                else if (Objects.equals(composante, "Afficher/masquer ellipses")){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                            if (Objects.equals(checkBox.getText(), "Ellipse 1") ||
                                    Objects.equals(checkBox.getText(), "Ellipse 2") ||
                                    Objects.equals(checkBox.getText(), "Ellipse 3") ||
                                    Objects.equals(checkBox.getText(), "Ellipse 4")){
                                checkBox.setState(estAffiche);
                            }
                        }
                    }
                }
                else if (Objects.equals(composante, "Afficher/masquer points de contrôles")){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                            if (Objects.equals(checkBox.getText(), "Point de contrôle 1") ||
                                    Objects.equals(checkBox.getText(), "Point de contrôle 2") ||
                                    Objects.equals(checkBox.getText(), "Point de contrôle 3") ||
                                    Objects.equals(checkBox.getText(), "Point de contrôle 4")){
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
                if (Objects.equals(composante, "Afficher/masquer tout")){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            ((JCheckBoxMenuItem) item).setState(estAffiche);
                        }
                    }
                }
                else if (Objects.equals(composante, "Afficher/masquer ellipses")){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                            if (Objects.equals(checkBox.getText(), "Ellipse 1") ||
                                    Objects.equals(checkBox.getText(), "Ellipse 2") ||
                                    Objects.equals(checkBox.getText(), "Ellipse 3") ||
                                    Objects.equals(checkBox.getText(), "Ellipse 4")){
                                checkBox.setState(estAffiche);
                            }
                        }
                    }
                }
                else if (Objects.equals(composante, "Afficher/masquer points de contrôles")){
                    for (int i = 0 ; i <  affichageMenu.getItemCount(); i++) {
                        Object item = affichageMenu.getItem(i);
                        if (item instanceof JCheckBoxMenuItem) {
                            JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                            if (Objects.equals(checkBox.getText(), "Point de contrôle 1") ||
                                    Objects.equals(checkBox.getText(), "Point de contrôle 2") ||
                                    Objects.equals(checkBox.getText(), "Point de contrôle 3") ||
                                    Objects.equals(checkBox.getText(), "Point de contrôle 4")){
                                checkBox.setState(estAffiche);
                            }
                        }
                    }
                }
                parent.controller.setComposanteVisible(false, composante);
            }

            parent.repaint();
        }
        else if (objet instanceof JRadioButtonMenuItem){
            JRadioButtonMenuItem radioButton = (JRadioButtonMenuItem) objet;
            String composante = radioButton.getText();
            parent.controller.setAffichageContreplaque(composante);
        }
    }

    private void quitterMenuItemActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public void update() {
            this.removeAll();
            initialiser();
        }

}
