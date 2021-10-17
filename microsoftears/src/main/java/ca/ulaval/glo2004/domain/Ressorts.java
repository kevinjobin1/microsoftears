package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

public class Ressorts extends Composante{
    private double poidsHayon;
    private PointPouce[] position = new PointPouce[2];
    private Pouce longueur;
    private double force;

    public Ressorts(RoulotteController parent, double poidsHayon) {
        super(parent);
        this.poidsHayon = poidsHayon;
    }

    public Ressorts(RoulotteController parent) {
        super(parent);
        this.poidsHayon = 50;
    }

    private PointPouce[] calculerPositionRessorts(){
        return null;
    }

    public PointPouce[] getPosition() {
        return position;
    }

    public double getPoidsHayon() {
        return poidsHayon;
    }

    public void setPoidsHayon(double poidsHayon) {
        this.poidsHayon = poidsHayon;
    }

}
