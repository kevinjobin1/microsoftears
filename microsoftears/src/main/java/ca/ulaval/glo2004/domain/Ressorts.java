package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

public class Ressorts extends Composante{
    private double poidsHayon;
    private PointPouce position1;
    private PointPouce position2;
    private double force;
    private Pouce longueur;

    public Ressorts(RoulotteController parent) {
        super(parent);
    }
}
