package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.LinkedList;
import java.util.List;

public class MurProfile extends Composante{

    /**
     * profilEllipses[0] est l'éllipse en haut à droite
     * profilEllipses[1] est l'éllipse en haut à gauche
     * profilEllipses[2] est l'éllipse en bas à gauche
     * profilEllipses[3] est l'éllipse en bas à droite
     */
    private ProfilEllipse[] profilEllipses= new ProfilEllipse[4];
    private ProfilBezier profilBezier;
    /**
     * true: Éllipses
     * false: Bézier
     */
    private boolean mode;

    public MurProfile(RoulotteController parent, boolean mode) {
        super(parent);
        this.mode = mode;
        this.profilEllipses[0] = new ProfilEllipse(parent, new Pouce(10,0,1), new Pouce(10,0,1),
                new PointPouce(parent.getMurBrute().getPolygone().getListePoints().get(0).getX().diff(new Pouce(5,0,1)),
                        parent.getMurBrute().getPolygone().getListePoints().get(0).getY().add(new Pouce(5,0,1)))); //ellipse en haut à droite

        this.profilEllipses[1] = new ProfilEllipse(parent, new Pouce(15,0,1), new Pouce(12,0,1),
                new PointPouce(parent.getMurBrute().getPolygone().getListePoints().get(1).getX().add(new Pouce(7,0,1)),
                        parent.getMurBrute().getPolygone().getListePoints().get(1).getY().add(new Pouce(5,0,1)))); //ellipse en haut à gauche

        this.profilEllipses[2] = new ProfilEllipse(parent, new Pouce(15,0,1), new Pouce(12,0,1),
                new PointPouce(parent.getMurBrute().getPolygone().getListePoints().get(2).getX().add(new Pouce(7,0,1)),
                        parent.getMurBrute().getPolygone().getListePoints().get(2).getY().diff(new Pouce(5,0,1)))); //ellipse en bas à gauche

        this.profilEllipses[3] = new ProfilEllipse(parent, new Pouce(5,0,1), new Pouce(5,0,1),
                parent.getMurBrute().getPolygone().getListePoints().get(3)); //ellipse en bas à droite

        this.profilBezier = new ProfilBezier(parent);
        this.setType(TypeComposante.MUR_PROFILE);
        this.setPolygone(getPolygone());
    }

    public ProfilEllipse[] getProfilEllipses() {
        return profilEllipses;
    }

    public ProfilBezier getProfilBezier() {
        return profilBezier;
    }

    public boolean getMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    @Override
    public Polygone getPolygone(){
        Polygone retour;
        if(mode){
            retour = new Polygone(listePointsModeEllipse());
        } else {
            retour = new Polygone(listePointsModeBezier());
        }
        return retour;
    }

    //todo mais pas pour le livrable 3
    private LinkedList<PointPouce> listePointsModeBezier() {
        return null;
    }


