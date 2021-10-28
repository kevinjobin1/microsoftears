package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

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

    //todo rendre ça propre
    public void afficher(Graphics2D g2d)
    {
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

    private void afficherPolygones(Graphics2D g2d)
    {
        g2d.setColor(Color.BLUE);
        ArrayList<Composante> composantes = roulotte.getListeComposantes();
        if (!composantes.isEmpty()) {
            LinkedList<PointPouce> polygoneList;
            int x, y;
            GeneralPath path = new GeneralPath();
            for (Composante composante : composantes) {
                polygoneList = composante.getPolygone().getListePoints();
                for (int i = 0; i < polygoneList.size(); i++){
                    x = polygoneList.get(i).getX().toPixel();
                    y = polygoneList.get(i).getY().toPixel();
                    if(i == 0) {
                        path.moveTo(x, y);
                    }else{
                        path.lineTo(x ,y);
                    }
                }
                path.closePath();
                g2d.draw(path);
            }
        }
    }
    //à coder
    private void afficherRessort(Graphics g){
    }
    

    public static void afficherBezier() {

    }



}

