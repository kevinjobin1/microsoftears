package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Ellipse extends Forme {


    public Ellipse(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    @Override
    //à tester
    public Polygone getPolygone() {
        int nombrePoints = 1000;
        LinkedList<PointPouce> listePoints = new LinkedList<>();
        for(int i = 0; i < nombrePoints; i++) {
            double x, y;
            x = this.getHauteur().toDouble() / 2 * Math.sin(Math.toRadians(i/360)) + this.getCentre().getX().toDouble();
            y = this.getLongueur().toDouble() / 2 * Math.cos(Math.toRadians(i/360)) + this.getCentre().getY().toDouble();
            listePoints.add(new PointPouce(new Pouce(x, true), new Pouce(y, true)));
        }
        return new Polygone(listePoints);
    }
}
