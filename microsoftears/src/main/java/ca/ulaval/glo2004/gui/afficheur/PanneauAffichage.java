package ca.ulaval.glo2004.gui.afficheur;

import ca.ulaval.glo2004.domain.drawing.RoulotteAfficheur;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import ca.ulaval.glo2004.utilitaires.PointPouce;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import javax.swing.*;

public class PanneauAffichage extends JPanel implements Serializable {

    public Dimension initialDimension;
    private FenetrePrincipale parent;
    private int width = 1035;
    private int height = 737;
    

    public PanneauAffichage(FenetrePrincipale parent) {
        this.parent = parent;
        setVisible(true);
        initialDimension = new Dimension(width, height);
        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (parent != null){
            super.paintComponent(g);
            RoulotteAfficheur afficheurRoulotte = new RoulotteAfficheur(parent.controller, new Dimension(width, height));
            Graphics2D g2d = (Graphics2D) g;
            this.setAntiAlias(g2d, true);
            afficheurRoulotte.afficher(g2d);

    }
    }

    public static void setAntiAlias(Graphics2D g2d, boolean isAntiAlias) {
        RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                isAntiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(renderHints);
    }

    public FenetrePrincipale getMainWindow(){
        return parent;
    }

    public void setMainWindow(FenetrePrincipale parent){
        this.parent = parent;
    }

    public Dimension getInitialDimension(){
        return initialDimension;
    }

    public void setInitialDimension(Dimension newDimension){
        this.initialDimension = newDimension;
    }

    public PointPouce getPositionReelle(Point position) {
        return new PointPouce();
    }

    public Point getPositionGrille(Point position) {
        return new Point();
    }

    public int getRatio() {
        return parent.controller.pixelsToInchesRatio;
    }
}



