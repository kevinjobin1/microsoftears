package ca.ulaval.glo2004.utilitaires;


import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class PointPouce  implements Serializable {
    private Pouce x;
    private Pouce y;

    public PointPouce(Pouce x, Pouce y) {
        this.x = x;
        this.y = y;
    }

    public PointPouce() {
        x = new Pouce();
        y = new Pouce();
    }


    public PointPouce(PointPouce pointCopie){
        this.x = pointCopie.getX();
        this.y = pointCopie.getY();
    }

    public PointPouce(Point2D p) {
        x = new Pouce(p.getX());
        y = new Pouce(p.getY());
    }

    public PointPouce(double _x, double _y){
        x = new Pouce(_x);
        y = new Pouce(_y);
    }

    @Override
    public String toString() {
        return "(" + x.toString() + ", " + y.toString()+ ")";
    }

    public String toStringMM(){
        return "(" + (int) x.getMilimetres() + " mm, " + (int) y.getMilimetres()+ "mm )";
    }

    public Pouce getX() {
        return x;
    }

    public void setX(Pouce x) {
        this.x = x;
    }

    public Pouce getY() {
        return y;
    }

    public void setY(Pouce y) {
        this.y = y;
    }

    public Point point(double pixelParPouce)
    {
        pixelParPouce = (pixelParPouce < 0) ? 0
                : pixelParPouce;

        return new Point(Math.round((float)(x.toDouble() * pixelParPouce)), Math.round((float)(y.toDouble() * pixelParPouce)));
    }

    /**
     * Fonction qui vérifie si un point q est sur le segment formé par le point p et r
     * @param p Point de départ du segment
     * @param q Point à vérifier
     * @param r Point d'arrivé du segment
     * @return true (boolean) si le point appartient à PR, false sinon */
    static boolean appartientSegment(PointPouce p, PointPouce q, PointPouce r)
    {
        return q.getX().ste(Pouce.max(p.getX(), r.getX())) &&
                q.getX().gte(Pouce.min(p.getX(), r.getX())) &&
                q.getY().ste(Pouce.max(p.getY(), r.getY())) &&
                q.getY().gte(Pouce.min(p.getY(), r.getY()));
    }
    /**
     * Fonction qui trouve l'orientation du triplet ordonné de points (p,q,r) en pouces (")
     * @param p Point de départ
     * @param q Point milieu
     * @param r Point d'arrivé
     * @return 0 si colinéaire, 1 si horaire, 2 si antihoraire
     * Input p1 = {1, 2}, p2 = {4, 4}, p3 = {0, 0}
     * Output:  1 (SensHoraire)
     *
     * Input:   p1 = {0, 0}, p2 = {4, 4}, p3 = {1, 2}
     * Output:  2 (SensAntiHoraire)
     *
     * Input:   p1 = {0, 0}, p2 = {4, 4}, p3 = {1, 1}
     * Output:  0 (Colinéaire) */

    static int orientation(PointPouce p, PointPouce q, PointPouce r)
    {
        // Pente du segment (p, q)
        Pouce deltaYpq = (q.getY().diff(p.getY()));
        Pouce deltaXpq = (q.getX().diff(p.getX()));
        double pentePQ = deltaYpq.diviser(deltaXpq);

        // Pente du segment (q, r)
        Pouce deltaYqr = (r.getY().diff(q.getY()));
        Pouce deltaXqr = (r.getX().diff(q.getX()));
        double penteQR = deltaYqr.diviser(deltaXqr);

        // Si l'on compare les pentes des segments PQ et QR, on obtient l'orientation
        if (pentePQ == penteQR)
        {
            return 0; // colinéaire
        }
        return (pentePQ > penteQR) ? 1 : 2; // horaire si vrai, antihoraire sinon
    }

    // The function that returns true if
    // line segment 'p1q1' and 'p2q2' intersect.
    static boolean intersect(PointPouce p1, PointPouce q1,
                               PointPouce p2, PointPouce q2)
    {
        // Find the four orientations needed for
        // general and special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
        {
            return true;
        }

        // Special Cases
        // p1, q1 and p2 are collinear and
        // p2 lies on segment p1q1
        if (o1 == 0 && appartientSegment(p1, p2, q1))
        {
            return true;
        }

        // p1, q1 and p2 are collinear and
        // q2 lies on segment p1q1
        if (o2 == 0 && appartientSegment(p1, q2, q1))
        {
            return true;
        }

        // p2, q2 and p1 are collinear and
        // p1 lies on segment p2q2
        if (o3 == 0 && appartientSegment(p2, p1, q2))
        {
            return true;
        }

        // p2, q2 and q1 are collinear and
        // q1 lies on segment p2q2
        return o4 == 0 && appartientSegment(p2, q1, q2);

        // Doesn't fall in any of the above cases
    }

    public Point2D toPoint2D(){
        return new Point2D.Double(this.getX().toDouble(), this.getY().toDouble());
    }

    public boolean equals(PointPouce point){
        return getX().equals(point.getX()) && getY().equals(point.getY());
    }

    public PointPouce translate(Pouce deltaX, Pouce deltaY){
        return new PointPouce(this.getX().add(deltaX), this.getY().add(deltaY));
    }
    
    public PointPouce add(Pouce ajoutX, Pouce ajoutY){
        return new PointPouce(getX().add(ajoutX), getY().add(ajoutY));
    }
    
    public PointPouce add(double ajoutX, double ajoutY){
        return this.add(new Pouce(ajoutX), new Pouce(ajoutY));
    }

    public PointPouce diff(Pouce diffX, Pouce diffY){
        return new PointPouce(getX().diff(diffX), getY().diff(diffY));
    }

    public PointPouce diff(double diffX, double diffY){
        return this.diff(new Pouce(diffX), new Pouce(diffY));
    }

    public static PointPouce toPointPouce(Point2D p){
        return new PointPouce(new Pouce(p.getX()), new Pouce(p.getY()));
    }
}

