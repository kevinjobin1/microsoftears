package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.awt.*;
import java.util.LinkedList;

public class PoutreArriere extends Composante{
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;
    private Pouce centreX;
    private Rectangle rectangle;

    public PoutreArriere(RoulotteController parent, Pouce longueur, Pouce hauteur, Pouce centreX) {
        super(parent);
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centreX = centreX;
        Pouce centreY = getCentreY(this.centreX);
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        if(centreY != null) {
            this.centre = new PointPouce(this.centreX, centreY);
        }else{
            this.centre = new PointPouce(this.centreX,murBrute.getCentre().getY().diff(murBrute.
                    getLargeur().diviser(2)).add(hauteur.diviser(2)));
        }
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre,getAngle());
        this.setCouleurInitiale(new Color(150,233,80));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.POUTRE_ARRIERE);
        this.setPolygone(rectangle.getPolygone());
    }

    public PoutreArriere(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(2,0,1);
        this.hauteur = new Pouce(2,0,1);
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        centreX = murBrute.getCentre().getX().diff(murBrute.getLongueur().diviser(2)).
                add(new Pouce(11,0,1));
        Pouce centreY = getCentreY(centreX);
        if(centreY != null) {
            this.centre = new PointPouce(centreX, centreY);
        }else{
            this.centre = new PointPouce(centreX,murBrute.getCentre().getY().diff(murBrute.
                    getLargeur().diviser(2)).add(hauteur.diviser(2)));
        }
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre,getAngle());
        this.setCouleurInitiale(new Color(150,233,80));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.POUTRE_ARRIERE);
        this.setPolygone(rectangle.getPolygone());
    }

    public PoutreArriere(PoutreArriere copie){
        super(copie.parent);
        this.longueur = copie.getLongueur();
        this.hauteur = copie.getHauteur();
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        centreX = murBrute.getCentre().getX().diff(murBrute.getLongueur().diviser(2)).
                add(new Pouce(11,0,1));
        Pouce centreY = getCentreY(centreX);
        if(centreY != null) {
            this.centre = new PointPouce(centreX, centreY);
        }else{
            this.centre = new PointPouce(centreX,murBrute.getCentre().getY().diff(murBrute.
                    getLargeur().diviser(2)).add(hauteur.diviser(2)));
        }
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre,getAngle());
        this.setCouleurInitiale(copie.getCouleurInitiale());
        this.setCouleur(copie.getCouleur());
        this.setType(copie.getType());
        this.setPolygone(rectangle.getPolygone());
    }

    public boolean verificationLongueur(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getLongueur().diviser(2)) && valeur.gt(new Pouce(0,0,1));
    }
    public boolean verificationHauteur(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getLargeur().diviser(2)) && valeur.gt(new Pouce(0,0,1));
    }
    public boolean verificationCentreX(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.gt(mur.getCentre().getX().diff(mur.getLongueur().diviser(2)).add(longueur.diviser(2)))
                && valeur.st(mur.getCentre().getX().add(mur.getLongueur().diviser(2)).diff(longueur.diviser(2)));
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

    @Override
    public int[] getValeurs() {
        return new int[]{hauteur.getPouces(), hauteur.getNumerateur(), hauteur.getDenominateur(),
                longueur.getPouces(), longueur.getNumerateur(), longueur.getDenominateur(),
                centre.getX().getPouces(), centre.getX().getNumerateur(), centre.getX().getDenominateur()};
    }

    @Override
    public void translate(PointPouce delta) {
        PointPouce pSouris = parent.getPositionPlan(parent.getPositionSouris());
        this.centreX = centreX.add(delta.getX().diff(pSouris.getX()));
        Pouce centreY = getCentreY(this.centreX);
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        if(centreY != null) {
            this.centre = new PointPouce(this.centreX, centreY);
        }else {
            this.centre = new PointPouce(this.centreX,murBrute.getCentre().getY().diff(murBrute.
                    getLargeur().diviser(2)).add(hauteur.diviser(2)));
        }
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre,getAngle());
        this.setPolygone(rectangle.getPolygone());

    }

    @Override
    public void snapToGrid(PointPouce pointGrille){
        this.centreX = pointGrille.getX();
        Pouce centreY = getCentreY(this.centreX);
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        if(centreY != null) {
            this.centre = new PointPouce(this.centreX, centreY);
        }else {
            this.centre = new PointPouce(this.centreX,murBrute.getCentre().getY().diff(murBrute.
                    getLargeur().diviser(2)).add(hauteur.diviser(2)));
        }
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre,getAngle());
        this.setPolygone(rectangle.getPolygone());
    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Hauteur", "Longueur", "CentreX"};
    }

    @Override
    public boolean getMode() {
        return false;
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
        this.centreX = centre.getX();
    }

    private Pouce getCentreY(Pouce centreX){
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        LinkedList<PointPouce> murProfilePoints = parent.getListeComposantes().get(1).getPolygone().getListePoints();
        int indiceDepart = 0;
        int indiceFin = 0;
        for(int i = 0; i < murProfilePoints.size();i++){
            if(murProfilePoints.get(i).getX().ste(centreX.add(getLongueur().diviser(2))) &&
                    murProfilePoints.get(i).getX().gte(centreX.diff(getLongueur().diviser(2))) &&
                    murProfilePoints.get(i).getY().ste(murBrute.getCentre().getY())){
                if(indiceDepart == 0){
                    indiceDepart = i;
                }
                indiceFin = i;
            }
        }
        int indiceMilieu = Math.round((indiceDepart + indiceFin)/2);
        Pouce retour = null;
        if(indiceMilieu != 0) {
            retour = murProfilePoints.get(indiceMilieu).getY().
                    add(hauteur.diviser(2).multiplier(Math.cos(getAngle()))).diff(longueur.diviser(2).multiplier(Math.sin(getAngle())));
        }
        return retour;
    }

    protected double getAngle(){
        MurBrute murBrute = (MurBrute) parent.getListeComposantes().get(0);
        LinkedList<PointPouce> murProfilePoints = parent.getListeComposantes().get(1).getPolygone().getListePoints();
        int indiceDepart = 0;
        int indiceFin = 0;
        for(int i = 0; i < murProfilePoints.size();i++){
            if(murProfilePoints.get(i).getX().ste(centreX.add(getLongueur().diviser(2))) &&
                    murProfilePoints.get(i).getX().gte(centreX.diff(getLongueur().diviser(2))) &&
                    murProfilePoints.get(i).getY().ste(murBrute.getCentre().getY())){
                if(indiceDepart == 0){
                    indiceDepart = i;
                }
                indiceFin = i;
            }
        }
        int indiceMilieu = Math.round((indiceDepart + indiceFin)/2);
        double angle = 0;

        if(indiceMilieu != 0) {
            PointPouce point1 = murProfilePoints.get(indiceMilieu - 1);
            PointPouce point2 = murProfilePoints.get(indiceMilieu + 1);
            angle = Math.atan(point2.getY().diff(point1.getY()).diviser(point2.getX().diff(point1.getX())));
        }
        return angle;
    }
}

