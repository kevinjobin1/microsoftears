package ca.ulaval.glo2004;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import ca.ulaval.glo2004.gui.MainWindow;
import com.formdev.flatlaf.FlatDarculaLaf;

public class Main {

    public static void main(String[] args) {

        // FlatDarculaIntelliJ look (apparence du GUI importé d'une librarie externe)

        UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainWindow mainWindow = new MainWindow();
        mainWindow.setExtendedState(mainWindow.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        mainWindow.setVisible(true);
    }
}

