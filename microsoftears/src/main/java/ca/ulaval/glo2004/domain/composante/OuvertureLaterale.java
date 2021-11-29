package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class OuvertureLaterale extends Composante{
    private Pouce hauteur;
    private Pouce largeur;
    private PointPouce centre;
    private Rectangle rectangle;

    public OuvertureLaterale(RoulotteController parent, Pouce hauteur, Pouce largeur, PointPouce centre) {
        super(parent);
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.centre = centre;
        this.rectangle = new Rectangle(hauteur, largeur, centre);
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.OUVERTURE_LATERALE);
    }

    public OuvertureLaterale(RoulotteController parent) {
        super(parent);
        this.hauteur = new Pouce(10,0,1);
        this.largeur = new Pouce(10,0,1);
        this.centre = parent.getListeComposantes().get(0).getCentre();
        this.rectangle = new Rectangle(hauteur, largeur, centre);
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.OUVERTURE_LATERALE);
    }

    public Pouce gethauteur() {
        return hauteur;
    }

    public void sethauteur(Pouce hauteur) {
        this.hauteur = hauteur;
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

    @Override
    public int[] getValeurs() {
        return new int[]{hauteur.getPouces(), hauteur.getNumerateur(), hauteur.getDenominateur(),
                largeur.getPouces(), largeur.getNumerateur(), largeur.getDenominateur(),
                centre.getX().getPouces(), centre.getX().getNumerateur(), centre.getX().getDenominateur(),
                centre.getY().getPouces(), centre.getY().getNumerateur(), centre.getY().getDenominateur()};
    }

    @Override
    public void translate(PointPouce delta) {
        PointPouce pSouris = parent.getPositionPlan(parent.getPositionSouris());
        Pouce differenceX = centre.getX().add(delta.getX().diff(pSouris.getX()));
        Pouce differenceY = centre.getY().add(delta.getY().diff(pSouris.getY()));
        this.centre = new PointPouce(differenceX, differenceY);
        this.rectangle = new Rectangle(this.hauteur,this.largeur,this.centre);
        this.setPolygone(rectangle.getPolygone());

    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Hauteur", "Longueur", "CentreX", "CentreY"};
    }

    @Override
    public boolean getMode() {
        return false;
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }
}
