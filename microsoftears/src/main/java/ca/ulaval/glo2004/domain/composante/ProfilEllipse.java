package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.roulotte.RoulotteController;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;


public class ProfilEllipse extends Composante{
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;
    private Ellipse ellipse;

    public ProfilEllipse(RoulotteController parent, Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centre = centre;
        this.ellipse = new Ellipse(this.longueur,this.hauteur,this.centre, this.parent.getNombrePoint());
        this.setPolygone(ellipse.getPolygone());
        this.setType(TypeComposante.PROFIL_ELLIPSE);
    }

        public void afficher(Graphics2D g2d){
        if (this.estVisible()){
            GeneralPath path = new GeneralPath();
            LinkedList<PointPouce> polygoneList = this.getPolygone().getListePoints();
            double x, y;
            for (int i = 0; i < polygoneList.size(); i++){
                x = parent.xVersEcran(polygoneList.get(i).getX());
                y = parent.yVersEcran(polygoneList.get(i).getY());
                if(i == 0) {
                    path.moveTo(x, y);
                }
                else{
                    path.lineTo(x ,y);
                }
            }
            path.closePath();
            Composite compositeInitial = g2d.getComposite();
            g2d.setComposite(definirComposite(0.4F));
            // TODO: changer la couleur des ellipses (*optionnel)
            g2d.setPaint(this.getCouleur());
            g2d.fill(path);
            g2d.setComposite(compositeInitial);
            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(path);
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

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }


}
