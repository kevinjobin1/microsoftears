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
        this.setType(TypeComposante.RESSORTS);
    }

    public Ressorts(RoulotteController parent) {
        super(parent);
        this.poidsHayon = 50;
        this.setType(TypeComposante.RESSORTS);
    }

    private PointPouce[] calculerPositionRessorts(){
        return null;
    }

    public PointPouce[] getPosition() {
        return position;
    }

    public Pouce getLongueur() {
        return longueur;
    }

    public double getForce() {
        return force;
    }

    public double getPoidsHayon() {
        return poidsHayon;
    }

    public void setPoidsHayon(double poidsHayon) {
        this.poidsHayon = poidsHayon;
    }

}
