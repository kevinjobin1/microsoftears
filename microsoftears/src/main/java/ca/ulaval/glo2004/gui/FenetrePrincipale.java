package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.gui.afficheur.PanneauAffichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame {

    //Declaration des variables graphiques
    private JPanel mainPanel;
    private JPanel boutonsTopPanel;
    private ButtonGroup boutonsTopGroup;
    private JButton nouveauButton;
    private JButton chargerButton;
    private JButton undoButton;
    private JButton redoButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton exportButton;
    private JTabbedPane barreOnglets;
    private JToolBar barreOutils;
    private JScrollPane mainScrollPane;
    private PanneauAffichage panneauAffichage;
    private JButton ajoutBouton;

    // Déclaration variables non-graphiques
    public RoulotteController controller;
    public TypeComposante composanteChoisie;
    private TypeAction actionChoisie;

    // Ces attributs servent à la gestion du déplacement.
    public Point actualMousePoint = new Point();
    public Point delta = new Point();

    public enum TypeAction {
        SELECT, ADD, DELETE
    }

    public FenetrePrincipale() {
        controller = new RoulotteController();
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        boutonsTopPanel = new JPanel();
        boutonsTopGroup= new ButtonGroup();
        barreOnglets = new JTabbedPane();
        barreOutils = new JToolBar();
        mainScrollPane = new JScrollPane();
        panneauAffichage = new PanneauAffichage(this);
        nouveauButton = new javax.swing.JButton();
        chargerButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        redoButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        deleteButton =  new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        ajoutBouton = new javax.swing.JButton();


        //======== boutonsTopGroup =========

        boutonsTopGroup.add(nouveauButton);
        boutonsTopGroup.add(chargerButton);
        boutonsTopGroup.add(undoButton);
        boutonsTopGroup.add(redoButton);
        boutonsTopGroup.add(saveButton);
        boutonsTopGroup.add(deleteButton);
        boutonsTopGroup.add(exportButton);

        //======== FenetrePrincipale ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Microsoftears");

        //======== mainPanel ========

        mainPanel.setLayout(new BorderLayout());
        
        //======== boutonsTopPanel ========
        boutonsTopPanel.setPreferredSize(new Dimension(400, 45));
        boutonsTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // ==== Bouton nouveau projet =======
        nouveauButton.setText("Nouveau");
        nouveauButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nouveauButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(nouveauButton);

        // ==== Bouton charger un projet =======
        chargerButton.setText("Charger");
        chargerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chargerButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(chargerButton);

        // ==== Bouton revenir en arrière =======
        undoButton.setText("Revenir");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undoButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(undoButton);

        // ==== Bouton refaire une action =======
        redoButton.setText("Refaire");
        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redoButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(redoButton);

        // ==== Bouton enregistrer un projet =======
        saveButton.setText("Enregistrer");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(saveButton);

        // ==== Bouton supprimer un projet  =======
        deleteButton.setText("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(deleteButton);

        // ==== Bouton exporter un projet  =======
        exportButton.setText("Exporter");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(exportButton);
        
        mainPanel.add(boutonsTopPanel, BorderLayout.NORTH);
        
        

        //======== barreOnglets ========

        barreOnglets.setPreferredSize(new Dimension(300, 900));
        barreOnglets.addTab("Tab1", makePanel("This is tab 1"));
        barreOnglets.addTab("Tab2", makePanel("This is tab 2"));
        barreOnglets.addTab("Tab3", makePanel("This is tab 3"));
        barreOnglets.addTab("Tab4", makePanel("This is tab 4"));

        mainPanel.add(barreOnglets, BorderLayout.EAST);

        //======== barreOutils ========

        barreOutils.setOrientation(SwingConstants.VERTICAL);
    
        //---- ajoutBouton ----
        
        ajoutBouton.setText("(logo)");
        barreOutils.add(ajoutBouton);

        mainPanel.add(barreOutils, BorderLayout.WEST);

        //======== mainScrollPane ========

        mainScrollPane.setSize(new Dimension(800, 400));

        mainPanel.add(mainScrollPane, BorderLayout.CENTER);

        panneauAffichage.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                drawingPanelMousePressed(e);
            }
        });

        panneauAffichage.addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                drawingPanelMouseWheelMoved(e);
            }
        });

        panneauAffichage.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                drawingPanelMouseMoved(e);
            }
        });

        GroupLayout drawingPanelLayout = new GroupLayout(panneauAffichage);
        panneauAffichage.setLayout(drawingPanelLayout);

        drawingPanelLayout.setHorizontalGroup(
                drawingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 888, Short.MAX_VALUE)
        );
        drawingPanelLayout.setVerticalGroup(
                drawingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 303, Short.MAX_VALUE)
        );

        mainScrollPane.setViewportView(panneauAffichage);

        mainPanel.add(mainScrollPane, BorderLayout.CENTER);



        pack();
        setLocationRelativeTo(getOwner());



        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
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

    private void quitMenuItemActionPerformed(ActionEvent e) {//GEN-FIRST:event_quitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitMenuItemActionPerformed

    private void drawingPanelMousePressed(MouseEvent e) {//GEN-FIRST:event_drawingPanelMousePressed
        Point mousePoint = panneauAffichage.getGridPosition(e.getPoint());
        System.out.format("GridPoint: (%f, %f)", mousePoint.getX(), mousePoint.getY());
        System.out.format("MousePoint: (%f, %f)", e.getPoint().getX(), e.getPoint().getY());
        TypeComposante composanteChoisie = this.composanteChoisie;
        this.controller.ajouterComposante(composanteChoisie,mousePoint);
        panneauAffichage.repaint();
    }//GEN-LAST:event_drawingPanelMousePressed

    private void drawingPanelMouseMoved(MouseEvent e) {//GEN-FIRST:event_drawingPanelMouseMoved
    }//GEN-LAST:event_drawingPanelMouseMoved

    private void drawingPanelMouseWheelMoved(MouseWheelEvent e) {//GEN-FIRST:event_drawingPanelMouseWheelMoved
        int wheelRotation = e.getWheelRotation();
        Point mousePoint = panneauAffichage.getGridPosition(e.getPoint());
        System.out.format("WheelRotation: %d", wheelRotation);
        panneauAffichage.setScale(wheelRotation);
        System.out.format("SetCenter: (%f, %f)", mousePoint.getX(), mousePoint.getY());
        // drawingPanel.setCenter(mousePoint);
        panneauAffichage.repaint();
    }//GEN-LAST:event_drawingPanelMouseWheelMoved

    public void setAction(TypeAction newAction) {
        this.actionChoisie = newAction;
    }

    private static JPanel makePanel(String text) {
        JPanel panel = new JPanel();
        panel.add(new Label(text));
        panel.setLayout(new GridLayout(1, 1));
        return panel;
    }

}


