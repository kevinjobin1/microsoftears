package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.RectangleCoinRond;

public class AideDesign extends Composante{

    private Pouce longueur;
    private Pouce largeur;
    private PointPouce centre;
    private RectangleCoinRond rectangle;


    public AideDesign(RoulotteController parent, Pouce longueur, Pouce largeur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.largeur = largeur;
        this.centre = centre;
        this.rectangle = new RectangleCoinRond(longueur,largeur, centre, new Pouce(4.75));
        this.setType(TypeComposante.AIDE_DESIGN);
        this.setPolygone(rectangle.getPolygone());
    }

    public AideDesign(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(10,0,1);
        this.largeur = new Pouce(10,0,1);
        Pouce centreX = parent.getListeComposantes().get(0).getCentre().getX().diff(new Pouce(15));
        Pouce centreY = parent.getListeComposantes().get(0).getCentre().getY().diff(new Pouce(10));
        this.centre = new PointPouce(centreX, centreY);
        //this.centre = parent.getListeComposantes().get(0).getCentre();
        this.rectangle = new RectangleCoinRond(longueur, largeur, centre, new Pouce(4.75));
        this.setType(TypeComposante.AIDE_DESIGN);
        this.setPolygone(rectangle.getPolygone());
    }

    public AideDesign(AideDesign copie) {
        super(copie.parent);
        this.longueur = copie.longueur;
        this.largeur = copie.largeur;
        this.centre = copie.centre;
        this.rectangle = copie.rectangle;
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

    @Override
    public int[] getValeurs() {
        return new int[]{longueur.getPouces(), longueur.getNumerateur(), longueur.getDenominateur(),
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
        this.rectangle = new RectangleCoinRond(this.longueur,this.largeur, this.centre , new Pouce(4.75));
        this.setPolygone(rectangle.getPolygone());
    }

    @Override
    public void snapToGrid(PointPouce pointGrille) {
        this.centre = pointGrille;
        this.rectangle = new RectangleCoinRond(this.longueur,this.largeur, this.centre, new Pouce(4.75));
        this.setPolygone(rectangle.getPolygone());
    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Longueur", "Largeur", "CentreX", "CentreY"};
    }

    @Override
    public Object[] getModes(){
        return new Object[]{};
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }
}
