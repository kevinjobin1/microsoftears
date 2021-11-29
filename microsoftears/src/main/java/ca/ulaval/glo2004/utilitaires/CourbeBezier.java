package ca.ulaval.glo2004.utilitaires;

import ca.ulaval.glo2004.domain.composante.PointControle;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourbeBezier extends Forme {

    private ArrayList<PointPouce> pointsControle;

    public CourbeBezier(ArrayList<PointPouce> pointsControles) {
        // TODO : changer la longueur, hauteur et centre
        super(  new Pouce(0,0,1),
                new Pouce(0,0,1),
                new PointPouce(
                        new Pouce(0,0,1),
                        new Pouce(0,0,1)
                ));
        this.pointsControle = pointsControles;
    }

    public ArrayList<PointPouce> getPointsControle() {
        return pointsControle;
    }

    public void setPointsControle(ArrayList<PointPouce> pointsControle) {
        this.pointsControle = pointsControle;
    }

    @Override
    public Polygone getPolygone() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();
        Point2D p0 = pointsControle.get(0).toPoint2D();
        Point2D p1 = pointsControle.get(1).toPoint2D();
        Point2D p2 = pointsControle.get(2).toPoint2D();
        Point2D p3 = pointsControle.get(3).toPoint2D();
        calculerBezier(listePoints, p0, p1, p2, p3, 0, 0);
        return new Polygone(listePoints);
    }

    /**
     * Méthode de calcul de la courbe de Bezier
     * @param listePoints la liste de PointPouce qui constitue la forme
     * @param p0,p1,p2,p3 les points de contrôle
     * @param profondeur profondeur de la récursion
     * @param decalage décalage
     * @return
     */

    private static double calculerBezier(LinkedList<PointPouce> listePoints, Point2D p0, Point2D p1, Point2D p2, Point2D p3,
                                         int profondeur, double decalage) {
        double resultat;

        // Calcul de la platitude (détermine si on doit faire un appel récursif)
        double l01 = distance(p0, p1);
        double l12 = distance(p1, p2);
        double l23 = distance(p2, p3);
        double l03 = distance(p0, p3);
        double platitude = (l01 + l12 + l23) / l03;

        // La profondeur previent le débordement de la pile et la platitude est fixée arbitrairement
        if ((profondeur > 12) || (platitude <= 1.001)) {
            Point2D vO = normalize(perpendiculaire(soustraire(p3, p0)), decalage);
            if (listePoints.isEmpty()) { // si c'est le premier point
                Point2D p0P = additionner(p0, vO);
                listePoints.add(toPointPouce(p0P));
            }
            Point2D p3P = additionner(p3, vO);
            listePoints.add(toPointPouce(p3P));
            resultat = l03;
        } else {
            // premier degré pointMilieu
            Point2D q0 = pointMilieu(p0, p1);
            Point2D q1 = pointMilieu(p1, p2);
            Point2D q2 = pointMilieu(p2, p3);

            // deuxième degré pointMilieu
            Point2D r0 = pointMilieu(q0, q1);
            Point2D r1 = pointMilieu(q1, q2);

            // troisième degré pointMilieu
            Point2D s = pointMilieu(r0, r1);


            // côté gauche Bezier
            resultat = calculerBezier(listePoints, p0, q0, r0, s, profondeur + 1, decalage);
            // côté droit Bezier
            resultat += calculerBezier(listePoints, s, r1, q2, p3, profondeur + 1, decalage);
        }
        return resultat;
    }


    /**
     * Calcul de distance entre deux points
     *
     * @param pA le premier point A
     * @param pB le second point B
     * @return (double) la distance entre les points
     */

    public static double distance(Point2D pA, Point2D pB) {
        return pA.distance(pB);
    }

    /**
     * Normalise un point (vecteur) sur une longueur
     *
     * @param p le point (vecteur)
     * @param longueur la longueur sur laquelle on normalise
     * @return le point normalisé (vecteur)
     */

    public static Point2D normalize( Point2D p, double longueur) {
        return multiplie(normalize(p), longueur);
    }

    /**
     * Normaliser un point (vecteur)
     *
     * @param p le point (vecteur)
     * @return le point normalisé (vecteur)
     */

    public static Point2D normalize( Point2D p) {
        Point2D resultat = p;
        double longueur = longueur(p);
        if (longueur >= 0.001) {
            resultat = diviser(p, longueur);
        }
        return resultat;
    }

    /**
     * @param p le point
     * @return le point perpendiculaire à p (relatif à l'origine (0,0))
     */
    public static Point2D perpendiculaire( Point2D p) {
        return new Point2D.Double(-p.getY(), p.getX());
    }

    /**
     * Soustraction de deux points
     *
     * @param pA le premier point
     * @param pB le deuxième point
     * @return différence entre deux points
     */

    public static Point2D soustraire( Point2D pA,  Point2D pB) {
        return new Point2D.Double(pA.getX() - pB.getX(), pA.getY() - pB.getY());
    }

    /**
     * Additionner deux points
     *
     * @param pA le premier point
     * @param pB le deuxième point
     * @return la somme des deux points
     */

    public static Point2D additionner( Point2D pA,  Point2D pB) {
        return new Point2D.Double(pA.getX() + pB.getX(), pA.getY() + pB.getY());
    }

    /**
     * Calcul du point milieu entre deux points
     *
     * @param pA le premier point
     * @param pB le deuxième point
     * @return le point milieu
     */

    public static Point2D pointMilieu( Point2D pA,  Point2D pB) {
        return interpolation(pA, pB, 0.5);
    }

    /**
     * Calcul du point milieu d'un rectangle
     *
     * @param r le rectangle
     * @return le point milieu du rectangle
     */

    public static Point2D midPoint( Rectangle2D r) {
        return center(r);
    }

    /**
     * Multiplication d'un point par un scalaire
     *
     * @param p le point
     * @param s le scalaire
     * @return le point multiplié
     */

    public static Point2D multiplie( Point2D p, double s) {
        return new Point2D.Double(p.getX() * s, p.getY() * s);
    }

    /**
     * Multiplication d'un point par deux scalaires
     *
     * @param p le point
     * @param x le scalaire en X
     * @param y le scalaire en Y
     * @return le point multiplié par les deux scalaires
     */

    public static Point2D multiplie( Point2D p, double x, double y) {
        return new Point2D.Double(p.getX() * x, p.getY() * y);
    }

    /**
     * Multiplication d'un scalaire par un point
     *
     * @param s le scalaire
     * @param p le point
     * @return le point multiplié
     */

    public static Point2D multiplie(double s,  Point2D p) {
        return new Point2D.Double(p.getX() * s, p.getY() * s);
    }

    /**
     * Multiplication d'un point par un autre point
     *
     * @param p1 le premier point
     * @param p2 le deuxième point
     * @return le produit du premier par le deuxième
     */

    public static Point2D multiplie( Point2D p1,  Point2D p2) {
        return multiplie(p1, p2.getX(), p2.getY());
    }

    /**
     * Calcule de la longueur d'un point (vecteur)
     *
     * @param p le point (vecteur)
     * @return la longueur du point (vecteur)
     */

    public static double longueur( Point2D p) {
        return Math.hypot(p.getX(), p.getY());
    }

    /**
     * Division d'un point par un scalaire
     *
     * @param p le point
     * @param s le scalaire
     * @return le point divisé
     */

    public static Point2D diviser( Point2D p, double s) {
        return new Point2D.Double(p.getX() / s, p.getY() / s);
    }

    /**
     * diviser a point by two scalars
     *
     * @param p le point
     * @param x le scalaire en X
     * @param y le scalaire en Y
     * @return le point divisé
     */

    public static Point2D diviser( Point2D p, double x, double y) {
        return new Point2D.Double(p.getX() / x, p.getY() / y);
    }

    /**
     * Calcul de l'interpolation linéaire entre deux entiers
     *
     * @param a le premier entier
     * @param b le second entier
     * @param t la fraction rationnelle (entre 0 et 1)
     * @return l'interpolation linéaire entre a et b pour un t donné
     */

    public static int interpolation(int a, int b, double t) {
        return (int) interpolation((double) a, (double) b, t);
    }

    /**
     * Calcul de l'interpolation linéaire entre deux nombres
     *
     * @param a le premier nombre
     * @param b le second nombre
     * @param t la fraction rationnelle (entre 0 et 1)
     * @return l'interpolation linéaire entre a et b pour un t donné
     */

    public static double interpolation(double a, double b, double t) {
        return ((1.0 - t) * a) + (t * b);
    }


    /**
     * Calcul de l'interpolation linéaire entre deux points
     *
     * @param pA le premier point
     * @param pB le deuxième point
     * @param t  la fraction rationnelle (entre 0 et 1)
     * @return l'interpolation linéaire entre pA et pB pour un t donné
     */

    public static Point2D interpolation(Point2D pA,  Point2D pB, double t) {
        return new Point2D.Double(interpolation(pA.getX(), pB.getX(), t), interpolation(pA.getY(), pB.getY(), t));
    }

    /**
     * Calcul du centre d'un rectangle
     *
     * @param r le rectangle
     * @return le centre du rectangle (point2d.double)
     */

    public static Point2D center( Rectangle2D r) {
        return new Point2D.Double(r.getCenterX(), r.getCenterY());
    }

    public static PointPouce toPointPouce(Point2D p){
        return new PointPouce(new Pouce(p.getX()), new Pouce(p.getY()));
    }

}
