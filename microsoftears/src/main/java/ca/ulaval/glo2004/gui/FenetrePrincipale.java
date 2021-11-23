package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.domain.roulotte.RoulotteController;
import ca.ulaval.glo2004.gui.barres.BarreBoutons;
import ca.ulaval.glo2004.gui.barres.BarreMenu;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.gui.panels.PanelConception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame {
    // ColorPicker
    protected Color couleurChoisie;
    // Dimensions
    private final Dimension DIMENSIONS_MINIMALES = new Dimension(1080,720);

    // Composantes graphiques du Frame
    private JPanel mainPanel, bottomPanel;
    protected BarreBoutons boutonsTopPanel;
    protected PanelConception panelConception;
    protected BarreMenu barreMenu;
    protected JTabbedPane barreOnglets;
    //protected PanneauAffichage panneauAffichage;
    public JLabel infoLabel;

    // Éléments rattachés non-graphiques
    public RoulotteController controller;
    public TypeComposante composanteChoisie;
    private TypeAction actionChoisie;

    public Color getCouleurChoisie() {
        return couleurChoisie;
    }

    public void setCouleurChoisie(Color couleurChoisie) {
        this.couleurChoisie = couleurChoisie;
    }

    public void setComposanteChoisie(TypeComposante composanteChoisie) {
        this.composanteChoisie = composanteChoisie;
    }

    public enum TypeAction {
        SELECTION, AJOUT, SUPPRIMER
    }

    public FenetrePrincipale() {
        initComponents();
    }

    private void initComponents() {
        controller = new RoulotteController(this);

        couleurChoisie = new Color(217, 217, 217);
        MurBrute murBrute = new MurBrute(controller);
        //controller.getListeComposantes().add(murBrute);
        controller.setMurBrute(murBrute);
        MurProfile murProfile = new MurProfile(controller,true);
        controller.setMurprofile(murProfile);
        Plancher plancher = new Plancher(controller);
        controller.setPlancher(plancher);
        PoutreArriere poutre = new PoutreArriere(controller);
        controller.setPoutreArriere(poutre);
        Hayon hayon = new Hayon(controller);
        controller.setHayon(hayon);
        controller.getListeComposantes().add(murProfile);
        for (ProfilEllipse ellipse : murProfile.getProfilEllipses()){
            controller.getListeComposantes().add(ellipse);
        }
        controller.getListeComposantes().add(hayon);
        controller.getListeComposantes().add(poutre);
        controller.getListeComposantes().add(plancher);


        mainPanel = new JPanel();
        bottomPanel = new JPanel();
        barreMenu = new BarreMenu(this);
        boutonsTopPanel = new BarreBoutons(this);
        barreOnglets = new BarreOnglet(this);
        panelConception = new PanelConception(this);
        infoLabel = new JLabel();

        //======== FenetrePrincipale ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Microsoftears");

        //======== barre de menu ===========
        barreMenu.setBorder(null);
        this.setJMenuBar(barreMenu);

        //======== mainPanel ========

        mainPanel.setLayout(new BorderLayout());

        //======== panel du bas ==========
       infoLabel.setText("Position(0,0) " + " Centre Plan (0,0) " + "Dimension Plan (0,0) " + "Dimension Afficheur (0.0)");
        bottomPanel.add(infoLabel);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        
        //======== boutonsTopPanel ========
        boutonsTopPanel.setPreferredSize(new Dimension(400, 50));
        boutonsTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        mainPanel.add(boutonsTopPanel, BorderLayout.NORTH);

        //======== barreOnglets ========

        mainPanel.add(barreOnglets, BorderLayout.EAST);

        //======== panelConception =======

        mainPanel.add(panelConception, BorderLayout.CENTER);


        //======== Layout Final ==========

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

    protected void quitterActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public void setAction(TypeAction newAction) {
        this.actionChoisie = newAction;
    }

}


