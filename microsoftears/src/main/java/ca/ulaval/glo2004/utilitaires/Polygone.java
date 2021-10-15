package ca.ulaval.glo2004.utilitaires;

import java.util.LinkedList;

public class Polygone {

    private LinkedList listePoints;

    public Polygone(LinkedList listePoints) {
        this.listePoints = listePoints;
    }

    public Polygone() {
    }

    public LinkedList getListePoints() {
        return listePoints;
    }

    public void setListePoints(LinkedList listePoints) {
        this.listePoints = listePoints;
    }
}
