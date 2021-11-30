package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.gui.barres.BarreBoutons;
import ca.ulaval.glo2004.gui.barres.BarreMenu;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.gui.panels.PanelConception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FenetrePrincipale extends JFrame {
    // Composantes graphiques du Frame
    private JPanel mainPanel, bottomPanel;
    protected BarreBoutons barreBoutons;
    protected PanelConception panelConception;
    protected BarreMenu barreMenu;
    public BarreOnglet barreOnglets;
    protected Color couleurChoisie;

    // Éléments rattachés non-graphiques
    public RoulotteController controller;
    private TypeComposante composanteChoisie;
    private TypeAction actionChoisie;
    private boolean estImperial = true;

    public boolean estImperial() {
        return estImperial;
    }

    public void estImperial(boolean estImperial) {
        this.estImperial = estImperial;
        updateBarres();
    }

    public Color getCouleurChoisie() {
        return couleurChoisie;
    }

    public void setCouleurChoisie(Color couleurChoisie) {
        this.couleurChoisie = couleurChoisie;
    }

    public TypeComposante getComposanteChoisie() {
        return composanteChoisie;
    }

    public void setComposanteChoisie(TypeComposante type) {
        this.composanteChoisie = type;
        updateBarres();
    }

    public void updateBarres() {
        this.barreMenu.update();
        this.barreBoutons.update();
        this.barreOnglets.update();
        switch (composanteChoisie) {
            case HAYON:
                this.barreOnglets.setSelectedIndex(7);
                break;
            case PLANCHER:
                this.barreOnglets.setSelectedIndex(5);
                break;
            case POUTRE_ARRIERE:
                this.barreOnglets.setSelectedIndex(6);
                break;
            case MUR_PROFILE:
                this.barreOnglets.setSelectedIndex(0);
                break;
            case PROFIL_ELLIPSE_1:
                this.barreOnglets.setSelectedIndex(1);
                break;
            case PROFIL_ELLIPSE_2:
                this.barreOnglets.setSelectedIndex(2);
                break;
            case PROFIL_ELLIPSE_3:
                this.barreOnglets.setSelectedIndex(3);
                break;
            case PROFIL_ELLIPSE_4:
                this.barreOnglets.setSelectedIndex(4);
                break;
            case POINT_CONTROLE_1:
                this.barreOnglets.setSelectedIndex(1);
                break;
            case POINT_CONTROLE_2:
                this.barreOnglets.setSelectedIndex(2);
                break;
            case POINT_CONTROLE_3:
                this.barreOnglets.setSelectedIndex(3);
                break;
            case POINT_CONTROLE_4:
                this.barreOnglets.setSelectedIndex(4);
                break;
            case MUR_SEPARATEUR:
                this.barreOnglets.setSelectedIndex(8);
                break;
            case TOIT:
                this.barreOnglets.setSelectedIndex(9);
                break;
            case OUVERTURE_LATERALE:
                this.barreOnglets.setSelectedIndex(10);
                break;
        }

    }

    public enum TypeAction {
        SELECTION, AJOUT, SUPPRIMER, REMPLIR
    }

    public FenetrePrincipale() {
        initComponents();
    }

    private void initComponents() {
        // Initialisation de la roulotte avec les valeurs par défaut
        controller = new RoulotteController(this);
        // Couleur par défaut
        couleurChoisie = new Color(0, 217, 217);

        // Action par défaut & composante par défaut (plan)
        actionChoisie = TypeAction.SELECTION;
        composanteChoisie = TypeComposante.PLAN;

        // Initialisation des panneaux du GUI
        mainPanel = new JPanel();
        bottomPanel = new JPanel();
        barreMenu = new BarreMenu(this);
        barreBoutons = new BarreBoutons(this);
        barreOnglets = new BarreOnglet(this);
        panelConception = new PanelConception(this);

        //======== FenetrePrincipale ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Microsoftears");

        //======== barre de menu ===========
        this.setJMenuBar(barreMenu);

        //======== mainPanel ========

        mainPanel.setLayout(new BorderLayout());

        //======== barreBoutons ========
        barreBoutons.setPreferredSize(new Dimension(400, 50));
        barreBoutons.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        mainPanel.add(barreBoutons, BorderLayout.NORTH);

        //======== barreOnglets ========

        mainPanel.add(barreOnglets, BorderLayout.EAST);

        //======== panelConception =======

        mainPanel.add(panelConception, BorderLayout.CENTER);

        //======== bottomPanel ===========
        bottomPanel.add("Pixel Par Pouce", new JLabel("Pixel par pouce : " + controller.getPixelsToInchesRatio()));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


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

    public void setActionChoisie(TypeAction newAction) {
        this.actionChoisie = newAction;
    }

    public TypeAction getActionChoisie() {
        return actionChoisie;
    }


}


