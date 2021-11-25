package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.domain.composante.TypeComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import java.awt.*;

public abstract class PanelComposante extends JPanel {


    protected final BarreOnglet parent;
    protected TypeComposante type;
    protected int[] valeurs;
    protected String[] attributs;

    public PanelComposante(BarreOnglet parent, IComposante composante) {
        this.parent = parent;
        this.type = composante.getType();
        this.valeurs = composante.getValeurs();
        this.attributs = composante.getNomsAttributs();
        this.setLayout(new GridBagLayout());
        initialiser(composante);
    }

    abstract void initialiser(IComposante composante);

    public TypeComposante getType() {
        return type;
    }

    public void setType(TypeComposante type) {
        this.type = type;
    }

    public int[] getValeurs() {
        return valeurs;
    }

    public void setValeurs(int[] valeurs) {
        this.valeurs = valeurs;
    }

    public String[] getAttributs() {
        return attributs;
    }

    public void setAttributs(String[] attributs) {
        this.attributs = attributs;
    }

    public int getNbAttributs(){
        return this.attributs.length;
    }

    protected void creerLabelAttribut(String titre, int posY){
        System.out.println("Label posX: " + 0 + " posY: " + posY);
        GridBagConstraints c = new GridBagConstraints();
        JLabel label = new JLabel(titre);
        c.gridx = 0;
        c.gridy= posY;
        c.gridwidth = 5;
        c.weightx = 0.20;
        c.insets = new Insets(20,5,20, 5);
        this.add(label, c);
    }

    protected void creerLabelAttributMM(String titre, int posY){
        GridBagConstraints c = new GridBagConstraints();
        JLabel label = new JLabel(titre);
        c.gridx = 0;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.20;
        c.insets = new Insets(20,5,20, 5);
        this.add(label, c);
    }

    protected void creerLabelSymbole(String symbole, int posX, int posY){
        System.out.println("Symbole posX: " + posX + " posY: " + posY);
        GridBagConstraints c = new GridBagConstraints();
        JLabel label = new JLabel(symbole);
        c.gridx = posX;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(label, c);
    }

    protected JSpinner creerSpinnerPouces(int posX, int posY, Pouce valeur){
        System.out.println("SpinnerPouce posX: " + posX + " posY: " + posY);
        GridBagConstraints c = new GridBagConstraints();
        JSpinner spinner;
        String symbole;
        if (posX == 0){
           spinner = new JSpinner(new SpinnerNumberModel(valeur.getPouces(),0,144,1));
            symbole = " - ";
        }
        else if (posX == 2){
            spinner = new JSpinner(new SpinnerNumberModel(valeur.getNumerateur(),0,64,1));
            symbole = " / ";
        }
        else {
            spinner = new JSpinner(new SpinnerNumberModel(valeur.getDenominateur(),1,64,1));
            symbole = " \" ";
        }
        c.gridx = posX;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(spinner, c);
        creerLabelSymbole(symbole,posX+1,posY);
        return spinner;
    }

    protected JSpinner creerSpinnerMM(int posY, double value){
        GridBagConstraints c = new GridBagConstraints();
        JSpinner spinner = new JSpinner();
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "0.000"));
        spinner.setValue(value);
        c.gridx = 1;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(spinner, c);
        creerLabelSymbole(" mm ", 2, posY);
        return spinner;
    }

    protected void updateComposante(){
        parent.parent.controller.updateComposante(valeurs, getType());
        parent.parent.repaint();

    }
}
