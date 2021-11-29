package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
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
        if (verificationLongueur(longueur)){
        this.longueur = longueur;}
        else {
            System.out.println("ERREUR LONGUEUR ELLIPSE");
            this.longueur = longueur;
        }
        if (verificationHauteur(hauteur)){
            this.hauteur = hauteur;}
        else {
            System.out.println("ERREUR HAUTEUR ELLIPSE");
            this.hauteur = hauteur;
        }
        this.centre = centre;
        this.ellipse = new Ellipse(this.longueur,this.hauteur,this.centre, this.parent.getNombrePoint());
        this.setCouleur(getCouleurInitiale());
        this.setPolygone(ellipse.getPolygone());
        this.setType(type);
    }
        @Override
        public void afficher(Graphics2D g2d){
        if (this.estVisible()){
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
            g2d.setComposite(definirComposite(0.4F));
            // TODO: changer la couleur des ellipses (*optionnel)
            g2d.setPaint(this.getCouleur());
            g2d.fill(path);
            g2d.setComposite(compositeInitial);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(path);
            double[] centreEllipse = parent.getPositionEcran(centre);
            g2d.fill(new Ellipse2D.Double(centreEllipse[0], centreEllipse[1], 5, 5));
        }
    }

    public boolean verificationLongueur(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getLongueur()) && valeur.gt(new Pouce(0,0,1));
    }

    public boolean verificationHauteur(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getLargeur()) && valeur.gt(new Pouce(0,0,1));
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
        this.ellipse = new Ellipse(this.longueur,this.hauteur,this.centre, this.parent.getNombrePoint());
        this.setPolygone(ellipse.getPolygone());
    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Hauteur", "Longueur", "CentreX", "CentreY"};
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }


}
