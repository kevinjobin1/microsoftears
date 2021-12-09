package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PanelInfoRessorts extends PanelComposante{
    private JSpinner poidsHayonSpinner;
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

        this.poidsHayonSpinner = creerSpinnerPoids(valeurs[0], 0);
        forceTextField = creerTextFieldReadOnly(1, valeurs[1], " lbs ");
        longueurExactExtensionTextField = creerTextFieldReadOnly(2, valeurs[2], " \" ");
        poidsHayonSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                poidsHayonSpinnerChangeListener(e);
            }
        });
    }

    private void poidsHayonSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        int valeur = (int) spinner.getValue();
        valeurs[0] = valeur;
        updateComposante();
    }

    private JSpinner creerSpinnerPoids(int value, int posY){
        GridBagConstraints c = new GridBagConstraints();
        JSpinner spinner;
        spinner = new JSpinner(new SpinnerNumberModel(value,0,10000,1));
        c.gridx = 1;
        c.gridy= posY;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(spinner, c);
        creerLabelSymbole(" lbs ", 2, posY);
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
