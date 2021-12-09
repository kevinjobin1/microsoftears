package ca.ulaval.glo2004.gui.barres;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import ca.ulaval.glo2004.gui.actions.ExporterProjet;
import ca.ulaval.glo2004.utilitaires.Pouce;
import org.kordamp.ikonli.bootstrapicons.BootstrapIcons;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class BarreBoutons extends JPanel {
    private final FenetrePrincipale parent;
    private JButton logoButton,
            nouveauButton,
            chargerButton,
            undoButton,
            redoButton,
            saveButton,
            deleteButton,
            exportButton,
            grilleButton;
    private JCheckBox afficherGrilleCheckBox, afficherLabelCheckBox,
                        estMagnetiqueCheckBox;
    private JLabel labelPouce, labelNum, labelDenum, labelMM;
    private JSpinner longueurLigneGrilleSpinner,
            longueurLigneGrilleSpinner1,
            longueurLigneGrilleSpinner2,
            longueurLigneGrilleSpinner3;
    private int value;

    public BarreBoutons(FenetrePrincipale parent) {
        this.parent = parent;
        this.setPreferredSize(new Dimension(400, 50));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
       this.initialiser();
    }

    private void initialiser() {
        // ======= Boutons ======== //
        logoButton = creerBoutonLogo();
        nouveauButton = creerBouton(BootstrapIcons.FILE_EARMARK_PLUS_FILL, 30, Color.WHITE);
        chargerButton = creerBouton(BootstrapIcons.FOLDER2_OPEN, 30, Color.WHITE);
        undoButton = creerBouton(BootstrapIcons.ARROW_LEFT, 30, Color.WHITE);
        redoButton = creerBouton(BootstrapIcons.ARROW_RIGHT, 30, Color.WHITE);
        saveButton = creerBouton(BootstrapIcons.SAVE, 30, Color.WHITE);
        deleteButton = creerBouton(BootstrapIcons.TRASH_FILL, 30, Color.WHITE);
        exportButton = creerBouton(BootstrapIcons.ARROW_BAR_RIGHT, 30, Color.WHITE);

        // ======= CheckBox ======== //
        afficherLabelCheckBox = new JCheckBox("Afficher description", true);
        afficherGrilleCheckBox = new JCheckBox(" Afficher grille", true);
        estMagnetiqueCheckBox = new JCheckBox(" Magnétique", false);
        this.add(afficherLabelCheckBox);
        this.add(afficherGrilleCheckBox);
        this.add(estMagnetiqueCheckBox);
        this.value = parent.controller.getEchelleGrille().toInt();
        creerSpinnerPouces(parent.controller.getEchelleGrille());
        creerSpinnerMM(parent.controller.getEchelleGrille().getMilimetres());
        this.update();

        // ==== Bouton À propos (logo) =======
       logoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logoButtonActionPerformed(e);
            }
        });

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
        exportButton.addActionListener(new ExporterProjet(parent));

        // ==== Afficher/masquer les labels des noms de composantes  =======
        afficherLabelCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherLabelCheckBoxActionPerformed(e);
            }
        });
        // ==== Afficher/masquer grille  =======
        afficherGrilleCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherGrilleCheckBoxActionPerformed(e);
            }
        });

        // ==== Magnétiser la grille  =======
        estMagnetiqueCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                estMagnetiqueCheckBoxActionPerformed(e);
            }
        });
    }



    private void logoButtonActionPerformed(ActionEvent e) {
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

    private JButton creerBoutonLogo() {
        ImageIcon imageIcon = new ImageIcon("caravan.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JButton bouton = new JButton(imageIcon);
        bouton.setBackground(null);
        bouton.setBorder(null);
        this.add(bouton);
        return bouton;
    }

    private void estMagnetiqueCheckBoxActionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            parent.controller.setGrilleMagnetique(value, checkBox.isSelected(), afficherGrilleCheckBox.isSelected(),parent.getDimensionAfficheur());
            parent.repaint();
        }

    }

    public void update(){
        if (parent.estImperial()){
            this.add(longueurLigneGrilleSpinner1);
            this.add(labelPouce);
            this.remove(longueurLigneGrilleSpinner);
            this.remove(labelMM);
            creerSpinnerMM(parent.controller.getEchelleGrille().getMilimetres());
        }
        else{
            this.remove(longueurLigneGrilleSpinner1);
            this.remove(labelPouce);
            this.add(longueurLigneGrilleSpinner);
            this.add(labelMM);
            creerSpinnerPouces(parent.controller.getEchelleGrille());
        }
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

    private void afficherLabelCheckBoxActionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            parent.controller.setAfficherLabel(checkBox.isSelected());
            parent.repaint();
        }
    }

    private void afficherGrilleCheckBoxActionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            this.estMagnetiqueCheckBox.setVisible(checkBox.isSelected());
            this.longueurLigneGrilleSpinner1.setVisible(checkBox.isSelected());
            this.longueurLigneGrilleSpinner.setVisible(checkBox.isSelected());
            this.labelPouce.setVisible(checkBox.isSelected());
            this.labelMM.setVisible(checkBox.isSelected());
            parent.controller.setGrilleMagnetique(value, estMagnetiqueCheckBox.isSelected(), afficherGrilleCheckBox.isSelected(), parent.getDimensionAfficheur());
            parent.repaint();
            
        }
    }

    private void exportButtonActionPerformed(ActionEvent e) {
    }

    private void deleteButtonActionPerformed(ActionEvent e) {

    }

    private void saveButtonActionPerformed(ActionEvent e) {
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

    private void redoButtonActionPerformed(ActionEvent e) {
        if(parent.controller.getUndoController() != null) {
            RoulotteController controller = parent.controller;
            parent.controller = parent.controller.getRedoController();
            parent.controller.setUndoController(controller.deepCopy());
        }
    }

    private void undoButtonActionPerformed(ActionEvent e) {
        if(parent.controller.getUndoController() != null) {
            RoulotteController controller = parent.controller;
            parent.controller = parent.controller.getUndoController();
            parent.controller.setRedoController(controller.deepCopy());
        }
    }

    private void chargerButtonActionPerformed(ActionEvent e) {
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

    private void nouveauButtonActionPerformed(ActionEvent e) {

    }

    protected void creerSpinnerPouces(Pouce valeur){
        this.longueurLigneGrilleSpinner1 = new JSpinner(new SpinnerNumberModel(valeur.getPouces(),1,24,1));
        this.labelPouce = creerLabelSymbole(" \" ");
        
        longueurLigneGrilleSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                longueurLigneGrilleSpinner1ChangeListener(e);
            }
        });
        
    }

    protected void creerSpinnerMM(double value){
        this.longueurLigneGrilleSpinner = new JSpinner(new SpinnerNumberModel(value,25.4,609.6,25.4));
        this.labelMM =  creerLabelSymbole(" mm ");
        
        longueurLigneGrilleSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                longueurLigneGrilleSpinnerChangeListener(e);
            }
        });
    }

    protected JLabel creerLabelSymbole(String symbole){
        JLabel label = new JLabel(symbole);
        return label;
    }

    private void longueurLigneGrilleSpinner1ChangeListener(ChangeEvent e) {
        this.value = (int) ((JSpinner) e.getSource()).getValue();
        parent.controller.setGrilleMagnetique(value, estMagnetiqueCheckBox.isSelected(), afficherGrilleCheckBox.isSelected(), parent.getDimensionAfficheur());
        parent.repaint();
    }

    private void longueurLigneGrilleSpinnerChangeListener(ChangeEvent e) {
        this.value = (int) (((double) ((JSpinner) e.getSource()).getValue())/25.4);
        parent.controller.setGrilleMagnetique(value, estMagnetiqueCheckBox.isSelected(), afficherGrilleCheckBox.isSelected(), parent.getDimensionAfficheur());
        parent.repaint();
    }

}
