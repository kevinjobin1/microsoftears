package ca.ulaval.glo2004;

import ca.ulaval.glo2004.domain.composante.MurBrute;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import junit.framework.TestCase;
import org.junit.Test;

public class TestComposante extends TestCase {
    FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
    RoulotteController parent = new RoulotteController(fenetrePrincipale);
    MurBrute mur= new MurBrute(parent);

    @Test
    public void testToString(){
        System.out.println(mur.toString());
        assertTrue(mur.toString().equals("Mur brute"));
    }
}
