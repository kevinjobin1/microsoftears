package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.util.List;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.Composante;

public class RoulotteAfficheur {

    RoulotteController roulotte;

    //todo: tout afficher
    public void afficher(Graphics g){
        afficherPolygone(g);
        afficherRessort(g);
        afficherBezier(g);
    }

    //todo: lier les points de la liste du polygone par une ligne
    private void afficherPolygone(Graphics g){
    }

    //todo: représenter les points des 2 ressorts par des petits cercles
    private void afficherRessort(Graphics g){
    }

    //todo: représenter les points par des petits cercles et lier les points par une ligne
    private void afficherBezier(Graphics g){
    }

}

