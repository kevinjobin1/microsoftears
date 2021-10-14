package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Pouce;

public class MurSeparateur extends Composante{

    private Pouce epaisseur;
    private Pouce hauteur;
    private Pouce distancePoutreArriere;


    public MurSeparateur(RoulotteController parent) {
        super(parent);
    }
}
