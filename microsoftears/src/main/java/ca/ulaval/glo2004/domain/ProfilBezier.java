package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;

import java.util.LinkedList;

public class ProfilBezier extends Composante{

    private LinkedList<PointPouce> pointsBezier;

    //à coder
    public ProfilBezier(RoulotteController parent, LinkedList<PointPouce> pointsBezier) {
        super(parent);
        this.pointsBezier = pointsBezier;
    }

    //à coder
    public ProfilBezier(RoulotteController parent) {
        super(parent);
    }

    public LinkedList<PointPouce> getPointsBezier() {
        return pointsBezier;
    }

    public void setPointsBezier(LinkedList<PointPouce> pointsBezier) {
        this.pointsBezier = pointsBezier;
    }
}
