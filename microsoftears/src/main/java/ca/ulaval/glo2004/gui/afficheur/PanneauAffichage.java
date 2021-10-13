package ca.ulaval.glo2004.gui.afficheur;

import ca.ulaval.glo2004.domain.drawing.RoulotteAfficheur;
import ca.ulaval.glo2004.gui.FenetrePrincipale;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import javax.swing.JPanel;

// afficheurMicroRoulotte
public class PanneauAffichage extends JPanel implements Serializable {

    public Dimension initialDimension;
    private FenetrePrincipale fenetrePrincipale;
    private final int GRID_SIZE = 20000;
    private final double MAX_SCALE = 6;
    private final double MIN_SCALE = 0.3;
    public double scale;
    public double centerX;
    public double centerY;
    public int pixelsToInchesRatio = 10;
    private Color lightGray;
    private Color lightLightGray;
    private Color lightLightLightGray;
    

    public PanneauAffichage(FenetrePrincipale fenetrePrincipale) {
        this.fenetrePrincipale = fenetrePrincipale;
        int width = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension(width,1));
        setVisible(true);
        int height = (int)(width * 0.5);
        initialDimension = new Dimension(width,height);
        lightGray = new Color(30,30,30);
        lightLightGray = new Color(70,70,70);
        lightLightLightGray = new Color(110,110,110);
        scale = 1;
        centerX = 0;
        centerY = 0;

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
            af.translate((width/2) - ((width/2-centerX)*(scale))  ,
                    (height/2) -((height/2-centerY)*(scale)));
            af.scale(scale, scale);
            g2d.setTransform(af);

            //white background
            g.setColor(Color.gray);
            g.fillRect(0-GRID_SIZE/2, 0-GRID_SIZE/2, GRID_SIZE, GRID_SIZE);
            g.setColor(Color.white);

            //grid
            if (scale >= 0.02 && scale <= 0.2){
                g.setColor(lightGray);
                drawGrid(g, 250);
            }
            if (scale > 0.2 && scale <= 0.6){

                g.setColor(lightLightGray);
                drawGrid(g, 50);
                g.setColor(lightGray);
                drawGrid(g, 250);
            }
            if (scale >0.6 ){
                g.setColor(lightLightLightGray);
                drawGrid(g, 10);
                g.setColor(lightLightGray);
                drawGrid(g, 50);
                g.setColor(lightGray);
                drawGrid(g, 250);
            }

            // end grid
            RoulotteAfficheur mainDrawer = new RoulotteAfficheur(fenetrePrincipale.controller,initialDimension);
            mainDrawer.draw(g);
        }


    }


    public void drawGrid(Graphics g, int scale){

        for (int i = -GRID_SIZE/2; i <=GRID_SIZE/2; i = i+scale){
            g.drawLine(-GRID_SIZE/2, i, GRID_SIZE/2 , i);
        }

        for (int i = -GRID_SIZE/2; i <=GRID_SIZE/2; i = i+scale){
            g.drawLine( i, -GRID_SIZE/2, i,GRID_SIZE/2 );
        }
        g.setColor(Color.white);
    }

    public void setCenter(Point mousePoint){
        int limit = GRID_SIZE/2 + 750;

        centerX += mousePoint.getX() * (scale - 1);
        centerY += mousePoint.getY() * (scale - 1);

        if (centerX > limit){
            centerX = limit;
        }
        if (centerX < -limit){
            centerX = -limit;
        }
        if (centerY < -limit){
            centerY = -limit;
        }
        if (centerY > limit){
            centerY = limit;
        }

        System.out.format("CenterX: %f \n CenterY: %f \n", centerX, centerY);
    }

    public void setScale(int wheelRotation){

        double scaleFactor = 0;

        if (Math.abs(wheelRotation) >= 1){
            scaleFactor = 1.1;
        }

        if (wheelRotation <= -1){
            if (scale < 6){
                scale *= scaleFactor;
            }
        }
        else if (wheelRotation >= 1){
            if (scale > 0.03){
                scale /= scaleFactor;
            }
        }

    }

    public Point getGridPosition(Point mousePoint) {

        int gridX = (int) ((mousePoint.getX()/scale) - (((1/scale) - 1) * (initialDimension.getWidth()/2)) - centerX);
        int gridY = (int) ((mousePoint.getY()/scale) - (((1/scale) - 1) * (initialDimension.getHeight()/2)) - centerY);

        return new Point (gridX, gridY);
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




