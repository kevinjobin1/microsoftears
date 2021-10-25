package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class PoutreArriere extends Composante{
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;
    private Rectangle rectangle;

    public PoutreArriere(RoulotteController parent, Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centre = centre;
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre);
        this.setType(TypeComposante.POUTRE_ARRIERE);
        this.setPolygone(rectangle.getPolygone());
    }
    
    //todo
    public PoutreArriere(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(2,0,1);
        this.hauteur = new Pouce(2,0,1);
        //this.centre = ;
        this.rectangle = new Rectangle(this.longueur,this.hauteur,this.centre);
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
    }
}

