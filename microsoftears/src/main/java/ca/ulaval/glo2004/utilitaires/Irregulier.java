package ca.ulaval.glo2004.utilitaires;


import java.util.LinkedList;

// NE PAS ENLEVER *** kJ
public class Irregulier extends Forme {

    public Irregulier(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    @Override
    protected LinkedList<PointPouce> getListePoints() {
        return null;
    }

}
