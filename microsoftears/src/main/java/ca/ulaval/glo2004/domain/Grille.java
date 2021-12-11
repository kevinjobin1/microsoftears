package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.*;

public class Grille implements Serializable{
    private final RoulotteController parent;
    private final Color couleurGrille = Color.WHITE;
    private final Color couleurBordureGrille = Color.BLACK;
    private Set<Point> points;
    private int echelle;
    private boolean estMagnetique;
    private final boolean estAffiche;
    private Dimension dimensions;

    public Grille(RoulotteController parent, int echelle, boolean estMagnetique, boolean estAffiche, Dimension dimensions) {
        this.parent = parent;
        this.echelle = echelle;
        this.estMagnetique = estMagnetique;
        this.estAffiche = estAffiche;
        this.dimensions = dimensions;
        this.points = calculerPoints();
    }

    public void afficher(Graphics2D g2d, Dimension dimensionEcran){
        if(estAffiche){
            this.dimensions = dimensionEcran;
            this.points = calculerPoints();
            Point origineEcran = new Point(0,0);
            Point limiteEcran = new Point((int) dimensionEcran.getWidth(),(int) dimensionEcran.getHeight());
            PointPouce debutEcran = parent.getPositionPlan(origineEcran);
            PointPouce finEcran = parent.getPositionPlan(limiteEcran);

            double[] p1,p2;
            g2d.setColor(couleurBordureGrille);
            p1 = parent.getPositionEcran(debutEcran);
            p2 = parent.getPositionEcran(finEcran);
            g2d.drawRect((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            g2d.setColor(this.couleurGrille);
            g2d.fillRect((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);

                // lignes horizontales
                int limite = finEcran.getX().toInt();
                for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i+=echelle){
                    p1 = parent.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                    p2 = parent.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
                    if (i % 6 != 0){
                        // Couleur ligne 3 pouces
                        g2d.setColor(new Color(200,200, 200));
                    }

                    else {
                        // Couleur ligne 6 pouces
                        g2d.setColor(new Color(160,160, 160));
                    }
                    g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
                }

                // lignes verticales
                limite = finEcran.getY().toInt();
                for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i+=echelle){
                    p1 = parent.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                    p2 = parent.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
                    if (i % 6 != 0){
                        // Couleur ligne 3 pouces
                        g2d.setColor(new Color(200,200, 200));
                    }

                    else {
                        // Couleur ligne 6 pouces
                        g2d.setColor(new Color(160,160, 160));
                    }
                    g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
                }
            }

            // TODO: afficher la grille en fonction du zoom (pas nécessaire pour le projet)
            /*else if (parent.getScale() > 1.5){
                // lignes horizontales
                int limite = finEcran.getX().toInt();
                for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i++){
                    p1 = parent.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                    p2 = parent.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
                    if (i % 6 != 0){
                        // Couleur ligne pouces
                        g2d.setColor(new Color(200,200, 200));
                    }
                    else {
                        // Couleur ligne pieds
                        g2d.setColor(new Color(150,150, 150));
                    }
                    g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
                }

                // lignes verticales
                limite = finEcran.getY().toInt();
                for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i++){
                    p1 = parent.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                    p2 = parent.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
                    if (i % 6 != 0){
                        // Couleur ligne pouces
                        g2d.setColor(new Color(200,200, 200));
                    }
                    else {
                        // Couleur ligne pieds
                        g2d.setColor(new Color(150,150, 150));
                    }
                    g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
                }
            }
            else {
                g2d.setColor(new Color(160,160, 160));
                // lignes horizontales
                int limite = finEcran.getX().toInt();
                for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i+=(echelle*2)){
                    p1 = parent.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                    p2 = parent.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
                    g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
                }

                // lignes verticales
                limite = finEcran.getY().toInt();
                for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i+=(echelle*2)){
                    p1 = parent.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                    p2 = parent.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
                    g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
                }
            }*/
    }

    private Set<Point> calculerPoints(){
        Set<Point> points = new HashSet<>();
        Point origineEcran = new Point(0,0);
        Point limiteEcran = new Point((int) dimensions.getWidth(),(int) dimensions.getHeight());
        PointPouce debutEcran = parent.getPositionPlan(origineEcran);
        PointPouce finEcran = parent.getPositionPlan(limiteEcran);
        double[] p;
            for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i+=echelle){
                for (int j=debutEcran.getY().toInt(); j < finEcran.getY().toInt(); j+=echelle){
                    p = parent.getPositionEcran(new PointPouce(new Pouce(i), new Pouce(j)));
                    points.add(new Point((int) p[0], (int) p[1]));
                }
            }

      return points;

    }

    public Point pointLePlusProche(Point p1){
        double distanceMin = Double.MAX_VALUE;
        Point plusProchePoint = new Point();
        Iterator<Point> it = points.iterator();
        while(it.hasNext()){
            Point p2 = it.next();
            if (p2.distance(p1) < distanceMin){
                plusProchePoint = p2;
                distanceMin = p2.distance(p1);
            }
        }
        return plusProchePoint;
    }


    public int getEchelle() {
        return echelle;
    }

    public void setEchelle(int echelle) {
        this.echelle = echelle;
    }

    public RoulotteController getParent() {
        return parent;
    }

    public boolean estMagnetique() {
        return estMagnetique;
    }

    public void setMagnetique(boolean estMagnetique) {
        this.estMagnetique = estMagnetique;
    }

    public Color getCouleurGrille() {
        return couleurGrille;
    }
}
