package ca.ulaval.glo2004.domain;

import java.awt.Color;
import java.awt.Point;

public abstract class Composante {

    private Point point;

    public Composante(Point point){
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public abstract Color getColor();

}