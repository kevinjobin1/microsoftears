package ca.ulaval.glo2004;

import ca.ulaval.glo2004.utilitaires.Pouce;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

import static com.google.common.truth.Truth.assertThat;

/**********************************************
 *  Fixture pour la classe de test de Pouce
 ***********************************************/

public class TestPouce extends TestCase {

        protected int pouces, numerateur, denominateur;
        protected Pouce testPouce1;
        protected Pouce testPouce2;
        protected Pouce testPouce3;
        protected Pouce resultatAdd;
        protected Pouce resultatDiff;


        // On assigne les valeurs qu'on veut tester plusieurs fois
        protected void setUp(){
            this.testPouce1 = new Pouce(3,1,2);
            this.testPouce2 = new Pouce(4,2,3);
            this.testPouce3 = new Pouce(1,3,4);
            this.resultatAdd = new Pouce(8,1,6);
            this.resultatDiff = new Pouce(1, 1, 6);
        }

    @Test
    public void testSimplifierFraction(){
        print("---- testSimplifierFraction ---------");
        print("500/1000 = " + Pouce.simplifierFraction(500,1000)[0] + "/" + Pouce.simplifierFraction(500,1000)[1]);
        int[] tableau = new int[]{1,2};
        int[] tableau2 = Pouce.simplifierFraction(500,1000);
        assertThat(tableau[0] == tableau2[0]).isTrue();
        assertThat(tableau[1] == tableau2[1]).isTrue();

        print("3/4 = " + Pouce.simplifierFraction(3,4)[0] + "/" + Pouce.simplifierFraction(3,4)[1]);
        tableau = new int[]{3,4};
        tableau2 = Pouce.simplifierFraction(3,4);
        assertThat(tableau[0] == tableau2[0]).isTrue();
        assertThat(tableau[1] == tableau2[1]).isTrue();

        print("9/192 = " + Pouce.simplifierFraction(9,192)[0] + "/" + Pouce.simplifierFraction(9,192)[1]);
        tableau = new int[]{3,64};
        tableau2 = Pouce.simplifierFraction(9,192);
        assertThat(tableau[0] == tableau2[0]).isTrue();
        assertThat(tableau[1] == tableau2[1]).isTrue();

    }

    @Test
    // test methode multiplier dans Pouce
    public void testMultiplierPouce(){
        assertThat(testPouce1.multiplier(2).equals(new Pouce(7,0,1))).isTrue();
        assertThat(testPouce2.multiplier(-3).equals(new Pouce(-14,0,1))).isTrue();
        assertThat(testPouce2.multiplier(1.01).equals(new Pouce(4,23,32))).isTrue();
        assertThat(testPouce2.multiplier(0.9).equals(new Pouce(4,13,64))).isTrue();
    }
    @Test
    public void testDiviserPouce(){
        assertThat(testPouce1.diviser(2).equals(new Pouce(1,3,4))).isTrue();
        assertThat(testPouce2.diviser(-2).equals(new Pouce(-2,-21,64))).isTrue();
        //assertThat(testPouce1.diviser(testPouce3) == 2).isTrue();
    }
        @Test
        // test methode additionner dans Pouce
        public void testAddPouce(){
            print("---- testAddPouce -------");

            Pouce resultat = this.testPouce1.add(testPouce2);

            assertThat(this.resultatAdd.equals(resultat)).isTrue();

            print(testPouce1.toString() + " + " + testPouce2.toString() + " = " + resultat.toString());
        }

    @Test
    // test methode soustraire dans Pouce
    public void testDiffPouce(){
        print("---- testDiffPouce -------");

        Pouce resultat = this.testPouce1.diff(testPouce2);

        assertThat(this.resultatDiff.equals(resultat)).isTrue();

        print(testPouce2.toString() + " - " + testPouce1.toString() + " = " + resultat.toString());
    }

    @Test
    // test methode soustraire dans Pouce
    public void testCopiePouce(){
        print("---- testCopiePouce -------");

        Pouce resultat = this.testPouce1.copy();

        assertThat(resultat.equals(testPouce1)).isTrue();

        print(testPouce1.toString() + " = " + resultat);
    }

    @Test
    // test methode construire à partir d'une mesure double en pouces
    public void testConstructeurPouceDouble(){
        print("---- testConstructeurPouceDouble -------");
        double mesure = 0.984252; // 25 mm en pouces
        Pouce resultat = new Pouce(mesure);

        print("Mesure en pouce: " + resultat + "(" + resultat.toDouble() + "\")");
        print("Mesure en mm: " + resultat.getMilimetres());
    }

    @Test
    // test methode construire à partir d'une mesure en milimètres
    public void testConstructeurMilimetres(){
        print("---- testConstructeurMilimetres -------");
        int mesure = 25; // equivalent à (0.984375" ou 0-63/64")
        Pouce resultat = new Pouce(mesure);

        print("Mesure en pouce: " + resultat + "(" + resultat.toDouble() + "\")");
        print("Mesure en mm: " + resultat.getMilimetres());
    }

    public static void print(String string){
            System.out.println(string);
        }

}
