package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoPlancher extends PanelComposante {
    private JSpinner
            epaisseurSpinner,
            margeAvantSpinner,
            margeArriereSpinner,
            epaisseurSpinner1,
            epaisseurSpinner2,
            epaisseurSpinner3,
            margeAvantSpinner1,
            margeAvantSpinner2,
            margeAvantSpinner3,
            margeArriereSpinner1,
            margeArriereSpinner2,
            margeArriereSpinner3;

    public PanelInfoPlancher(BarreOnglet parent, IComposante composante) {
        super(parent, composante);
    }
    
    void initialiser(IComposante composante) {

        if (parent.estImperial()) {
            int posY;
            Pouce pouces;
            for (int i = 0; i < getNbAttributs(); i++) {
                creerLabelAttribut(attributs[i] + " : ", 2 * i);
                posY = 2 * (i+1) - 1;
                if (i == 0) {
                    pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
                    this.epaisseurSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.epaisseurSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.epaisseurSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else if (i == 1) {
                    pouces = new Pouce(valeurs[3], valeurs[4], valeurs[5]);
                    this.margeAvantSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.margeAvantSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.margeAvantSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else {
                    pouces = new Pouce(valeurs[6], valeurs[7], valeurs[8]);
                    this.margeArriereSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.margeArriereSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.margeArriereSpinner3 = creerSpinnerPouces(4, posY, pouces);
                }
            }

            //=============================================================================//
            //                                                                             //
            //=========================EVENTS (ACTION LISTENERS)===========================//
            //                                                                             //
            //=============================================================================//


            //==============epaisseur=============//

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

            //==============margeAvant=============//
            margeAvantSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    margeAvantSpinner1ChangeListener(e);
                }
            });

            margeAvantSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    margeAvantSpinner2ChangeListener(e);
                }
            });

            margeAvantSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    margeAvantSpinner3ChangeListener(e);
                }
            });

            //==============CENTRE X=============//
            margeArriereSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    margeArriereSpinner1ChangeListener(e);
                }
            });

            margeArriereSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    margeArriereSpinner2ChangeListener(e);
                }
            });

            margeArriereSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    margeArriereSpinner3ChangeListener(e);
                }
            });

        } else {
            Pouce pouces;
            for (int i = 0; i < getNbAttributs(); i++) {
                creerLabelAttributMM(attributs[i] + " : ", i);
                if (i == 0) {
                    pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
                    this.epaisseurSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else if (i == 1) {
                    pouces = new Pouce(valeurs[3], valeurs[4], valeurs[5]);
                    this.margeAvantSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else {
                    pouces = new Pouce(valeurs[6], valeurs[7], valeurs[8]);
                    this.margeArriereSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                }
            }

            // ==== Poutre =======
            epaisseurSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    epaisseurSpinnerChangeListener(e);
                }
            });

            // ==== Plancher =======
            margeAvantSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    margeAvantSpinnerChangeListener(e);
                }
            });
            margeArriereSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    margeArriereSpinnerChangeListener(e);
                }
            });
        }
    }

    private void epaisseurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        ;
        this.valeurs[0] = value;
        updateComposante();
    }

    private void epaisseurSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[2]) {
            this.valeurs[1] = value;
        } else if (value >= this.valeurs[2]) {
            this.epaisseurSpinner1.setValue((int) epaisseurSpinner1.getValue() + 1);
            this.epaisseurSpinner2.setValue(0);
            this.valeurs[1] = 0;
        }
        updateComposante();
    }

    private void epaisseurSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[1]) { // 4/4
            this.valeurs[2] = value;
        } else if (value <= this.valeurs[1]) {
            this.epaisseurSpinner3.setValue(this.valeurs[2]);
        }
        updateComposante();
    }

    private void margeAvantSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        ;
        this.valeurs[3] = value;
        updateComposante();
    }

    private void margeAvantSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[5]) {
            this.valeurs[4] = value;
        } else if (value >= this.valeurs[5]) {
            this.margeAvantSpinner1.setValue((int) margeAvantSpinner1.getValue() + 1);
            this.margeAvantSpinner2.setValue(0);
            this.valeurs[4] = 0;
        }
        updateComposante();
    }

    private void margeAvantSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[4]) { // 4/4
            this.valeurs[5] = value;
        } else if (value <= this.valeurs[4]) {
            this.margeAvantSpinner3.setValue(this.valeurs[5]);
        }
        updateComposante();
    }

    private void margeArriereSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        ;
        this.valeurs[6] = value;
        updateComposante();
    }

    private void margeArriereSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[8]) {
            this.valeurs[7] = value;
        } else if (value >= this.valeurs[8]) {
            this.margeArriereSpinner1.setValue((int) margeArriereSpinner1.getValue() + 1);
            this.margeArriereSpinner2.setValue(0);
            this.valeurs[7] = 0;
        }
        updateComposante();
    }

    private void margeArriereSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[7]) { // 4/4
            this.valeurs[8] = value;
        } else if (value <= this.valeurs[7]) {
            this.margeArriereSpinner3.setValue(this.valeurs[8]);
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


    private void margeAvantSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[3] = pouces.getPouces();
        valeurs[4] = pouces.getNumerateur();
        valeurs[5] = pouces.getDenominateur();
        updateComposante();
    }

    private void margeArriereSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[6] = pouces.getPouces();
        valeurs[7] = pouces.getNumerateur();
        valeurs[8] = pouces.getDenominateur();
        updateComposante();
    }
}