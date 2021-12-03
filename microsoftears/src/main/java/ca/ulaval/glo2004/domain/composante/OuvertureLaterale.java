package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.RectangleCoinRond;

import java.awt.*;
import java.awt.geom.Area;

public class OuvertureLaterale extends Composante{
    private Pouce hauteur;
    private Pouce largeur;
    private PointPouce centre;
    private Pouce rayon;
    private RectangleCoinRond rectangle;

    public OuvertureLaterale(RoulotteController parent, Pouce hauteur, Pouce largeur, PointPouce centre, Pouce rayonCourbure) {
        super(parent);
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.centre = centre;
        this.rayon = rayonCourbure;
        this.rectangle = new RectangleCoinRond(hauteur, largeur, centre, rayon);
        this.setTransparenceInitiale(0.1f);
        this.setTransparence(0.1f);
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.OUVERTURE_LATERALE);
    }

    public OuvertureLaterale(RoulotteController parent) {
        super(parent);
        this.hauteur = new Pouce(36,0,1);
        this.largeur = new Pouce(26,0,1);
        Pouce centreX = parent.getListeComposantes().get(0).getCentre().getX().add(new Pouce(15,0,0));
        this.centre = new PointPouce(centreX, parent.getListeComposantes().get(0).getCentre().getY());
        this.rayon = new Pouce(4,1,2);
        this.rectangle = new RectangleCoinRond(hauteur, largeur, centre, rayon);
        this.setTransparenceInitiale(0.1f);
        this.setTransparence(0.1f);
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
                centre.getY().getPouces(), centre.getY().getNumerateur(), centre.getY().getDenominateur(),
                rayon.getPouces(), rayon.getNumerateur(), rayon.getDenominateur()};
    }

    @Override
    public void translate(PointPouce delta) {
        PointPouce pSouris = parent.getPositionPlan(parent.getPositionSouris());
        Pouce differenceX = centre.getX().add(delta.getX().diff(pSouris.getX()));
        Pouce differenceY = centre.getY().add(delta.getY().diff(pSouris.getY()));
        this.centre = new PointPouce(differenceX, differenceY);
        this.rectangle = new RectangleCoinRond(this.hauteur,this.largeur,this.centre, this.rayon);
        this.setPolygone(rectangle.getPolygone());
    }

    @Override
    public void snapToGrid(PointPouce pointGrille){
        this.centre = pointGrille;
        this.rectangle = new RectangleCoinRond(this.hauteur,this.largeur,this.centre, this.rayon);
        this.setPolygone(rectangle.getPolygone());
    }


    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Hauteur", "Longueur", "CentreX", "CentreY", "Rayon (courbure)"};
    }

    @Override
    public boolean[] getModes(){
        return new boolean[]{};
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }
}
