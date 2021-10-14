package ca.ulaval.glo2004;

import javax.swing.*;

import ca.ulaval.glo2004.gui.FenetrePrincipale;
import com.formdev.flatlaf.FlatDarculaLaf;

public class Microsoftears {

    public static void main(String[] args) {

        // FlatDarculaIntelliJ look (apparence du GUI importé d'une librarie externe)

        UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
        fenetrePrincipale.setTitle("Microsoftears");
        fenetrePrincipale.setExtendedState(fenetrePrincipale.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        fenetrePrincipale.setVisible(true);
    }
}

