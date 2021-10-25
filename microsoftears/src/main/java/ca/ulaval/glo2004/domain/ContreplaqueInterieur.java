package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Pouce;

public class ContreplaqueInterieur extends Composante{
    private Pouce epaisseur;

    //todo
    public ContreplaqueInterieur(RoulotteController parent, Pouce epaisseur) {
        super(parent);
        this.epaisseur = epaisseur;
        this.setType(TypeComposante.CONTREPLAQUE_INTERIEUR);
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }
}
