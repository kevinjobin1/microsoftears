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
    public LinkedList<PointPouce> getPointsContour() {
        //à coder
        return null;
    }
}
