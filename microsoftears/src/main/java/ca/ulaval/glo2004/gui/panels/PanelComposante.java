package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.gui.barres.BarreOnglet;

import javax.swing.*;
import java.awt.*;

public abstract class PanelComposante extends JPanel {


    private final BarreOnglet parent;
    private int nbAttribut;

    public PanelComposante(BarreOnglet parent) {
        this.parent = parent;
        this.setLayout(new GridBagLayout());
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

    protected JLabel creerLabelAttributMM(String titre, int posX, int posY){
        GridBagConstraints c = new GridBagConstraints();
        JLabel label = new JLabel(titre);
        c.gridx = posX;
        c.gridy= posY;
        c.gridwidth = 1;
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
        JSpinner spinner;
        if (posX == 0){
           spinner = new JSpinner(new SpinnerNumberModel(0,0,144,1));
        }
        else if (posX == 2){
            spinner = new JSpinner(new SpinnerNumberModel(0,0,64,1));
        }
        else {
            spinner = new JSpinner(new SpinnerNumberModel(1,1,64,1));
        }
        c.gridx = posX;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(spinner, c);
        return spinner;
    }

    protected JSpinner creerSpinnerPouces(int posX, int posY, int[] valeur){
        GridBagConstraints c = new GridBagConstraints();
        JSpinner spinner;
        if (posX == 0){
            spinner = new JSpinner(new SpinnerNumberModel(valeur[0],0,144,1));
        }
        else if (posX == 2){
            spinner = new JSpinner(new SpinnerNumberModel(valeur[1],0,64,1));
        }
        else {
            spinner = new JSpinner(new SpinnerNumberModel(valeur[2],0,64,1));
        }
        c.gridx = posX;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(spinner, c);
        return spinner;
    }

    protected JSpinner creerSpinnerMM(int x, int y){
        GridBagConstraints c = new GridBagConstraints();
        JSpinner spinner = new JSpinner();
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "0.000"));
        c.gridx = x;
        c.gridy= y;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(spinner, c);
        return spinner;
    }
}
