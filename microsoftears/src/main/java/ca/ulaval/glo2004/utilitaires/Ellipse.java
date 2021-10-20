package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Ellipse extends Forme {

    public static int NOMBRE_POINTS = 1000;

    public Ellipse(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    @Override
    //à tester
    public Polygone getPolygone() {

        LinkedList<PointPouce> listePoints = new LinkedList<>();
        for(int i = 0; i < NOMBRE_POINTS; i++) {
            double x, y;
            x = this.getHauteur().toDouble() / 2 * Math.sin(Math.toRadians(i*360/NOMBRE_POINTS)) + this.getCentre().getX().toDouble();
            y = this.getLongueur().toDouble() / 2 * Math.cos(Math.toRadians(i*360/NOMBRE_POINTS)) + this.getCentre().getY().toDouble();
            listePoints.add(new PointPouce(new Pouce(x), new Pouce(y)));
        }
        return new Polygone(listePoints);
    }
}
