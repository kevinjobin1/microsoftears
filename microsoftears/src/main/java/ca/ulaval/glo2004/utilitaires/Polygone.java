package ca.ulaval.glo2004.utilitaires;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.LinkedList;

public class Polygone {

    private LinkedList<PointPouce> listePoints;
    private Rectangle rectangleLimite;
    private double[] xPoints;
    private double[] yPoints;

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
        int rectangleLimiteMinX = Integer.MAX_VALUE;
        int rectangleLimiteMinY = Integer.MAX_VALUE;
        int rectangleLimiteMaxX = Integer.MIN_VALUE;
        int rectangleLimiteMaxY = Integer.MIN_VALUE;


        for (int i = 0; i < listePoints.size(); i++) {
            double x = xPoints[i];
            rectangleLimiteMinX = (int)Math.min(rectangleLimiteMinX, x);
            rectangleLimiteMaxX = (int)Math.max(rectangleLimiteMaxX, x);
            double y = yPoints[i];
            rectangleLimiteMinY = (int)Math.min(rectangleLimiteMinY, y);
            rectangleLimiteMaxY = (int)Math.max(rectangleLimiteMaxY, y);
        }
        rectangleLimite = new Rectangle(rectangleLimiteMinX, rectangleLimiteMinY,
                rectangleLimiteMaxX - rectangleLimiteMinX,
                rectangleLimiteMaxY - rectangleLimiteMinY);
    }

    public Rectangle getBounds() {
        if (listePoints.size() == 0) {
            return new Rectangle();
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
