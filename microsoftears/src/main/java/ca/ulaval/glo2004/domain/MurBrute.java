package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class MurBrute extends Composante{

    private Pouce longueur;
    private Pouce largeur;
    private PointPouce centre;
    private Rectangle rectangle;

    public MurBrute(RoulotteController parent, Pouce longueur, Pouce largeur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.largeur = largeur;
        this.centre = centre;
        this.setType(TypeComposante.MUR_BRUTE);
    }

    public MurBrute(RoulotteController parent) {
        super(parent);
        this.rectangle = new Rectangle(new Pouce(96), new Pouce(48), new PointPouce());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.MUR_BRUTE);
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
