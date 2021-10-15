package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;

import java.util.LinkedList;

public class ProfilBezier extends Composante{

    private LinkedList<PointPouce> pointsBezier;

    public ProfilBezier(RoulotteController parent, LinkedList<PointPouce> pointsBezier) {
        super(parent);
        this.pointsBezier = pointsBezier;
        this.setType(TypeComposante.PROFIL_BEZIER);
        this.setPolygone(new Polygone(this.pointsBezier));
    }

    //à compléter
    public ProfilBezier(RoulotteController parent) {
        super(parent);
        //this.pointsBezier = ;
        this.setType(TypeComposante.PROFIL_BEZIER);
        this.setPolygone(new Polygone(this.pointsBezier));
    }

    public LinkedList<PointPouce> getPointsBezier() {
        return pointsBezier;
    }

    public void setPointsBezier(LinkedList<PointPouce> pointsBezier) {
        this.pointsBezier = pointsBezier;
    }
}