    private LinkedList<PointPouce> listePointsModeEllipse(){
        List<PointPouce> pointsMur = parent.getMurBrute().getPolygone().getListePoints();

        //cadran en haut à droite de l'éllipse0
        List<PointPouce> listeEllipse0 = new LinkedList<> (profilEllipses[0].getPolygone().getListePoints().
                subList(0, Math.round(parent.getNombrePoint()/4)));

        //cadran en haut à gauche de l'éllipse1
        List<PointPouce> listeEllipse1 = new LinkedList<> (profilEllipses[1].getPolygone().getListePoints().
                subList( Math.round(parent.getNombrePoint()/4),  Math.round(parent.getNombrePoint()/2)));

        //cadran en bas à gauche de l'éllipse2
        List<PointPouce> listeEllipse2 = new LinkedList<> (profilEllipses[2].getPolygone().getListePoints().
                subList(  Math.round(parent.getNombrePoint()/2),  Math.round(parent.getNombrePoint()*3/4)));

        //cadran en bas à droite de l'éllipse3
        List<PointPouce> listeEllipse3 = new LinkedList<> (profilEllipses[3].getPolygone().getListePoints().
                subList(  Math.round(parent.getNombrePoint()*3/4),  Math.round(parent.getNombrePoint())));

        LinkedList<PointPouce> listeRetour = new LinkedList<>();

        //ellipse 0
        boolean intersectionX = false;
        boolean intersectionY = false;
        boolean aucunPointAjoute = true;
        int indiceDernierPoint = 0;
        for(PointPouce point : listeEllipse0){
            if(point.getX().gte(pointsMur.get(0).getX())){
                intersectionX = true;
            }
            if(point.getY().ste(pointsMur.get(0).getY())){
                intersectionY = true;
            }
        }
        if(intersectionX && intersectionY){
            for(int i = 0; i < listeEllipse0.size(); i++){
                if(listeEllipse0.get(i).getX().ste(pointsMur.get(0).getX()) &&
                        listeEllipse0.get(i).getY().gte(pointsMur.get(0).getY())) {
                    if(aucunPointAjoute){
                        //ajouter un point sur la ligne du mur brute
                        listeRetour.add(new PointPouce(pointsMur.get(0).getX(), listeEllipse0.get(i).getY()));
                    }
                    listeRetour.add(listeEllipse0.get(i));
                    aucunPointAjoute = false;
                    indiceDernierPoint = i;
                }
            }
        }
        if (aucunPointAjoute) {
            listeRetour.add(pointsMur.get(0));
        }else{
            //ajouter un point sur la ligne du mur brute
            listeRetour.add(new PointPouce(listeEllipse0.get(indiceDernierPoint).getX(), pointsMur.get(0).getY()));
        }

        //ellipse 1
        intersectionX = false;
        intersectionY = false;
        aucunPointAjoute = true;
        indiceDernierPoint = 0;
        for(PointPouce point : listeEllipse1){
            if(point.getX().ste(pointsMur.get(1).getX())){
                intersectionX = true;
            }
            if(point.getY().ste(pointsMur.get(1).getY())){
                intersectionY = true;
            }
        }
        if(intersectionX && intersectionY){
            for(int i = 0; i < listeEllipse1.size(); i++){
                if(listeEllipse1.get(i).getX().gte(pointsMur.get(1).getX()) &&
                        listeEllipse1.get(i).getY().gte(pointsMur.get(1).getY())) {
                    if(aucunPointAjoute){
                        //ajouter un point sur la ligne du mur brute
                        listeRetour.add(new PointPouce(listeEllipse1.get(i).getX(), pointsMur.get(1).getY()));
                    }
                    listeRetour.add(listeEllipse1.get(i));
                    aucunPointAjoute = false;
                    indiceDernierPoint = i;
                }
            }
        }
        if (aucunPointAjoute) {
            listeRetour.add(pointsMur.get(1));
        }else{
            //ajouter un point sur la ligne du mur brute
            listeRetour.add(new PointPouce(pointsMur.get(1).getX(), listeEllipse1.get(indiceDernierPoint).getY()));
        }

        //ellipse 2
        intersectionX = false;
        intersectionY = false;
        aucunPointAjoute = true;
        indiceDernierPoint = 0;
        for(PointPouce point : listeEllipse2){
            if(point.getX().ste(pointsMur.get(2).getX())){
                intersectionX = true;
            }
            if(point.getY().gte(pointsMur.get(2).getY())){
                intersectionY = true;
            }
        }
        if(intersectionX && intersectionY){
            for(int i = 0; i < listeEllipse2.size(); i++){
                if(listeEllipse2.get(i).getX().gte(pointsMur.get(2).getX()) &&
                        listeEllipse2.get(i).getY().ste(pointsMur.get(2).getY())) {
                    if(aucunPointAjoute){
                        //ajouter un point sur la ligne du mur brute
                        listeRetour.add(new PointPouce(pointsMur.get(2).getX(), listeEllipse2.get(i).getY()));
                    }
                    listeRetour.add(listeEllipse2.get(i));
                    aucunPointAjoute = false;
                    indiceDernierPoint = i;
                }
            }
        }
        if (aucunPointAjoute) {
            listeRetour.add(pointsMur.get(2));
        }else{
            //ajouter un point sur la ligne du mur brute
            listeRetour.add(new PointPouce(listeEllipse2.get(indiceDernierPoint).getX(), pointsMur.get(2).getY()));
        }

        //ellipse 3
        intersectionX = false;
        intersectionY = false;
        aucunPointAjoute = true;
        indiceDernierPoint = 0;
        for(PointPouce point : listeEllipse3){
            if(point.getX().gte(pointsMur.get(3).getX())){
                intersectionX = true;
            }
            if(point.getY().gte(pointsMur.get(3).getY())){
                intersectionY = true;
            }
        }
        if(intersectionX && intersectionY){
            for(int i = 0; i < listeEllipse3.size(); i++){
                if(listeEllipse3.get(i).getX().ste(pointsMur.get(3).getX()) &&
                        listeEllipse3.get(i).getY().ste(pointsMur.get(3).getY())) {
                    if(aucunPointAjoute){
                        //ajouter un point sur la ligne du mur brute
                        listeRetour.add(new PointPouce(listeEllipse3.get(i).getX(), pointsMur.get(3).getY()));
                    }
                    listeRetour.add(listeEllipse3.get(i));
                    aucunPointAjoute = false;
                    indiceDernierPoint = i;
                }
            }
        }
        if (aucunPointAjoute) {
            listeRetour.add(pointsMur.get(3));
        }else{
            //ajouter un point sur la ligne du mur brute
            listeRetour.add(new PointPouce(pointsMur.get(3).getX(), listeEllipse3.get(indiceDernierPoint).getY()));
        }

        return listeRetour;
    }
}
