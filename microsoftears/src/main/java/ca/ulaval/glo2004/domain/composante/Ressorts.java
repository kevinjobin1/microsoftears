package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

public class Ressorts extends Composante{
    private double poidsHayon;
    private PointPouce positionSurHayon;
    private PointPouce positionSurMur;
    private Pouce longueurExactExtension;
    private Pouce longueurIdealExtension;
    private double force;

    public Ressorts(RoulotteController parent, double poidsHayon) {
        super(parent);
        this.poidsHayon = poidsHayon;
        this.setType(TypeComposante.RESSORTS);
        this.longueurIdealExtension = getLongueurHayon().multiplier(0.6);
        this.force = calculerForce();
    }

    public Ressorts(RoulotteController parent) {
        super(parent);
        this.poidsHayon = 50;
        this.setType(TypeComposante.RESSORTS);
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
    public void snapToGrid(PointPouce pointGrille) {

    }

    @Override
    public String[] getNomsAttributs() {
        return new String[0];
    }

    @Override
    public boolean getMode() {
        return false;
    }

    public Pouce getLongueurExactExtension() {
        return longueurExactExtension;
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
        Hayon hayon = (Hayon) parent.getListeComposantes().get(8);
        double x = hayon.getPointRotation().getX().toDouble() -
                hayon.getPointFinHayon().getX().toDouble();
        double y = hayon.getPointRotation().getY().toDouble() -
                hayon.getPointFinHayon().getY().toDouble();
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

    private double calculerDistanceEntre2Points(PointPouce point1, PointPouce point2){
        return Math.sqrt(Math.pow(point2.getX().toDouble()-point1.getX().toDouble(),2) +
                Math.pow(point2.getY().toDouble()-point1.getY().toDouble(),2));
    }

    //todo: à tester
    public void calculerPositionSurHayon(){
        Hayon hayon = (Hayon) parent.getListeComposantes().get(8);
        PointPouce dernierPointHayon = hayon.getPointsInterieurHayon().getFirst();
        boolean positionSurHayonCalculer = false;
        for (int i =0 ; i < hayon.getPointsInterieurHayon().size(); i++) {
            PointPouce point = hayon.getPointsInterieurHayon().get(i);
            double distancePointRotation = calculerDistanceEntre2Points(point, hayon.getPointRotation());

            if(distancePointRotation <= getDistanceRessortsDuPointDeRotation().toDouble() && !positionSurHayonCalculer) {
                dernierPointHayon = point;
                if (distancePointRotation == getDistanceRessortsDuPointDeRotation().toDouble()) {
                    positionSurHayon = point;
                    positionSurHayonCalculer = true;
                }
            }
        }
        if(!positionSurHayonCalculer){
            double y = Math.sqrt(Math.pow(getDistanceRessortsDuPointDeRotation().toDouble(),2) -
                    Math.pow(dernierPointHayon.getX().toDouble(),2));
            positionSurHayon = new PointPouce(dernierPointHayon.getX(), new Pouce(y).add(hayon.getPointRotation().getY()));
        }
    }

    //todo: à tester
    public void calculerPositionSurMur(){
        Hayon hayon = (Hayon) parent.getListeComposantes().get(8);
        PointPouce dernierPointMur = hayon.getPointsInterieurHayon().getFirst();
        boolean positionSurMurCalculer = false;
        for (int i =0 ; i < hayon.getPointsInterieurHayon().size(); i++) {
            PointPouce point = hayon.getPointsInterieurHayon().get(i);
            double distancePointRotation = calculerDistanceEntre2Points(point, hayon.getPointRotation());

            if(distancePointRotation <= longueurExactExtension.toDouble() && !positionSurMurCalculer) {
                dernierPointMur = point;
                if (distancePointRotation == longueurExactExtension.toDouble()) {
                    positionSurHayon = point;
                    positionSurMurCalculer = true;
                }
            }
        }
        if(!positionSurMurCalculer){
            double y = Math.sqrt(Math.pow(longueurExactExtension.toDouble(),2) -
                    Math.pow(dernierPointMur.getX().toDouble(),2));
            positionSurHayon = new PointPouce(dernierPointMur.getX(), new Pouce(y).add(hayon.getPointRotation().getY()));
        }
    }

    public Pouce getDistanceRessortsDuPointDeRotation(){
        Pouce strokeLength;
        if(longueurIdealExtension.ste(new Pouce(7,1,100))){
            strokeLength = new Pouce(1,97,100);
            longueurExactExtension = new Pouce(7,1,100);

        }else if(longueurIdealExtension.ste(new Pouce(7,2,5))){
            strokeLength = new Pouce(2,9,25);
            longueurExactExtension = new Pouce(7,2,5);

        }else if(longueurIdealExtension.ste(new Pouce(9,13,20))){
            strokeLength = new Pouce(3,27,50);
            longueurExactExtension = new Pouce(9,13,20);

        }else if(longueurIdealExtension.ste(new Pouce(12,1,5))){
            strokeLength = new Pouce(3,47,50);
            longueurExactExtension = new Pouce(12,1,5);

        }else if(longueurIdealExtension.ste(new Pouce(13,19,100))){
            strokeLength = new Pouce(4,23,25);
            longueurExactExtension = new Pouce(13,19,100);

        }else if(longueurIdealExtension.ste(new Pouce(15,6,25))){
            strokeLength = new Pouce(5,47,100);
            longueurExactExtension = new Pouce(15,6,25);

        }else if(longueurIdealExtension.ste(new Pouce(17,13,100))){
            strokeLength = new Pouce(6,3,10);
            longueurExactExtension =new Pouce(17,13,100);

        }else if(longueurIdealExtension.ste(new Pouce(19,18,25))){
            strokeLength = new Pouce(7,87,100);
            longueurExactExtension =new Pouce(19,18,25);

        }else if(longueurIdealExtension.ste(new Pouce(20,3,25))){
            strokeLength = new Pouce(8,27,100);
            longueurExactExtension =new Pouce(20,3,25);

        }else if(longueurIdealExtension.ste(new Pouce(27,87,100))){
            strokeLength = new Pouce(10,6,25);
            longueurExactExtension =new Pouce(27,87,100);
        }else if(longueurIdealExtension.ste(new Pouce(29,49,100))){
            strokeLength = new Pouce(12,4,5);
            longueurExactExtension =new Pouce(29,49,100);
        }else {
            strokeLength = new Pouce(16,7,50);
            longueurExactExtension =new Pouce(35,43,100);
        }
        return strokeLength.multiplier(0.85);
    }
}
