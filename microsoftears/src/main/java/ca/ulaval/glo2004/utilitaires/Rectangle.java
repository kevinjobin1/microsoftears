package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Rectangle extends Forme{

    double angle = 0;
    public Rectangle(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    public Rectangle(Pouce longueur, Pouce hauteur, PointPouce centre, double angle){
        super(longueur, hauteur, centre);
        this.angle = angle;
    }
    public Rectangle(Pouce longueur, Pouce hauteur) {
        super(longueur, hauteur, new PointPouce(longueur.diviser(2), hauteur.diviser(2)));
    }

    @Override
    public Polygone getPolygone() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();

        //coin en haut à droite
        listePoints.add(new PointPouce(getCentre().getX().add(getLongueur().diviser(2).multiplier(Math.cos(angle))).
                add(getHauteur().diviser(2).multiplier(Math.sin(angle))),
                getCentre().getY().add(getLongueur().diviser(2).multiplier(Math.sin(angle))).
                        diff(getHauteur().diviser(2).multiplier(Math.cos(angle)))));

        //coin en haut à gauche
        listePoints.add(new PointPouce(getCentre().getX().diff(getLongueur().diviser(2).multiplier(Math.cos(angle))).
                add(getHauteur().diviser(2).multiplier(Math.sin(angle))),
                getCentre().getY().diff(getLongueur().diviser(2).multiplier(Math.sin(angle))).
                        diff(getHauteur().diviser(2).multiplier(Math.cos(angle)))));

        //coin en bas à gauche
        listePoints.add(new PointPouce(getCentre().getX().diff(getLongueur().diviser(2).multiplier(Math.cos(angle))).
                diff(getHauteur().diviser(2).multiplier(Math.sin(angle))),
                getCentre().getY().diff(getLongueur().diviser(2).multiplier(Math.sin(angle))).
                        add(getHauteur().diviser(2).multiplier(Math.cos(angle)))));

        //coin en bas à droite
        listePoints.add(new PointPouce(getCentre().getX().add(getLongueur().diviser(2).multiplier(Math.cos(angle))).
                diff(getHauteur().diviser(2).multiplier(Math.sin(angle))),
                getCentre().getY().add(getLongueur().diviser(2).multiplier(Math.sin(angle))).
                        add(getHauteur().diviser(2).multiplier(Math.cos(angle)))));

        return new Polygone(listePoints);
    }
}
