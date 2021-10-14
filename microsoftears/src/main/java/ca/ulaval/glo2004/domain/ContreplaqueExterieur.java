package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Pouce;

public class ContreplaqueExterieur extends Composante {
    private Pouce epaisseur;

    public ContreplaqueExterieur(Pouce epaisseur, RoulotteController parent) {
        super(parent);
        this.epaisseur = epaisseur;
    }

    public ContreplaqueExterieur(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(0,1,4);
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }
}
