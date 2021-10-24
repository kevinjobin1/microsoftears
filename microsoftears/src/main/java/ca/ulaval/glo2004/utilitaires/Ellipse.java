package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Ellipse extends Forme {

    public static final int NOMBRE_POINTS = 1000;  //de préférence un multiple de 4

    public Ellipse(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    @Override
    public Polygone getPolygone() {

        LinkedList<PointPouce> listePoints = new LinkedList<>();
        for(int i = 0; i < NOMBRE_POINTS; i++) {
            Pouce x = getLongueur().diviser(2).multiplier(Math.cos(Math.toRadians(i*360/NOMBRE_POINTS))).add(getCentre().getX());
            Pouce y = getHauteur().diviser(2).multiplier(-Math.sin(Math.toRadians(i*360/NOMBRE_POINTS))).add(getCentre().getY());
            listePoints.add(new PointPouce(x, y));
        }
        return new Polygone(listePoints);
    }
}
