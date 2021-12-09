package ca.ulaval.glo2004.utilitaires;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class Ellipse extends Forme {

    public Ellipse(Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(longueur, hauteur, centre);
    }

    @Override
    public LinkedList<PointPouce> getListePoints() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();
        for(int i = 0; i < NOMBRE_POINTS; i++) {
            // x = centreX + ((longueur/2) * cos(angle))
            Pouce x = (getLongueur().diviser(2).multiplier(Math.cos(Math.toRadians(((double)i/NOMBRE_POINTS) * 360d)))).add(getCentre().getX());
            // y = centreY + ((hauteur/2) * -sin(angle)) --> parce que les y sont inversés
            Pouce y = (getHauteur().diviser(2).multiplier(-Math.sin(Math.toRadians(((double)i/NOMBRE_POINTS) * 360d)))).add(getCentre().getY());
            listePoints.add(new PointPouce(x, y));
        }
        return listePoints;
    }

/*

public intersectRectangle
    Intersection.intersectEllipseRectangle = function(c, rx, ry, r1, r2) {
        var min        = r1.min(r2);
        var max        = r1.max(r2);
        var topRight   = new Point2D( max.x, min.y );
        var bottomLeft = new Point2D( min.x, max.y );

        var inter1 = Intersection.intersectEllipseLine(c, rx, ry, min, topRight);
        var inter2 = Intersection.intersectEllipseLine(c, rx, ry, topRight, max);
        var inter3 = Intersection.intersectEllipseLine(c, rx, ry, max, bottomLeft);
        var inter4 = Intersection.intersectEllipseLine(c, rx, ry, bottomLeft, min);

        var result = new Intersection("No Intersection");

        result.appendPoints(inter1.points);
        result.appendPoints(inter2.points);
        result.appendPoints(inter3.points);
        result.appendPoints(inter4.points);

        if ( result.points.length > 0 )
            result.status = "Intersection";

        return result;
    };

*/
/*****
 *
 *   intersectEllipseLine
 *
 *   NOTE: Rotation will need to be added to this function
 *
 *****//*



*/
/*****
 *
 *   intersectEllipsePolygon
 *
 *****//*

    Intersection.intersectEllipsePolygon = function(c, rx, ry, points) {
        var result = new Intersection("No Intersection");
        var length = points.length;

        for ( var i = 0; i < length; i++ ) {
            var b1 = points[i];
            var b2 = points[(i+1) % length];
            var inter = Intersection.intersectEllipseLine(c, rx, ry, b1, b2);

            result.appendPoints(inter.points);
        }

        if ( result.points.length > 0 )
            result.status = "Intersection";

        return result;
    };
*/

}
