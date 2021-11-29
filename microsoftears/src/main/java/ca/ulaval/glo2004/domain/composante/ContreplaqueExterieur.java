package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.ArrayList;
import java.util.LinkedList;

public class ContreplaqueExterieur extends Composante {
    private Pouce epaisseur;



    //todo pas necessaire pour le livrable 3
    public ContreplaqueExterieur(Pouce epaisseur, RoulotteController parent) {
        super(parent);
        this.epaisseur = epaisseur;
        this.setType(TypeComposante.CONTREPLAQUE_EXTERIEUR);
    }

    public ContreplaqueExterieur(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(0,1,4);
        this.setType(TypeComposante.CONTREPLAQUE_EXTERIEUR);
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    public Polygone getPolygone(){
        ArrayList<OuvertureLaterale> ouverturesLaterales = parent.getListeOuverturesLaterales();
        LinkedList<PointPouce> hayon = parent.getListeComposantes().get(8).getPolygone().getListePoints();
        LinkedList<PointPouce> murProfile = parent.getListeComposantes().get(2).getPolygone().getListePoints();
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
