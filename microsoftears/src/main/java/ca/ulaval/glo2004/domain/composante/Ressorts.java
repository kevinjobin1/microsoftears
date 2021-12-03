package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.*;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.LinkedList;

public class Ressorts extends Composante{
    private int poidsHayon;
    private PointPouce positionSurHayon;
    private PointPouce positionSurMur;
    private Pouce longueurExactExtension;
    private Pouce longueurIdealExtension;
    private Pouce DistancePointHayonDuPointDeRotation;
    private Ellipse pointHayon;
    private Ellipse pointMur;
    private Ligne ligne;
    private RectangleCoinRond corpsRessort;
    private static final Pouce RAYON_TIGE = new Pouce(0.31);
    private static final  Pouce LARGEUR_CORPS = new Pouce(0.73);
    
    
    private double force;

    public Ressorts(RoulotteController parent, int poidsHayon) {
        super(parent);
        this.poidsHayon = poidsHayon;
        this.setType(TypeComposante.RESSORTS);
        this.longueurIdealExtension = getLongueurHayon().multiplier(0.6);
        calculerDistancePositionsDuPointDeRotation();
        calculerPositionSurHayon();
        calculerPositionSurMur();
        this.force = calculerForce();
        this.setCouleur(Color.LIGHT_GRAY);
        this.setStrokeCouleur(Color.LIGHT_GRAY);
        this.setTransparence(0.15f);

        // la ligne qui constitue le ressort
        this.ligne = new Ligne(positionSurHayon, positionSurMur);

        // Le corps du ressort à gaz
        this.corpsRessort = new RectangleCoinRond(ligne.getLongueur().diff(RAYON_TIGE.multiplier(2)),LARGEUR_CORPS, ligne.getCentre(), RAYON_TIGE);

        // Les points d'attache (têtes du ressort)
        this.pointHayon = new Ellipse(RAYON_TIGE, RAYON_TIGE, positionSurHayon);
        this.pointMur = new Ellipse(RAYON_TIGE,RAYON_TIGE, positionSurMur);
        this.setPolygone(new Polygone(corpsRessort.getListePoints()));

    }

    public Ressorts(RoulotteController parent) {
        super(parent);
        this.poidsHayon = 50;
        this.setType(TypeComposante.RESSORTS);
        this.longueurIdealExtension = getLongueurHayon().multiplier(0.6);
        calculerDistancePositionsDuPointDeRotation();
        calculerPositionSurHayon();
        calculerPositionSurMur();
        this.force = calculerForce();
        this.setCouleur(Color.LIGHT_GRAY);
        this.setStrokeCouleur(Color.LIGHT_GRAY);
        this.setTransparence(0.50f);

        // la ligne qui constitue le ressort
        this.ligne = new Ligne(positionSurHayon, positionSurMur);

        // Le corps du ressort à gaz
        this.corpsRessort = new RectangleCoinRond(ligne.getLongueur().diff(RAYON_TIGE.multiplier(2)),LARGEUR_CORPS, ligne.getCentre(), RAYON_TIGE);

        // Les points d'attache (têtes du ressort)
        this.pointHayon = new Ellipse(RAYON_TIGE, RAYON_TIGE, positionSurHayon);
        this.pointMur = new Ellipse(RAYON_TIGE,RAYON_TIGE, positionSurMur);

        this.setPolygone(new Polygone(corpsRessort.getListePoints()));
    }

    @Override
    public void afficher(Graphics2D g2d) {
        if (estVisible()){
            if(parent.afficherLabel()) {
            if (getAfficherPosition()){
                String positionMur = parent.estImperial() ? "Point (mur) : " + positionSurMur : "Point (mur) : " + positionSurMur.toStringMM();
                String positionHayon = parent.estImperial() ?  "Point (hayon) : " + positionSurHayon : "Point (hayon) : " + positionSurHayon.toStringMM();
                g2d.drawString(this.toString(), (float) parent.getPositionSouris().getX() + 30, (float) parent.getPositionSouris().getY() - 30);
                g2d.drawString(positionMur, (float) parent.getPositionSouris().getX() + 30,(float) parent.getPositionSouris().getY() + 30);
                g2d.drawString(positionHayon, (float) parent.getPositionSouris().getX() + 30,(float) parent.getPositionSouris().getY() + 60);
            } }
            afficherLigne(g2d, ligne);
            afficherCorpsRessort(g2d,corpsRessort);
            afficherPointRessort(g2d, pointHayon);
            afficherPointRessort(g2d, pointMur);
        }
    }

