package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
import java.awt.geom.Area;

public class PointControle extends Composante{

    private Ellipse ellipse;
    private PointPouce centre;
    private final Pouce longueur;
    private final Pouce hauteur;


    public PointControle(RoulotteController parent, Pouce longueur, Pouce hauteur, PointPouce centre, TypeComposante type) {
        super(parent);
        this.hauteur = hauteur;
        this.longueur = longueur;
        this.centre = centre;
        this.ellipse = new Ellipse(longueur, hauteur,
                            centre);
        this.setCouleur(getCouleurInitiale());
        this.setPolygone(ellipse.getPolygone());
        this.setType(type);
    }

    public PointControle(PointControle copie, PointPouce centre) {
        super(copie.parent);
        this.hauteur = copie.hauteur;
        this.longueur = copie.longueur;
        this.centre = centre;
        this.ellipse = new Ellipse(longueur, hauteur,
                centre);
        this.setCouleurInitiale(copie.getCouleurInitiale());
        this.setCouleur(copie.getCouleur());
        this.setPolygone(ellipse.getPolygone());
        this.setType(copie.getType());
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }

    @Override
    public void afficher(Graphics2D g2d){
        if (this.estVisible()){
            Area area = getArea();
            Composite compositeInitial = g2d.getComposite();
            g2d.setComposite(definirComposite(getTransparence()));
            g2d.setPaint(this.getCouleur());
            g2d.fill(area);
            g2d.setComposite(compositeInitial);
            g2d.setColor(getStrokeCouleur());
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(area);
        }
    }

    @Override
    public PointPouce getCentre() {
        // On s'assure que le centre est ?? jour
        if (getType() == TypeComposante.POINT_CONTROLE_1)
        {
            setCentre(parent.getListeComposantes().get(0).getPolygone().getListePoints().get(2));
        }
        else if(getType() == TypeComposante.POINT_CONTROLE_4){
            setCentre(parent.getListeComposantes().get(0).getPolygone().getListePoints().get(3));
        }
        return this.centre;
    }

    @Override
    public int[] getValeurs() {
        return new int[]{
                hauteur.getPouces(), hauteur.getNumerateur(), hauteur.getDenominateur(),
                longueur.getPouces(), longueur.getNumerateur(), longueur.getDenominateur(),
                centre.getX().getPouces(), centre.getX().getNumerateur(), centre.getX().getDenominateur(),
                centre.getY().getPouces(), centre.getY().getNumerateur(), centre.getY().getDenominateur()};
    }

    @Override
    public void translate(PointPouce delta) {
        // On drag seulement les points de contr??les, pas les points de d??part et de fin de la courbe
        if (getType() == TypeComposante.POINT_CONTROLE_2 || getType() == TypeComposante.POINT_CONTROLE_3) {
            PointPouce pSouris = parent.getPositionPlan(parent.getPositionSouris());
            Pouce differenceX = centre.getX().add(delta.getX().diff(pSouris.getX()));
            Pouce differenceY = centre.getY().add(delta.getY().diff(pSouris.getY()));
            this.centre = new PointPouce(differenceX, differenceY);
            this.ellipse = new Ellipse(this.longueur, this.hauteur, this.centre);
            this.setPolygone(ellipse.getPolygone());
            MurProfile profil = ((MurProfile) (this.parent.getListeComposantes().get(1)));

            if (getType() == TypeComposante.POINT_CONTROLE_2){
                profil.getProfilBezier().getPointsControle().set(1, this);
            }
            if (getType() == TypeComposante.POINT_CONTROLE_3){
                profil.getProfilBezier().getPointsControle().set(2, this);
            }

       }
        else if (getType() == TypeComposante.POINT_CONTROLE_1){
           this.centre =  parent.getListeComposantes().get(0).getPolygone().getListePoints().get(2);
            this.ellipse = new Ellipse(this.longueur, this.hauteur, this.centre);
            this.setPolygone(ellipse.getPolygone());
        }
        else if (getType() == TypeComposante.POINT_CONTROLE_4){
            this.centre = parent.getListeComposantes().get(0).getPolygone().getListePoints().get(3);
            this.ellipse = new Ellipse(this.longueur, this.hauteur, this.centre);
            this.setPolygone(ellipse.getPolygone());
        }
    }

    @Override
    public void snapToGrid(PointPouce pointGrille){
        this.centre = pointGrille;
        this.ellipse = new Ellipse(this.longueur,this.hauteur,this.centre);
        this.setPolygone(ellipse.getPolygone());
    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Hauteur", "Longueur", "CentreX", "CentreY"};
    }

    @Override
    public Object[] getModes(){
        return new Object[]{};
    }


    public Pouce getLongueur() {
        return longueur;
    }

    public Pouce getHauteur() {
        return hauteur;
    }
}
