package ca.ulaval.glo2004.domain;

import java.awt.Color;
import java.awt.Point;

public class Plancher extends Composante {

    private Color color;

    public Plancher(Point point) {
        super(point);
        this.color = Color.RED;
    }

    public Color getColor(){
        return color;
    }
}
