package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Ellipse extends Forme {

    private double nombre_points;  //de préférence un multiple de 4

    public Ellipse(Pouce longueur, Pouce hauteur, PointPouce centre, double nombrePoints) {
        super(longueur, hauteur, centre);
        this.nombre_points = nombrePoints;
    }

    public double getNombrePoints() {
        return this.nombre_points;
    }

    @Override
    public Polygone getPolygone() {

        LinkedList<PointPouce> listePoints = new LinkedList<>();
        for(int i = 0; i < nombre_points; i++) {
            Pouce x = getLongueur().diviser(2).multiplier(Math.cos(Math.toRadians(i*360/nombre_points))).add(getCentre().getX());
            Pouce y = getHauteur().diviser(2).multiplier(-Math.sin(Math.toRadians(i*360/nombre_points))).add(getCentre().getY());
            listePoints.add(new PointPouce(x, y));
        }
        return new Polygone(listePoints);
    }
}
