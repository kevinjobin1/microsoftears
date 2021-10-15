package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.ArrayList;

public class Profil extends Composante{

    private Ellipse[] ellipses = new Ellipse[4];
    private ArrayList<PointPouce> pointsBezier;

    public Profil(RoulotteController parent, Ellipse[] ellipses) {
        super(parent);
        this.ellipses = ellipses;
        this.setType(TypeComposante.PROFIL);
    }

    public Profil(RoulotteController parent, ArrayList<PointPouce> pointsBezier) {
        super(parent);
        this.pointsBezier = pointsBezier;
    }

    public Ellipse[] getEllipses() {
        return ellipses;
    }

    public void setEllipses(Ellipse[] ellipses) {
        this.ellipses = ellipses;
    }

    public ArrayList<PointPouce> getPointsBezier() {
        return pointsBezier;
    }

    public void setPointsBezier(ArrayList<PointPouce> pointsBezier) {
        this.pointsBezier = pointsBezier;
    }
}
