package ca.ulaval.glo2004.utilitaires;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

public class Polygone {

    private LinkedList<PointPouce> listePoints;

    public Polygone(LinkedList<PointPouce> listePoints) {
        super();
        this.listePoints = listePoints;
    }

    public LinkedList<PointPouce> getListePoints() {
        return listePoints;
    }

    public void setListePoints(LinkedList<PointPouce> listePoints) {
        this.listePoints = listePoints;
    }

}
