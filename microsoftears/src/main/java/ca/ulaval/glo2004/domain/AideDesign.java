package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class AideDesign extends Composante{

    private Rectangle rectangle;

    public AideDesign(Rectangle rectangle, RoulotteController parent) {
        super(parent);
        this.rectangle = rectangle;
        this.setType(TypeComposante.AIDE_DESIGN);
        this.setPolygone(rectangle.getPolygone());
    }

    public AideDesign(RoulotteController parent) {
        super(parent);
        this.rectangle = new Rectangle(new Pouce(12), new Pouce(12),
                new PointPouce(new Pouce(50), new Pouce(50)));
        this.setType(TypeComposante.AIDE_DESIGN);
        this.setPolygone(rectangle.getPolygone());
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
