package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static ca.ulaval.glo2004.utilitaires.Pouce.KG_TO_LBS;
import static ca.ulaval.glo2004.utilitaires.Pouce.MM_PAR_POUCE;

public class PanelInfoRessorts extends PanelComposante{
    private JSpinner poidsHayonSpinner, poidsKgHayonSpinner;
    private JTextField
            forceTextField,
            longueurExactExtensionTextField;

    public PanelInfoRessorts(BarreOnglet parent, IComposante composante) {
        super(parent, composante);
    }

    @Override
    void initialiser(IComposante composante) {
        for (int i =0; i < attributs.length; i++){
            creerLabelAttributMM(attributs[i] + " : ", i);
        }

        if (parent.estImperial()){
        this.poidsHayonSpinner = creerSpinnerPoids(valeurs[0], 0, " lbs ");
        this.forceTextField = creerTextFieldReadOnly(1, valeurs[1], " lbs ");
        this.longueurExactExtensionTextField = creerTextFieldReadOnly(2, valeurs[2], " \" ");
            poidsHayonSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poidsHayonSpinnerChangeListener(e);
                }
            });
        }
        else{
            double poidsKg = valeurs[0]/KG_TO_LBS;
            double forceKg = valeurs[1]/KG_TO_LBS;
            double longueurMM = valeurs[2] * MM_PAR_POUCE;
        this.poidsKgHayonSpinner = creerSpinnerPoids((int) poidsKg, 0, " kg ");
        this.forceTextField = creerTextFieldReadOnly(1, (int) forceKg, " kg ");
        this.longueurExactExtensionTextField = creerTextFieldReadOnly(2, (int) longueurMM, " mm ");
            poidsKgHayonSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poidsKgHayonSpinnerChangeListener(e);
                }
            });

        }


    }

    private void poidsKgHayonSpinnerChangeListener(ChangeEvent e) {

        JSpinner spinner = (JSpinner) e.getSource();
        int valeur = (int) spinner.getValue();
        valeurs[0] = (int) (valeur * KG_TO_LBS);

        updateComposante();
    }

    private void poidsHayonSpinnerChangeListener(ChangeEvent e) {

        JSpinner spinner = (JSpinner) e.getSource();
        int valeur = (int) spinner.getValue();

        valeurs[0] = valeur;
        updateComposante();
    }

    private JSpinner creerSpinnerPoids(int value, int posY, String symbole){
        GridBagConstraints c = new GridBagConstraints();
        JSpinner spinner;
        spinner = new JSpinner(new SpinnerNumberModel(value,0,10000,1));
        c.gridx = 1;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(spinner, c);
        creerLabelSymbole(symbole, 2, posY);
        return spinner;
    }

    private JTextField creerTextFieldReadOnly(int posY, int valeur, String symbole){
        JTextField textField = new JTextField();
        textField.setText(valeur + symbole);
        textField.setEditable(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = posY;
        this.add(textField, c);
        return textField;

    }
}
