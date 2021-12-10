package ca.ulaval.glo2004;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static ca.ulaval.glo2004.utilitaires.Forme.addPointsIntersection;

public class TestForme extends TestCase {

private final Rectangle rectangle0 = new Rectangle(new Pouce(2,0,1), new Pouce(4,0,1),
        new PointPouce(new Pouce(5,0,1), new Pouce(5,2,4)));

private final Ellipse ellipse0 = new Ellipse(new Pouce(1,0,1),new Pouce(1,0,1),
        new PointPouce(new Pouce(2,0,1), new Pouce(2,0,1)));

    private final Rectangle rectangle1 = new Rectangle(new Pouce(96,0,1),
            new Pouce(48,0,1),
            new PointPouce(
                    new Pouce(65,0,1),
                    new Pouce(50,0,1))
    );

    private final Ellipse ellipse1 = new Ellipse(
            new Pouce(40,0,1),
            new Pouce(30,0,1),
            new PointPouce(
                    new Pouce(95,0,1),
                    new Pouce(39,0,1))
    );

    private final Ellipse ellipse2 = new Ellipse(
            new Pouce(40,0,1),
            new Pouce(30,0,1),
            new PointPouce(
                    new Pouce(35,0,1),
                    new Pouce(39,0,1))
    );

    private final Ellipse ellipse3 = new Ellipse(
            new Pouce(40,0,1),
            new Pouce(30,0,1),
            new PointPouce(
                    new Pouce(35,0,1),
                    new Pouce(61,0,1))
    );

    private final Ellipse ellipse4 = new Ellipse(
            new Pouce(40,0,1),
            new Pouce(30,0,1),
            new PointPouce(
                    new Pouce(95,0,1),
                    new Pouce(61,0,1))
    );


    @Test
    public void testGetPolygoneRectangle(){
        print("testGetPolygoneRectangle");
        print(rectangle1.getPolygone().getListePoints().toString());
    }

    @Test
    public void testGetPolygoneEllipse(){
        print("testGetPolygoneEllipse");
        print(ellipse1.getPolygone().getListePoints().toString());
    }


    @Test
    public void testGetPointsIntersection(){

        LinkedList<PointPouce> listePointsPolygone = new LinkedList<>();

        print("testGetPointsIntersection");
        print("Origine (coin haut-gauche) : " + new PointPouce(rectangle1.getCentre().getX().diff(rectangle1.getLongueur().diviser(2)),
                                                            rectangle1.getCentre().getY().diff(rectangle1.getHauteur().diviser(2))));
        print("Origine (coin haut-droit) : " + new PointPouce(rectangle1.getCentre().getX().add(rectangle1.getLongueur().diviser(2)),
                rectangle1.getCentre().getY().diff(rectangle1.getHauteur().diviser(2))));
        print("Origine (coin bas-gauche) : " + new PointPouce(rectangle1.getCentre().getX().diff(rectangle1.getLongueur().diviser(2)),
                rectangle1.getCentre().getY().add(rectangle1.getHauteur().diviser(2))));
        print("Origine (coin bas-droit) : " + new PointPouce(rectangle1.getCentre().getX().add(rectangle1.getLongueur().diviser(2)),
                rectangle1.getCentre().getY().add(rectangle1.getHauteur().diviser(2))));

        addPointsIntersection(listePointsPolygone, rectangle1, ellipse1, 1);
        addPointsIntersection(listePointsPolygone, rectangle1, ellipse2, 2);
        addPointsIntersection(listePointsPolygone, rectangle1, ellipse3, 3);
        addPointsIntersection(listePointsPolygone, rectangle1, ellipse4, 4);

        for (int i=0; i < listePointsPolygone.size(); i++){
        print(listePointsPolygone.get(i));
        }

    }



    public static void print(Object object){
        System.out.println(object.toString());
    }
}
