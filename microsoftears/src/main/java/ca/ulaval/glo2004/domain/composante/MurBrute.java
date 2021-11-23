package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.roulotte.RoulotteController;
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
        this.rectangle = new Rectangle(this.longueur,this.largeur,this.centre);
        this.setType(TypeComposante.MUR_BRUTE);
        this.setPolygone(rectangle.getPolygone());
    }

    public MurBrute(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(96,0,1);
        this.largeur = new Pouce(48,0,1);
        this.centre = new PointPouce();
        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.MUR_BRUTE);
    }

    public boolean verificationLongueur(Pouce valeur){
        return valeur.ste(new Pouce(96,0,1)) && valeur.gt(new Pouce(0,0,1));
    }

    public boolean verificationLargeur(Pouce valeur){
        return valeur.ste(new Pouce(48,0,1)) && valeur.gt(new Pouce(0,0,1));
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
