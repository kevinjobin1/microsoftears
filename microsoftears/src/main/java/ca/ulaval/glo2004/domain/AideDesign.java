package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.awt.*;

public class AideDesign extends Composante{

    private Pouce longueur;
    private Pouce largeur;
    private PointPouce centre;
    private Rectangle rectangle;

    public AideDesign(RoulotteController parent, Pouce longueur, Pouce largeur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.largeur = largeur;
        this.centre = centre;
        this.rectangle = new Rectangle(this.longueur,this.largeur,this.centre);
        this.setType(TypeComposante.AIDE_DESIGN);
        this.setPolygone(rectangle.getPolygone());
    }

    public AideDesign(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(12,0,1);
        this.largeur = new Pouce(12,0,1);
        this.centre = new PointPouce(new Pouce(50,0,1), new Pouce(50,0,1));
        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.setType(TypeComposante.AIDE_DESIGN);
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
