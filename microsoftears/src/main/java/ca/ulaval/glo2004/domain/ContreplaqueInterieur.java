package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Pouce;

public class ContreplaqueInterieur {
    private Pouce epaisseur;

    public ContreplaqueInterieur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }
}
