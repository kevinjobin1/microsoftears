package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.gui.afficheur.PanneauAffichage;
import org.kordamp.ikonli.swing.FontIcon;
import org.kordamp.ikonli.bootstrapicons.*;
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
    public JLabel positionSouris;

    // Éléments rattachés non-graphiques
    public RoulotteController controller;
    public TypeComposante composanteChoisie;
    private TypeAction actionChoisie;

    // Ces attributs servent à la gestion du déplacement.
    public Point actualMousePoint = new Point();
    public Point delta = new Point();

    public enum TypeAction {
        SELECTION, AJOUT, SUPPRIMER
    }

    public FenetrePrincipale() {
        controller = new RoulotteController();
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        bottomPanel = new JPanel();
        barreMenu = new BarreMenu(this);
        boutonsTopPanel = new BarreBoutons(this);
        barreOnglets = new BarreOnglet(this);
        //panneauAffichage = new PanneauAffichage(this);
        panelConception = new PanelConception(this);
        positionSouris = new JLabel();
        

        //======== FenetrePrincipale ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Microsoftears");

        //======== barre de menu ===========
        barreMenu.setBorder(null);
        this.setJMenuBar(barreMenu);
        

        //======== mainPanel ========

        mainPanel.setLayout(new BorderLayout());

        //======== panel du bas ==========
        positionSouris.setText("Position(0,0)");
        bottomPanel.add(positionSouris);
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


