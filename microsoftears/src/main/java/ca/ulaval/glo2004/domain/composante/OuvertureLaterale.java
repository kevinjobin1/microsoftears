package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.roulotte.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class OuvertureLaterale extends Composante{
    private Pouce longueur;
    private Pouce largeur;
    private PointPouce centre;
    private Rectangle rectangle;

    public OuvertureLaterale(RoulotteController parent, Pouce longueur, Pouce largeur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.largeur = largeur;
        this.centre = centre;
        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.OUVERTURE_LATERALE);
    }

    public OuvertureLaterale(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(10,0,1);
        this.largeur = new Pouce(10,0,1);
        this.centre = parent.getMurBrute().getCentre();
        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.OUVERTURE_LATERALE);
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
