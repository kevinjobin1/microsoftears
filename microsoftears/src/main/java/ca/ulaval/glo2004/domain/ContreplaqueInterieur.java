package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.LinkedList;

public class ContreplaqueInterieur extends Composante{
    private Pouce epaisseur;

    //todo pas necessaire pour le livrable 3
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

    public Polygone getPolygone(){
        LinkedList<PointPouce> contreplaqueExterieur = parent.getContreplaqueExterieur().getPolygone().getListePoints();
        LinkedList<PointPouce> plancher = parent.getPlancher().getPolygone().getListePoints();
        LinkedList<PointPouce> toit = parent.getToit().getPolygone().getListePoints();
        LinkedList<PointPouce> murSeparateur = parent.getMurSeparateur().getPolygone().getListePoints();
        LinkedList<PointPouce> poutreArriere = parent.getPoutreArriere().getPolygone().getListePoints();
        return null;
    }
}
