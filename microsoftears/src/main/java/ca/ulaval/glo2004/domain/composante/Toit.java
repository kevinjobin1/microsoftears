package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
import java.util.LinkedList;

public class Toit extends Composante implements Cloneable {
    private Pouce epaisseur;

    public Toit(RoulotteController parent, Pouce epaisseur) {
        super(parent);
        this.epaisseur = epaisseur;
        this.setCouleurInitiale(new Color(100,70,200));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.TOIT);
        this.setPolygone(getPolygone());
    }

    public Toit(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(2,1,4);
        this.setCouleurInitiale(new Color(100,70,200));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.TOIT);
        this.setPolygone(getPolygone());
    }

    public Toit(Toit copie) {
        super(copie.parent);
        this.epaisseur = copie.epaisseur;
        this.setCouleurInitiale(copie.getCouleurInitiale());
        this.setCouleur(copie.getCouleur());
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
        MurProfile murProfile = (MurProfile) parent.getListeComposantes().get(1);
        MurSeparateur murSeparateur = (MurSeparateur) parent.getListeComposantes().get(9);

        if (murProfile.getMode()){
        Pouce xFin = murSeparateur.getCentre().getX().add(murSeparateur.getEpaisseur().diviser(2));
        Pouce yDepart = murBrute.getCentre().getY().add(murBrute.getLargeur().diviser(2)).
                diff(plancher.getEpaisseur());
        LinkedList<PointPouce> listePointMur = murProfile.getPolygone().getListePoints();
        LinkedList<PointPouce> pointsProfil= new LinkedList<>();
        boolean yDepartManquant = true;
        boolean xFinManquant = true;

        int j = 0;
        for(int i = 0; i < listePointMur.size(); i++){
            PointPouce point = listePointMur.get(i);
            if(point.getX().gte(xFin) && point.getY().ste(yDepart)) {
                if(point.getY().gt(murBrute.getCentre().getY())){
                    if(j==0){
                        pointsProfil.add(j,point);
                        j++;
                    }
                    pointsProfil.add(j, point);
                    j++;
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
            }
        }
            if(yDepartManquant){
                if(pointsProfil.get(0).getY().gt(murBrute.getCentre().getY())){
                    pointsProfil.add(0, new PointPouce(pointsProfil.get(0).getX(),yDepart));
                    pointsProfil.add(0, new PointPouce(pointsProfil.get(0).getX(),yDepart));
                }else {
                    pointsProfil.add(0,new PointPouce(murBrute.getPolygone().getListePoints().get(3).getX(),yDepart));
                    pointsProfil.add(0,new PointPouce(murBrute.getPolygone().getListePoints().get(3).getX(),yDepart));
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
                y = murBrute.getCentre().getY().diff(murBrute.getLargeur().diviser(2)).
                        add(epaisseur);
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
        else {
            return new Polygone(calculerPositionBezier(murBrute, murProfile, plancher, murSeparateur));
        }
    }

    private LinkedList<PointPouce> calculerPositionBezier(MurBrute mur, MurProfile profil,
                                                          Plancher plancher, MurSeparateur murSeparateur){

        // notre liste de points de la courbe et notre liste vide
        LinkedList<PointPouce> pointsToit = new LinkedList<>();
        LinkedList<PointPouce> profilPoints = profil.getPolygone().getListePoints();

        // déclarations de variables
        PointPouce p1,p2,p, premierPointCourbe, dernierPointCourbe, coinDebutToit, coinFinToit;
        Pouce x,y;
        int indexPremierPoint = 0;
        int indexDernierPoint = 0;
        double x1,x2,y1,y2, angle, deltaX, deltaY;

        // On cherche les quatres coins de notre polygone du toit
        // On connait déjà les coins sur la courbe qui sont en bas et en haut de notre courbe

        // La position en x du coin supérieur  qui est aussi le x de notre p2 (l'autre coin supérieur)
        x = murSeparateur.getCentre().getX().add(murSeparateur.getEpaisseur().diviser(2));
        // la position en y de notre coin inférieur est aussi le y de notre p3 (l'autre coin inférieur)
        y = plancher.getCentre().getY().diff(plancher.getEpaisseur().diviser(2));

        // On part de la fin de la courbe (à gauche) et on trouve notre segment de courbe correspondant au toit
        for(int i = profilPoints.size() - 1; i > 0; i--){

            if (profilPoints.get(i).getY().gte(y) && profilPoints.get(i).getX().gte(x)){
                indexPremierPoint = i;
            }
            if(profilPoints.get(i).getX().gte(x) && profilPoints.get(i).getY().ste(y)){
                indexDernierPoint = i;
            }
        }
        indexPremierPoint -= 1;
        premierPointCourbe = profilPoints.get(indexPremierPoint);
        pointsToit.add(premierPointCourbe);

        for(int i = indexPremierPoint -1; i > indexDernierPoint; i--){
            pointsToit.add(profilPoints.get(i));
        }

        dernierPointCourbe = profilPoints.get(indexDernierPoint);
        pointsToit.add(dernierPointCourbe);

        coinFinToit = dernierPointCourbe.add(new Pouce(), epaisseur);
        pointsToit.add(coinFinToit);

        for(int i = indexDernierPoint + 1; i < indexPremierPoint; i++){
            p = profilPoints.get(i);
            p1 = profilPoints.get(i - 1);
            p2 = profilPoints.get(i + 1);
            y1 = p1.getY().toDouble();
            y2 = p2.getY().toDouble();
            x1 = p1.getX().toDouble();
            x2 = p2.getX().toDouble();
            angle = Math.acos((y1-y2)/(Math.sqrt((Math.pow((x1-x2),2) + Math.pow((y1-y2),2)))));
            angle -= Math.PI/2; // on retranche 90 degrés (pi/2)

            // deltaX = sin(angle) * epaisseur et deltaY = cos(angle) * epaisseur
            deltaX = Math.sin(angle) * epaisseur.toDouble();
            deltaY = Math.cos(angle) * epaisseur.toDouble();
            pointsToit.add(new PointPouce(new Pouce(p.getX().toDouble() - deltaX),
                    new Pouce(p.getY().toDouble() + deltaY)));
        }

        // calcul de la tangente à ce point en utilisant une approximation par les deux points voisins
        p1 = profilPoints.get(indexPremierPoint - 1);
        p2 = profilPoints.get(indexPremierPoint + 1);


        y1 = p1.getY().toDouble();
        y2 = p2.getY().toDouble();
        x1 = p1.getX().toDouble();
        x2 = p2.getX().toDouble();
        angle = Math.acos((y1-y2)/(Math.sqrt((Math.pow((x1-x2),2) + Math.pow((y1-y2),2)))));
        angle -= Math.PI/2; // on retranche 90 degrés (pi/2)

        // deltaX = sin(angle) * epaisseur et deltaY = cos(angle) * epaisseur
        deltaX = Math.sin(angle) * epaisseur.toDouble();
        coinDebutToit = new PointPouce(new Pouce(premierPointCourbe.getX().toDouble() - deltaX),
               premierPointCourbe.getY());
        pointsToit.add(coinDebutToit);

        /*LinkedList<PointPouce> listeTemp = new LinkedList<>(); // sert a contenir nos points décalés
        for(int i = pointsToit.size() - 2; i > 0; i--){
            listeTemp.add(getPointDecaleBezier(pointsToit, i, epaisseur, -1));
        }
        for(PointPouce p : listeTemp){
            pointsToit.add(p);
        }*/

        return pointsToit;
    }

    @Override
    public PointPouce getCentre() {
        return null;
    }


    @Override
    public int[] getValeurs() {
        return new int[]{epaisseur.getPouces(), epaisseur.getNumerateur(), epaisseur.getDenominateur()};
    }

    @Override
    public void translate(PointPouce delta) {

    }

    @Override
    public void snapToGrid(PointPouce pointGrille) {

    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Épaisseur"};
        }

    @Override
    public Object[] getModes(){
        return new Object[]{};
    }

}
