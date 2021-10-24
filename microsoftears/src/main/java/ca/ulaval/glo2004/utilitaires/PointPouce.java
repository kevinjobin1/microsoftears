package ca.ulaval.glo2004.utilitaires;


public class PointPouce {
    private Pouce x;
    private Pouce y;

    public PointPouce(Pouce x, Pouce y) {
        this.x = x;
        this.y = y;
    }

    public PointPouce() {
        x = new Pouce(50);
        y = new Pouce(50);
    }

    @Override
    public String toString() {
        return "PointPouce[x=" + x.toString() + ", y=" + y.toString() + "]";
    }

    public Pouce getX() {
        return x;
    }

    public void setX(Pouce x) {
        this.x = x;
    }

    public Pouce getY() {
        return y;
    }

    public void setY(Pouce y) {
        this.y = y;
    }
}
