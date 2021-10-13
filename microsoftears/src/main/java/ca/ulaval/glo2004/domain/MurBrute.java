package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

public class MurBrute extends Composante{

    private Rectangle rectangle;

    public MurBrute(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public MurBrute() {
        Pouce longueur=new Pouce(96,0,0);
        Pouce hauteur=new Pouce(48,0,0);
        PointPouce centre = new PointPouce(); //à modifier
        this.rectangle = new Rectangle(longueur, hauteur, centre);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
