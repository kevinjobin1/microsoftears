package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Rectangle extends Forme{

    public Rectangle(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    public Rectangle(Pouce longueur, Pouce hauteur) {
        super(longueur, hauteur, new PointPouce(longueur.diviser(2), hauteur.diviser(2)));
    }

    @Override
    public Polygone getPolygone() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();

        //coin en haut à droite
        listePoints.add(new PointPouce(
                new Pouce(this.getCentre().getX().toDouble()+this.getLongueur().toDouble()/2),
                new Pouce(this.getCentre().getY().toDouble()-this.getHauteur().toDouble()/2))
        );

        //coin en haut à gauche
        listePoints.add(new PointPouce(
                new Pouce(this.getCentre().getX().toDouble()-this.getLongueur().toDouble()/2),
                new Pouce(this.getCentre().getY().toDouble()-this.getHauteur().toDouble()/2))
        );

        //coin en bas à gauche
        listePoints.add(new PointPouce(
                new Pouce(this.getCentre().getX().toDouble()-this.getLongueur().toDouble()/2),
                new Pouce(this.getCentre().getY().toDouble()+this.getHauteur().toDouble()/2))
        );

        //coin en bas à droite
        listePoints.add(new PointPouce(
                new Pouce(this.getCentre().getX().toDouble()+this.getLongueur().toDouble()/2),
                new Pouce(this.getCentre().getY().toDouble()+this.getHauteur().toDouble()/2))
        );

        return new Polygone(listePoints);
    }
}
