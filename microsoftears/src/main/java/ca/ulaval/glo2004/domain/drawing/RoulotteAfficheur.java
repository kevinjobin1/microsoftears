package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.Composante;
import ca.ulaval.glo2004.utilitaires.Polygone;

public class RoulotteAfficheur
{

    private final RoulotteController roulotte;
    private Dimension dimension;
    private int Pouce = 15;
    
    public RoulotteAfficheur(RoulotteController roulotte, Dimension dimension)
    {
        this.roulotte = roulotte;
        this.dimension = dimension;
    }

    public void afficher(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        afficherPlan(g2d);
        afficherPolygones(g2d);
        AffineTransform af = new AffineTransform();
        af.translate(roulotte.origineX, roulotte.origineY);
        af.scale(roulotte.zoom, roulotte.zoom);
        af.translate(-roulotte.origineX, -roulotte.origineY);
        g2d.setTransform(af);
    }

    private void afficherPlan(Graphics2D g2d) {
        Rectangle plan = new Rectangle(roulotte.origineX,roulotte.origineY,(int) (250/roulotte.scale), (int) (250/roulotte.scale));
        g2d.setColor(Color.YELLOW);
        g2d.fill(plan);
        g2d.draw(plan);
    }

    //à coder
    private void afficherPolygones(Graphics2D g2d)
    {
        ArrayList<Composante> composantes = roulotte.getListeComposantes();
        if (!composantes.isEmpty()) {
            for (Composante composante : composantes) {
                g2d.draw(composante);
            }
        }

    }

    //à coder
    private void afficherRessort(Graphics g){
    }
    

    public static void afficherBezier() {

    }



}

