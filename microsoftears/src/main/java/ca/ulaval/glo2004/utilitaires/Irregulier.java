package ca.ulaval.glo2004.utilitaires;


// NE PAS ENLEVER *** kJ
public class Irregulier extends Forme {

    public Irregulier(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    @Override
    public Polygone getPolygone() {
        return null;
    }
}
