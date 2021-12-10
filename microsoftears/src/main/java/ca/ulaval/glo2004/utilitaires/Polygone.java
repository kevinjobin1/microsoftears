package ca.ulaval.glo2004.utilitaires;

import java.awt.geom.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Polygone implements Serializable {

    private LinkedList<PointPouce> listePoints;
    private Rectangle2D rectangleLimite;
    private final double[] xPoints;
    private final double[] yPoints;

    public Polygone(LinkedList<PointPouce> listePoints) {
        super();
        this.listePoints = listePoints;
        this.xPoints = getXpoints();
        this.yPoints = getYpoints();
        calculerLimite();

    }

    public LinkedList<PointPouce> getListePoints() {
        return listePoints;
    }

    public void setListePoints(LinkedList<PointPouce> listePoints) {
        this.listePoints = listePoints;
    }

    private void calculerLimite() {
        double rectangleLimiteMinX = Double.MAX_VALUE;
        double rectangleLimiteMinY = Double.MAX_VALUE;
        double rectangleLimiteMaxX = Double.MIN_VALUE;
        double rectangleLimiteMaxY = Double.MIN_VALUE;


        for (int i = 0; i < listePoints.size(); i++) {
            double x = xPoints[i];
            rectangleLimiteMinX = Math.min(rectangleLimiteMinX, x);
            rectangleLimiteMaxX = Math.max(rectangleLimiteMaxX, x);
            double y = yPoints[i];
            rectangleLimiteMinY = Math.min(rectangleLimiteMinY, y);
            rectangleLimiteMaxY = Math.max(rectangleLimiteMaxY, y);
        }
        rectangleLimite = new Rectangle2D.Double(rectangleLimiteMinX, rectangleLimiteMinY,
                rectangleLimiteMaxX - rectangleLimiteMinX,
                rectangleLimiteMaxY - rectangleLimiteMinY);
    }

    public Rectangle2D getBounds() {
        if (listePoints.size() == 0) {
            return new Rectangle2D.Double();
        }
        if (rectangleLimite == null) {
            calculerLimite();
        }
        return rectangleLimite;
    }

    private double[] getXpoints(){
        int nbPoints = listePoints.size();
        double[] xPoints = new double[nbPoints];
        PointPouce point;
        for (int i = 0; i < nbPoints; i++){
            point = listePoints.get(i);
            xPoints[i] = point.getX().toDouble();
        }
        return xPoints;
    }

    private double[] getYpoints(){
        int nbPoints = listePoints.size();
        PointPouce point;
        double[] yPoints = new double[listePoints.size()];
        for (int i = 0; i < nbPoints; i++){
            point = listePoints.get(i);
            yPoints[i] = point.getY().toDouble();
        }
        return yPoints;
    }
    
    public boolean contient(PointPouce p) {
        double x = p.getX().toDouble(),
                y = p.getY().toDouble();

        // On teste si le point est à l'intérieur du rectangle qui contient le polygone
        if (listePoints.size() <= 2 || !getBounds().contains(x, y)) {
            return false;
        }
        
        int intersections = 0;
        double xFin = xPoints[listePoints.size() - 1], 
                yFin = yPoints[listePoints.size() - 1],
                xCourant, yCourant;

        // On suit les côtés du polygone
        for (int i = 0; i < listePoints.size(); xFin = xCourant, yFin = yCourant, i++) {
            xCourant = xPoints[i];
            yCourant = yPoints[i];

            if (yCourant == yFin) {
                continue;
            }

            double leftx;
            if (xCourant < xFin) {
                if (x >= xFin) {
                    continue;
                }
                leftx = xCourant;
            } else {
                if (x >= xCourant) {
                    continue;
                }
                leftx = xFin;
            }

            double test1, test2;
            if (yCourant < yFin) {
                if (y < yCourant || y >= yFin) {
                    continue;
                }
                if (x < leftx) {
                    intersections++;
                    continue;
                }
                test1 = x - xCourant;
                test2 = y - yCourant;
            } else {
                if (y < yFin || y >= yCourant) {
                    continue;
                }
                if (x < leftx) {
                    intersections++;
                    continue;
                }
                test1 = x - xFin;
                test2 = y - yFin;
            }

            if (test1 < (test2 / (yFin - yCourant) * (xFin - xCourant))) {
                intersections++;
            }
        }

        return ((intersections & 1) != 0);
    }




    }
