package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.afficheur.PlanDrawer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class DrawingPanel extends JPanel implements Serializable {

    public Dimension initialDimension;
    private MainWindow mainWindow;

    public DrawingPanel(){

    }

    public DrawingPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        int width = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension(width,1));
        setVisible(true);
        int height = (int)(width*0.5);
        initialDimension = new Dimension(width,height);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        if (mainWindow != null){
            super.paintComponent(g);
            PlanDrawer mainDrawer = new PlanDrawer(mainWindow.controller,initialDimension);
            mainDrawer.draw(g);
        }
    }

    public MainWindow getMainWindow(){
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public Dimension getInitialDimension(){
        return initialDimension;
    }

    public void setInitialDimension(){

    }
}

