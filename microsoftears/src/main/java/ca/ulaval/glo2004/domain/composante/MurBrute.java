package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

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
        this.setCouleurInitiale(new Color(200,200,200));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.MUR_BRUTE);
        this.estVisible(false);
        this.setPolygone(rectangle.getPolygone());
    }

    public MurBrute(RoulotteController parent) {
        super(parent);
        this.longueur = new Pouce(96,0,1);
        this.largeur = new Pouce(48,0,1);
        this.centre = new PointPouce();
        this.rectangle = new Rectangle(longueur, largeur, centre);
        this.setCouleurInitiale(new Color(200,200,200));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.MUR_BRUTE);
        this.estVisible(false);
        this.setPolygone(rectangle.getPolygone());

    }

    @Override
    public void afficher(Graphics2D g2d){
        if (estVisible()){
            GeneralPath path = new GeneralPath();
            LinkedList<PointPouce> polygoneList = this.getPolygone().getListePoints();
            double[] point;
            for (int i = 0; i < polygoneList.size(); i++){
                point = parent.getPositionEcran(polygoneList.get(i));
                if(i == 0) {
                    path.moveTo(point[0], point[1]);
                }
                else{
                    path.lineTo(point[0] ,point[1]);
                }
            }
            path.closePath();
            Composite compositeInitial = g2d.getComposite();
            g2d.setComposite(definirComposite(getTransparence()));
            g2d.setPaint(getCouleur());
            g2d.fill(path);
            g2d.setComposite(compositeInitial);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(path);
        }

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

    @Override
    public int[] getValeurs() {
        return new int[]{largeur.getPouces(), largeur.getNumerateur(), largeur.getDenominateur(),
                longueur.getPouces(), longueur.getNumerateur(), longueur.getDenominateur(),
                centre.getX().getPouces(), centre.getX().getNumerateur(), centre.getX().getDenominateur(),
                centre.getY().getPouces(), centre.getY().getNumerateur(), centre.getY().getDenominateur()};
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }
}
