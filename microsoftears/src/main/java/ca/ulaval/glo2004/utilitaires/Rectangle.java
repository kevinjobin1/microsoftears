package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Rectangle extends Forme{

    public Rectangle(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    @Override
    public Polygone getPolygone() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();

        //coin en haut à droite
        listePoints.add(new PointPouce(
                new Pouce(this.getCentre().getX().toDouble()+this.getLongueur().toDouble()/2, true),
                new Pouce(this.getCentre().getY().toDouble()-this.getHauteur().toDouble()/2, true))
        );

        //coin en haut à gauche
        listePoints.add(new PointPouce(
                new Pouce(this.getCentre().getX().toDouble()-this.getLongueur().toDouble()/2, true),
                new Pouce(this.getCentre().getY().toDouble()-this.getHauteur().toDouble()/2, true))
        );

        //coin en bas à gauche
        listePoints.add(new PointPouce(
                new Pouce(this.getCentre().getX().toDouble()-this.getLongueur().toDouble()/2, true),
                new Pouce(this.getCentre().getY().toDouble()+this.getHauteur().toDouble()/2, true))
        );

        //coin en bas à droite
        listePoints.add(new PointPouce(
                new Pouce(this.getCentre().getX().toDouble()+this.getLongueur().toDouble()/2, true),
                new Pouce(this.getCentre().getY().toDouble()+this.getHauteur().toDouble()/2, true))
        );

        return new Polygone(listePoints);
    }
}
