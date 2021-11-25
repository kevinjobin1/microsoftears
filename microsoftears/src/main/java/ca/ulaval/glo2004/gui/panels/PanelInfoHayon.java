package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoHayon extends PanelComposante{
    private JSpinner
    epaisseurSpinner,
    poutreSpinner,
    plancherSpinner,
    scieSpinner,
    rayonSpinner,
    epaisseurSpinner1,
     epaisseurSpinner2,
     epaisseurSpinner3,
     poutreSpinner1,
     poutreSpinner2,
     poutreSpinner3,
     plancherSpinner1,
     plancherSpinner2,
     plancherSpinner3,
     scieSpinner1,
     scieSpinner2,
     scieSpinner3,
     rayonSpinner1,
     rayonSpinner2,
     rayonSpinner3;

    public PanelInfoHayon(BarreOnglet parent, IComposante composante) {
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
                    this.poutreSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.poutreSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.poutreSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else if (i == 2) {
                    pouces = new Pouce(valeurs[6], valeurs[7], valeurs[8]);
                    this.plancherSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.plancherSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.plancherSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else if (i == 3){
                    pouces = new Pouce(valeurs[9], valeurs[10], valeurs[11]);
                    this.scieSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.scieSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.scieSpinner3 = creerSpinnerPouces(4, posY, pouces);
                }
                else {
                    pouces = new Pouce(valeurs[12], valeurs[13], valeurs[14]);
                    this.rayonSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.rayonSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.rayonSpinner3 = creerSpinnerPouces(4, posY, pouces);
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

            //==============poutre=============//
            poutreSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poutreSpinner1ChangeListener(e);
                }
            });

            poutreSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poutreSpinner2ChangeListener(e);
                }
            });

            poutreSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poutreSpinner3ChangeListener(e);
                }
            });

            //==============CENTRE X=============//
            plancherSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    plancherSpinner1ChangeListener(e);
                }
            });

            plancherSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    plancherSpinner2ChangeListener(e);
                }
            });

            plancherSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    plancherSpinner3ChangeListener(e);
                }
            });

            //==============CENTRE Y=============//
            scieSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    scieSpinner1ChangeListener(e);
                }
            });

            scieSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    scieSpinner2ChangeListener(e);
                }
            });

            scieSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    scieSpinner3ChangeListener(e);
                }
            });

            rayonSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    rayonSpinner1ChangeListener(e);
                }
            });

            rayonSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    rayonSpinner2ChangeListener(e);
                }
            });

            rayonSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    rayonSpinner3ChangeListener(e);
                }
            });
        }
        else {
            Pouce pouces;
            for (int i = 0; i < getNbAttributs(); i++) {
                creerLabelAttributMM(attributs[i] + " : ", i);
                if (i == 1) {
                    pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
                    this.epaisseurSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else if (i == 2) {
                    pouces = new Pouce(valeurs[3], valeurs[4], valeurs[5]);
                    this.poutreSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else if (i == 3) {
                    pouces = new Pouce(valeurs[6], valeurs[7], valeurs[8]);
                    this.plancherSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else if (i == 4){
                    pouces = new Pouce(valeurs[9], valeurs[10], valeurs[11]);
                    this.scieSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                }
                else {
                    pouces = new Pouce(valeurs[12], valeurs[13], valeurs[14]);
                    this.rayonSpinner = creerSpinnerMM(i, pouces.getMilimetres());
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
            poutreSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    poutreSpinnerChangeListener(e);
                }
            });
            plancherSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    plancherSpinnerChangeListener(e);
                }
            });

            scieSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    scieSpinnerChangeListener(e);
                }
            });

            rayonSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    rayonSpinnerChangeListener(e);
                }
            });
        }
    }

    private void epaisseurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
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

    private void poutreSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[3] =  value;
        updateComposante();
    }
    private void poutreSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[5]){
            this.valeurs[4] =  value;
        }
        else if (value >= this.valeurs[5]){
            this.poutreSpinner1.setValue((int) poutreSpinner1.getValue() + 1);
            this.poutreSpinner2.setValue(0);
            this.valeurs[4] = 0;
        }
        updateComposante();
    }
    private void poutreSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[4]){ // 4/4
            this.valeurs[5] =  value;
        }
        else if (value <= this.valeurs[4]){
            this.poutreSpinner3.setValue(this.valeurs[5]);
        }
        updateComposante();
    }

    private void plancherSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[6] =  value;
        updateComposante();
    }
    private void plancherSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[8]){
            this.valeurs[7] =  value;
        }
        else if (value >= this.valeurs[8]){
            this.plancherSpinner1.setValue((int) plancherSpinner1.getValue() + 1);
            this.plancherSpinner2.setValue(0);
            this.valeurs[7] = 0;
        }
        updateComposante();
    }
    private void plancherSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[7]){ // 4/4
            this.valeurs[8] =  value;
        }
        else if (value <= this.valeurs[7]){
            this.plancherSpinner3.setValue(this.valeurs[8]);
        }
        updateComposante();
    }

    private void scieSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[9] =  value;
        updateComposante();
    }
    private void scieSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[11]){
            this.valeurs[10] =  value;
        }
        else if (value >= this.valeurs[11]){
            this.scieSpinner1.setValue((int) scieSpinner1.getValue() + 1);
            this.scieSpinner2.setValue(0);
            this.valeurs[10] = 0;
        }
        updateComposante();
    }
    private void scieSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[10]){ // 4/4
            this.valeurs[11] =  value;
        }
        else if (value <= this.valeurs[10]){
            this.scieSpinner3.setValue(this.valeurs[11]);
        }
        updateComposante();
    }

    private void rayonSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[12] =  value;
        updateComposante();
    }
    private void rayonSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[14]){
            this.valeurs[13] =  value;
        }
        else if (value >= this.valeurs[14]){
            this.rayonSpinner1.setValue((int) rayonSpinner1.getValue() + 1);
            this.rayonSpinner2.setValue(0);
            this.valeurs[13] = 0;
        }
        updateComposante();
    }
    private void rayonSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[13]){ // 4/4
            this.valeurs[14] =  value;
        }
        else if (value <= this.valeurs[13]){
            this.rayonSpinner3.setValue(this.valeurs[14]);
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


    private void poutreSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[3] = pouces.getPouces();
        valeurs[4] = pouces.getNumerateur();
        valeurs[5] = pouces.getDenominateur();
        updateComposante();
    }

    private void plancherSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[6] = pouces.getPouces();
        valeurs[7] = pouces.getNumerateur();
        valeurs[8] = pouces.getDenominateur();
        updateComposante();
    }

    private void scieSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[9] = pouces.getPouces();
        valeurs[10] = pouces.getNumerateur();
        valeurs[11] = pouces.getDenominateur();
        updateComposante();
    }

    private void rayonSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[12] = pouces.getPouces();
        valeurs[13] = pouces.getNumerateur();
        valeurs[14] = pouces.getDenominateur();
        updateComposante();
    }

}
