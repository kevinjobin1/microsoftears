package ca.ulaval.glo2004.domain;

import java.awt.Color;
import java.awt.Point;

public abstract class Element {

    private Point point;

    public Element(Point point){
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public abstract Color getColor();

}