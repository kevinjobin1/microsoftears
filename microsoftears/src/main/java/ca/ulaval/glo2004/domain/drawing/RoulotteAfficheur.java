package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.Composante;

public class RoulotteAfficheur {

    RoulotteController roulotte;
    Dimension dimensions;

    public RoulotteAfficheur(RoulotteController roulotte, Dimension dimensions) {
        this.roulotte = roulotte;
        this.dimensions = dimensions;
    }

    //todo: tout afficher
    public void afficher(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(roulotte.origineX,roulotte.origineY,(int) (roulotte.width/roulotte.scale), (int) (roulotte.height/roulotte.scale));
        g2d.setColor(Color.gray);
        g2d.fillRect((int) (roulotte.origineX + (250/roulotte.scale)), (int) (roulotte.origineY + (250/roulotte.scale)),(int) (250/roulotte.scale),(int) (250/roulotte.scale));
        AffineTransform oldAf = g2d.getTransform();
        AffineTransform af = new AffineTransform(oldAf);
        af.scale(roulotte.zoom, roulotte.zoom);
        af.translate(-roulotte.origineX, -roulotte.origineY);
        g2d.setTransform(af);
        afficherPolygone(g);
        afficherRessort(g);
        afficherBezier(g);
    }

    //todo: lier les points de la liste du polygone par une ligne
    private void afficherPolygone(Graphics g){
        // on itéré sur la liste des polygones et appelé leur méthode "afficher"
    }

    //todo: représenter les points des 2 ressorts par des petits cercles
    private void afficherRessort(Graphics g){
    }

    //todo: représenter les points par des petits cercles et lier les points par une ligne
    private void afficherBezier(Graphics g){
    }

}

