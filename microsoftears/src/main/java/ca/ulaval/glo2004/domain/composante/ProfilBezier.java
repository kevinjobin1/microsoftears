package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.CourbeBezier;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.ArrayList;


public class ProfilBezier extends Composante {

    private CourbeBezier bezier;
    private ArrayList<PointControle> pointsControle;
    //TODO: à remplacer, mais pas pour le livrable 3
    public ProfilBezier(RoulotteController parent) {
        super(parent);
        // Points de contrôles initiaux
        this.pointsControle = new ArrayList<>();
        ArrayList<PointPouce> points = new ArrayList<>();
        points.add(parent.getListeComposantes().get(0).getPolygone().getListePoints().get(2));
        points.add(parent.getListeComposantes().get(0).getPolygone().getListePoints().get(1));
        points.add(parent.getListeComposantes().get(0).getPolygone().getListePoints().get(0));
        points.add(parent.getListeComposantes().get(0).getPolygone().getListePoints().get(3));
        this.pointsControle.add(new PointControle(parent,
                new Pouce(3,0,1),
                new Pouce(3,0,1),
                points.get(0), TypeComposante.POINT_CONTROLE_1));
        this.pointsControle.add(new PointControle(parent,
                new Pouce(3,0,1),
                new Pouce(3,0,1),
                points.get(1).diff(3,13), TypeComposante.POINT_CONTROLE_2));
        this.pointsControle.add(new PointControle(parent,
                new Pouce(3,0,1),
                new Pouce(3,0,1),
                points.get(2).diff(0,13).add(3,0), TypeComposante.POINT_CONTROLE_3));
        this.pointsControle.add(new PointControle(parent,
                new Pouce(3,0,1),
                new Pouce(3,0,1),
                points.get(3),
                TypeComposante.POINT_CONTROLE_4));
        this.bezier = new CourbeBezier(points);
        this.setPolygone(bezier.getPolygone());
        this.setType(TypeComposante.PROFIL_BEZIER);
    }

    @Override
    public PointPouce getCentre() {
        return null;
    }

    public void updatePointsControles(){
        ArrayList<PointPouce> points = new ArrayList<>();
        for (PointControle point : this.getPointsControle()){
            points.add(point.getCentre());
        }
        this.bezier = new CourbeBezier(points);
        this.setPolygone(bezier.getPolygone());
    }

    public CourbeBezier getBezier() {
        return bezier;
    }

    public void setBezier(CourbeBezier bezier) {
        this.bezier = bezier;
    }

    public ArrayList<PointControle> getPointsControle() {
        return pointsControle;
    }

    public void setPointsControle(ArrayList<PointControle> pointsControle) {
        this.pointsControle = pointsControle;
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

}
