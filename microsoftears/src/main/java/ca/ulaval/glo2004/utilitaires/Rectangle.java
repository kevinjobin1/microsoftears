package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Rectangle extends Forme{
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;

    public Rectangle(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    public Rectangle() {
    }

    @Override
    public LinkedList<PointPouce> getPointsContour() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();

        //coin en haut à droite
        listePoints.add(new PointPouce(
                new Pouce(centre.getX().toDouble()+longueur.toDouble()/2, true),
                new Pouce(centre.getY().toDouble()-hauteur.toDouble()/2, true))
        );

        //coin en haut à gauche
        listePoints.add(new PointPouce(
                new Pouce(centre.getX().toDouble()-longueur.toDouble()/2, true),
                new Pouce(centre.getY().toDouble()-hauteur.toDouble()/2, true))
        );

        //coin en bas à gauche
        listePoints.add(new PointPouce(
                new Pouce(centre.getX().toDouble()-longueur.toDouble()/2, true),
                new Pouce(centre.getY().toDouble()+hauteur.toDouble()/2, true))
        );

        //coin en bas à droite
        listePoints.add(new PointPouce(
                new Pouce(centre.getX().toDouble()+longueur.toDouble()/2, true),
                new Pouce(centre.getY().toDouble()+hauteur.toDouble()/2, true))
        );

        return listePoints;
    }
}