    private void afficherCorpsRessort(Graphics2D g2d, RectangleCoinRond corpsRessort) {
        Path2D path = new Path2D.Double();
        LinkedList<PointPouce> pointsRectangle = corpsRessort.getListePoints();
        double[] point;
        for (int i = 0; i < pointsRectangle.size(); i++){
            point = parent.getPositionEcran(pointsRectangle.get(i));
            if(i == 0) {
                path.moveTo(point[0], point[1]);
            }
            else{
                path.lineTo(point[0] ,point[1]);
            }
        }
        path.closePath();
        Area areaRectangle = new Area(path);
        Composite compositeInitial = g2d.getComposite();
        g2d.setComposite(definirComposite(getTransparence()));
        g2d.setPaint(getCouleur());
        g2d.fill(areaRectangle);
        g2d.setComposite(compositeInitial);
        g2d.setColor(getStrokeCouleur());
        //g2d.setStroke(getStroke());
        g2d.draw(areaRectangle);
    }

    private void afficherLigne(Graphics2D g2d, Ligne ligne) {
        Path2D path = new Path2D.Double();
        LinkedList<PointPouce> pointsLigne = ligne.getListePoints();
        double[] point;
        for (int i = 0; i < pointsLigne.size(); i++){
            point = parent.getPositionEcran(pointsLigne.get(i));
            if(i == 0) {
                path.moveTo(point[0], point[1]);
            }
            else{
                path.lineTo(point[0] ,point[1]);
            }
        }
        path.closePath();
        g2d.setColor(Color.BLACK);
        //g2d.setStroke(getStroke());
        g2d.draw(path);
    }

    private void afficherPointRessort(Graphics2D g2d, Ellipse pointAttache){
        Path2D path = new Path2D.Double();
        LinkedList<PointPouce> pointsEllipse = pointAttache.getListePoints();
        double[] point;
        for (int i = 0; i < pointsEllipse.size(); i++){
            point = parent.getPositionEcran(pointsEllipse.get(i));
            if(i == 0) {
                path.moveTo(point[0], point[1]);
            }
            else{
                path.lineTo(point[0] ,point[1]);
            }
        }
        path.closePath();
        Area areaEllipse = new Area(path);
        Composite compositeInitial = g2d.getComposite();
        g2d.setComposite(definirComposite(getTransparence()));
        g2d.setPaint(getCouleur());
        g2d.fill(areaEllipse);
        g2d.setComposite(compositeInitial);
        g2d.setColor(getStrokeCouleur());
        //g2d.setStroke(getStroke());
        g2d.draw(areaEllipse);
    }

    @Override
    public PointPouce getCentre() {
        return null;
    }


    @Override
    public void translate(PointPouce delta) {
        this.longueurIdealExtension = getLongueurHayon().multiplier(0.6);
        calculerDistancePositionsDuPointDeRotation();
        calculerPositionSurHayon();
        calculerPositionSurMur();
        this.force = calculerForce();

        // la ligne qui constitue le ressort
        this.ligne = new Ligne(positionSurHayon, positionSurMur);

        // Le corps du ressort à gaz
        this.corpsRessort = new RectangleCoinRond(ligne.getLongueur().diff(RAYON_TIGE.multiplier(2)),LARGEUR_CORPS, ligne.getCentre(), RAYON_TIGE);

        // Les points d'attache (têtes du ressort)
        this.pointHayon = new Ellipse(RAYON_TIGE, RAYON_TIGE, positionSurHayon);
        this.pointMur = new Ellipse(RAYON_TIGE,RAYON_TIGE, positionSurMur);
        this.setPolygone(new Polygone(corpsRessort.getListePoints()));
    }

