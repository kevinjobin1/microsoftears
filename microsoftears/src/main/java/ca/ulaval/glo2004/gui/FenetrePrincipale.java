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
    final Color couleurs[] = { Color.RED, Color.BLUE, Color.BLACK, Color.WHITE };
    // Dimensions
    private final Dimension DIMENSIONS_MINIMALES = new Dimension(1080,720);

    // Composantes graphiques du Frame
    private JPanel mainPanel,
                 bottomPanel,
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
        bottomPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        
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
        barreOnglets.addTab("Hayon", makeTabPanel("Informations du hayon..."));
        barreOnglets.addTab("Plancher", makeTabPanel("Informations du plancher..."));
        barreOnglets.addTab("Mur Int.", makeTabPanel("Informations du mur intérieur..."));
        barreOnglets.addTab("Mur Ext.", makeTabPanel("Informations du mur extérieur..."));

        mainPanel.add(barreOnglets, BorderLayout.EAST);

        //======== barreOutils ========


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

    public void nouveauProjetActionPerformed(ActionEvent e) {
    }

    public void sauvegarderProjetActionPerformed(ActionEvent e) {
    }

    public void ouvrirProjetActionPerformed(ActionEvent e) {
    }

    public void exporterProjetActionPerformed(ActionEvent e) {
    }

    public void quitterActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public void undoActionPerformed(ActionEvent e) {
    }

    public void redoActionPerformed(ActionEvent e) {
    }

    public void supprimerActionPerformed(ActionEvent e) {
    }

    public void zoomInActionPerformed(ActionEvent e) {
    }

    public void zoomOutActionPerformed(ActionEvent e) {
    }

    public void optionsActionPerformed(ActionEvent e) {
    }

    public void aboutActionPerformed(ActionEvent e) {
    }

    public void showHayonActionPerformed(ActionEvent e) {
    }

    public void showPlancherActionPerformed(ActionEvent e) {
    }

    public void showMurIntActionPerformed(ActionEvent e) {
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
        this.positionSouris.setText("Position (" + e.getX() + "," + e.getY() + ")");
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

    private static JPanel makeTabPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new Label(text), BorderLayout.CENTER);
        return panel;
    }

}


