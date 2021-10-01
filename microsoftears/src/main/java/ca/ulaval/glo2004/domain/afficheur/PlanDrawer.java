package ca.ulaval.glo2004.domain.afficheur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import ca.ulaval.glo2004.domain.PlanController;
import ca.ulaval.glo2004.domain.Element;

public class PlanDrawer {

    private final PlanController controller;
    private Dimension initialDimension;
    private int radius = 50;


    public PlanDrawer(PlanController controller, Dimension initialDimension) {
        this.controller = controller;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g) {
        drawPlan(g);
        drawElements(g);
    }

    private void drawElements(Graphics g) {
        List<Element> elements = controller.getElementList();
        for (Element element: elements){
            Point elementPoint = element.getPoint();
            Color color = element.getColor();
            g.setColor(color);
            g.fillOval((int)elementPoint.getX()-radius,(int)elementPoint.getY()-radius, radius*2, radius*2);
        }
    }

    private void drawPlan(Graphics g) {
        int width = (int) initialDimension.getWidth();
        int height = (int) initialDimension.getHeight();
        g.setColor(new Color(140,98,57));
        g.fillRect(width/4, (int)(height/1.75), width/2, height/4);
    }
}

