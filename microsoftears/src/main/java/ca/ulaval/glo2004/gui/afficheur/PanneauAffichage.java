package ca.ulaval.glo2004.gui.afficheur;

import ca.ulaval.glo2004.domain.drawing.RoulotteAfficheur;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import javax.swing.*;

public class PanneauAffichage extends JPanel implements Serializable {

    public Dimension initialDimension;
    private FenetrePrincipale fenetrePrincipale;
    private int width = 1035;
    private int height = 737;
    

    public PanneauAffichage(FenetrePrincipale fenetrePrincipale) {
        this.fenetrePrincipale = fenetrePrincipale;
        setVisible(true);
        initialDimension = new Dimension(width, height);
        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (fenetrePrincipale != null){
            super.paintComponent(g);
            RoulotteAfficheur mainDrawer = new RoulotteAfficheur(fenetrePrincipale.controller,new Dimension(width, height));
            mainDrawer.afficher(g);

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

    }



