package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.Color;
import java.awt.Point;
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
        this.setPolygone(new Polygone(this.getPointsContour()));
    }


    public Hayon(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(2,0,1);
        this.distancePoutre = new Pouce(0,5,16);
        this.distancePlancher = new Pouce(0,3,8);
        this.epaisseurTraitScie = new Pouce(0,1,16);
        this.rayonArcCercle = new Pouce(2,3,8);
        this.setType(TypeComposante.HAYON);
        this.setPolygone(new Polygone(this.getPointsContour()));
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

    //à coder
    private LinkedList<PointPouce> getPointsContour(){
        return null;
    }
}

