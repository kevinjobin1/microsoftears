package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.*;

import java.awt.Color;

import java.util.Collections;
import java.util.LinkedList;

public class Hayon extends Composante {

    private Pouce epaisseur;
    private Pouce distancePoutre;
    private Pouce distancePlancher;
    private Pouce epaisseurTraitScie;
    private Pouce rayonArcCercle;


    public Hayon(RoulotteController parent, Pouce epaisseur, Pouce distancePoutre, Pouce distancePlancher, Pouce epaisseurTraitScie, Pouce rayonArcCercle) {
        super(parent);
        this.epaisseur = epaisseur;
        this.distancePoutre = distancePoutre;
        this.distancePlancher = distancePlancher;
        this.epaisseurTraitScie = epaisseurTraitScie;
        this.rayonArcCercle = rayonArcCercle;
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
        this.setType(TypeComposante.HAYON);
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

    //à tester (je vais attendre de pouvoir l'afficher)
    //marche sûrement pas mais très près d'une version fonctionnelle
    @Override
    public Polygone getPolygone(){
        LinkedList<PointPouce> pointsProfil= parent.getMurprofile().getPolygone().getListePoints();
        Rectangle plancher = parent.getPlancher().getRectangle();

        Pouce xDepart = parent.getPoutreArriere().getCentre().getX().
                diff(parent.getPoutreArriere().getLongueur().diviser(2)).diff(distancePoutre).diff(rayonArcCercle);
        Pouce xFin = plancher.getCentre().getX().
                diff(plancher.getLongueur().diviser(2)).diff(distancePlancher);
        Pouce yMinFin = parent.getMurBrute().getCentre().getY().add(parent.getMurBrute().getLargeur().diviser(2)).diff(plancher.getHauteur());

        LinkedList<PointPouce> pointsHayon = new LinkedList<>();
        LinkedList<PointPouce> retour;

        int indiceDepart = 0;
        int indiceFin = 0;
        double pente;
        double angleNormale;
        Pouce x;
        Pouce y;
        for (int i = 0; i < pointsProfil.size(); i++){
            if(indiceDepart == 0 && pointsProfil.get(i).getX().equals(xDepart)){
                indiceDepart = i;
            }
            if(pointsProfil.get(i).getX().equals(xFin) && pointsProfil.get(i).getY().gte(yMinFin)){
                indiceFin = i + 1;
            }
            if(indiceDepart != 0 && indiceFin == 0){
                PointPouce point1 = pointsProfil.get(i - 1);
                PointPouce point2 = pointsProfil.get(i + 1);
                pente = point2.getY().diff(point1.getY()).diviser(point2.getX().diff(point1.getX()));
                angleNormale = Math.atan(-1/pente);
                x = epaisseur.add(epaisseurTraitScie).multiplier(Math.cos(angleNormale)).add(pointsProfil.get(i).getX());
                y = epaisseur.add(epaisseurTraitScie).multiplier(Math.sin(angleNormale)).add(pointsProfil.get(i).getY());
                pointsHayon.add(new PointPouce(x,y));
            }
        }
        Collections.reverse(pointsHayon);

        PointPouce pointCercle = pointsHayon.getLast();
        for (int i = 1; i <= Ellipse.NOMBRE_POINTS/4; i++) {
            x = rayonArcCercle.multiplier(Math.cos(Math.toRadians(270 + 90 * i / (Ellipse.NOMBRE_POINTS / 4)))).add(pointCercle.getX());
            y = rayonArcCercle.multiplier(Math.cos(Math.toRadians(270 + 90 * i / (Ellipse.NOMBRE_POINTS / 4)))).add(pointCercle.getY());
            pointsHayon.add(new PointPouce(x,y));
        }
        retour = (LinkedList<PointPouce>) pointsProfil.subList(indiceDepart,indiceFin);
        retour.addAll(pointsHayon);
        return new Polygone(retour);
    }
}

