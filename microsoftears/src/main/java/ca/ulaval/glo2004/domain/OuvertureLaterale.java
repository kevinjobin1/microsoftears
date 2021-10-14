package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class OuvertureLaterale extends Composante{
    private Rectangle rectangle;


    public OuvertureLaterale(Rectangle rectangle, RoulotteController parent) {
        super(parent);
        this.rectangle = rectangle;
    }

    public OuvertureLaterale(RoulotteController parent) {
        super(parent);
        this.rectangle = new Rectangle();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
