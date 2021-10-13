package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Pouce;

public class ContreplaqueExterieur {
    private Pouce epaisseur;

    public ContreplaqueExterieur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    public ContreplaqueExterieur() {
        this.epaisseur = new Pouce(0,1,4);
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }
}
