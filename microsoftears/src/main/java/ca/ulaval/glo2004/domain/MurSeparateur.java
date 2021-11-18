package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class MurSeparateur extends Composante{

    private Pouce epaisseur;
    private Pouce hauteur;
    private Pouce distancePoutreArriere;
    private Rectangle rectangle;

    public MurSeparateur(RoulotteController parent, Pouce epaisseur, Pouce hauteur, Pouce distancePoutreArriere) {
        super(parent);
        this.epaisseur = epaisseur;
        this.hauteur = hauteur;
        this.distancePoutreArriere = distancePoutreArriere;
        this.rectangle = new Rectangle(this.epaisseur, this.hauteur, this.getCentre());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.MUR_SEPARATEUR);
    }

    public MurSeparateur(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(3,0,1);
        this.hauteur = new Pouce(40,0,1);
        this.distancePoutreArriere = new Pouce(5,0,1);
        this.rectangle = new Rectangle(this.epaisseur, this.hauteur, this.getCentre());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.MUR_SEPARATEUR);
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    public Pouce getHauteur() {
        return hauteur;
    }

    public void setHauteur(Pouce hauteur) {
        this.hauteur = hauteur;
    }

    public Pouce getDistancePoutreArriere() {
        return distancePoutreArriere;
    }

    public void setDistancePoutreArriere(Pouce distancePoutreArriere) {
        this.distancePoutreArriere = distancePoutreArriere;
    }

    //à tester
    public PointPouce getCentre(){
        PoutreArriere poutre = this.getParent().getPoutreArriere();
        MurBrute mur = this.getParent().getMurBrute();
        Pouce x = poutre.getCentre().getX().add(poutre.getLongueur().diviser(2)).add(distancePoutreArriere).add(epaisseur.diviser(2));
        Pouce y = mur.getCentre().getY().diff(mur.getLargeur().diviser(2)).add(hauteur.diviser(2));
        return new PointPouce(x,y);
    }
}
