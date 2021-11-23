package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.roulotte.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.util.LinkedList;

public class PoutreArriere extends Composante{
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;
    private Pouce centreX;
    private Rectangle rectangle;

    //todo ou enlever pas fonctionnel
    public PoutreArriere(RoulotteController parent, Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centre = centre;
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre,getAngle());
        this.setType(TypeComposante.POUTRE_ARRIERE);
        this.setPolygone(rectangle.getPolygone());
    }

    public PoutreArriere(RoulotteController parent, Pouce longueur, Pouce hauteur, Pouce centreX) {
        super(parent);
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centreX = centreX;
        Pouce centreY = getCentreY(this.centreX);
        if(centreY != null) {
            this.centre = new PointPouce(this.centreX, centreY);
        }else{
            this.centre = new PointPouce(this.centreX,parent.getMurBrute().getCentre().getY().diff(parent.getMurBrute().
                    getLargeur().diviser(2)).add(hauteur.diviser(2)));
        }
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre,getAngle());
        this.setType(TypeComposante.POUTRE_ARRIERE);
        this.setPolygone(rectangle.getPolygone());
    }

    public PoutreArriere(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(2,0,1);
        this.hauteur = new Pouce(2,0,1);
        centreX = parent.getMurBrute().getCentre().getX().diff(parent.getMurBrute().getLongueur().diviser(2)).
                add(new Pouce(11,0,1));
        Pouce centreY = getCentreY(centreX);
        if(centreY != null) {
            this.centre = new PointPouce(centreX, centreY);
        }else{
            this.centre = new PointPouce(centreX,parent.getMurBrute().getCentre().getY().diff(parent.getMurBrute().
                    getLargeur().diviser(2)).add(hauteur.diviser(2)));
        }
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre,getAngle());
        this.setType(TypeComposante.POUTRE_ARRIERE);
        this.setPolygone(rectangle.getPolygone());
    }

    public Pouce getLongueur() {
        return longueur;
    }

    public void setLongueur(Pouce longueur) {
        this.longueur = longueur;
    }

    public Pouce getHauteur() {
        return hauteur;
    }

    public void setHauteur(Pouce hauteur) {
        this.hauteur = hauteur;
    }

    public PointPouce getCentre() {
        return centre;
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
        this.centreX = centre.getX();
    }

    private Pouce getCentreY(Pouce centreX){
        LinkedList<PointPouce> mur = parent.getMurprofile().getPolygone().getListePoints();
        int indiceDepart = 0;
        int indiceFin = 0;
        for(int i = 0; i < mur.size();i++){
            if(mur.get(i).getX().ste(centreX.add(getLongueur().diviser(2))) &&
                    mur.get(i).getX().gte(centreX.diff(getLongueur().diviser(2))) &&
                    mur.get(i).getY().ste(parent.getMurBrute().getCentre().getY())){
                if(indiceDepart == 0){
                    indiceDepart = i;
                }
                indiceFin = i;
            }
        }
        int indiceMilieu = Math.round((indiceDepart + indiceFin)/2);
        Pouce retour = null;
        if(indiceMilieu != 0) {
            retour = parent.getMurprofile().getPolygone().getListePoints().get(indiceMilieu).getY().
                    add(hauteur.diviser(2).multiplier(Math.cos(getAngle()))).diff(longueur.diviser(2).multiplier(Math.sin(getAngle())));
        }
        return retour;
    }

    protected double getAngle(){
        LinkedList<PointPouce> mur = parent.getMurprofile().getPolygone().getListePoints();
        int indiceDepart = 0;
        int indiceFin = 0;
        for(int i = 0; i < mur.size();i++){
            if(mur.get(i).getX().ste(centreX.add(getLongueur().diviser(2))) &&
                    mur.get(i).getX().gte(centreX.diff(getLongueur().diviser(2))) &&
                    mur.get(i).getY().ste(parent.getMurBrute().getCentre().getY())){
                if(indiceDepart == 0){
                    indiceDepart = i;
                }
                indiceFin = i;
            }
        }
        int indiceMilieu = Math.round((indiceDepart + indiceFin)/2);
        double angle = 0;

        if(indiceMilieu != 0) {
            PointPouce point1 = mur.get(indiceMilieu - 1);
            PointPouce point2 = mur.get(indiceMilieu + 1);
            angle = Math.atan(point2.getY().diff(point1.getY()).diviser(point2.getX().diff(point1.getX())));
        }
        return angle;
    }
}

