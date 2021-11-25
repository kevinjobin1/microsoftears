package ca.ulaval.glo2004;

import junit.framework.*;
import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import ca.ulaval.glo2004.utilitaires.*;
import ca.ulaval.glo2004.gui.*;
import ca.ulaval.glo2004.domain.*;


/**
 * Exemple de test unitaire utilisant JUnit et Google Truth. Vous n'avez pas a faire des tests dans le cadre de votre
 * projet. Par contre, il peut s'averer utile de faire quelques tests pour certaines method pour s'assurer qu'elle retourne
 * bien ce qu'elle devrait retourner
 */

public class TestPointPouce extends TestCase {
    PointPouce p1;
    PointPouce p2;
    PointPouce p3;


    // On assigne les valeurs qu'on veut tester plusieurs fois
    protected void setUp(){
       this.p1 = new PointPouce(new Pouce(0,0,1),
                                new Pouce(0,0,1));
       this.p2 = new PointPouce(new Pouce(2,0,1),
                                new Pouce(4,0,1));
       this.p3 = new PointPouce(new Pouce(6,2,3),
                                new Pouce(1,0,1));
    }

    @Test
    public void testTranslate(){
        print("------- TestTranslate --------");
        PointPouce resultat = p1.translate(new Pouce(2,0,1), new Pouce(4,0,1));
        assertThat(resultat.equals(p2));
        print("Point a: " + p1 + " Translation: (" + p2.getX() + ", " + p2.getY() + ") Point translate = " + resultat);
    }


    public static void print(String string){
        System.out.println(string);
    }


}
