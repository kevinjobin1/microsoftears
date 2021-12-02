package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.gui.barres.BarreOnglet;
import ca.ulaval.glo2004.utilitaires.Pouce;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoRessorts extends PanelComposante{
    private JSpinner poidsHayonSpinner;
    //todo: implementer les textFields
    private JTextField
            forceTextField,
            longueurExactExtensionTextField;

    public PanelInfoRessorts(BarreOnglet parent, IComposante composante) {
        super(parent, composante);
    }

    @Override
    void initialiser(IComposante composante) {
        creerLabelAttributMM(attributs[0] + " : ", 0);
        this.poidsHayonSpinner = new JSpinner();

        // ==== TOIT =======
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
    }
}
