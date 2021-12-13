package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.RectangleCoinRond;

import java.awt.*;
import java.awt.geom.Area;
import java.lang.reflect.Type;

public class OuvertureLaterale extends Composante{
    private Pouce hauteur;
    private Pouce largeur;
    private PointPouce centre;
    private final Pouce rayon;
    private RectangleCoinRond rectangle;
    private boolean estAjoute;

    public OuvertureLaterale(RoulotteController parent, Pouce hauteur, Pouce largeur, PointPouce centre, Pouce rayonCourbure, TypeComposante type, boolean estAjoute) {
        super(parent);
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.centre = centre;
        this.rayon = rayonCourbure;
        this.rectangle = new RectangleCoinRond(hauteur, largeur, centre, rayon);
        this.estAjoute = estAjoute;
        this.setTransparenceInitiale(0.1f);
        this.setTransparence(0.1f);
        this.setPolygone(rectangle.getPolygone());
        this.setType(type);
    }

    public OuvertureLaterale(RoulotteController parent, TypeComposante type) {
        super(parent);
        if (type == TypeComposante.PORTE){
        this.hauteur = new Pouce(36,0,1);
        this.largeur = new Pouce(26,0,1);
        Pouce centreX = parent.getListeComposantes().get(0).getCentre().getX().add(new Pouce(15,0,0));
        this.centre = new PointPouce(centreX, parent.getListeComposantes().get(0).getCentre().getY());
        this.rayon = new Pouce(4,3,4);
        this.rectangle = new RectangleCoinRond(hauteur, largeur, centre, rayon);
        }
        // fenetre
        else {
            this.hauteur = new Pouce(10,0,1);
            this.largeur = new Pouce(10,0,1);
            Pouce centreX = parent.getListeComposantes().get(0).getCentre().getX().diff(new Pouce(15));
            Pouce centreY = parent.getListeComposantes().get(0).getCentre().getY().diff(new Pouce(10));
            this.centre = new PointPouce(centreX, centreY);
            this.rayon = new Pouce(4.75);
            this.rectangle = new RectangleCoinRond(hauteur, largeur, centre, rayon);
        }
        this.estAjoute = false;
        this.setTransparenceInitiale(0.1f);
        this.setTransparence(0.1f);
        this.setPolygone(rectangle.getPolygone());
        this.setType(type);
    }

    public OuvertureLaterale(OuvertureLaterale copie) {
        super(copie.parent);
        this.hauteur = copie.hauteur;
        this.largeur = copie.largeur;
        this.centre = copie.centre;
        this.rayon = copie.rayon;
        this.rectangle = new RectangleCoinRond(hauteur, largeur, centre, rayon);
        this.estAjoute = copie.estAjoute();
        this.setTransparenceInitiale(copie.getTransparenceInitiale());
        this.setTransparence(copie.getTransparence());
        this.setPolygone(rectangle.getPolygone());
        this.setType(copie.getType());
    }

    public OuvertureLaterale(OuvertureLaterale copie, Pouce decalageX, Pouce decalageY) {
        super(copie.parent);
        System.out.println(decalageX + " , " + decalageY);
        this.hauteur = copie.hauteur;
        this.largeur = copie.largeur;
        PointPouce nouveauCentre = new PointPouce(copie.getCentre().getX().add(decalageX), copie.getCentre().getY().add(decalageY));
        System.out.println(nouveauCentre);
        this.centre = nouveauCentre;
        this.rayon = copie.rayon;
        this.estAjoute = copie.estAjoute();
        this.rectangle = new RectangleCoinRond(hauteur, largeur, centre, rayon);
        this.setTransparenceInitiale(copie.getTransparenceInitiale());
        this.setTransparence(copie.getTransparence());
        this.setPolygone(rectangle.getPolygone());
        this.setType(copie.getType());
    }

    @Override
    public void afficher(Graphics2D g2d) {
        if(this.estVisible() && estAjoute()){
            if(parent.afficherLabel()){
                if (getAfficherPosition()) {
                    g2d.drawString(this.toString(), (float) parent.getPositionSouris().getX() + 30, (float) parent.getPositionSouris().getY() - 30);
                }
            }
            Composite compositeInitial = g2d.getComposite();
            g2d.setComposite(compositeInitial);
            Area area = getArea();
            g2d.setComposite(definirComposite(getTransparence()));
            g2d.fill(area);
            g2d.setComposite(compositeInitial);
        }
    }

    public Pouce getHauteur() {
        return hauteur;
    }

    public void setHauteur(Pouce hauteur) {
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
    public Object[] getModes(){
        return new Object[]{};
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }
@Override
    public boolean estAjoute() {
        return estAjoute;
    }
@Override
    public void estAjoute(boolean estAjoute) {
        this.estAjoute = estAjoute;
    }
}
