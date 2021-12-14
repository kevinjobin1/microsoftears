package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;
import ca.ulaval.glo2004.utilitaires.RectangleCoinRond;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import static ca.ulaval.glo2004.utilitaires.ImageDesign.toBufferedImage;

public class AideDesign extends Composante{

    private Pouce longueur;
    private Pouce largeur;
    private PointPouce centre;
    private Rectangle rectangle;
    private boolean estAjoute;

    // constructeur par valeurs
    public AideDesign(RoulotteController parent, Pouce longueur, Pouce largeur, PointPouce centre, TypeComposante type, boolean estAjoute) {
        super(parent);
        this.longueur = longueur;
        this.largeur = largeur;
        this.centre = new PointPouce(centre);
        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.estAjoute = estAjoute;
        this.setTransparence(0.6f);
        this.setType(type);
        this.setPolygone(rectangle.getPolygone());
    }

    // constructeur copie
    public AideDesign(AideDesign copie) {
        super(copie.parent);
        this.longueur = copie.longueur;
        this.largeur = copie.largeur;
        this.centre = copie.centre;
        this.rectangle = copie.rectangle;
        this.estAjoute = copie.estAjoute();
        this.setType(copie.getType());
        this.setTransparenceInitiale(copie.getTransparenceInitiale());
        this.setTransparence(copie.getTransparence());
        this.setPolygone(rectangle.getPolygone());
    }
    // constructeur copie avec décalage
    public AideDesign(AideDesign copie, Pouce decalageX, Pouce decalageY) {
        super(copie.parent);
        System.out.println(decalageX + " , " + decalageY);
        this.longueur = copie.longueur;
        this.largeur = copie.largeur;
        PointPouce nouveauCentre = new PointPouce(copie.getCentre().getX().add(decalageX), copie.getCentre().getY().add(decalageY));
        System.out.println(nouveauCentre);
        this.centre = nouveauCentre;
        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.estAjoute = copie.estAjoute;
        this.setTransparenceInitiale(copie.getTransparenceInitiale());
        this.setTransparence(copie.getTransparence());
        this.setPolygone(rectangle.getPolygone());
        this.setType(copie.getType());
    }
//  constructeur par défaut
    public AideDesign(RoulotteController parent, TypeComposante type) {
        super(parent);
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        switch(type){
            case ROUE:
                this.longueur = new Pouce(19);
                this.largeur = new Pouce(19);
                this.centre = new PointPouce(mur.getCentre().getX().diff(mur.getLongueur().diviser(4)).add(new Pouce(13)),
                        mur.getCentre().getY().add(mur.getLargeur().diviser(2)).add(new Pouce(4)));
                break;
            case CADRE:
                this.longueur = new Pouce(144);
                this.largeur = new Pouce(23,1,2);
                this.centre = new PointPouce(mur.getCentre().getX().add(mur.getLongueur().diviser(4)).diff(new Pouce(1)),
                        mur.getCentre().getY().add(mur.getLargeur().diviser(2)).add(new Pouce(3,1,2)));
                break;
            case LIT:
                this.longueur = new Pouce(60);
                this.largeur = new Pouce(18);
                Pouce centreX = parent.getListeComposantes().get(0).getCentre().getX().diff(new Pouce(15));
                Pouce centreY = parent.getListeComposantes().get(0).getCentre().getY().diff(new Pouce(10));
                this.centre = new PointPouce(centreX, centreY.add(new Pouce(26)));
                break;
            case PERSONNE:
                this.longueur = new Pouce(46);
                this.largeur = new Pouce(60);
               centreX = parent.getListeComposantes().get(0).getCentre().getX().diff(new Pouce(15));
               centreY = parent.getListeComposantes().get(0).getCentre().getY().diff(new Pouce(10));
                this.centre = new PointPouce(centreX.diff(new Pouce(41)), centreY.add(new Pouce(7)));
                break;
            case LOGO:
                this.longueur = new Pouce(5);
                this.largeur = new Pouce(5);
                centreX = parent.getListeComposantes().get(0).getCentre().getX().diff(new Pouce(50));
                centreY = parent.getListeComposantes().get(0).getCentre().getY().diff(new Pouce(40));
                this.centre = new PointPouce(centreX, centreY);
                break;
            case AIDE_AU_DESIGN:
                this.longueur = new Pouce(10);
                this.largeur = new Pouce(20);
                centreX = parent.getListeComposantes().get(0).getCentre().getX();
                centreY = parent.getListeComposantes().get(0).getCentre().getY();
                this.centre = new PointPouce(centreX, centreY);
                break;
        }

        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.estAjoute = false;
        this.setTransparence(0.6f);
        this.setType(type);
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
        this.rectangle = new Rectangle(this.longueur,this.largeur, this.centre);
        this.setPolygone(rectangle.getPolygone());
    }

    @Override
    public void snapToGrid(PointPouce pointGrille) {
        this.centre = pointGrille;
        this.rectangle = new Rectangle(this.longueur,this.largeur, this.centre);
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

    @Override
    public void afficher(Graphics2D g2d) {
        if (estVisible() && estAjoute()){
            if (parent.afficherLabel()){
                if (getAfficherPosition()) {
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawString(this.toString(), (float) parent.getPositionSouris().getX() + 30, (float) parent.getPositionSouris().getY() - 30);
                }}

            if (getType() != TypeComposante.AIDE_AU_DESIGN){
                Composite compositeInitial = g2d.getComposite();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getTransparence()));
                double[] positionEcran = parent.getPositionEcran(new PointPouce(centre.getX().diff(longueur.diviser(2)),
                        centre.getY().diff(largeur.diviser(2))));
                g2d.drawImage(getType().getImage(), (int) positionEcran[0], (int) positionEcran[1],(int) (longueur.toPixel(parent.getPixelsToInchesRatio()) * parent.getScale()), (int) (largeur.toPixel(parent.getPixelsToInchesRatio()) * parent.getScale()), null);
                g2d.setComposite(compositeInitial);}
            else {
                Area area = getArea();
                Composite compositeInitial = g2d.getComposite();
                g2d.setComposite(definirComposite(getTransparence()));
                g2d.setPaint(getCouleur());
                g2d.fill(area);
                g2d.setComposite(compositeInitial);
                g2d.setColor(getStrokeCouleur());
                //g2d.setStroke(getStroke());
                g2d.draw(area);
            }
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
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
