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
    private JPanel mainPanel,
                 bottomPanel,
                centerPanel,
                 boutonsTopPanel;
    private BarreMenu barreMenu;
    private JButton nouveauButton,
                     chargerButton,
                     undoButton,
                     redoButton,
                     saveButton,
                     deleteButton,
                     exportButton;
    private JTabbedPane barreOnglets;
    private BarreOutils barreOutils;
    private JScrollPane mainScrollPane;
    private PanneauAffichage panneauAffichage;
    private JLabel positionSouris;

    // Éléments rattachés non-graphiques
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
        bottomPanel = new JPanel();
        centerPanel = new JPanel();
        barreMenu = new BarreMenu(this);
        boutonsTopPanel = new JPanel();
        barreOnglets = new JTabbedPane();
        barreOutils = new BarreOutils(this);
        mainScrollPane = new JScrollPane();
        panneauAffichage = new PanneauAffichage(this);
        nouveauButton = new javax.swing.JButton();
        chargerButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        redoButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        deleteButton =  new javax.swing.JButton();
        exportButton = new javax.swing.JButton();


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

        // ==== Bouton nouveau projet =======
        FontIcon nouveauButtonIcon = FontIcon.of(BootstrapIcons.FILE_EARMARK_PLUS_FILL, 30, Color.WHITE);
        nouveauButton.setIcon(nouveauButtonIcon);
        nouveauButton.setPreferredSize(new Dimension(32,32));
        nouveauButton.setBackground(null);
        nouveauButton.setBorder(null);
        nouveauButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nouveauButtonActionPerformed(e);
            }
        });

        boutonsTopPanel.add(nouveauButton);

        // ==== Bouton charger un projet =======
        FontIcon chargerButtonIcon = FontIcon.of(BootstrapIcons.FOLDER2_OPEN, 30, Color.WHITE);
        chargerButton.setIcon(chargerButtonIcon);
        chargerButton.setPreferredSize(new Dimension(32,32));
        chargerButton.setBackground(null);
        chargerButton.setBorder(null);
        chargerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chargerButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(chargerButton);

        // ==== Bouton revenir en arrière =======
        FontIcon undoButtonIcon = FontIcon.of(BootstrapIcons.ARROW_LEFT, 30, Color.WHITE);
        undoButton.setIcon(undoButtonIcon);
        undoButton.setPreferredSize(new Dimension(32,32));
        undoButton.setBackground(null);
        undoButton.setBorder(null);
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undoButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(undoButton);

        // ==== Bouton refaire une action =======
        FontIcon redoButtonIcon = FontIcon.of(BootstrapIcons.ARROW_RIGHT, 30, Color.WHITE);
        redoButton.setIcon(redoButtonIcon);
        redoButton.setPreferredSize(new Dimension(32,32));
        redoButton.setBackground(null);
        redoButton.setBorder(null);
        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redoButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(redoButton);

        // ==== Bouton enregistrer un projet =======
        FontIcon saveButtonIcon = FontIcon.of(BootstrapIcons.SAVE, 30, Color.WHITE);
        saveButton.setIcon(saveButtonIcon);
        saveButton.setPreferredSize(new Dimension(32,32));
        saveButton.setBackground(null);
        saveButton.setBorder(null);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(saveButton);

        // ==== Bouton supprimer un projet  =======
        FontIcon deleteButtonIcon = FontIcon.of(BootstrapIcons.TRASH_FILL, 30, Color.WHITE);
        deleteButton.setIcon(deleteButtonIcon);
        deleteButton.setPreferredSize(new Dimension(32,32));
        deleteButton.setBackground(null);
        deleteButton.setBorder(null);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(deleteButton);

        // ==== Bouton exporter un projet  =======
        FontIcon exportButtonIcon = FontIcon.of(BootstrapIcons.ARROW_BAR_RIGHT, 30, Color.WHITE);
        exportButton.setIcon(exportButtonIcon);
        exportButton.setPreferredSize(new Dimension(32,32));
        exportButton.setBackground(null);
        exportButton.setBorder(null);
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportButtonActionPerformed(e);
            }
        });
        boutonsTopPanel.add(exportButton);
        mainPanel.add(boutonsTopPanel, BorderLayout.NORTH);
        
        

        //======== barreOnglets ========

        barreOnglets.setPreferredSize(new Dimension(300, 900));
        barreOnglets.addTab("Hayon", makeTabPanel("Informations du hayon..."));
        barreOnglets.addTab("Plancher", makeTabPanel("Informations du plancher..."));
        barreOnglets.addTab("Mur Int.", makeTabPanel("Informations du mur intérieur..."));
        barreOnglets.addTab("Mur Ext.", makeTabPanel("Informations du mur extérieur..."));

        mainPanel.add(barreOnglets, BorderLayout.EAST);

        //======== barreOutils ========


        mainPanel.add(barreOutils, BorderLayout.WEST);

        //======== Panneau d'affichage ========

        mainScrollPane = new JScrollPane(centerPanel);

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

        GroupLayout panneauAffichageLayout = new GroupLayout(panneauAffichage);
        panneauAffichage.setLayout(panneauAffichageLayout);

        panneauAffichageLayout.setHorizontalGroup(
                panneauAffichageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 888, Short.MAX_VALUE)
        );
        panneauAffichageLayout.setVerticalGroup(
                panneauAffichageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 303, Short.MAX_VALUE)
        );

        mainScrollPane.setViewportView(panneauAffichage);
        mainPanel.add(mainScrollPane, BorderLayout.CENTER);

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

    protected void nouveauProjetActionPerformed(ActionEvent e) {
    }

    protected void sauvegarderProjetActionPerformed(ActionEvent e) {
    }

    protected void ouvrirProjetActionPerformed(ActionEvent e) {
    }

    protected void exporterProjetActionPerformed(ActionEvent e) {
    }

    protected void quitterActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    protected void undoActionPerformed(ActionEvent e) {
    }

    protected void redoActionPerformed(ActionEvent e) {
    }

    protected void supprimerActionPerformed(ActionEvent e) {
    }

    protected void zoomInActionPerformed(ActionEvent e) {
    }

    protected void zoomOutActionPerformed(ActionEvent e) {
    }

    protected void optionsActionPerformed(ActionEvent e) {
    }

    protected void aboutActionPerformed(ActionEvent e) {
    }

    protected void showHayonActionPerformed(ActionEvent e) {
    }

    protected void showPlancherActionPerformed(ActionEvent e) {
    }

    protected void showMurIntActionPerformed(ActionEvent e) {
    }


    private void quitMenuItemActionPerformed(ActionEvent e) {//GEN-FIRST:event_quitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitMenuItemActionPerformed

    private void drawingPanelMousePressed(MouseEvent e) {//GEN-FIRST:event_drawingPanelMousePressed
        Point mousePoint = panneauAffichage.getPosition(e.getPoint());
        TypeComposante composanteChoisie = this.composanteChoisie;
        this.controller.ajouterComposante(composanteChoisie,mousePoint);
        panneauAffichage.repaint();
    }//GEN-LAST:event_drawingPanelMousePressed

    private void drawingPanelMouseMoved(MouseEvent e) {//GEN-FIRST:event_drawingPanelMouseMoved
        this.actualMousePoint = new Point(e.getX(), e.getY());
        this.positionSouris.setText("Position (" + e.getX() + "," + e.getY() + ")");
    }//GEN-LAST:event_drawingPanelMouseMoved

    private void drawingPanelMouseWheelMoved(MouseWheelEvent e) {//GEN-FIRST:event_drawingPanelMouseWheelMoved

        int wheelRotation = e.getWheelRotation();
        Point mousePoint = new Point(e.getX(),e.getY());

        panneauAffichage.setScale(wheelRotation);
        panneauAffichage.setCenter(mousePoint);
        panneauAffichage.repaint();
    }//GEN-LAST:event_drawingPanelMouseWheelMoved

    public void setAction(TypeAction newAction) {
        this.actionChoisie = newAction;
    }

    private static JPanel makeTabPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new Label(text), BorderLayout.CENTER);
        return panel;
    }

}