    @Override
    public void snapToGrid(PointPouce pointGrille) {
        translate(pointGrille);
    }

    public Pouce getLongueurExactExtension() {
        return longueurExactExtension;
    }

    public double getForce() {
        return force;
    }

    public int getPoidsHayon() {
        return poidsHayon;
    }

    public void setPoidsHayon(int poidsHayon) {
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
        double deadWeightNewton = ((double) poidsHayon) * 4.4482216;
        double centerOfGravityLengthMM = getLongueurHayon().toDouble() * (25.4/2);
        double powerArmLengthMM = DistancePointHayonDuPointDeRotation.toDouble() * 25.4;
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

    public void calculerPositionSurHayon(){
        Hayon hayon = (Hayon) parent.getListeComposantes().get(8);
        PointPouce dernierPointHayon = hayon.getPointsInterieurHayon().getFirst();
        boolean positionSurHayonCalculer = false;
        for (int i =hayon.getPointsInterieurHayon().size()-1; i >= 0; i--) {
            PointPouce point = hayon.getPointsInterieurHayon().get(i);
            double distancePointRotation = calculerDistanceEntre2Points(point, hayon.getPointRotation());

            if(distancePointRotation <= DistancePointHayonDuPointDeRotation.toDouble() && !positionSurHayonCalculer) {
                dernierPointHayon = point;
                if (distancePointRotation == DistancePointHayonDuPointDeRotation.toDouble()) {
                    positionSurHayon = point;
                    positionSurHayonCalculer = true;
                }
            }
        }
        if(!positionSurHayonCalculer){
            double y = Math.sqrt(Math.pow(DistancePointHayonDuPointDeRotation.toDouble(),2) -
                    Math.pow(dernierPointHayon.getX().toDouble()-hayon.getPointRotation().getX().toDouble(),2));
            positionSurHayon = new PointPouce(dernierPointHayon.getX(), new Pouce(y).add(hayon.getPointRotation().getY()));
        }
    }

    public void calculerPositionSurMur(){
        Hayon hayon = (Hayon) parent.getListeComposantes().get(8);
        PointPouce dernierPointMur = hayon.getPointsInterieurHayon().getFirst();
        boolean positionSurMurCalculer = false;
        for (int i =hayon.getPointsInterieurHayon().size()-1 ; i >= 0; i--) {
            PointPouce point = hayon.getPointsInterieurHayon().get(i);
            double distancePointRotation = calculerDistanceEntre2Points(point, hayon.getPointRotation());

            if(distancePointRotation <= longueurExactExtension.toDouble() && !positionSurMurCalculer) {
                dernierPointMur = point;
                if (distancePointRotation == longueurExactExtension.toDouble()) {
                    positionSurMur = point;
                    positionSurMurCalculer = true;
                }
            }
        }
        if(!positionSurMurCalculer){
            double y = Math.sqrt(Math.pow(longueurExactExtension.toDouble(),2) -
                    Math.pow(dernierPointMur.getX().toDouble() - hayon.getPointRotation().getX().toDouble(),2));
            positionSurMur = new PointPouce(dernierPointMur.getX(), new Pouce(y).add(hayon.getPointRotation().getY()));
        }
    }

    public void calculerDistancePositionsDuPointDeRotation(){
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
        DistancePointHayonDuPointDeRotation = strokeLength.multiplier(0.85);
    }

    @Override
    public int[] getValeurs() {
        return new int[]{poidsHayon, (int) force, longueurIdealExtension.toInt()};
    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Poids (hayon)", "Force", "Longueur"};
    }

    @Override
    public boolean[] getModes(){
        return new boolean[]{};
    }

    @Override
    public Pouce[] getLimit() {
        return new Pouce[0];
    }


}
