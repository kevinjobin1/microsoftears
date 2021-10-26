package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.Composante;
import ca.ulaval.glo2004.utilitaires.Polygone;

public class RoulotteAfficheur
{

    private final RoulotteController roulotte;
    private Dimension dimension;
    private int Pouce = 15;

    public RoulotteAfficheur(RoulotteController roulotte, Dimension dimension)
    {
        this.roulotte = roulotte;
        this.dimension = dimension;
    }



    //à coder
    public void afficher(Graphics g)
    {
        afficher(g);
        afficherPolygones(g);

    }

    //à coder
    private void afficherPolygones(Graphics g)
    {
        ArrayList<Composante> composantes = roulotte.getListeComposantes();
        Graphics2D g2d = (Graphics2D) g;
        for(Composante composante: composantes)
        {
            g2d.draw(composante);
        }

    }

    //à coder
    private void afficherRessort(Graphics g){
    }

    //à coder
    private void afficherBezier(Graphics g){
    }
/*
    private final RoulotteController controller;
    private Dimension initialDimension;
    private int radius = 50;


    public RoulotteAfficheur(RoulotteController controller, Dimension initialDimension) {
        this.controller = controller;
        this.initialDimension = initialDimension;
    }


    public void draw(Graphics g) {
        // drawPlan(g);
        drawElements(g);
    }

    private void drawElements(Graphics g) {
        List<Composante> composantes = controller.getElementList();
        for (Composante composante : composantes){
            Point elementPoint = composante.getPoint();
            Color color = composante.getColor();
            int posX =  (int) elementPoint.getX();
            int posY =  (int) elementPoint.getY();
            int width = radius * 2;
            int height = radius * 2;
            System.out.format("Point // %f, %f)", elementPoint.getX(), elementPoint.getY());
            System.out.format("Element // posX: %d%n posY: %d%n width: %d%n height %d%n", posX, posY, width, height);
            System.out.println();
            g.setColor(color);
            g.fillOval(posX,posY, width, height);
        }
    }

    private void drawPlan(Graphics g) {
        int planHeight = controller.getPlanHeight();
        int planWidth = controller.getPlanWidth();
        int posX = (int) ((initialDimension.getWidth() - planWidth) / 2);
        int posY = (int) ((initialDimension.getHeight() - planHeight) / 2);
        System.out.format("PLAN // posX: %d%n posY: %d%n width: %d%n height %d%n", posX, posY, planWidth, planHeight);
        System.out.println();
        g.setColor(new Color(140,98,57));
        g.fillRect(posX, posY, planWidth, planHeight);

    }

    private void roundCornerPolygon() {
        Polygon p1 = new Polygon();
        Polygon p2 = new Polygon();
        Polygon p = new Polygon();


    }

    public class Ellipse  {

        private int a;
        private int b;
        private int centerX;
        private int centerY;

        public Ellipse(int a, int b, int centerX, int centerY) {
            this.a = a;
            this.b = b;
            this.centerX = centerX;
            this.centerY = centerY;
        }

        public void drawEllipse(Graphics g)
        {
            // major and minor axis
            double px = 0, py = 0;

            // set color
            g.setColor(Color.red);

            // draw the ellipse
            for (int i = 0; i <= 360; i++) {
                double x, y;
                x = a * Math.sin(Math.toRadians(i));
                y = b * Math.cos(Math.toRadians(i));

                if (i != 0) {
                    // draw a line joining previous and new point .
                    g.drawLine((int)px + centerX, (int)py + centerY,
                            (int)x + centerX, (int)y + centerY);
                }

                // store the previous points
                px = x;
                py = y;
            }
        }
    }









*/
}

