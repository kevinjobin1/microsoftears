package ca.ulaval.glo2004.utilitaires;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class RectangleCoinRond extends Forme {

    private Pouce rayonCourbure;

    public RectangleCoinRond(Pouce hauteur, Pouce longueur, PointPouce centre, Pouce rayonCourbure) {
        super(longueur, hauteur, centre);
        this.rayonCourbure = rayonCourbure;

    }

    @Override
    public Polygone getPolygone() {
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
        System.out.println("Haut droite");
        System.out.println(p1);
        listePoints.add(new PointPouce(p1));
        for (Point2D p : points){
            listePoints.add(new PointPouce(p));
        }
        listePoints.add(new PointPouce(p2));
        System.out.println(p2);

        //coin en bas à droite
        coin = new Point2D.Double(centre.getX() + (longueur/2), centre.getY() + (hauteur/2));
        p1 = new Point2D.Double(centre.getX() + (longueur/2), centre.getY() + (hauteur/2) - rayon);
        p2 = new Point2D.Double(centre.getX() + (longueur/2) - rayon, centre.getY() + (hauteur/2));
        points = calculerCoinsArrondis(coin, p1, p2, rayon);
        System.out.println("Bas droite");
        System.out.println(p1);
        listePoints.add(new PointPouce(p1));
        for (Point2D p : points){
            listePoints.add(new PointPouce(p));
        }
        listePoints.add(new PointPouce(p2));
        System.out.println(p2);

        //coin en bas à gauche
        coin = new Point2D.Double(centre.getX() - (longueur/2), centre.getY() + (hauteur/2));
        p1 = new Point2D.Double(centre.getX() - (longueur/2) + rayon, centre.getY() + (hauteur/2));
        p2 = new Point2D.Double(centre.getX() - (longueur/2), centre.getY() + (hauteur/2) - rayon);
        points = calculerCoinsArrondis(coin, p1, p2, rayon);
        System.out.println("Bas gauche");
        System.out.println(p1);
        listePoints.add(new PointPouce(p1));
        for (Point2D p : points){
            listePoints.add(new PointPouce(p));
        }
        listePoints.add(new PointPouce(p2));
        System.out.println(p2);

        //coin en haut à gauche
         coin = new Point2D.Double(centre.getX() - (longueur/2), centre.getY() - (hauteur/2));
         p1 = new Point2D.Double(centre.getX() - (longueur/2), centre.getY() - (hauteur/2) + rayon);
         p2 = new Point2D.Double(centre.getX() - (longueur/2) + rayon, centre.getY() - (hauteur/2));
         points = calculerCoinsArrondis(coin, p1, p2, rayon);
        System.out.println("Haut gauche");
        System.out.println(p1);
        listePoints.add(new PointPouce(p1));
        for (int i = points.length - 1; i >= 0; i--){
            listePoints.add(new PointPouce(points[i]));
        }
        listePoints.add(new PointPouce(p2));
        System.out.println(p2);

        return new Polygone(listePoints);
    }
    
    private void calcul(){
    }

    private Point2D[] calculerCoinsArrondis(Point2D coin, Point2D p1, Point2D p2, double rayon)
    {
        //Vecteur 1
        double dx1 = coin.getX() - p1.getX();
        double dy1 = coin.getY() - p1.getY();
       
        //Vecteur 2
        double dx2 = coin.getX() - p2.getX();
        double dy2 = coin.getY() - p2.getY();
        
        //Angle entre les deux vecteurs, divisé par 2
        double angle = (Math.atan2(dy1, dx1) - Math.atan2(dy2, dx2)) / 2;

        // La longueur du segment entre le coin et les points d'intersections
        // avec le cercle de rayon donnée
        double tan = Math.abs(Math.tan(angle));
        double segment = rayon / tan;

        // 
        double longueur1 = getLongueurSegment(dx1, dy1);
        double longueur2 = getLongueurSegment(dx2, dy2);

        double longueur = Math.min(longueur1, longueur2);

        if (segment > longueur)
        {
            segment = longueur;
            rayon = longueur * tan;
        }

        // Points d'intersections sont calculés avec la proportion entre les coordonnées
        // du vecteur, la longueur du vecteur et la longueur du segment.
         Point2D p1Cross = getPointProportion(coin, segment, longueur1, dx1, dy1);
         Point2D p2Cross = getPointProportion(coin, segment, longueur2, dx2, dy2);

        // Calcul des coordonnées du centre du cercle 
        // en additionnant les vecteurs angulaires
        double dx = coin.getX() * 2 - p1Cross.getX() - p2Cross.getX();
        double dy = coin.getY() * 2 - p1Cross.getY() - p2Cross.getY();

        double L = getLongueurSegment(dx, dy);
        double d = getLongueurSegment(segment, rayon);

        Point2D pointCercle = getPointProportion(coin, d, L, dx, dy);

        // Début et fin des angles de l'arc
        double angleDebut = Math.atan2(p1Cross.getY() - pointCercle.getY(), p1Cross.getX() - pointCercle.getX());
        double angleFin = Math.atan2(p2Cross.getY() - pointCercle.getY(), p2Cross.getX() - pointCercle.getX());

        // angle Arc
        double angleArc = angleFin - angleDebut;

        // Checks
        if (angleArc < 0)
        {
            angleDebut = angleFin;
            angleArc = -angleArc;
        }

        if (angleArc > Math.PI)
            angleArc = Math.PI - angleArc;

        double gauche = pointCercle.getX() - rayon;
        double haut = pointCercle.getY() - rayon;
        double diameter = 2 * rayon;
        double facteurDegree = 180 / Math.PI;

        return calculerPointsArc(angleArc, facteurDegree, pointCercle, rayon, angleDebut);

    }
    
    private Point2D[] calculerPointsArc(double angleArc, double facteurDegree, Point2D pointCercle, double rayon, double angleDebut){
        // Un point par degré, au besoin ajuster le facteurDegree
        int nbPoints = (int) Math.abs(angleArc * facteurDegree);
        double sign = Math.signum(angleArc); // 1.0 si > 0 , 0.0 = 0, -1.0 < 0;

        Point2D[] points = new Point2D[nbPoints];

        for (int i = 0; i < nbPoints; ++i)
        {
            double pointX =
                    (float)(pointCercle.getX()
                            + Math.cos(angleDebut + sign * ((double)i / facteurDegree))
                            * rayon);

            double pointY =
                    (float)(pointCercle.getY()
                            + Math.sin(angleDebut + sign * ((double)i / facteurDegree))
                            * rayon);

            points[i] = new Point2D.Double(pointX, pointY);
        }

        return points;
    }

    private double getLongueurSegment(double dx, double dy)
    {
        return Math.sqrt(dx * dx + dy * dy);
    }

    private Point2D getPointProportion(Point2D point, double segment,
                                     double longueur, double dx, double dy)
    {
        double facteur = segment / longueur;

        return new Point2D.Double((point.getX() - dx * facteur),
                (point.getY() - dy * facteur));
    }
}
