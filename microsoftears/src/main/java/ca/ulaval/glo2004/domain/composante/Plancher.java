package ca.ulaval.glo2004.domain.composante;


import ca.ulaval.glo2004.domain.roulotte.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class Plancher extends Composante {
    private Pouce epaisseur;
    private Pouce margeAvant;
    private Pouce margeArriere;
    private Rectangle rectangle;

    public Plancher(RoulotteController parent, Pouce epaisseur, Pouce margeAvant, Pouce margeArriere) {
        super(parent);
        this.epaisseur = epaisseur;
        this.margeAvant = margeAvant;
        this.margeArriere = margeArriere;
        this.rectangle = new Rectangle(this.getLongueur(), this.epaisseur, this.getCentre());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.PLANCHER);
    }

    public Plancher(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(1,1,4);
        this.margeAvant = new Pouce(5,0,1);
        this.margeArriere = new Pouce(5,0,1);;
        this.rectangle = new Rectangle(this.getLongueur(), this.epaisseur, this.getCentre());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.PLANCHER);
    }

    public boolean verificationEpaisseur(Pouce valeur){
        return valeur.st(parent.getMurBrute().getCentre().getY()) && valeur.gt(new Pouce(0,0,1));
    }

    public boolean verificationMargeAvant(Pouce valeur){
        return valeur.st(parent.getMurBrute().getLongueur().diviser(2)) &&
                valeur.gt(parent.getMurprofile().getProfilEllipses()[3].getLongueur().diviser(2).
                        diff(parent.getMurprofile().getProfilEllipses()[3].getCentre().getX().
                                diff(parent.getMurBrute().getPolygone().getListePoints().get(3).getX())));
    }

    public boolean verificationMargeArriere(Pouce valeur){
        return valeur.st(parent.getMurBrute().getLongueur().diviser(2)) &&
                valeur.gt(parent.getMurprofile().getProfilEllipses()[2].getLongueur().diviser(2).
                        diff(parent.getMurBrute().getPolygone().getListePoints().get(2).getX().
                                diff(parent.getMurprofile().getProfilEllipses()[2].getCentre().getX())));
    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    public Pouce getMargeAvant() {
        return margeAvant;
    }

    public void setMargeAvant(Pouce margeAvant) {
        this.margeAvant = margeAvant;
    }

    public Pouce getMargeArriere() {
        return margeArriere;
    }

    public void setMargeArriere(Pouce margeArriere) {
        this.margeArriere = margeArriere;
    }

    private PointPouce getCentre(){
        MurBrute mur = parent.getMurBrute();
        Pouce x = mur.getCentre().getX().add(mur.getLongueur().diviser(2)).diff(margeAvant).diff(getLongueur().diviser(2));
        Pouce y = mur.getLargeur().diviser(2).add(mur.getCentre().getY()).diff(this.epaisseur.diviser(2));
        return new PointPouce(x,y);
    }

    private Pouce getLongueur(){
        return this.parent.getMurBrute().getLongueur().diff(this.margeArriere).diff(this.margeAvant);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
