package ca.ulaval.glo2004;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;
import junit.framework.TestCase;
import org.junit.Test;

public class TestForme extends TestCase {

private Rectangle rectangle1 = new Rectangle(new Pouce(2,0,1), new Pouce(4,0,1),
        new PointPouce(new Pouce(5,0,1), new Pouce(5,2,4)));

private Ellipse ellipse1 = new Ellipse(new Pouce(1,0,1),new Pouce(1,0,1),
        new PointPouce(new Pouce(2,0,1), new Pouce(2,0,1)));

    @Test
    public void testGetPolygoneRectangle(){
        System.out.println(rectangle1.getPolygone().getListePoints().toString());
    }

    @Test
    public void testGetPolygoneEllipse(){
        System.out.println(ellipse1.getPolygone().getListePoints().toString());
    }
}
