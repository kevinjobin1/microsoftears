package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import ca.ulaval.glo2004.domain.composante.Composante;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.composante.MurProfile;
import ca.ulaval.glo2004.domain.composante.ProfilBezier;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;

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
        AffineTransform af = new AffineTransform();
        af.scale(roulotte.getScale(), roulotte.getScale());
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

