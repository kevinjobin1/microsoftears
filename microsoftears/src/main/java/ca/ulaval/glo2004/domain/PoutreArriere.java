package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class PoutreArriere extends Composante{
    private Pouce longueur;
    private Pouce largeur;
    private PointPouce centre;
    private Rectangle rectangle;

    public PoutreArriere(RoulotteController parent, Pouce longueur, Pouce largeur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.largeur = largeur;
        this.centre = centre;
        this.rectangle = new Rectangle(this.longueur,this.largeur,this.centre);
        this.setType(TypeComposante.POUTRE_ARRIERE);
        this.setPolygone(rectangle.getPolygone());
    }
    
    //à compléter
    public PoutreArriere(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(2,0,1);
        this.largeur = new Pouce(2,0,1);
        //this.centre = ;
        this.rectangle = new Rectangle(this.longueur,this.largeur,this.centre);
        this.setType(TypeComposante.POUTRE_ARRIERE);
        this.setPolygone(rectangle.getPolygone());
    }

    public Pouce getLongueur() {
        return longueur;
    }

    public void setLongueur(Pouce longueur) {
        this.longueur = longueur;
    }

    public Pouce getLargeur() {
        return largeur;
    }

    public void setLargeur(Pouce largeur) {
        this.largeur = largeur;
    }

    public PointPouce getCentre() {
        return centre;
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }
}

