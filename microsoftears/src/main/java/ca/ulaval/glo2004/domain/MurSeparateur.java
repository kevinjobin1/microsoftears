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
        this.setType(TypeComposante.MUR_SEPARATEUR);
    }

    //à coder
    public MurSeparateur(RoulotteController parent) {
        super(parent);
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

    //à coder
    private PointPouce getCentre(){
        return null;
    }
}
