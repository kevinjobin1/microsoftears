package ca.ulaval.glo2004.utilitaires;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class RectangleCoinRond extends Forme {

    private final Pouce rayonCourbure;
    private double angle = 0; // en rad

    public RectangleCoinRond(Pouce hauteur, Pouce longueur, PointPouce centre, Pouce rayonCourbure) {
        super(longueur, hauteur, centre);
        this.rayonCourbure = rayonCourbure;

    }

    public RectangleCoinRond(Pouce hauteur, Pouce longueur, PointPouce centre, Pouce rayonCourbure, double angle) {
        super(longueur, hauteur, centre);
        this.rayonCourbure = rayonCourbure;
        this.angle = angle;

    }

    @Override
    public LinkedList<PointPouce> getListePoints() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();
        double longueur = this.getLongueur().toDouble();
        double hauteur = this.getHauteur().toDouble();
        double rayon = rayonCourbure.toDouble();
        Point2D centre = new Point2D.Double(this.getCentre().getX().toDouble(), this.getCentre().getY().toDouble());

        // coin en haut à droite
        Point2D coin = new Point2D.Double(centre.getX() + (longueur/2), centre.getY() - (hauteur/2));
        Point2D p1 = new Point2D.Double(centre.getX() + (longueur/2) - rayon, centre.getY() - (hauteur/2));
        Point2D p2 = new Point2D.Double(centre.getX() + (longueur/2), centre.getY() - (hauteur/2) + rayon);
        Point2D[] points = calculerCoinsArrondis(coin, p1, p2, rayon);

        listePoints.add(new PointPouce(p1));
        for (Point2D p : points){
            listePoints.add(new PointPouce(p));
        }
        listePoints.add(new PointPouce(p2));

        //coin en bas à droite
        coin = new Point2D.Double(centre.getX() + (longueur/2), centre.getY() + (hauteur/2));
        p1 = new Point2D.Double(centre.getX() + (longueur/2), centre.getY() + (hauteur/2) - rayon);
        p2 = new Point2D.Double(centre.getX() + (longueur/2) - rayon, centre.getY() + (hauteur/2));
        points = calculerCoinsArrondis(coin, p1, p2, rayon);

        listePoints.add(new PointPouce(p1));
        for (Point2D p : points){
            listePoints.add(new PointPouce(p));
        }
        listePoints.add(new PointPouce(p2));

        //coin en bas à gauche
        coin = new Point2D.Double(centre.getX() - (longueur/2), centre.getY() + (hauteur/2));
        p1 = new Point2D.Double(centre.getX() - (longueur/2) + rayon, centre.getY() + (hauteur/2));
        p2 = new Point2D.Double(centre.getX() - (longueur/2), centre.getY() + (hauteur/2) - rayon);
        points = calculerCoinsArrondis(coin, p1, p2, rayon);

        listePoints.add(new PointPouce(p1));
        for (Point2D p : points){
            listePoints.add(new PointPouce(p));
        }
        listePoints.add(new PointPouce(p2));

        //coin en haut à gauche
        coin = new Point2D.Double(centre.getX() - (longueur/2), centre.getY() - (hauteur/2));
        p1 = new Point2D.Double(centre.getX() - (longueur/2), centre.getY() - (hauteur/2) + rayon);
        p2 = new Point2D.Double(centre.getX() - (longueur/2) + rayon, centre.getY() - (hauteur/2));
        points = calculerCoinsArrondis(coin, p1, p2, rayon);

        listePoints.add(new PointPouce(p1));
        for (int i = points.length - 1; i >= 0; i--){
            listePoints.add(new PointPouce(points[i]));
        }
        listePoints.add(new PointPouce(p2));


        if (angle != 0){
            PointPouce point;
            double x,y,nouveauX, nouveauY;
            double decalageX = getCentre().getX().toDouble();
            double decalageY = getCentre().getY().toDouble();

            for (int i = 0; i < listePoints.size(); i++){
                point = listePoints.get(i);
                // position en x,y devient (𝑥cos𝜃−𝑦sin𝜃 ,𝑥sin𝜃+𝑦cos𝜃) ou l'angle est en Rad
                // et le point doit être centré à l'origine
                x = point.getX().toDouble() - decalageX;
                y = point.getY().toDouble() - decalageY;
                nouveauX = x * Math.cos(angle) - y * -Math.sin(angle);
                nouveauY = x * -Math.sin(angle) - y * Math.cos(angle);
                listePoints.set(i, new PointPouce(new Pouce(nouveauX + decalageX), new Pouce(nouveauY +decalageY)));
            }
        }

        return listePoints;
    }

}
