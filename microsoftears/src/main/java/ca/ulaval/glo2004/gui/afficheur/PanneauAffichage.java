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

    protected final FenetrePrincipale parent;

    public PanneauAffichage(FenetrePrincipale parent) {
        this.parent = parent;
        setVisible(true);
        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (parent != null){
            super.paintComponent(g);
            RoulotteAfficheur afficheurRoulotte = new RoulotteAfficheur(parent.controller, this.getSize());
            Graphics2D g2d = (Graphics2D) g;
            afficheurRoulotte.afficher(g2d);
            this.setAntiAlias(g2d, true);
    }
    }

    /**
     * Sert à appliquer ou non l'antialiasing de notre objet Graphics2d pour en améliorer la qualité du rendu
     * @param g2d (Graphics2d) notre objet graphique
     * @param isAntiAlias (boolean) un booléen correspondant à On (true) / Off (false)
     */

    public static void setAntiAlias(Graphics2D g2d, boolean isAntiAlias) {
            // On/Off selon la valeur de isAntiAlias passé en paramètre
            RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    isAntiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);

            // On applique le render à notre objet Graphics2d
            renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHints(renderHints);
    }

}



