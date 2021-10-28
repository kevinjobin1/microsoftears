package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.Composante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
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

    //à tester
    private void afficherPolygones(Graphics2D g2d)
    {
        ArrayList<Composante> composantes = roulotte.getListeComposantes();
        if (!composantes.isEmpty()) {
            LinkedList<PointPouce> polygoneList;
            int x1, y1, x2, y2;
            for (Composante composante : composantes) {
                polygoneList = composante.getPolygone().getListePoints();
                for (int i = 0; i < polygoneList.size(); i++){
                    x1 = polygoneList.get(i).getX().toPixel();
                    y1 = polygoneList.get(i).getX().toPixel();
                    if (i+1 < polygoneList.size()) {
                        x2 = polygoneList.get(i + 1).getX().toPixel();
                        y2 = polygoneList.get(i + 1).getX().toPixel();
                    } else {
                        x2 = polygoneList.get(0).getX().toPixel();
                        y2 = polygoneList.get(0).getX().toPixel();
                    }
                    g2d.drawLine(x1,y1,x2,y2);
                }
            }
        }
    }
    //à coder
    private void afficherRessort(Graphics g){
    }
    

    public static void afficherBezier() {

    }



}

