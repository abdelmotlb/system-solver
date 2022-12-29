package org.example.GUI_part2;

import org.example.GUI.GlobalFrame;
import org.example.GUI.MyButton;
import org.example.logic_partB.*;
import org.example.plot.PlotGraph;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.example.GUI.GlobalFrame.Logic;
import static org.example.GUI.GlobalFrame.usedColor;
import static org.example.GUI_part2.methodToSolve.wantedMethod;
import static org.example.GUI_part2.rootsGlobalData.*;

public class steps implements ActionListener {
    private ArrayList<double[]> arrayList;
    private JLabel[] labels;
    private JButton tempBut;
    private MyButton[][] DataCells;
    private MyButton[] plotButs;
    private int scaleX = 150, scaleY = 50;
    private int totalNumCols;

    public steps(ArrayList<double[]> arrayList) {
        this.arrayList = arrayList;

        // display edit
        Logic.setBorder(new EmptyBorder((50) * (arrayList.size() + 3), 1400, 0, 0));

        if (wantedMethod == "False-Position" || wantedMethod == "Bisection" || wantedMethod == "Secant Method") {
            totalNumCols = 8;
            scaleX = 150;
            scaleY = 50;
            labels = new JLabel[totalNumCols];
            sevenParameterLabels();
            iterationsValuesFn();
            plotButFun();
        } else if (wantedMethod == "Newton-Raphson") {
            totalNumCols = 6;
            scaleX = 200;
            scaleY = 50;
            labels = new JLabel[totalNumCols];
            fiveParameterLabels();
            iterationsValuesFn();
            plotButFun();
        } else {
            totalNumCols = 4;
            scaleX = 300;
            scaleY = 50;
            labels = new JLabel[totalNumCols];
            threeParameterLabels();
            iterationsValuesFn();
            plotButFun();
        }

        editButtonsDisplay();
    }

    private void sevenParameterLabels() {
        labels[0] = new JLabel();
        labels[1] = new JLabel();
        labels[2] = new JLabel();
        labels[3] = new JLabel();
        labels[4] = new JLabel();
        labels[5] = new JLabel();
        labels[6] = new JLabel();
        labels[7] = new JLabel();
        displayLabel(labels[0], "iter.", 0 * scaleX);
        displayLabel(labels[1], (wantedMethod == "Secant Method") ? "Xi-1" : "Xl", scaleX);
        displayLabel(labels[2], (wantedMethod == "Secant Method") ? "Xi" : "Xu", 2 * scaleX);
        displayLabel(labels[3], (wantedMethod == "Secant Method") ? "f(Xi-1)" : "Xr", 3 * scaleX);
        displayLabel(labels[4], (wantedMethod == "Secant Method") ? "f(Xi)" : "f(Xl)", 4 * scaleX);
        displayLabel(labels[5], (wantedMethod == "Secant Method") ? "Xi+1" : "f(Xu)", 5 * scaleX);
        displayLabel(labels[6], (wantedMethod == "Secant Method") ? "f(Xi+1)" : "f(Xr)", 6 * scaleX);
        displayLabel(labels[7], "ea", 7 * scaleX);
    }

    private void fiveParameterLabels() {
        labels[0] = new JLabel();
        labels[1] = new JLabel();
        labels[2] = new JLabel();
        labels[3] = new JLabel();
        labels[4] = new JLabel();
        labels[5] = new JLabel();
        displayLabel(labels[0], "iter.", 0 * scaleX);
        displayLabel(labels[1], "Xi", scaleX);
        displayLabel(labels[2], "f(Xi)", 2 * scaleX);
        displayLabel(labels[3], "f `(Xi)", 3 * scaleX);
        displayLabel(labels[4], "Xi+1", 4 * scaleX);
        displayLabel(labels[5], "ea", 5 * scaleX);
    }

    private void threeParameterLabels() {
        labels[0] = new JLabel();
        labels[1] = new JLabel();
        labels[2] = new JLabel();
        labels[3] = new JLabel();
        displayLabel(labels[0], "iter.", 0 * scaleX);
        displayLabel(labels[1], "Xi", scaleX);
        displayLabel(labels[2], "g(Xi)", 2 * scaleX);
        displayLabel(labels[3], "ea", 3 * scaleX);
    }

