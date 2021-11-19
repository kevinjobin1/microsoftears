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
        this.longueur = getLongueurHayon().multiplier(0.6);
        this.force = calculerForce();
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

    public Pouce getLongueurHayon(){
        double x = parent.getHayon().getPointRotation().getX().toDouble() -
                parent.getHayon().getPointFinHayon().getX().toDouble();
        double y = parent.getHayon().getPointRotation().getY().toDouble() -
                parent.getHayon().getPointFinHayon().getY().toDouble();
        return new Pouce(Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
    }
    //todo: à tester
    public double calculerForce(){
        double deadWeightNewton = poidsHayon * 4.4482216;
        double centerOfGravityLengthMM = getLongueurHayon().toDouble() * (25.4/2);
        double powerArmLengthMM = getDistanceRessortsDuPointDeRotation().toDouble() * 25.4;
        double forceRequiredNewton = deadWeightNewton*centerOfGravityLengthMM/(powerArmLengthMM*2);
        double safetyFactorNewton;
        if(deadWeightNewton < 300) {
            safetyFactorNewton = forceRequiredNewton * 0.1;
        }else{
            safetyFactorNewton = 50;
        }
        return (forceRequiredNewton+safetyFactorNewton)*0.224808943871;
    }

    public Pouce getDistanceRessortsDuPointDeRotation(){
        Pouce strokeLength;
        if(longueur.ste(new Pouce(7,1,100))){
            strokeLength = new Pouce(1,97,100);
        }else if(longueur.ste(new Pouce(7,2,5))){
            strokeLength = new Pouce(2,9,25);
        }else if(longueur.ste(new Pouce(9,13,20))){
            strokeLength = new Pouce(3,27,50);
        }else if(longueur.ste(new Pouce(12,1,5))){
            strokeLength = new Pouce(3,47,50);
        }else if(longueur.ste(new Pouce(13,19,100))){
            strokeLength = new Pouce(4,23,25);
        }else if(longueur.ste(new Pouce(15,6,25))){
            strokeLength = new Pouce(5,47,100);
        }else if(longueur.ste(new Pouce(17,13,100))){
            strokeLength = new Pouce(6,3,10);
        }else if(longueur.ste(new Pouce(19,18,25))){
            strokeLength = new Pouce(7,87,100);
        }else if(longueur.ste(new Pouce(20,3,25))){
            strokeLength = new Pouce(8,27,100);
        }else if(longueur.ste(new Pouce(27,87,100))){
            strokeLength = new Pouce(10,6,25);
        }else if(longueur.ste(new Pouce(29,49,100))){
            strokeLength = new Pouce(12,4,5);
        }else {
            strokeLength = new Pouce(16,7,50);
        }
        return strokeLength.multiplier(0.85);
    }
}
