package ca.ulaval.glo2004.domain;

import java.awt.Color;
import java.awt.Point;

public class Hayon extends Element {

    private Color color;

    public Hayon(Point point) {
        super(point);
        this.color = Color.ORANGE;
    }

    public Color getColor(){
        return color;
    }
}

