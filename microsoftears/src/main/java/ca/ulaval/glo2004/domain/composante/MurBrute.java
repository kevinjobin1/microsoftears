package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.awt.*;

public class MurBrute extends Composante{

    private Pouce longueur;
    private Pouce largeur;
    private PointPouce centre;
    private Rectangle rectangle;

    public MurBrute(RoulotteController parent, Pouce longueur, Pouce largeur, PointPouce centre) {
        super(parent);
        this.largeur = largeur;
        this.longueur = longueur;
        this.centre = centre;
        this.rectangle = new Rectangle(this.longueur,this.largeur,this.centre);
        this.setCouleurInitiale(new Color(200,200,200));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.MUR_BRUTE);
        this.setPolygone(rectangle.getPolygone());
    }

    public MurBrute(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(96,0,1);
        this.largeur = new Pouce(48,0,1);
        this.centre = new PointPouce(new Pouce(81, 0,1),
                new Pouce(58, 0,1));
        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.setCouleurInitiale(new Color(200,200,200));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.MUR_BRUTE);
        this.setPolygone(rectangle.getPolygone());

    }

    @Override
    public PointPouce getCentre() {
        return this.centre;
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

    public Rectangle getRectangle(){
        return this.rectangle;
    }


    @Override
    public int[] getValeurs() {
        return new int[]{largeur.getPouces(), largeur.getNumerateur(), largeur.getDenominateur(),
                longueur.getPouces(), longueur.getNumerateur(), longueur.getDenominateur(),
                centre.getX().getPouces(), centre.getX().getNumerateur(), centre.getX().getDenominateur(),
                centre.getY().getPouces(), centre.getY().getNumerateur(), centre.getY().getDenominateur()};
    }

    @Override
    public void translate(PointPouce delta) {
        PointPouce pSouris = parent.getPositionPlan(parent.getPositionSouris());
        Pouce differenceX = centre.getX().add(delta.getX().diff(pSouris.getX()));
        Pouce differenceY = centre.getY().add(delta.getY().diff(pSouris.getY()));
        this.centre = new PointPouce(differenceX, differenceY);
        this.rectangle = new Rectangle(this.longueur,this.largeur,this.centre);
        this.setPolygone(rectangle.getPolygone());
    }

    @Override
    public void snapToGrid(PointPouce pointGrille){
        PointPouce coin = this.rectangle.getCoinPlusPres(pointGrille);
        Pouce differenceX = centre.getX().add(pointGrille.getX().diff(coin.getX()));
        Pouce differenceY = centre.getY().add(pointGrille.getY().diff(coin.getY()));
        PointPouce delta = new PointPouce(differenceX, differenceY);
        this.centre = delta;
        this.rectangle = new Rectangle(this.longueur, this.largeur, this.centre);
        this.setPolygone(rectangle.getPolygone());
        for (int i = 2; i < parent.getListeComposantes().size(); i++){
            parent.getListeComposantes().get(i).translate(delta);
        }
    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Hauteur", "Longueur", "CentreX", "CentreY"};
    }

    @Override
    public Object[] getModes(){
        return new Object[]{};
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
