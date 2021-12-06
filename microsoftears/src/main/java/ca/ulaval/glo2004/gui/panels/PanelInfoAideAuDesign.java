package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInfoAideAuDesign extends PanelComposante {
    private JSpinner largeurSpinner,
            longueurSpinner,
            centreXSpinner,
            centreYSpinner,
            largeurSpinner1,
            largeurSpinner2,
            largeurSpinner3,
            longueurSpinner1,
            longueurSpinner2,
            longueurSpinner3,
            centreXSpinner1,
            centreXSpinner2,
            centreXSpinner3,
            centreYSpinner1,
            centreYSpinner2,
            centreYSpinner3;
    private JButton addButton;

    public PanelInfoAideAuDesign(BarreOnglet parent, IComposante composante) {
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
                    this.longueurSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.longueurSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.longueurSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else if (i == 1) {
                    pouces = new Pouce(valeurs[3], valeurs[4], valeurs[5]);
                    this.largeurSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.largeurSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.largeurSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else if (i == 2) {
                    pouces = new Pouce(valeurs[6], valeurs[7], valeurs[8]);
                    this.centreXSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.centreXSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.centreXSpinner3 = creerSpinnerPouces(4, posY, pouces);
                } else {
                    pouces = new Pouce(valeurs[9], valeurs[10], valeurs[11]);
                    this.centreYSpinner1 = creerSpinnerPouces(0, posY, pouces);
                    this.centreYSpinner2 = creerSpinnerPouces(2, posY, pouces);
                    this.centreYSpinner3 = creerSpinnerPouces(4, posY, pouces);
                }
            }

            //=============================================================================//
            //                                                                             //
            //=========================EVENTS (ACTION LISTENERS)===========================//
            //                                                                             //
            //=============================================================================//


            //==============largeur=============//

            largeurSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    largeurSpinner1ChangeListener(e);
                }
            });

            largeurSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    largeurSpinner2ChangeListener(e);
                }
            });

            largeurSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    largeurSpinner3ChangeListener(e);
                }
            });

            //==============LONGUEUR=============//
            longueurSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    longueurSpinner1ChangeListener(e);
                }
            });

            longueurSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    longueurSpinner2ChangeListener(e);
                }
            });

            longueurSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    longueurSpinner3ChangeListener(e);
                }
            });

            //==============CENTRE X=============//
            centreXSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    centreXSpinner1ChangeListener(e);
                }
            });

            centreXSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    centreXSpinner2ChangeListener(e);
                }
            });

            centreXSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    centreXSpinner3ChangeListener(e);
                }
            });

            //==============CENTRE Y=============//
            centreYSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    centreYSpinner1ChangeListener(e);
                }
            });

            centreYSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    centreYSpinner2ChangeListener(e);
                }
            });

            centreYSpinner3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    centreYSpinner3ChangeListener(e);
                }
            });
        }
        else {
            Pouce pouces;
            for (int i = 0; i < getNbAttributs(); i++) {
                creerLabelAttributMM(attributs[i] + " : ", i);
                if (i == 0) {
                    pouces = new Pouce(valeurs[0], valeurs[1], valeurs[2]);
                    this.longueurSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else if (i == 1) {
                    pouces = new Pouce(valeurs[3], valeurs[4], valeurs[5]);
                    this.largeurSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else if (i == 2) {
                    pouces = new Pouce(valeurs[6], valeurs[7], valeurs[8]);
                    this.centreXSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                } else {
                    pouces = new Pouce(valeurs[9], valeurs[10], valeurs[11]);
                    this.centreYSpinner = creerSpinnerMM(i, pouces.getMilimetres());
                }
            }

            largeurSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    largeurSpinnerChangeListener(e);
                }
            });
            longueurSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    longueurSpinnerChangeListener(e);
                }
            });
            centreXSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    centreXSpinnerChangeListener(e);
                }
            });
            centreYSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    centreYSpinnerChangeListener(e);
                }
            });
        }
        addButton = creerButton( " + ", 1, 10);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creerAideActionPerformed(e);
            }
        });

    }
    private void largeurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[3] =  value;
        updateComposante();
    }
    private void largeurSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[5]){
            this.valeurs[4] =  value;
        }
        else if (value >= this.valeurs[5]){
            this.largeurSpinner1.setValue((int) largeurSpinner1.getValue() + 1);
            this.largeurSpinner2.setValue(0);
            this.valeurs[4] = 0;
        }
        updateComposante();
    }
    private void largeurSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[4]){ // 4/4
            this.valeurs[5] =  value;
        }
        else if (value <= this.valeurs[4]){
            this.largeurSpinner3.setValue(this.valeurs[5]);
        }
        updateComposante();
    }

    private void longueurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[0] =  value;
        updateComposante();
    }
    private void longueurSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[2]){
            this.valeurs[1] =  value;
        }
        else if (value >= this.valeurs[2]){
            this.longueurSpinner1.setValue((int) longueurSpinner1.getValue() + 1);
            this.longueurSpinner2.setValue(0);
            this.valeurs[1] = 0;
        }
        updateComposante();
    }
    private void longueurSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[1]){ // 4/4
            this.valeurs[2] =  value;
        }
        else if (value <= this.valeurs[1]){
            this.longueurSpinner3.setValue(this.valeurs[2]);
        }
        updateComposante();
    }

    private void centreXSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[6] =  value;
        updateComposante();
    }
    private void centreXSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[8]){
            this.valeurs[7] =  value;
        }
        else if (value >= this.valeurs[8]){
            this.centreXSpinner1.setValue((int) centreXSpinner1.getValue() + 1);
            this.centreXSpinner2.setValue(0);
            this.valeurs[7] = 0;
        }
        updateComposante();
    }
    private void centreXSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[7]){ // 4/4
            this.valeurs[8] =  value;
        }
        else if (value <= this.valeurs[7]){
            this.centreXSpinner3.setValue(this.valeurs[8]);
        }
        updateComposante();
    }

    private void centreYSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.valeurs[9] =  value;
        updateComposante();
    }
    private void centreYSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.valeurs[11]){
            this.valeurs[10] =  value;
        }
        else if (value >= this.valeurs[11]){
            this.centreYSpinner1.setValue((int) centreYSpinner1.getValue() + 1);
            this.centreYSpinner2.setValue(0);
            this.valeurs[10] = 0;
        }
        updateComposante();
    }
    private void centreYSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.valeurs[10]){ // 4/4
            this.valeurs[11] =  value;
        }
        else if (value <= this.valeurs[10]){
            this.centreYSpinner3.setValue(this.valeurs[11]);
        }
        updateComposante();
    }

    private void largeurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[3] = pouces.getPouces();
        valeurs[4] = pouces.getNumerateur();
        valeurs[5] = pouces.getDenominateur();
        updateComposante();
    }
    private void longueurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[0] = pouces.getPouces();
        valeurs[1] = pouces.getNumerateur();
        valeurs[2] = pouces.getDenominateur();
        updateComposante();
    }
    private void centreXSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[6] = pouces.getPouces();
        valeurs[7] = pouces.getNumerateur();
        valeurs[8] = pouces.getDenominateur();
        updateComposante();
    }
    private void centreYSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Pouce pouces = new Pouce((double) spinner.getValue(), true);
        valeurs[9] = pouces.getPouces();
        valeurs[10] = pouces.getNumerateur();
        valeurs[11] = pouces.getDenominateur();
        updateComposante();
    }
    private JButton creerButton(String string, int x, int y){
        GridBagConstraints c = new GridBagConstraints();
        JButton bouton = new JButton(string);
        c.gridx = x;
        c.gridy= y;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        this.add(bouton, c);
        return bouton;
    }

    public void creerAideActionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource();
        parent.parent.controller.addAideDesign();
    }
}
