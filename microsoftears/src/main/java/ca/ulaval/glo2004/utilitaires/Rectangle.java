package ca.ulaval.glo2004.utilitaires;

import java.util.ArrayList;
import java.util.LinkedList;

public class Rectangle extends Forme{

    double angle = 0;
    public Rectangle(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    @Override
    protected LinkedList<PointPouce> getListePoints() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();
        if (angle != 0) {
            //coin en haut à droite
            // x = centreX + ((longueur/2) * cos(angle)) + ((hauteur/2) * sin(angle))
            // y = centreY + ((longueur/2) * sin(angle)) + ((hauteur/2) * cos(angle))
            listePoints.add(new PointPouce(getCentre().getX().add(getLongueur().diviser(2).multiplier(Math.cos(angle))).
                    add(getHauteur().diviser(2).multiplier(Math.sin(angle))),
                    getCentre().getY().add(getLongueur().diviser(2).multiplier(Math.sin(angle))).
                            diff(getHauteur().diviser(2).multiplier(Math.cos(angle)))));

            //coin en haut à gauche
            // x = centreX + ((longueur/2) * cos(angle)) + ((hauteur/2) * sin(angle))
            // y = centreY + ((longueur/2) * sin(angle)) + ((hauteur/2) * cos(angle))
            listePoints.add(new PointPouce(getCentre().getX().diff(getLongueur().diviser(2).multiplier(Math.cos(angle))).
                    add(getHauteur().diviser(2).multiplier(Math.sin(angle))),
                    getCentre().getY().diff(getLongueur().diviser(2).multiplier(Math.sin(angle))).
                            diff(getHauteur().diviser(2).multiplier(Math.cos(angle)))));

            //coin en bas à gauche
            // x = centreX + ((longueur/2) * cos(angle)) + ((hauteur/2) * sin(angle))
            // y = centreY + ((longueur/2) * sin(angle)) + ((hauteur/2) * cos(angle))
            listePoints.add(new PointPouce(getCentre().getX().diff(getLongueur().diviser(2).multiplier(Math.cos(angle))).
                    diff(getHauteur().diviser(2).multiplier(Math.sin(angle))),
                    getCentre().getY().diff(getLongueur().diviser(2).multiplier(Math.sin(angle))).
                            add(getHauteur().diviser(2).multiplier(Math.cos(angle)))));

            //coin en bas à droite
            // x = centreX + ((longueur/2) * cos(angle)) + ((hauteur/2) * sin(angle))
            // y = centreY + ((longueur/2) * sin(angle)) + ((hauteur/2) * cos(angle))
            listePoints.add(new PointPouce(getCentre().getX().add(getLongueur().diviser(2).multiplier(Math.cos(angle))).
                    diff(getHauteur().diviser(2).multiplier(Math.sin(angle))),
                    getCentre().getY().add(getLongueur().diviser(2).multiplier(Math.sin(angle))).
                            add(getHauteur().diviser(2).multiplier(Math.cos(angle)))));
        }
        else {
            // Haut droite = (centreX + long/2 , centreY - haut/2)
            listePoints.add(new PointPouce(getCentre().getX().add(getLongueur().diviser(2)),
                    getCentre().getY().diff(getHauteur().diviser(2))));

            // Haut gauche = (centreX - long/2 , centreY - haut/2)
            listePoints.add(new PointPouce(getCentre().getX().diff(getLongueur().diviser(2)),
                    getCentre().getY().diff(getHauteur().diviser(2))));

            // Bas gauche = (centreX - long/2 , centreY + haut/2)
            listePoints.add(new PointPouce(getCentre().getX().diff(getLongueur().diviser(2)),
                    getCentre().getY().add(getHauteur().diviser(2))));

            // Bas droite = (centreX + long/2 , centreY + haut/2)
            listePoints.add(new PointPouce(getCentre().getX().add(getLongueur().diviser(2)),
                    getCentre().getY().add(getHauteur().diviser(2))));

        }
        return listePoints;
    }

    public Rectangle(Pouce longueur, Pouce hauteur, PointPouce centre, double angle){
        super(longueur, hauteur, centre);
        this.angle = angle;
    }

    public PointPouce getMax(){
        return this.getPolygone().getListePoints().get(3);
    }

    public PointPouce getMin(){
        return this.getPolygone().getListePoints().get(1);
    }
    
    public PointPouce getCoinPlusPres(PointPouce point){
        int indexCoin;
        // à gauche
        if(point.getX().ste(getCentre().getX())){
            // en haut
            if(point.getY().ste(getCentre().getY())){
               indexCoin = 1;
            }
            // en bas
            else {
                indexCoin = 2;
            }
        }
        // à droite
        else {
            // en haut
            if(point.getY().ste(getCentre().getY())){
                indexCoin = 0;
            }
            // en bas
            else {
                indexCoin = 3;
            }
        }
    return this.getPolygone().getListePoints().get(indexCoin);

    }

}
