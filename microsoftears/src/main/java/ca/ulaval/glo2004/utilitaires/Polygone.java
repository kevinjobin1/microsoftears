package ca.ulaval.glo2004.utilitaires;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Polygone {

    private LinkedList<PointPouce> listePoints;

    public Polygone(LinkedList<PointPouce> listePoints) {
        super();
        this.listePoints = listePoints;
    }

    public LinkedList<PointPouce> getListePoints() {
        return listePoints;
    }

    public void setListePoints(LinkedList<PointPouce> listePoints) {
        this.listePoints = listePoints;
    }

    public boolean contient(PointPouce p, Pouce limiteX){
        boolean resultat = false;

        // Nombre de sommets
        int n = listePoints.size();

        // Il faut au moins 3 points
        if (n < 3)
        {
            return resultat;
        }

        // Créé un point d'extremité pour qu'un segment s'étire "à l'infini" (on limite a la dimension max du plan)
        PointPouce extreme = new PointPouce(limiteX, p.getY());

        // Compte le nombre d'intersections avec la ligne formé du point p jusqu'à l'extrêmité du plan
        int count = 0,
                i = 0;
        do
        {
            int next = (i + 1) % n;

            // Vérifie si la ligne (p, extreme) a un point d'intersection avec un segment du polygone
            if (PointPouce.intersect(listePoints.get(i), listePoints.get(next), p, extreme))
            {
                // Vérifie si le point est sur le contour du polygone (colinéaire avec les sommets)
                if (PointPouce.orientation(listePoints.get(i), p, listePoints.get(next)) == 0)
                {
                    return PointPouce.appartientSegment(listePoints.get(i), p,
                            listePoints.get(next));
                }

                count++;
            }
            i = next;
        } while (i != 0);

        System.out.println(this + ": Count " + count);

        // Si le nombre de points d'intersection est impair, le point est contenu dans la polygone, false sinon
        return (count % 2 == 1);
    }

    public boolean contains(PointPouce p) {
        int i, j;
        int n = listePoints.size();
        boolean resultat = false;
        for (i = 0, j = n - 1; i < n; j = i++) {
            if ((listePoints.get(i).getY().gt(p.getY())) != (listePoints.get(j).getY().gt(p.getY())) &&
                    (p.getX().st((listePoints.get(j).getX().diff(listePoints.get(i).getX())).multiplier((p.getY().diff(listePoints.get(i).getY())).diviser((listePoints.get(j).getY().diff(listePoints.get(i).getY())))).add(listePoints.get(i).getX()))))
            {
                resultat = true;
            }
        }
        return resultat;
    }

}
