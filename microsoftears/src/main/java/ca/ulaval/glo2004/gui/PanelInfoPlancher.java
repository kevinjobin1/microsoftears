package ca.ulaval.glo2004.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoPlancher extends PanelComposante {

    private JLabel epaisseurLabel,
            margeAvantLabel,
            margeArriereLabel;

    private JSpinner epaisseurSpinner1,
            epaisseurSpinner2,
            epaisseurSpinner3,
            margeAvantSpinner1,
            margeAvantSpinner2,
            margeAvantSpinner3,
            margeArriereSpinner1,
            margeArriereSpinner2,
            margeArriereSpinner3;

    int[] epaisseur, margeAvant, margeArriere;

    public PanelInfoPlancher(){
        super();
        this.initialiser();
    }

    @Override
    void initialiser() {

        this.epaisseur = new int[3];
        this.margeAvant = new int[3];
        this.margeArriere = new int[3];

        this.epaisseurLabel = creerLabelAttribut("Épaisseur : ", 0,0);
        int posY = 2*getNbAttribut() -1;
        this.epaisseurSpinner1 = creerSpinnerPouces(0, posY);
        creerLabelSymbole( " \" ",1,posY);
        this.epaisseurSpinner2 =  creerSpinnerPouces(2, posY);
        creerLabelSymbole( " / ",3,posY);
        this.epaisseurSpinner3 = creerSpinnerPouces(4, posY);

        this.margeAvantLabel = creerLabelAttribut("Marge Avant : ", 0, 2);
        posY = 2*getNbAttribut() -1;
        this.margeAvantSpinner1 = creerSpinnerPouces(0, posY);
        creerLabelSymbole( " \" ",1,posY);
        this.margeAvantSpinner2 =  creerSpinnerPouces(2, posY);
        creerLabelSymbole( " / ",3,posY);
        this.margeAvantSpinner3 = creerSpinnerPouces(4, posY);

        this.margeArriereLabel = creerLabelAttribut("Marge Arrière : ", 0,4 );
        posY = 2*getNbAttribut() -1;
        this.margeArriereSpinner1 = creerSpinnerPouces(0, posY);
        creerLabelSymbole( " \" ",1,posY);
        this.margeArriereSpinner2 =  creerSpinnerPouces(2, posY);
        creerLabelSymbole( " / ",3,posY);
        this.margeArriereSpinner3 = creerSpinnerPouces(4, posY);



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

        //==============MARGE AVANT=============//
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

        //==============MARGE ARREIRE=============//
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
    }

    private void epaisseurSpinner1ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[0] = (int) spinner.getValue();
    }
    private void epaisseurSpinner2ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[1] = (int) spinner.getValue();
    }
    private void epaisseurSpinner3ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[2] = (int) spinner.getValue();
    }

    private void margeAvantSpinner1ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeAvant[0] = (int) spinner.getValue();
    }
    private void margeAvantSpinner2ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeAvant[1] = (int) spinner.getValue();
    }
    private void margeAvantSpinner3ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeAvant[2] = (int) spinner.getValue();
    }

    private void margeArriereSpinner1ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeArriere[0] = (int) spinner.getValue();
    }
    private void margeArriereSpinner2ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeArriere[1] = (int) spinner.getValue();
    }
    private void margeArriereSpinner3ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeArriere[2] = (int) spinner.getValue();
    }
}
