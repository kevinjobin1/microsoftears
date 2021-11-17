package ca.ulaval.glo2004.utilitaires;


import java.awt.*;
import java.io.Serializable;

public class PointPouce  implements Serializable {
    private Pouce x;
    private Pouce y;

    public PointPouce(Pouce x, Pouce y) {
        this.x = x.st(0) ? new Pouce(0,0,1) : x;
        this.y = y.st(0) ? new Pouce(0,0,1) : y;
    }

    public PointPouce() {
        x = new Pouce(90,0,1);
        y = new Pouce(40,0,1);
    }


    public PointPouce(PointPouce pointCopie){
        this.x = pointCopie.getX();
        this.y = pointCopie.getY();
    }

    @Override
    public String toString() {
        return "PointPouce[x=" + x.toString() + ", y=" + y.toString() + "]";
    }

    public Pouce getX() {
        return x;
    }

    public void setX(Pouce x) {
        this.x = x.st(0) ? new Pouce(0,1,0) : x;
    }

    public Pouce getY() {
        return y;
    }

    public void setY(Pouce y) {
        this.y = y.st(0) ? new Pouce(0,1,0) : y;
    }

    public Point point(double pixelParPouce)
    {
        pixelParPouce = (pixelParPouce < 0) ? 0
                : pixelParPouce;

        return new Point(Math.round((float)(x.toDouble() * pixelParPouce)), Math.round((float)(y.toDouble() * pixelParPouce)));
    }
}

