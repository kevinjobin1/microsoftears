package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Ellipse extends Forme {
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;

    public Ellipse(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    public Ellipse() {
    }

    @Override
    //à tester
    public LinkedList<PointPouce> getPointsContour() {
        int nombrePoints = 1000;
        LinkedList<PointPouce> listePoints = new LinkedList<>();
        for(int i = 0; i < nombrePoints; i++) {
            double x, y;
            x = hauteur.toDouble() / 2 * Math.sin(Math.toRadians(i/360)) + centre.getX().toDouble();
            y = longueur.toDouble() / 2 * Math.cos(Math.toRadians(i/360)) + centre.getY().toDouble();
            listePoints.add(new PointPouce(new Pouce(x), new Pouce(y)));
        }
        return listePoints;
    }
}
