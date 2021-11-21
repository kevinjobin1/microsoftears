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
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

public class RoulotteAfficheur
{

    private final RoulotteController roulotte;
    private double largeur;
    private double hauteur;
    
    public RoulotteAfficheur(RoulotteController roulotte, Dimension dimensionPanneau)
    {
        this.roulotte = roulotte;
        this.largeur = dimensionPanneau.getWidth();
        this.hauteur = dimensionPanneau.getHeight();
    }

    //todo rendre ça propre
    public void afficher(Graphics2D g2d)
    {
        afficherTousPolygones(g2d);
        afficherPointClic(g2d);
        AffineTransform af = new AffineTransform();
        af.scale(roulotte.scale, roulotte.scale);
        g2d.setTransform(af);
    }

    private void afficherTousPolygones(Graphics2D g2d)
    {
        ArrayList<Composante> composantes = roulotte.getListeComposantes();
        if (!composantes.isEmpty()) {
            for (Composante composante : composantes) {
              composante.afficher(g2d);
            }
        }
    }
    // TODO: à retirer
    private void afficherPointClic(Graphics2D g2d){
        PointPouce mousePoint = roulotte.mousePoint;
        int x = (int) (roulotte.xVersEcran(mousePoint.getX()));
        int y = (int) (roulotte.yVersEcran(mousePoint.getY()));
        int rayon = 5;
        g2d.setColor(Color.RED);
        g2d.drawOval(x, y, rayon, rayon);
        g2d.fillOval(x,y,rayon,rayon);
    }


    //todo pourrait être pratique
    public void afficherComposante(Graphics2D g2d, Composante composante){

    }
    //todo mais pas pour le livrable 3
    private void afficherRessort(Graphics g){
    }
    
    //todo mais pas pour le livrable 3
    public static void afficherBezier() {

    }



}

