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
        //à coder
        return null;
    }
}
