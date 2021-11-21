package ca.ulaval.glo2004.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoHayonMM extends PanelComposante {
    private JLabel epaisseurLabel,
            poutreLabel,
            plancherLabel,
            scieLabel,
            rayonLabel;
    JSpinner epaisseurSpinner,
            poutreSpinner,
            plancherSpinner,
            scieSpinner,
            rayonSpinner;
    int[] epaisseur,
            poutre,
            plancher,
            scie,
            rayon;


    @Override
    void initialiser() {
        this.epaisseurLabel = creerLabelAttributMM("Épaisseur : ", 0, 0);
        int posY = 0;
        this.epaisseurSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm ", 2, posY);

        this.poutreLabel = creerLabelAttributMM("Distance de la poutre : ", 0, 1);
        posY = 1;
        this.poutreSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm ", 2, posY);


        this.plancherLabel = creerLabelAttributMM("Distance du plancher : ", 0, 2);
        posY = 2;
        this.plancherSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm  ", 2, posY);


        this.scieLabel = creerLabelAttributMM("Distance du trait de scie : ", 0, 3);
        posY = 3;
        this.scieSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm  ", 2, posY);


        this.rayonLabel = creerLabelAttributMM("Rayon de l'arc du cercle : ", 0, 4);
        posY = 4;
        this.rayonSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm ", 2, posY);



        epaisseurSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                epaisseurSpinnerChangeListener(e);
            }
        });

        // ==== Poutre =======
        poutreSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                poutreSpinnerChangeListener(e);
            }
        });

        // ==== Plancher =======
        plancherSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                plancherSpinnerChangeListener(e);
            }
        });

        // ==== Scie =======
        scieSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                scieSpinnerChangeListener(e);
            }
        });

        // ==== Rayon =======
        rayonSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                rayonSpinnerChangeListener(e);
            }
        });
    }
    private void epaisseurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[0] = (int) spinner.getValue();
    }


    private void poutreSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.poutre[0] = (int) spinner.getValue();
    }

    private void plancherSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.plancher[0] = (int) spinner.getValue();
    }

    private void scieSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.scie[0] = (int) spinner.getValue();
    }

    private void rayonSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.rayon[0] = (int) spinner.getValue();
    }
}
