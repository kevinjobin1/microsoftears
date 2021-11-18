package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.LinkedList;
import java.util.List;

public class Toit extends Composante{
    private Pouce epaisseur;

    public Toit(RoulotteController parent, Pouce epaisseur) {
        super(parent);
        this.epaisseur = epaisseur;
        this.setType(TypeComposante.TOIT);
        this.setPolygone(getPolygone());
    }

    public Toit(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(2,1,4);
        this.setType(TypeComposante.TOIT);
        this.setPolygone(getPolygone());
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    @Override
    public Polygone getPolygone(){
        Pouce xFin = parent.getMurSeparateur().getCentre().getX().add(parent.getMurSeparateur().getEpaisseur().diviser(2));
        Pouce yDepart = parent.getMurBrute().getCentre().getY().add(parent.getMurBrute().getLargeur().diviser(2)).
                diff(parent.getPlancher().getEpaisseur());
        LinkedList<PointPouce> listePointMur = parent.getMurprofile().getPolygone().getListePoints();
        LinkedList<PointPouce> pointsProfil= new LinkedList<>();
        boolean yDepartManquant = true;
        boolean xFinManquant = true;

        for(int i = 0; i < listePointMur.size(); i++){
            PointPouce point = listePointMur.get(i);
            if(point.getX().gte(xFin) && point.getY().ste(yDepart)) {
                if(point.getY().gt(parent.getMurBrute().getCentre().getY())){
                    pointsProfil.add(0, point);
                }else {
                    pointsProfil.add(point);
                }
            }
            if(point.getX().equals(xFin) && point.getY().ste(yDepart)){
                xFinManquant = false;
                pointsProfil.add(new PointPouce(xFin,point.getY()));
            }else if(point.getX().st(xFin) && point.getY().ste(yDepart) && xFinManquant){
                pointsProfil.add(new PointPouce(xFin,point.getY()));
                pointsProfil.add(new PointPouce(xFin,point.getY()));
                xFinManquant = false;
            }
            if(point.getY().equals(yDepart) && point.getX().gte(xFin)){
                yDepartManquant = false;
                if(point.getY().gt(parent.getMurBrute().getCentre().getY())){
                    pointsProfil.add(0, new PointPouce(point.getX(),yDepart));
                }else {
                    pointsProfil.add(new PointPouce(point.getX(),yDepart));
                }
            }else if(point.getY().gt(yDepart) && point.getX().gte(xFin) && yDepartManquant){
                if(point.getY().gt(parent.getMurBrute().getCentre().getY())){
                    pointsProfil.add(0, new PointPouce(point.getX(),yDepart));
                    pointsProfil.add(0, new PointPouce(point.getX(),yDepart));
                }else {
                    pointsProfil.add(new PointPouce(point.getX(),yDepart));
                    pointsProfil.add(new PointPouce(point.getX(),yDepart));
                }
                yDepartManquant = false;
            }
        }

        for(int i = pointsProfil.size()-2; i > 0; i--){
            PointPouce point1 = pointsProfil.get(i - 1);
            PointPouce point2 = pointsProfil.get(i + 1);
            double pente = point2.getY().diff(point1.getY()).diviser(point2.getX().diff(point1.getX()));
            double angleNormale = Math.atan(1 / pente);
            Pouce x = pointsProfil.get(i).getX().diff(epaisseur.multiplier(Math.cos(angleNormale)));
            Pouce y = pointsProfil.get(i).getY().diff(epaisseur.multiplier(-Math.sin(angleNormale)));
            pointsProfil.add(new PointPouce(x,y));
        }


        return new Polygone(pointsProfil);
    }
}
