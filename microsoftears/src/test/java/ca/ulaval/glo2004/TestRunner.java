package ca.ulaval.glo2004;

import com.sun.net.httpserver.Authenticator;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.junit.Test;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestJUnit.class, TestPouce.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("====================================");
        System.out.println("Resultat : " + result.wasSuccessful());
    }

}
