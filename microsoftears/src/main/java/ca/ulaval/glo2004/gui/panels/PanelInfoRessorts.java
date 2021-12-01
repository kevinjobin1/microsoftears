package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoRessorts extends PanelComposante{
    private JSpinner
            poidsHayonSpinner,
            poidsHayonSpinner1,
            poidsHayonSpinner2,
            poidsHayonSpinner3;

    public PanelInfoRessorts(BarreOnglet parent, IComposante composante) {
        super(parent, composante);
    }

    @Override
    void initialiser(IComposante composante) {
        if (parent.estImperial()) {
            int posY;
            Pouce pouces;
            creerLabelAttribut(attributs[0] + " : ", 0);
            posY = 1;
            pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
            this.poidsHayonSpinner1 = creerSpinnerPouces(0, posY, pouces);
            this.poidsHayonSpinner2 = creerSpinnerPouces(2, posY, pouces);
            this.poidsHayonSpinner3 = creerSpinnerPouces(4, posY, pouces);


            //=============================================================================//
            //                                                                             //
            //=========================EVENTS (ACTION LISTENERS)===========================//
            //                                                                             //
            //=============================================================================//


            //==============EPAISSEUR=============//

            poidsHayonSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poidsHayonSpinner1ChangeListener(e);
                }
            });

            poidsHayonSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poidsHayonSpinner2ChangeListener(e);
                }
            });

            poidsHayonSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poidsHayonSpinner3ChangeListener(e);
                }
            });
        } else {
            Pouce pouces;
            creerLabelAttributMM(attributs[0] + " : ", 0);
            pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
            this.poidsHayonSpinner = creerSpinnerMM(0, pouces.getMilimetres());

            // ==== TOIT =======
            poidsHayonSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poidsHayonSpinnerChangeListener(e);
                }
            });
        }
    }

    private void poidsHayonSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        this.valeurs[0] =  value;
        updateComposante();
    }

    private void poidsHayonSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[2]){
            this.valeurs[1] =  value;
        }
        else if (value >= this.valeurs[2]){
            this.poidsHayonSpinner1.setValue((int) poidsHayonSpinner1.getValue() + 1);
            this.poidsHayonSpinner2.setValue(0);
            this.valeurs[1] = 0;
        }
        updateComposante();
    }

    private void poidsHayonSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[1]){ // 4/4
            this.valeurs[2] =  value;
        }
        else if (value <= this.valeurs[1]){
            this.poidsHayonSpinner3.setValue(this.valeurs[2]);
        }
        updateComposante();
    }

    private void poidsHayonSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[0] = pouces.getPouces();
        valeurs[1] = pouces.getNumerateur();
        valeurs[2] = pouces.getDenominateur();
        updateComposante();
    }
}
