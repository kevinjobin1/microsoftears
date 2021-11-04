package ca.ulaval.glo2004;

import ca.ulaval.glo2004.domain.MurBrute;
import ca.ulaval.glo2004.domain.MurProfile;
import ca.ulaval.glo2004.domain.RoulotteController;
import junit.framework.TestCase;
import org.junit.Test;

public class TestComposante extends TestCase {
    RoulotteController parent = new RoulotteController();
    MurBrute mur= new MurBrute(parent);

    @Test
    public void testToString(){
        System.out.println(mur.toString());
        assertTrue(mur.toString().equals("Mur brute"));
    }
}
