package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;


public class ProfilEllipse extends Composante{
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;
    private Ellipse ellipse;

    public ProfilEllipse(RoulotteController parent, Pouce longueur, Pouce hauteur, PointPouce centre, TypeComposante type) {
        super(parent);
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centre = centre;
        this.ellipse = new Ellipse(this.longueur,this.hauteur,this.centre);
        this.setCouleur(getCouleurInitiale());
        this.setStrokeCouleur(Color.LIGHT_GRAY);
        this.setPolygone(ellipse.getPolygone());
        this.setType(type);
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
            double[] centreEllipse = parent.getPositionEcran(centre);
            g2d.fill(new Ellipse2D.Double(centreEllipse[0], centreEllipse[1], 5, 5));
        }
    }



    public Pouce getLongueur() {
        return longueur;
    }

    public void setLongueur(Pouce longueur) {
        this.longueur = longueur;
    }

    public Pouce getHauteur() {
        return hauteur;
    }

    public void setHauteur(Pouce hauteur) {
        this.hauteur = hauteur;
    }

    public PointPouce getCentre() {
        return centre;
    }

    public Ellipse getEllipse() {
        return ellipse;
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
        PointPouce pSouris = parent.getPositionPlan(parent.getPositionSouris());
        Pouce differenceX = centre.getX().add(delta.getX().diff(pSouris.getX()));
        Pouce differenceY = centre.getY().add(delta.getY().diff(pSouris.getY()));
        this.centre = new PointPouce(differenceX, differenceY);
        this.ellipse = new Ellipse(this.longueur,this.hauteur,this.centre);
        this.setPolygone(ellipse.getPolygone());
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
    public boolean[] getModes(){
        return new boolean[]{};
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }


}
