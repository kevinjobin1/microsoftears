package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.TypeComposante;
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
    private Point actualMousePoint;

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
        GridBagConstraints gbc = new GridBagConstraints();
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());

        JPanel bordurePanel = new JPanel(new BorderLayout());
        bordurePanel.setOpaque(false);
        bordurePanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        mainScrollPane = new JScrollPane(panneauAffichage);
        mainScrollPane.setViewportView(panneauAffichage);
        mainScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        bordurePanel.add(mainScrollPane);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        this.add(barreOutils, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        this.add(bordurePanel, gbc);

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

    }

    private void panneauAffichageMousePressed(MouseEvent e) {
        Point mousePoint = parent.controller.getPosition(e.getPoint());
        TypeComposante composanteChoisie = this.parent.composanteChoisie;
        this.parent.controller.ajouterComposante(composanteChoisie,mousePoint);
        panneauAffichage.repaint();
    }

    private void panneauAffichageMouseMoved(MouseEvent e) {
        this.actualMousePoint = new Point(e.getX(), e.getY());
        this.parent.positionSouris.setText("Position (" + e.getX() + "," + e.getY() + ")");
    }

    private void panneauAffichageMouseWheelMoved(MouseWheelEvent e) {

        int wheelRotation = e.getWheelRotation();
        Point mousePoint = new Point(e.getX(),e.getY());
        parent.controller.setScale(wheelRotation, mousePoint);
        parent.controller.setCenter(mousePoint);
        panneauAffichage.repaint();
    }


}

