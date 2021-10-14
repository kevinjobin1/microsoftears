package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Polygone;
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
        this.rectangle = new Rectangle();
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
