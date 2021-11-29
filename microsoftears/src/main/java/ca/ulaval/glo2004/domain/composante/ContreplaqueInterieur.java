package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
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
        // TODO:
        /*LinkedList<PointPouce> contreplaqueExterieur = parent.getContreplaqueExterieur().getPolygone().getListePoints();
        LinkedList<PointPouce> plancher = parent.getPlancher().getPolygone().getListePoints();
        LinkedList<PointPouce> toit = parent.getToit().getPolygone().getListePoints();
        LinkedList<PointPouce> murSeparateur = parent.getMurSeparateur().getPolygone().getListePoints();
        LinkedList<PointPouce> poutreArriere = parent.getPoutreArriere().getPolygone().getListePoints();
        */
        return null;
    }

    @Override
    public PointPouce getCentre() {
        return null;
    }


    @Override
    public int[] getValeurs() {
        return new int[0];
    }

    @Override
    public void translate(PointPouce delta) {

    }


    @Override
    public String[] getNomsAttributs() {
        return new String[0];
    }
}
