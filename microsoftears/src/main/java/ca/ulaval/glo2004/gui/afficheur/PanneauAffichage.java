package ca.ulaval.glo2004.gui.afficheur;

import ca.ulaval.glo2004.domain.drawing.RoulotteAfficheur;
import ca.ulaval.glo2004.gui.FenetrePrincipale;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.BevelBorder;

// afficheurMicroRoulotte
public class PanneauAffichage extends JPanel implements Serializable {

    public Dimension initialDimension;
    private FenetrePrincipale fenetrePrincipale;
    //private final int GRID_SIZE = 125000;
    public double scale;
    private double panneauLeft;
    private double panneauTop;
    private double deltaX;
    private double deltaY;
    public int pixelsToInchesRatio = 10;
    

    public PanneauAffichage(FenetrePrincipale fenetrePrincipale) {
        this.fenetrePrincipale = fenetrePrincipale;
        int width = 1035;
        int height = 737;
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(getPreferredSize());
        setVisible(true);
        initialDimension = new Dimension(width, height);
        this.setBackground(Color.DARK_GRAY);
        scale = 1;
        panneauLeft = 0;
        panneauTop = 0;

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (fenetrePrincipale != null){
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            double width = initialDimension.getWidth(); // real width of canvas
            double height = initialDimension.getHeight(); // real height of canvas
            AffineTransform oldAf = g2d.getTransform();
            AffineTransform af = new AffineTransform(oldAf);
            af.scale(scale, scale);
            af.translate(-deltaX, -deltaY);
            g2d.setTransform(af);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,(int) width, (int) height);

            g2d.setColor(Color.gray);
            g2d.fillRect(250, 250,250,250);

            /*//white background
            g.setColor(Color.white);
            g.fillRect(0-GRID_SIZE/2, 0-GRID_SIZE/2, GRID_SIZE, GRID_SIZE);
            g.setColor(Color.gray);

            //grid
            if (scale >= 0.02 && scale <= 0.2){
                g.setColor(ligneGrilleCouleur);
                drawGrid(g, 250);
            }
            if (scale > 0.2 && scale <= 0.6){

                g.setColor(ligneInterieurGrilleCouleur);
                drawGrid(g, 50);
                g.setColor(ligneGrilleCouleur);
                drawGrid(g, 250);
            }
            if (scale >0.6 ){
                g.setColor(ligneInterieurGrilleCouleur);
                drawGrid(g, 10);
                g.setColor(ligneInterieurGrilleCouleur);
                drawGrid(g, 50);
                g.setColor(ligneGrilleCouleur);
                drawGrid(g, 250);
            }
            // end grid*/

          /*
            RoulotteAfficheur mainDrawer = new RoulotteAfficheur(fenetrePrincipale.controller,initialDimension);
            mainDrawer.draw(g);*/
    }
    }

    /*public void drawGrid(Graphics g, int scale){

        for (int i = -GRID_SIZE/2; i <=GRID_SIZE/2; i = i+scale){
            g.drawLine(-GRID_SIZE/2, i, GRID_SIZE/2 , i);
        }

        for (int i = -GRID_SIZE/2; i <=GRID_SIZE/2; i = i+scale){
            g.drawLine( i, -GRID_SIZE/2, i,GRID_SIZE/2 );
        }
        g.setColor(Color.white);
    }*/

    public void setCenter(Point mousePoint){
        //TODO: à faire, zoom en fonction de la position de la souris,
        // il faut tenir compte du déplacement du centre réel de l'objet Graphics
        // pour l'instant le zoom/position fonctionne seulement si on reste à l'intérieur de la forme...
           deltaX = (mousePoint.getX() * scale) - mousePoint.getX();
           deltaY = (mousePoint.getY() * scale) - mousePoint.getY();
    }

    public void setScale(int wheelRotation){

        double scaleFactor = 0;

        if (Math.abs(wheelRotation) >= 1){
            scaleFactor = 1.01;
        }

        if (wheelRotation <= -1){
            if (scale > 0.03){
                scale /= scaleFactor;
            }
            System.out.println("Zoom out: " + scale);
        }
        else if (wheelRotation >= 1){
            if (scale < 6){
                scale *= scaleFactor;

            System.out.println("Zoom in: " + scale);
        }

    }
    }

    public Point getPosition(Point mousePoint) {
            //TODO: à refaire, pas du tout le bon calcul
        //int gridX = (int) ((mousePoint.getX()/scale) - (((1/scale) - 1) * (initialDimension.getWidth()/2)) - centerX);
        //int gridY = (int) ((mousePoint.getY()/scale) - (((1/scale) - 1) * (initialDimension.getHeight()/2)) - centerY);

        return new Point (0,0);
    }

    public FenetrePrincipale getMainWindow(){
        return fenetrePrincipale;
    }

    public void setMainWindow(FenetrePrincipale fenetrePrincipale){
        this.fenetrePrincipale = fenetrePrincipale;
    }

    public Dimension getInitialDimension(){
        return initialDimension;
    }

    public void setInitialDimension(Dimension newDimension){
        this.initialDimension = newDimension;
    }


    public int getPixelsToInchesRatio() {
        return this.pixelsToInchesRatio;
    }

    public int inchesToPixel(int inches){
        int pixels = inches * pixelsToInchesRatio;
        return pixels;
    }

    public int pixelsToInches(int pixels){
        int inches = pixels / this.getPixelsToInchesRatio();
        return inches;
    }

    }



