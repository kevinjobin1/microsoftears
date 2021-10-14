package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public abstract class Forme {
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;
    private Polygone polygone;

    public Forme(Pouce longueur, Pouce hauteur, PointPouce centre) {
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centre = centre;
        this.polygone = new Polygone(this.getPointsContour());
    }

    public Pouce getLongueur() {
        return longueur;
    }

    public void setLongueur(Pouce longueur) {
        this.longueur = longueur;
    }

    public Pouce getHauteur() {
        return hauteur;
    }

    public void setHauteur(Pouce hauteur) {
        this.hauteur = hauteur;
    }

    public PointPouce getCentre() {
        return centre;
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }

    public Polygone getPolygone() {
        return polygone;
    }

    public void setPolygone(Polygone polygone) {
        this.polygone = polygone;
    }

    public abstract LinkedList<PointPouce> getPointsContour();
}
