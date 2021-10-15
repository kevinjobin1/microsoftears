package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.LinkedList;

public class Toit extends Composante{
    private Pouce epaisseur;

    public Toit(RoulotteController parent, Pouce epaisseur) {
        super(parent);
        this.epaisseur = epaisseur;
        this.setType(TypeComposante.TOIT);
        this.setPolygone(new Polygone(this.getPointsContour()));
    }

    public Toit(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(2,1,4);
        this.setType(TypeComposante.TOIT);
        this.setPolygone(new Polygone(this.getPointsContour()));
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    //à coder
    private LinkedList<PointPouce> getPointsContour(){
        return null;
    }
}
