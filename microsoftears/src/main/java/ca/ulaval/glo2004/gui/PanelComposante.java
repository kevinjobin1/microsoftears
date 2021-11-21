package ca.ulaval.glo2004.gui;

import javax.swing.*;
import java.awt.*;

public abstract class PanelComposante extends JPanel {


    //private final BarreOnglet parent;
    private int nbAttribut;

    public PanelComposante() {
        this.setLayout(new GridBagLayout());
        //this.parent = parent;
        this.initialiser();
    }

    abstract void initialiser();

    public void setNbAttribut(int nbAttribut) {
        this.nbAttribut = nbAttribut;
    }

    public int getNbAttribut() {
        return nbAttribut;
    }

    protected JLabel creerLabelAttribut(String titre, int posX, int posY){
        GridBagConstraints c = new GridBagConstraints();
        JLabel label = new JLabel(titre);
        c.gridx = posX;
        c.gridy= posY;
        c.gridwidth = 5;
        c.weightx = 0.20;
        c.insets = new Insets(20,5,20, 5);
        this.add(label, c);
        this.setNbAttribut(getNbAttribut() + 1);
        return label;
    }

    protected void creerLabelSymbole(String symbole, int posX, int posY){
        GridBagConstraints c = new GridBagConstraints();
        JLabel label = new JLabel(symbole);
        c.gridx = posX;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(label, c);
    }

    protected JSpinner creerSpinnerPouces(int posX, int posY){
        GridBagConstraints c = new GridBagConstraints();
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,12,1));
        c.gridx = posX;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(spinner, c);
        return spinner;
    }

}
