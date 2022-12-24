package org.example.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static java.lang.Double.parseDouble;
import static org.example.GUI.GlobalFrame.Logic;

public class GridMatrix implements ActionListener {

    private JLabel[][] variablesName;
    private JTextField[][] pocket;
    private double cofficients[][] ;
    private double results[];
    private JButton GotoType;
    private int NumofEquations;
    JButton tempBut;

    GridMatrix(int NumofEquations) {
        this.NumofEquations = NumofEquations;
        EquationsLayout();
    }

    public void EquationsLayout() {

        // int startX = (1400 - 120 * (NumofEquations + 1)) / 2, startY = 10 * (10 -
        // NumofEquations + 1);
        int startX = 100, startY = 50;
        variablesName = new JLabel[NumofEquations][NumofEquations];
        pocket = new JTextField[NumofEquations][NumofEquations + 1];
        Logic.setBorder(new EmptyBorder(100 + NumofEquations * 60,150 + (NumofEquations*2+2)*60,0, 0));

        // distance added reversely relational with Number of equations
        int distanceAdded = 60;

        // labels
        for (int i = 0; i < NumofEquations; i++) {
            for (int j = 0; j < NumofEquations; j++) {

                String s = (j != NumofEquations - 1) ? String.format(" x%d + ", j + 1)
                        : String.format(" x%d = ", j + 1);
                variablesName[i][j] = new JLabel(s);
                variablesName[i][j].setBounds(startX + (2 * j + 2) * distanceAdded, startY + i * distanceAdded, 60, distanceAdded);
                variablesName[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 20));
                variablesName[i][j].setHorizontalAlignment(JLabel.CENTER);
                variablesName[i][j].setForeground(Color.white);
                Logic.add(variablesName[i][j]);
            }
        }

        for (int i = 0; i < NumofEquations; i++) {
            for (int j = 0; j <= NumofEquations; j++) {
                pocket[i][j] = new JTextField();
                pocket[i][j].setBounds(startX + j * distanceAdded + distanceAdded * (j + 1), startY + i * distanceAdded, 50, distanceAdded - 20);
                pocket[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 30));
                pocket[i][j].setHorizontalAlignment(JLabel.CENTER);
                Logic.add(pocket[i][j]);
            }
        }

        GotoType = new JButton("GotoType");
        GotoType.setBounds(50 + (NumofEquations*2+1)*60,50 + NumofEquations * 60, 150, 50);
        GotoType.setBackground(GlobalFrame.secUsedColor);
        GotoType.setForeground(new Color(0xFFFFFF));
        GotoType.setFont(new Font("Arial", Font.BOLD, 20));
        GotoType.addActionListener(this);
        tempBut = new JButton();
        tempBut.setBounds(1600, 800, 0, 1);
        tempBut.setBackground(GlobalFrame.background);
        tempBut.setBorder(null);
        tempBut.setEnabled(false);
        Logic.add(GotoType);
        Logic.add(tempBut);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GotoType) {

            // intialize the user arrays
            cofficients = new double[NumofEquations][NumofEquations];
            results = new double[NumofEquations];

            // store the values got from user and change visibility.
            for (int i = 0; i < NumofEquations; i++) {
                for (int j = 0; j <= NumofEquations; j++) {
                    if (j != NumofEquations) {

                        try { cofficients[i][j] = parseDouble(pocket[i][j].getText()); }
                        catch(NumberFormatException ea) { cofficients[i][j] = 0; } //
                        variablesName[i][j].setVisible(false);
                        pocket[i][j].setVisible(false);

                    } else {

                        try { results[i] = parseDouble(pocket[i][j].getText()); }
                        catch(NumberFormatException ea) { results[i] = 0; } //
                        pocket[i][j].setVisible(false);

                    }
                }
            }
            GotoType.setVisible(false);
            tempBut.setVisible(false);
            new wayToSolve(cofficients, results);
        }
    }
}
