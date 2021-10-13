package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.PlanController;
import ca.ulaval.glo2004.domain.PlanController.ElementModes;
import ca.ulaval.glo2004.gui.afficheur.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame
{
    public PlanController controller;
    public ElementModes selectedElementCreationMode;
    private ApplicationMode actualMode;

    // Ces attributs servent à la gestion du déplacement.
    public Point actualMousePoint = new Point();
    public Point delta = new Point();
    private JRadioButton radioButton1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;

    public enum ApplicationMode {
        SELECT,ADD
    }

    public MainWindow()
    {
        controller = new PlanController();
        initComponents();
    }

    private void initComponents() {
        createElementButtonGroup = new ButtonGroup();
        mainPanel = new JPanel();
        buttonTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hayonButton = new javax.swing.JToggleButton();
        plancherButton = new javax.swing.JToggleButton();
        mainScrollPane = new JScrollPane();
        drawingPanel = new DrawingPanel(this);
        topMenuBar = new JMenuBar();
        fileMenu = new JMenu();
        openMenuItem = new JMenuItem();
        quitMenuItem = new JMenuItem();
        editMenu = new JMenu();

        createElementButtonGroup.add(hayonButton);
        createElementButtonGroup.add(plancherButton);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Microsoftears");

        mainPanel.setLayout(new BorderLayout());

        buttonTopPanel.setPreferredSize(new Dimension(400, 35));

        plancherButton.setText("Plancher");
        plancherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plancherButtonActionPerformed(e);
            }
        });
        buttonTopPanel.add(plancherButton);

        hayonButton.setText("Hayon");
        hayonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hayonButtonActionPerformed(e);
            }
        });
        buttonTopPanel.add(hayonButton);



        mainPanel.add(buttonTopPanel, BorderLayout.NORTH);

        drawingPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                drawingPanelMousePressed(e);
            }
        });

        drawingPanel.addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                drawingPanelMouseWheelMoved(e);
            }
        });

        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                drawingPanelMouseMoved(e);
            }
        });

        GroupLayout drawingPanelLayout = new GroupLayout(drawingPanel);
        drawingPanel.setLayout(drawingPanelLayout);
        drawingPanelLayout.setHorizontalGroup(
                drawingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 888, Short.MAX_VALUE)
        );
        drawingPanelLayout.setVerticalGroup(
                drawingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 303, Short.MAX_VALUE)
        );

        mainScrollPane.setViewportView(drawingPanel);

        mainPanel.add(mainScrollPane, BorderLayout.CENTER);

        fileMenu.setText("Fichier");

        openMenuItem.setText("Ouvrir");
        fileMenu.add(openMenuItem);

        quitMenuItem.setText("Quitter");
        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quitMenuItemActionPerformed(e);
            }
        });
        fileMenu.add(quitMenuItem);

        topMenuBar.add(fileMenu);

        editMenu.setText("Éditer");
        topMenuBar.add(editMenu);

        setJMenuBar(topMenuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void plancherButtonActionPerformed(ActionEvent e) {//GEN-FIRST:event_plancherButtonActionPerformed
        this.setMode(ElementModes.PLANCHER);
    }//GEN-LAST:event_plancherButtonActionPerformed

    private void quitMenuItemActionPerformed(ActionEvent e) {//GEN-FIRST:event_quitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitMenuItemActionPerformed

    private void hayonButtonActionPerformed(ActionEvent e) {//GEN-FIRST:event_hayonButtonActionPerformed
        this.setMode(ElementModes.HAYON);
    }//GEN-LAST:event_hayonButtonActionPerformed

    private void drawingPanelMousePressed(MouseEvent e) {//GEN-FIRST:event_drawingPanelMousePressed
        Point mousePoint = drawingPanel.getGridPosition(e.getPoint());
        System.out.format("GridPoint: (%f, %f)", mousePoint.getX(), mousePoint.getY());
        System.out.format("MousePoint: (%f, %f)", e.getPoint().getX(), e.getPoint().getY());
        ElementModes actualMode = this.selectedElementCreationMode;
        this.controller.addElement(actualMode,mousePoint);
        drawingPanel.repaint();
    }//GEN-LAST:event_drawingPanelMousePressed

    private void drawingPanelMouseMoved(MouseEvent e) {//GEN-FIRST:event_drawingPanelMouseMoved
    }//GEN-LAST:event_drawingPanelMouseMoved

    private void drawingPanelMouseWheelMoved(MouseWheelEvent e) {//GEN-FIRST:event_drawingPanelMouseWheelMoved
        int wheelRotation = e.getWheelRotation();
        Point mousePoint = drawingPanel.getGridPosition(e.getPoint());
        System.out.format("WheelRotation: %d", wheelRotation);
        drawingPanel.setScale(wheelRotation);
        System.out.format("SetCenter: (%f, %f)", mousePoint.getX(), mousePoint.getY());
        // drawingPanel.setCenter(mousePoint);
        drawingPanel.repaint();
    }//GEN-LAST:event_drawingPanelMouseWheelMoved

    public void setMode(ElementModes newMode) {
        this.selectedElementCreationMode = newMode;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel buttonTopPanel;
    private ButtonGroup createElementButtonGroup;
    private DrawingPanel drawingPanel;
    private JMenu editMenu;
    private JMenu fileMenu;
    private JPanel mainPanel;
    private JScrollPane mainScrollPane;
    private JMenuItem openMenuItem;
    private JToggleButton hayonButton;
    private JToggleButton plancherButton;
    private JMenuItem quitMenuItem;
    private JMenuBar topMenuBar;

}