    private void displayLabel(JLabel label, String stringDisplayed, int xDist) {
        label.setBounds(50 + xDist, 0, scaleX, 50);
        label.setText(stringDisplayed);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.ITALIC, 35));
        label.setForeground(usedColor);
        Logic.add(label);
    }

    private void iterationsValuesFn() {
        DataCells = new MyButton[arrayList.size()][totalNumCols];
        for (int i = 0; i < totalNumCols; i++) {
            displayCol(i);
            System.out.println("in display Col");
        }
    }

    private void displayCol(int vectorDisplayedNum) {

        for (int i = 0; i < arrayList.size(); i++) {

            // creation with bounds
            DataCells[i][vectorDisplayedNum] = new MyButton();
            MyButton butObj = DataCells[i][vectorDisplayedNum];
            butObj.setBounds(50 + scaleX * vectorDisplayedNum, 50 + scaleY * i, scaleX, 50);
            butObj.setText(Double.toString(arrayList.get(i)[vectorDisplayedNum]));

            // colors and font
            butObj.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            butObj.setForeground(new Color(0xE3EA14));
            butObj.setBackground(GlobalFrame.secUsedColor);
            butObj.setColorClick(GlobalFrame.secUsedColor);
            butObj.setColor(new Color(119, 113, 75));
            butObj.setColorOver(GlobalFrame.secUsedColor);
            butObj.setBorderColor(new Color(0x6F7333));

            // display edits
            butObj.setHorizontalAlignment(JButton.CENTER);
            Logic.add(butObj);
        }
    }

    private void plotButFun() {
        plotButs = new MyButton[arrayList.size() + 1];

        int margin = 20;
        String plotText = "<HTML><U>|~</U></HTML>";

        for (int i = 0; i < arrayList.size() + 1; i++) {
            plotButs[i] = new MyButton();
            MyButton butObj = plotButs[i];
            butObj.setBounds(50 + margin + scaleX * totalNumCols, 50 + scaleY * i, 50, 50);
            butObj.setText(plotText);
            butObj.setRadius(50);
            butObj.setBorder(null);

            // colors
            butObj.setFont(new Font("Times New Roman", Font.BOLD, 25));
            yellowColorFn(butObj);

            // display edits
            butObj.setHorizontalAlignment(JButton.CENTER);
            butObj.addActionListener(this);
            if (i == arrayList.size()) {
                butObj.setText("←");
                butObj.setForeground(new Color(0x2A7AF1));
                butObj.setBackground(GlobalFrame.secUsedColor);
                butObj.setColorClick(GlobalFrame.secUsedColor);
                butObj.setColor(new Color(23, 55, 72));
                butObj.setColorOver(GlobalFrame.secUsedColor);
            }
            Logic.add(butObj);
        }

    }

    private void yellowColorFn(MyButton butObj) {
        butObj.setForeground(new Color(0xE3EA14));
        butObj.setBackground(GlobalFrame.secUsedColor);
        butObj.setColorClick(GlobalFrame.secUsedColor);
        butObj.setColor(new Color(119, 113, 75));
        butObj.setColorOver(GlobalFrame.secUsedColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (e.getSource() == plotButs[i]) {

                // create used obj
                PlotGraph plotObj = new PlotGraph();

                if (wantedMethod == "Bisection") {

                    plotObj.plotBisection(Function, arrayList.get(0)[1], arrayList.get(0)[2], arrayList.get(i)[1],
                            arrayList.get(i)[2]);

                } else if (wantedMethod == "False-Position") {
                    plotObj.plotFalsePosition(Function, arrayList.get(0)[1], arrayList.get(0)[2], arrayList.get(i)[1],
                            arrayList.get(i)[2]);

                } else if (wantedMethod == "Fixed Point") {
                    if (i != 0) {
                        plotObj.plotFixedPoint(Function, arrayList.get(arrayList.size() - 1)[2],
                                arrayList.get(i - 1)[1],
                                arrayList.get(i - 1)[1]);
                    } else {
                        ////// ************************** I need the initial guess
                        plotObj.plotFixedPoint(Function, arrayList.get(arrayList.size() - 1)[2],
                                0,
                                0);
                    }

                } else if (wantedMethod == "Newton-Raphson") {

                    plotObj.plotNewton(Function, arrayList.get(arrayList.size() - 1)[4], arrayList.get(i)[1]);

                } else if (wantedMethod == "Secant Method") {

                    plotObj.plotSecant(Function, arrayList.get(arrayList.size() - 1)[5], arrayList.get(i)[1],
                            arrayList.get(i)[2]);

                }
            }
        }

        if (e.getSource() == plotButs[arrayList.size()]) {
            for (MyButton[] but : DataCells) {
                for (MyButton nestedBut : but) {
                    nestedBut.setVisible(false);
                }
            }
            System.out.println(labels.length);
            int testCounter = 0;
            for (int i = 0; i < totalNumCols; i++) {
                labels[i].setVisible(false);
            }
            for (MyButton plotBut : plotButs) {
                plotBut.setVisible(false);
            }
            new methodToSolve();
        }
    }

    public void editButtonsDisplay() {
        tempBut = new JButton();
        tempBut.setBounds(1600, (50) * (arrayList.size() + 3), 0, 1);
        tempBut.setBackground(GlobalFrame.background);
        tempBut.setBorder(null);
        tempBut.setEnabled(false);
        Logic.add(tempBut);
    }

}
