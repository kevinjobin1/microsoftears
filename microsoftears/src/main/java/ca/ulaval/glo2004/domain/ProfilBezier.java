package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;

import java.util.LinkedList;

//à repenser peut-être (ajout d'une classe pointBezier?)
public class ProfilBezier extends Composante{

    private LinkedList<PointPouce> pointsBezier;
    private PointPouce[] pointsControle;

    public ProfilBezier(RoulotteController parent, LinkedList<PointPouce> pointsBezier, int nombrePointsControle) {
        super(parent);
        this.pointsBezier = pointsBezier;
        this.setType(TypeComposante.PROFIL_BEZIER);
        this.setPolygone(new Polygone(this.pointsBezier));
        this.pointsControle = new PointPouce[nombrePointsControle];
    }

    //todo
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
