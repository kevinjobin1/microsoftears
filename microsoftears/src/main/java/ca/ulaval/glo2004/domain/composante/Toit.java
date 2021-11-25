package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.util.LinkedList;

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

        Plancher plancher = (Plancher) (parent.getListeComposantes().get(6));
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutreArriere = (PoutreArriere) parent.getListeComposantes().get(7);
        MurProfile murProfile = (MurProfile) parent.getListeComposantes().get(2);
        MurSeparateur murSeparateur = (MurSeparateur) parent.getListeComposantes().get(9); 

        Pouce xFin = murSeparateur.getCentre().getX().add(murSeparateur.getEpaisseur().diviser(2));
        Pouce yDepart = murBrute.getCentre().getY().add(murBrute.getLargeur().diviser(2)).
                diff(plancher.getEpaisseur());
        LinkedList<PointPouce> listePointMur = murProfile.getPolygone().getListePoints();
        LinkedList<PointPouce> pointsProfil= new LinkedList<>();
        boolean yDepartManquant = true;
        boolean xFinManquant = true;

        for(int i = 0; i < listePointMur.size(); i++){
            PointPouce point = listePointMur.get(i);
            if(point.getX().gte(xFin) && point.getY().ste(yDepart)) {
                if(point.getY().gt(murBrute.getCentre().getY())){
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
                if(point.getY().gt(murBrute.getCentre().getY())){
                    pointsProfil.add(0, new PointPouce(point.getX(),yDepart));
                }else {
                    pointsProfil.add(new PointPouce(point.getX(),yDepart));
                }
            }else if(point.getY().gt(yDepart) && point.getX().gte(xFin) && yDepartManquant){
                if(point.getY().gt(murBrute.getCentre().getY())){
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

            //lorsque les points sont dans le coin mais sont plus petit que l'épaisseur du mur
            if(x.gt(murBrute.getCentre().getX().add(murBrute.getLongueur().diviser(2)).
                    diff(epaisseur))){
                x = murBrute.getCentre().getX().add(murBrute.getLongueur().diviser(2)).
                        diff(epaisseur);
            }
            if(y.st(murBrute.getCentre().getY().diff(murBrute.getLargeur().diviser(2)).
                    add(epaisseur))) {
                y = murBrute.getCentre().getY().add(murBrute.getLargeur().diviser(2)).
                        diff(epaisseur);
            }
            //exception: retirer les points qui sont plus petit que la courbe
            boolean pointValide = true;
            if (y.ste(murBrute.getCentre().getY()) &&
                    pointsProfil.getLast().getY().ste(murBrute.getCentre().getY())) {
                if (x.st(pointsProfil.getLast().getX())) {
                    pointsProfil.removeLast();
                }
                if (y.st(pointsProfil.getLast().getY())) {
                    pointValide = false;
                }
            }
            if(pointValide) {
                pointsProfil.add(new PointPouce(x, y));
            }
        }
        return new Polygone(pointsProfil);
    }

    @Override
    protected PointPouce getCentre() {
        return null;
    }

    @Override
    public int[] getValeurs() {
        return new int[0];
    }

    @Override
    public String[] getNomsAttributs() {
        return new String[0];
    }
}
