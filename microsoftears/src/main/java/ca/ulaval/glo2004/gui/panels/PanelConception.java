package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.gui.barres.BarreOutils;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import ca.ulaval.glo2004.gui.afficheur.PanneauAffichage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * @author Kevin
 * @since 26/10/2021
 * @version 1.0
 * 
 * Panel qui regroupe le panneau d'affichage et le JScrollPane
 * 
 */
public class PanelConception extends JPanel
{
    public FenetrePrincipale parent;
    public PanneauAffichage panneauAffichage;
    public BarreOutils barreOutils;
    public JScrollPane mainScrollPane;


    public PanelConception(FenetrePrincipale parent)
    {
        this.parent = parent;
        this.panneauAffichage = new PanneauAffichage(parent);
        this.barreOutils = new BarreOutils(parent);
        this.initialiser();

    }

    private void initialiser()
    {
        // Organisation du Layout en grille
        GridBagConstraints layout = new GridBagConstraints();
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());

        JPanel bordurePanel = new JPanel(new BorderLayout());
        bordurePanel.setOpaque(false);
        bordurePanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        mainScrollPane = new JScrollPane(panneauAffichage);
        //mainScrollPane.setViewportView(panneauAffichage);
        mainScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        bordurePanel.add(mainScrollPane);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.weightx = 0;
        layout.weighty = 0;
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.anchor = GridBagConstraints.NORTH;

        this.add(barreOutils, layout);

        layout.gridx = 1;
        layout.gridy = 0;
        layout.weightx = 1;
        layout.weighty = 1;
        layout.fill = GridBagConstraints.BOTH;
        layout.anchor = GridBagConstraints.CENTER;

        this.add(bordurePanel, layout);

        //======== ActionListeners PanneauAffichage =========
        this.panneauAffichage.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                panneauAffichageMousePressed(e);
            }
        });

        this.panneauAffichage.addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                panneauAffichageMouseWheelMoved(e);
            }
        });

        this.panneauAffichage.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                panneauAffichageMouseMoved(e);
            }
        });

        this.panneauAffichage.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                panneauAffichageMouseDragged(e);
            }
        });


    }

    private void panneauAffichageMouseDragged(MouseEvent e) {
        if (parent.getActionChoisie() == FenetrePrincipale.TypeAction.SELECTION) {
            parent.controller.dragSurPlan(e.getPoint());
            this.parent.updateBarres();
            this.panneauAffichage.repaint();
        }
    }

    private void panneauAffichageMousePressed(MouseEvent e) {
        this.parent.controller.clicSurPlan(e.getPoint(), parent.getActionChoisie());
        this.parent.updateBarres();
        this.panneauAffichage.repaint();
    }

    private void panneauAffichageMouseMoved(MouseEvent e) {
        this.parent.controller.setPositionSouris(e.getPoint());
        this.panneauAffichage.repaint();
/*        this.parent.controller.setDimension(panneauAffichage.getSize());
        String position = "Position (" + e.getX() + "," + e.getY() + ") ";
        String centre = " Centre Plan(" + (int) parent.controller.centrePlanX + "," + (int) parent.controller.centrePlanY + ")";
        String dimensionPlan = " Dimension Plan (" + (int) parent.controller.largeurPlan + "," + (int) parent.controller.hauteurPlan + ")";
        String dimensionAfficheur = " Dimension Afficheur (" + panneauAffichage.getWidth() + "," + panneauAffichage.getHeight() + ")";
        this.parent.infoLabel.setText(position + centre + dimensionPlan + dimensionAfficheur);*/
    }

    private void panneauAffichageMouseWheelMoved(MouseWheelEvent e) {
        this.parent.controller.setPositionSouris(e.getPoint());
        this.parent.controller.setScale(e.getWheelRotation());
        this.panneauAffichage.repaint();
       /* this.parent.controller.setDimension(panneauAffichage.getSize());
        String position = "Position (" + e.getX() + "," + e.getY() + ") ";
        String centre = " Centre Plan (" + (int) parent.controller.centrePlanX + "," + (int) parent.controller.centrePlanY + ")";
        String dimensionPlan = " Dimension Plan (" + (int) parent.controller.largeurPlan + "," + (int) parent.controller.hauteurPlan + ")";
        String dimensionAfficheur = " Dimension Afficheur (" + panneauAffichage.getWidth() + "," + panneauAffichage.getHeight() + ")";
        this.parent.infoLabel.setText(position + centre + dimensionPlan + dimensionAfficheur);*/
    }



}

