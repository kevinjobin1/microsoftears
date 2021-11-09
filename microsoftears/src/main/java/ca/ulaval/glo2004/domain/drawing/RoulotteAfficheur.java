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
import ca.ulaval.glo2004.utilitaires.Rectangle;

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
        afficherPlan(g2d);
        afficherTousPolygones(g2d);
        AffineTransform af = new AffineTransform();
        af.scale(roulotte.scale, roulotte.scale);
        g2d.setTransform(af);
    }

    private void afficherPlan(Graphics2D g2d) {
        System.out.println("Dimension: (" + largeur + "," + hauteur + ")");
        System.out.println("Centre: (" + largeur/2 + "," + hauteur/2 + ")");

        /*Rectangle2D.Double rectangle = new Rectangle2D.Double(0,0, largeur, hauteur);
        g2d.setColor(Color.YELLOW);
        g2d.fill(rectangle);
        g2d.draw(rectangle);*/
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

