package ca.ulaval.glo2004;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;
import junit.framework.TestCase;
import org.junit.Test;

import static ca.ulaval.glo2004.utilitaires.Forme.getPointsIntersection;

public class TestForme extends TestCase {

private Rectangle rectangle0 = new Rectangle(new Pouce(2,0,1), new Pouce(4,0,1),
        new PointPouce(new Pouce(5,0,1), new Pouce(5,2,4)));

private Ellipse ellipse0 = new Ellipse(new Pouce(1,0,1),new Pouce(1,0,1),
        new PointPouce(new Pouce(2,0,1), new Pouce(2,0,1)));

    private Rectangle rectangle1 = new Rectangle(new Pouce(96,0,1),
            new Pouce(48,0,1),
            new PointPouce(
                    new Pouce(65,0,1),
                    new Pouce(50,0,1))
    );

    private Ellipse ellipse1 = new Ellipse(
            new Pouce(40,0,1),
            new Pouce(30,0,1),
            new PointPouce(
                    new Pouce(95,0,1),
                    new Pouce(39,0,1))
    );

    private Ellipse ellipse2 = new Ellipse(
            new Pouce(40,0,1),
            new Pouce(30,0,1),
            new PointPouce(
                    new Pouce(35,0,1),
                    new Pouce(39,0,1))
    );

    private Ellipse ellipse3 = new Ellipse(
            new Pouce(40,0,1),
            new Pouce(30,0,1),
            new PointPouce(
                    new Pouce(35,0,1),
                    new Pouce(61,0,1))
    );

    private Ellipse ellipse4 = new Ellipse(
            new Pouce(40,0,1),
            new Pouce(30,0,1),
            new PointPouce(
                    new Pouce(95,0,1),
                    new Pouce(61,0,1))
    );


    @Test
    public void testGetPolygoneRectangle(){
        System.out.println(rectangle1.getPolygone().getListePoints().toString());
    }

    @Test
    public void testGetPolygoneEllipse(){
        System.out.println(ellipse1.getPolygone().getListePoints().toString());
    }


    @Test
    public void testGetPointsIntersection(){
        System.out.println(getPointsIntersection(rectangle1, ellipse1, 1));
        System.out.println(getPointsIntersection(rectangle1, ellipse2, 2));
        System.out.println(getPointsIntersection(rectangle1, ellipse3, 3));
        System.out.println(getPointsIntersection(rectangle1, ellipse4, 4));
    }
}
