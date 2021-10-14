package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

public class PoutreArriere extends Composante{
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;

    public PoutreArriere(RoulotteController parent) {
        super(parent);
    }
}

