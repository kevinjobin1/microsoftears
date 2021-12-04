package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.*;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Hayon extends Composante {

    private Pouce epaisseur;
    private Pouce distancePoutre;
    private Pouce distancePlancher;
    private Pouce epaisseurTraitScie;
    private Pouce rayonArcCercle;
    private LinkedList<PointPouce> pointsInterieurHayon;
    private PointPouce pointRotation = null;
    private PointPouce pointFinHayon;

    public Hayon(RoulotteController parent, Pouce epaisseur, Pouce distancePoutre, Pouce distancePlancher, Pouce epaisseurTraitScie, Pouce rayonArcCercle) {
        super(parent);
        this.epaisseur = epaisseur;
        this.distancePoutre = distancePoutre;
        this.distancePlancher = distancePlancher;
        this.epaisseurTraitScie = epaisseurTraitScie;
        this.rayonArcCercle = rayonArcCercle;
        this.setCouleurInitiale(new Color(0,233,233));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.HAYON);
        this.setPolygone(getPolygone());
    }


    public Hayon(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(2,0,1);
        this.distancePoutre = new Pouce(0,5,16);
        this.distancePlancher = new Pouce(0,3,8);
        this.epaisseurTraitScie = new Pouce(0,1,16);
        this.rayonArcCercle = new Pouce(2,3,8);
        this.setCouleurInitiale(new Color(0,233,233));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.HAYON);
        this.setPolygone(getPolygone());
    }

    public Hayon(Hayon copie) {
        super(copie.parent);
        this.epaisseur = copie.epaisseur;
        this.distancePoutre = copie.distancePoutre;
        this.distancePlancher = copie.distancePlancher;
        this.epaisseurTraitScie = copie.epaisseurTraitScie;
        this.rayonArcCercle = copie.rayonArcCercle;
        this.setCouleurInitiale(copie.getCouleurInitiale());
        this.setCouleur(copie.getCouleur());
        this.setType(copie.getType());
        this.setPolygone(getPolygone());
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    public Pouce getDistancePoutre() {
        return distancePoutre;
    }

    public void setDistancePoutre(Pouce distancePoutre) {
        this.distancePoutre = distancePoutre;
    }

    public Pouce getDistancePlancher() {
        return distancePlancher;
    }

    public void setDistancePlancher(Pouce distancePlancher) {
        this.distancePlancher = distancePlancher;
    }

    public Pouce getEpaisseurTraitScie() {
        return epaisseurTraitScie;
    }

    public void setEpaisseurTraitScie(Pouce epaisseurTraitScie) {
        this.epaisseurTraitScie = epaisseurTraitScie;
    }

    public Pouce getRayonArcCercle() {
        return rayonArcCercle;
    }

    public void setRayonArcCercle(Pouce rayonArcCercle) {
        this.rayonArcCercle = rayonArcCercle;
    }

    public PointPouce getPointRotation() {
        return pointRotation;
    }

    public PointPouce getPointFinHayon() {
        return pointFinHayon;
    }


    @Override
    public Polygone getPolygone(){
        Rectangle plancher = ((Plancher) (parent.getListeComposantes().get(6))).getRectangle();
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutreArriere = (PoutreArriere) parent.getListeComposantes().get(7);
        MurProfile murProfile = (MurProfile) parent.getListeComposantes().get(1);

        // xdepart = (poutre.centreX - poutre.longueur/2 - distancePoutre - rayonArcCercle)
        // xFin = (plancher.centreX - plancher.longueur/2 - distancePlancher)
        // yMinFin = (
        // yFinArcCercle =

        Pouce xDepart = poutreArriere.getCentre().getX().
                diff(poutreArriere.getLongueur().diviser(2)).diff(distancePoutre).diff(rayonArcCercle);
        Pouce xFin = plancher.getCentre().getX().
                diff(plancher.getLongueur().diviser(2)).diff(distancePlancher);
        Pouce yMinFin = murBrute.getCentre().getY().
                add(murBrute.getLargeur().diviser(2)).diff(plancher.getHauteur());
        Pouce yFinArcCercle = new Pouce(0,0,1);

        boolean yFinArcCercleManquant = true;
        List<PointPouce> pointsProfil= new LinkedList<>();
        boolean xDepartManquant = true;
        boolean xFinManquant = true;
        PointPouce pointDepart = null;
        PointPouce pointArcCercle = null;
        for(PointPouce point: murProfile.getPolygone().getListePoints()){
            if(point.getX().st(murBrute.getCentre().getX())) {
                if(point.getX().ste(xDepart.add(rayonArcCercle)) && point.getY().st(yMinFin) && yFinArcCercleManquant){
                    yFinArcCercle = point.getY();
                    pointRotation = point;
                    yFinArcCercleManquant = false;
                    pointArcCercle = new PointPouce(point.getX(),point.getY().diff(rayonArcCercle.diff(epaisseur.add(epaisseurTraitScie))));
                    if(point.getX().st(xDepart.add(rayonArcCercle.multiplier(Math.cos(poutreArriere.getAngle()))))) {
                        pointArcCercle = new PointPouce(xDepart.add(rayonArcCercle.multiplier
                                (Math.cos(poutreArriere.getAngle()))), point.getY().diff(rayonArcCercle).diff(epaisseur.add(epaisseurTraitScie)));
                        pointsProfil.add(pointArcCercle);
                    }
                }
                if(point.getX().equals(xDepart) && point.getY().st(yMinFin)){
                    xDepartManquant = false;
                }else if(point.getX().st(xDepart) && xDepartManquant && point.getY().st(yMinFin)){
                    pointDepart =new PointPouce(xDepart, point.getY());
                    pointsProfil.add(pointDepart);
                    xDepartManquant = false;
                }
                if(point.getY().gte(yMinFin) && point.getX().equals(xFin)){
                    xFinManquant = false;
                    pointFinHayon = point;
                }else if(point.getY().gte(yMinFin) && point.getX().gt(xFin) && xFinManquant){
                    pointFinHayon = new PointPouce(xFin, point.getY());
                    pointsProfil.add(pointFinHayon);
                    xFinManquant = false;
                }
                pointsProfil.add(point);
            }
        }
        if(xFinManquant){
            pointFinHayon = new PointPouce(xFin, murBrute.getCentre().getY().add(murBrute.getLargeur().diviser(2)));
            pointsProfil.add(pointFinHayon);
        }

        PointPouce pointCentreDebut = new PointPouce(murBrute.getCentre().getX(),
                murBrute.getCentre().getY().diff(murBrute.getLargeur().diviser(2)));
        pointsProfil.add(0, pointCentreDebut);

        pointsProfil.add(new PointPouce(murBrute.getCentre().getX(),
                murBrute.getCentre().getY().add(murBrute.getLargeur().diviser(2))));

        LinkedList<PointPouce> pointsHayon = new LinkedList<>();
        LinkedList<PointPouce> retour = new LinkedList<>();

        int indiceDepart = 0;
        int indiceDepartCercle = 0;
        int indiceFin = 0;
        double pente;
        double angleNormale;
        boolean aucunPointAjoute = true;
        for(int i = 1; i < pointsProfil.size()-1; i++){
            if(pointsProfil.get(i).getX().equals(xDepart.add(rayonArcCercle.multiplier(Math.cos(poutreArriere.getAngle()))))){
                indiceDepart = i;
            }
            if((pointsProfil.get(i).getX().ste(xDepart) && pointsProfil.get(i).getY().st(yMinFin)) ||
                    (pointsProfil.get(i).getY().gte(yMinFin) && pointsProfil.get(i).getX().ste(xFin))){
                if(aucunPointAjoute){
                    indiceDepartCercle = i;
                }
                PointPouce point1 = pointsProfil.get(i - 1);
                PointPouce point2 = pointsProfil.get(i + 1);
                pente = point2.getY().diff(point1.getY()).diviser(point2.getX().diff(point1.getX()));
                angleNormale = Math.atan(1 / pente);
                Pouce x = epaisseur.add(epaisseurTraitScie).multiplier(Math.cos(angleNormale)).add(pointsProfil.get(i).getX());
                Pouce y = epaisseur.add(epaisseurTraitScie).multiplier(-Math.sin(angleNormale)).add(pointsProfil.get(i).getY());

                //lorsque les points sont dans le coin mais sont plus petit que l'épaisseur du mur
                if(x.st(murBrute.getCentre().getX().diff(murBrute.getLongueur().diviser(2)).
                        add(epaisseur).add(epaisseurTraitScie))){
                    x = murBrute.getCentre().getX().diff(murBrute.getLongueur().diviser(2)).
                            add(epaisseur).add(epaisseurTraitScie);
                }
                if(y.gt(murBrute.getCentre().getY().add(murBrute.getLargeur().diviser(2)).
                        diff(epaisseur).diff(epaisseurTraitScie))){
                    y = murBrute.getCentre().getY().add(murBrute.getLargeur().diviser(2)).
                            diff(epaisseur).diff(epaisseurTraitScie);
                } else if (y.st(murBrute.getCentre().getY().diff(murBrute.getLargeur().diviser(2)).
                        add(epaisseur).add(epaisseurTraitScie))){
                    y = murBrute.getCentre().getY().diff(murBrute.getLargeur().diviser(2)).
                            add(epaisseur).add(epaisseurTraitScie);
                }

                //exception: retirer les points qui sont plus petit que la courbe
                boolean pointValide = true;
                if (!aucunPointAjoute && y.ste(murBrute.getCentre().getY()) &&
                        pointsHayon.getLast().getY().ste(murBrute.getCentre().getY())) {
                    if (x.gt(pointsHayon.getLast().getX())) {
                            pointsHayon.removeLast();
                    }
                    if (y.st(pointsHayon.getLast().getY())) {
                        pointValide = false;
                    }
                } else if(!aucunPointAjoute && y.gte(murBrute.getCentre().getY()) &&
                        pointsHayon.getLast().getY().gte(murBrute.getCentre().getY())) {
                    if (y.st(pointsHayon.getLast().getY())) {
                        pointsHayon.removeLast();
                    }
                    if (x.st(pointsHayon.getLast().getX())) {
                        pointValide = false;
                    }
                }

                if((x.ste(xFin) || pointsProfil.get(i).getY().st(yMinFin)) && pointValide) {
                    pointsHayon.add(new PointPouce(x, y));
                    aucunPointAjoute = false;
                    indiceFin = i+1;
                }
            }
        }

        if(indiceFin == 0){
            indiceFin = pointsProfil.size();
        }

        Pouce xDebutCercle = pointsHayon.getFirst().getX();

        //arc de cercle
        PointPouce point1 = pointsProfil.get(indiceDepartCercle - 1);
        PointPouce point2 = pointsProfil.get(indiceDepartCercle + 1);
        pente = point2.getY().diff(point1.getY()).diviser(point2.getX().diff(point1.getX()));
        angleNormale = Math.atan(1 / pente);

        Pouce x = pointsProfil.get(indiceDepartCercle).getX().diff((rayonArcCercle.diff(epaisseur).diff(epaisseurTraitScie)).multiplier(Math.cos(angleNormale)));
        Pouce y = pointsProfil.get(indiceDepartCercle).getY().diff((rayonArcCercle.diff(epaisseur).diff(epaisseurTraitScie)).multiplier(-Math.sin(angleNormale)));
        PointPouce pointCercle = new PointPouce(x, y);

        Collections.reverse(pointsHayon);

        double angle;

        for (int i = (Forme.getNombrePoint()/2); i >= -(Forme.getNombrePoint()/4); i--) {
            angle = Math.toRadians(360 * i / Forme.getNombrePoint());
            Pouce xCercle = rayonArcCercle.multiplier(Math.cos(angle)).add(pointCercle.getX());
            Pouce yCercle = rayonArcCercle.multiplier(Math.sin(angle)).add(pointCercle.getY());
            if(yCercle.gte(yFinArcCercle) && xCercle.gte(xDebutCercle)) {
                pointsHayon.add(new PointPouce(xCercle, yCercle));
            }
        }

        retour.addAll(pointsProfil.subList(indiceDepart,indiceFin));
        if(pointArcCercle != null) {
            retour.remove(pointArcCercle);
        }
        if(pointDepart != null) {
            retour.remove(pointDepart);
        }
        retour.remove(pointCentreDebut);
        pointsInterieurHayon = pointsHayon;
        retour.addAll(pointsHayon);
        return new Polygone(retour);
    }

    @Override
    public PointPouce getCentre() {
        return null;
    }


    @Override
    public int[] getValeurs() {
        return new int[]{epaisseur.getPouces(), epaisseur.getNumerateur(), epaisseur.getDenominateur(),
                distancePoutre.getPouces(), distancePoutre.getNumerateur(), distancePoutre.getDenominateur(),
                distancePlancher.getPouces(), distancePlancher.getNumerateur(), distancePlancher.getDenominateur(),
                epaisseurTraitScie.getPouces(), epaisseurTraitScie.getNumerateur(), epaisseurTraitScie.getDenominateur(),
                rayonArcCercle.getPouces(), rayonArcCercle.getNumerateur(), rayonArcCercle.getDenominateur()
        };
    }

    @Override
    public void translate(PointPouce delta) {

    }

    @Override
    public void snapToGrid(PointPouce pointGrille){

    }



    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Épaisseur", "Distance (poutre)","Distance (plancher)", "Épaisseur (scie)", "Rayon (arc)"};
    }

    @Override
    public boolean[] getModes(){
        return new boolean[]{};
    }

    public boolean verificationEpaisseur(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutre = (PoutreArriere) parent.getListeComposantes().get(7);
        return valeur.st(mur.getCentre().getX().diff(mur.getLongueur().diviser(2)).
                diff(poutre.getCentre().getX().diff(poutre.getLongueur().diviser(2))).diff(epaisseurTraitScie).diff(distancePoutre))
                && valeur.gt(new Pouce(0,0,1));
    }

    public boolean verificationDistancePoutre(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutre = (PoutreArriere) parent.getListeComposantes().get(7);
        return valeur.st(poutre.getCentre().getX().diff(poutre.getLongueur().diviser(2)).
                diff(mur.getCentre().getX().diff(mur.getLongueur().diviser(2))).
                diff(epaisseur).diff(epaisseurTraitScie)) && valeur.gt(new Pouce(0,0,1));
    }

    public boolean verificationDistancePlancher(Pouce valeur){
        Plancher plancher = (Plancher) parent.getListeComposantes().get(6);
        return valeur.st(plancher.getMargeArriere()) && valeur.gt(new Pouce(0,0,1));
    }

    public boolean verificationEpaisseurTraitScie(Pouce valeur){
        return valeur.st(new Pouce(1,0,1)) && valeur.gt(new Pouce(0,0,1));
    }

    public boolean verificationRayonArcCercle(Pouce valeur){
        return valeur.st(epaisseur.add(new Pouce(1,0,1))) && valeur.gt(epaisseur.diff(new Pouce(1,0,1)));
    }

    @Override
    public Pouce[] getLimit() {
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutre = (PoutreArriere) parent.getListeComposantes().get(7);
        Plancher plancher = (Plancher) parent.getListeComposantes().get(6);

        Pouce maxEpaisseur = mur.getCentre().getX().diff(mur.getLongueur().diviser(2)).
                diff(poutre.getCentre().getX().diff(poutre.getLongueur().diviser(2))).diff(epaisseurTraitScie).diff(distancePoutre);
        Pouce minEpaisseur = new Pouce(0,1,64);
        Pouce maxDistancePoutre = poutre.getCentre().getX().diff(poutre.getLongueur().diviser(2)).
                diff(mur.getCentre().getX().diff(mur.getLongueur().diviser(2))).
                diff(epaisseur).diff(epaisseurTraitScie);
        Pouce minDistancePoutre = new Pouce(0,1,64);
        Pouce maxDistancePlancher = plancher.getMargeArriere();
        Pouce minDistancePlancher = new Pouce(0,1,64);
        Pouce maxEpaisseurTraitScie = new Pouce(1,0,1);
        Pouce minEpaisseurTraitScie = new Pouce(0,1,64);
        Pouce maxRayonArcCercle = epaisseur.add(new Pouce(1,0,1));
        Pouce minRayonArcCercle = new Pouce(0,1,64);

        return new Pouce[]{maxEpaisseur,minEpaisseur,maxDistancePoutre,minDistancePoutre,maxDistancePlancher,
                minDistancePlancher,maxEpaisseurTraitScie,minEpaisseurTraitScie,maxRayonArcCercle,minRayonArcCercle};
    }

    public LinkedList<PointPouce> getPointsInterieurHayon() {
        return pointsInterieurHayon;
    }
}

