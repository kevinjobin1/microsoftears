package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.*;

import java.awt.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    //le cas où les coins sont carré ne marche pas
    @Override
    public Polygone getPolygone(){
        Rectangle plancher = parent.getPlancher().getRectangle();
        Pouce xDepart = parent.getPoutreArriere().getCentre().getX().
                diff(parent.getPoutreArriere().getLongueur().diviser(2)).diff(distancePoutre).diff(rayonArcCercle);
        Pouce xFin = plancher.getCentre().getX().
                diff(plancher.getLongueur().diviser(2)).diff(distancePlancher);
        Pouce yMinFin = parent.getMurBrute().getCentre().getY().
                add(parent.getMurBrute().getLargeur().diviser(2)).diff(plancher.getHauteur());

        List<PointPouce> pointsProfil= new LinkedList<>();
        boolean xDepartManquant = true;
        boolean xFinManquant = true;
        for(PointPouce point: parent.getMurprofile().getPolygone().getListePoints()){
            if(point.getX().st(parent.getMurBrute().getCentre().getX())) {
                pointsProfil.add(point);
                if(point.getX().equals(xDepart)){
                    xDepartManquant = false;
                }else if(point.getY().gte(yMinFin) && point.getX().equals(xFin)){
                    xFinManquant = false;
                }
            }
        }
        pointsProfil.add(0, new PointPouce(parent.getMurBrute().getCentre().getX(),
                parent.getMurBrute().getCentre().getY().diff(parent.getMurBrute().getLargeur().diviser(2))));
        if(xDepartManquant) {
            pointsProfil.add(1, new PointPouce(xDepart,
                    parent.getMurBrute().getCentre().getY().diff(parent.getMurBrute().getLargeur().diviser(2))));
        }
        if(xFinManquant) {
            pointsProfil.add(new PointPouce(xFin,
                    parent.getMurBrute().getCentre().getY().add(parent.getMurBrute().getLargeur().diviser(2))));
        }

        pointsProfil.add(new PointPouce(parent.getMurBrute().getCentre().getX(),
                parent.getMurBrute().getCentre().getY().add(parent.getMurBrute().getLargeur().diviser(2))));



        LinkedList<PointPouce> pointsHayon = new LinkedList<>();
        LinkedList<PointPouce> retour = new LinkedList<>();

        int indiceDepart = 0;
        int indiceFin = 0;
        double pente;
        double angleNormale;
        Pouce x;
        Pouce y;

        for (int i = 1; i < (pointsProfil.size() - 1); i++){
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
                if(pointsProfil.get(i).getY().gte(parent.getMurBrute().getCentre().getY())) {
                    angleNormale = Math.atan(1 / pente);
                }else{
                    angleNormale = Math.atan(-1 / pente);
                }
                x = epaisseur.add(epaisseurTraitScie).multiplier(Math.cos(angleNormale)).add(pointsProfil.get(i).getX());
                y = epaisseur.add(epaisseurTraitScie).multiplier(-Math.sin(angleNormale)).add(pointsProfil.get(i).getY());
                if(x.ste(xFin) || pointsProfil.get(i).getY().ste(parent.getMurBrute().getCentre().getY())) {
                    pointsHayon.add(new PointPouce(x, y));
                }
            }
        }
        if(indiceFin == 0){
            indiceFin = pointsProfil.size();
        }
        Collections.reverse(pointsHayon);

        PointPouce pointCercle = new PointPouce(pointsProfil.get(indiceDepart).getX(),
                pointsProfil.get(indiceDepart).getY().diff(rayonArcCercle.diff(epaisseur)));
        double angle;
        for (int i = Ellipse.NOMBRE_POINTS/4; i >= 0; i--) {
            angle = Math.toRadians(90 * i / (Ellipse.NOMBRE_POINTS / 4));
            x = rayonArcCercle.multiplier(Math.cos(angle)).add(pointCercle.getX());
            y = rayonArcCercle.multiplier(Math.sin(angle)).add(pointCercle.getY());
            if(y.gte(parent.getMurBrute().getCentre().getY().diff(parent.getMurBrute().getLargeur().diviser(2)))) {
                pointsHayon.add(new PointPouce(x, y));
            }
        }
        retour.addAll(pointsProfil.subList(indiceDepart,indiceFin));
        retour.addAll(pointsHayon);
        return new Polygone(retour);
    }
}

