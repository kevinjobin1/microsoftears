package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoToit extends PanelComposante {

    private JSpinner
            epaisseurSpinner,
            epaisseurSpinner1,
            epaisseurSpinner2,
            epaisseurSpinner3;


    public PanelInfoToit(BarreOnglet parent, IComposante composante) {
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
            this.epaisseurSpinner1 = creerSpinnerPouces(0, posY, pouces);
            this.epaisseurSpinner2 = creerSpinnerPouces(2, posY, pouces);
            this.epaisseurSpinner3 = creerSpinnerPouces(4, posY, pouces);


            //=============================================================================//
            //                                                                             //
            //=========================EVENTS (ACTION LISTENERS)===========================//
            //                                                                             //
            //=============================================================================//


            //==============EPAISSEUR=============//

            epaisseurSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    epaisseurSpinner1ChangeListener(e);
                }
            });

            epaisseurSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    epaisseurSpinner2ChangeListener(e);
                }
            });

            epaisseurSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    epaisseurSpinner3ChangeListener(e);
                }
            });
        } else {
            Pouce pouces;
            creerLabelAttributMM(attributs[0] + " : ", 0);
            pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
            this.epaisseurSpinner = creerSpinnerMM(0, pouces.getMilimetres());

            // ==== TOIT =======
            epaisseurSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    epaisseurSpinnerChangeListener(e);
                }
            });
            }
        }

    private void epaisseurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        this.valeurs[0] =  value;
        updateComposante();
    }

    private void epaisseurSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[2]){
            this.valeurs[1] =  value;
        }
        else if (value >= this.valeurs[2]){
            this.epaisseurSpinner1.setValue((int) epaisseurSpinner1.getValue() + 1);
            this.epaisseurSpinner2.setValue(0);
            this.valeurs[1] = 0;
        }
        updateComposante();
    }

    private void epaisseurSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[1]){ // 4/4
            this.valeurs[2] =  value;
        }
        else if (value <= this.valeurs[1]){
            this.epaisseurSpinner3.setValue(this.valeurs[2]);
        }
        updateComposante();
    }

    private void epaisseurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[0] = pouces.getPouces();
        valeurs[1] = pouces.getNumerateur();
        valeurs[2] = pouces.getDenominateur();
        updateComposante();
    }






}
