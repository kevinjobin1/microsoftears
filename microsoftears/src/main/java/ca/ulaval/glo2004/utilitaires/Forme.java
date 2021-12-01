package ca.ulaval.glo2004.utilitaires;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class Forme implements Serializable {
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;

    public Forme(Pouce longueur, Pouce hauteur, PointPouce centre) {
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centre = centre;
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

    public abstract Polygone getPolygone();
}
