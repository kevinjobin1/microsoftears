package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoMurSeparateur extends PanelComposante{

    private JSpinner
            hauteurSpinner,
            epaisseurSpinner,
            distancePoutreArriereSpinner,
            hauteurSpinner1,
            hauteurSpinner2,
            hauteurSpinner3,
            epaisseurSpinner1,
            epaisseurSpinner2,
            epaisseurSpinner3,
            distancePoutreArriereSpinner1,
            distancePoutreArriereSpinner2,
            distancePoutreArriereSpinner3;

    public PanelInfoMurSeparateur(BarreOnglet parent, IComposante composante) {
        super(parent, composante);
    }

    @Override
    void initialiser(IComposante composante) {
        if (parent.estImperial()) {
            int posY;
            Pouce pouces;
            for (int i = 0; i < getNbAttributs(); i++) {
                creerLabelAttribut(attributs[i] + " : ", 2 * i);
                posY = 2 * (i+1) - 1;
                if (i == 0) {
                    pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
                    this.hauteurSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.hauteurSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.hauteurSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else if (i == 1) {
                    pouces = new Pouce(valeurs[3], valeurs[4], valeurs[5]);
                    this.epaisseurSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.epaisseurSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.epaisseurSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else {
                    pouces = new Pouce(valeurs[6], valeurs[7], valeurs[8]);
                    this.distancePoutreArriereSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.distancePoutreArriereSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.distancePoutreArriereSpinner3 = creerSpinnerPouces(4, posY, pouces);
                }
            }

            //=============================================================================//
            //                                                                             //
            //=========================EVENTS (ACTION LISTENERS)===========================//
            //                                                                             //
            //=============================================================================//


            //==============HAUTEUR=============//

            hauteurSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    hauteurSpinner1ChangeListener(e);
                }
            });

            hauteurSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    hauteurSpinner2ChangeListener(e);
                }
            });

            hauteurSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    hauteurSpinner3ChangeListener(e);
                }
            });

            //==============LONGUEUR=============//
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

            //==============CENTRE X=============//
            distancePoutreArriereSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    distancePoutreArriereSpinner1ChangeListener(e);
                }
            });

            distancePoutreArriereSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    distancePoutreArriereSpinner2ChangeListener(e);
                }
            });

            distancePoutreArriereSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    distancePoutreArriereSpinner3ChangeListener(e);
                }
            });

        }
        else {
            Pouce pouces;
            for (int i = 0; i < getNbAttributs(); i++) {
                creerLabelAttributMM(attributs[i] + " : ", i);
                if (i == 1) {
                    pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
                    this.hauteurSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else if (i == 2) {
                    pouces = new Pouce(valeurs[3], valeurs[4], valeurs[5]);
                    this.epaisseurSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else {
                    pouces = new Pouce(valeurs[6], valeurs[7], valeurs[8]);
                    this.distancePoutreArriereSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                }
            }

            // ==== Poutre =======
            hauteurSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    hauteurSpinnerChangeListener(e);
                }
            });

            // ==== Plancher =======
            epaisseurSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    epaisseurSpinnerChangeListener(e);
                }
            });
            distancePoutreArriereSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    distancePoutreArriereSpinnerChangeListener(e);
                }
            });
        }
    }

    private void distancePoutreArriereSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[6] = pouces.getPouces();
        valeurs[7] = pouces.getNumerateur();
        valeurs[8] = pouces.getDenominateur();
        updateComposante();
    }

    private void epaisseurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[3] = pouces.getPouces();
        valeurs[4] = pouces.getNumerateur();
        valeurs[5] = pouces.getDenominateur();
        updateComposante();
    }

    private void hauteurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[0] = pouces.getPouces();
        valeurs[1] = pouces.getNumerateur();
        valeurs[2] = pouces.getDenominateur();
        updateComposante();
    }

    private void distancePoutreArriereSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[7]){ // 4/4
            this.valeurs[8] =  value;
        }
        else if (value <= this.valeurs[7]){
            this.distancePoutreArriereSpinner3.setValue(this.valeurs[8]);
        }
        updateComposante();
    }

    private void distancePoutreArriereSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[8]){
            this.valeurs[7] =  value;
        }
        else if (value >= this.valeurs[8]){
            this.distancePoutreArriereSpinner1.setValue((int) distancePoutreArriereSpinner1.getValue() + 1);
            this.distancePoutreArriereSpinner2.setValue(0);
            this.valeurs[7] = 0;
        }
        updateComposante();
    }

    private void distancePoutreArriereSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[6] =  value;
        updateComposante();
    }

    private void epaisseurSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[4]){ // 4/4
            this.valeurs[5] =  value;
        }
        else if (value <= this.valeurs[4]){
            this.epaisseurSpinner3.setValue(this.valeurs[5]);
        }
        updateComposante();
    }

    private void epaisseurSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[5]){
            this.valeurs[4] =  value;
        }
        else if (value >= this.valeurs[5]){
            this.epaisseurSpinner1.setValue((int) epaisseurSpinner1.getValue() + 1);
            this.epaisseurSpinner2.setValue(0);
            this.valeurs[4] = 0;
        }
        updateComposante();
    }

    private void epaisseurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[3] =  value;
        updateComposante();
    }

    private void hauteurSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[1]){ // 4/4
            this.valeurs[2] =  value;
        }
        else if (value <= this.valeurs[1]){
            this.hauteurSpinner3.setValue(this.valeurs[2]);
        }
        updateComposante();
    }

    private void hauteurSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[2]){
            this.valeurs[1] =  value;
        }
        else if (value >= this.valeurs[2]){
            this.hauteurSpinner1.setValue((int) hauteurSpinner1.getValue() + 1);
            this.hauteurSpinner2.setValue(0);
            this.valeurs[1] = 0;
        }
        updateComposante();
    }

    private void hauteurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[0] =  value;
        updateComposante();
    }
}
