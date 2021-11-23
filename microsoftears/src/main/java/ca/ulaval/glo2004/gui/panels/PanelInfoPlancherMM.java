package ca.ulaval.glo2004.gui.panels;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoPlancherMM extends PanelComposante{

    private JLabel epaisseurLabel,
            margeAvantLabel,
            margeArriereLabel;

    private JSpinner epaisseurSpinner,
            margeAvantSpinner,
            margeArriereSpinner;

    int[] epaisseur,
            margeAvant,
            margeArriere;

    @Override
    void initialiser() {

        this.epaisseurLabel = creerLabelAttributMM("Épaisseur : ", 0, 0);
        int posY = 0;
        this.epaisseurSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm ", 2, posY);

        this.margeAvantLabel = creerLabelAttributMM("Marge avant : ", 0, 1);
        posY = 1;
        this.margeAvantSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm ", 2, posY);

        this.margeArriereLabel = creerLabelAttributMM("Marge Arrière : ", 0, 2);
        posY = 2;
        this.margeArriereSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm  ", 2, posY);

        epaisseurSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                epaisseurSpinnerChangeListener(e);
            }
        });

        // ==== Poutre =======
        margeAvantSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margeAvantSpinnerChangeListener(e);
            }
        });

        // ==== Plancher =======
        margeArriereSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margeArriereSpinnerChangeListener(e);
            }
        });
    }
    private void epaisseurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[0] = (int) spinner.getValue();
    }

    private void margeAvantSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeArriere[0] = (int) spinner.getValue();
    }

    private void margeArriereSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeArriere[0] = (int) spinner.getValue();
    }
}
