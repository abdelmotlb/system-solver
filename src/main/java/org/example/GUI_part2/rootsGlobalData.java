package org.example.GUI_part2;

import org.example.GUI.GlobalFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static org.example.GUI.GlobalFrame.Logic;
import static org.example.GUI.GlobalFrame.usedColor;
import static org.example.GUI_part2.methodToSolve.wantedMethod;

public class rootsGlobalData implements ActionListener {

    private JLabel equationLab;
    private JTextField equationTF;
    private JLabel precisionLab;
    private JTextField precisionTF;
    private JLabel relativeErrorLab;
    private JTextField relativeErrorTF;
    private JLabel maxIterationsLab;
    private JTextField maxIterationsTF;
    private JLabel initialGuessLab;
    private JTextField[] initialGuessTF;
    private JButton showSolutionBut;

    // got data
    public static String Function;
    public static int Precision;
    public static double relativeError;
    public static int maxIterations;
    public static double[] initialGuesses;

    // edit page
    private JButton tempBut;

    public rootsGlobalData() {
        renderMethod();
    }

    public void renderMethod(){

        // update border
        Logic.setBorder(new EmptyBorder(750, 1350, 0, 0));

        // equation label
        {
            equationLab = new JLabel();
            equationLab.setBounds(50, 50, 450, 50);
            equationLab.setFont(new Font("Times New Roman", Font.ITALIC, 35));
            equationLab.setForeground(usedColor);
            if( wantedMethod == "Fixed Point" )
                equationLab.setText("  → Enter the equation: g(x) = ");
            else
                equationLab.setText("  → Enter the equation: f(x) = ");
        }
        // end equation label

        // equation TF
        {
            equationTF = new JTextField();
            equationTF.setBounds(550, 50, 1300-500, 50);
            equationTF.setHorizontalAlignment(JTextField.CENTER);
            equationTF.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        }
        // end equation TF

        // precision label
        {
            precisionLab = new JLabel();
            precisionLab.setBounds(50, 150, 450, 50);
            precisionLab.setText("  → Enter the precision used: ");
            precisionLab.setFont(new Font("Times New Roman", Font.ITALIC, 35));
            precisionLab.setForeground(usedColor);
        }
        // end precision label

        // precision TF
        {
            precisionTF = new JTextField();
            precisionTF.setBounds(550, 150, 1300-500, 50);
            precisionTF.setHorizontalAlignment(JTextField.CENTER);
            precisionTF.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        }
        // end precision TF

        // relative error label
        {
            relativeErrorLab = new JLabel();
            relativeErrorLab.setBounds(50, 250, 450, 50);
            relativeErrorLab.setText("  → Enter the relative error: ");
            relativeErrorLab.setFont(new Font("Times New Roman", Font.ITALIC, 35));
            relativeErrorLab.setForeground(usedColor);
        }
        // end relative error label

        // relative error TF
        {
            relativeErrorTF = new JTextField();
            relativeErrorTF.setBounds(550, 250, 1300-500, 50);
            relativeErrorTF.setHorizontalAlignment(JTextField.CENTER);
            relativeErrorTF.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        }
        // end relative error TF

        // max iterations label
        {
            maxIterationsLab = new JLabel();
            maxIterationsLab.setBounds(50, 350, 450, 50);
            maxIterationsLab.setText("  → Enter max iterations: ");
            maxIterationsLab.setFont(new Font("Times New Roman", Font.ITALIC, 35));
            maxIterationsLab.setForeground(usedColor);
        }
        // end max iterations label

        // max iterations TF
        {
            maxIterationsTF = new JTextField();
            maxIterationsTF.setBounds(550, 350, 1300-500, 50);
            maxIterationsTF.setHorizontalAlignment(JTextField.CENTER);
            maxIterationsTF.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        }
        // end max iterations TF

        // initial guess label
        {
            initialGuessLab = new JLabel();
            initialGuessLab.setBounds(50, 450, 450, 50);
            initialGuessLab.setText("  → Enter initial guess: ");
            initialGuessLab.setFont(new Font("Times New Roman", Font.ITALIC, 35));
            initialGuessLab.setForeground(usedColor);
        }
        // end initial guess label

        // initial guess TF
        if( wantedMethod == "Fixed Point" || wantedMethod == "Newton-Raphson" ){
            initialGuessTF = new JTextField[1];
            initialGuessTF[0] = new JTextField();
            initialGuessTF[0].setBounds(550, 450, 1300-500, 50);
            initialGuessTF[0].setHorizontalAlignment(JTextField.CENTER);
            initialGuessTF[0].setFont(new Font("Times New Roman", Font.ITALIC, 35));
            Logic.add( initialGuessTF[0] );
        }
        else {
            initialGuessTF = new JTextField[2];
            for(int i = 0 ; i < 2; i++){
                initialGuessTF[i] = new JTextField();
                initialGuessTF[i].setBounds(550 + 450*i, 450, 350, 50);
                initialGuessTF[i].setHorizontalAlignment(JTextField.CENTER);
                initialGuessTF[i].setFont(new Font("Times New Roman", Font.ITALIC, 35));
                Logic.add( initialGuessTF[i] );
            }
        }
        // end initial guess TF

        // show solution button
        {
            showSolutionBut = new JButton("Show the Solution");
            showSolutionBut.setBounds(600, 700, 300, 50);
            showSolutionBut.setBackground(GlobalFrame.secUsedColor);
            showSolutionBut.setForeground(new Color(0xFFFFFF));
            showSolutionBut.setFont( new Font("Times New Roman", Font.BOLD, 30) );
            showSolutionBut.addActionListener(this);
        }
        // end show solution button



        Logic.add(equationLab);
        Logic.add(equationTF);
        Logic.add(precisionLab);
        Logic.add(precisionTF);
        Logic.add(relativeErrorLab);
        Logic.add(relativeErrorTF);
        Logic.add(maxIterationsLab);
        Logic.add(maxIterationsTF);
        Logic.add(initialGuessLab);
        Logic.add(showSolutionBut);
        System.out.println( "in render labels" );

        editButtonsDisplay();

    }

    public void editButtonsDisplay(){
        tempBut = new JButton();
        tempBut.setBounds(1600, 900, 0, 1);
        tempBut.setBackground(GlobalFrame.background);
        tempBut.setBorder(null);
        tempBut.setEnabled(false);
        Logic.add(tempBut);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        equationLab.setVisible(false);
        equationTF.setVisible(false);
        precisionLab.setVisible(false);
        precisionTF.setVisible(false);
        relativeErrorLab.setVisible(false);
        relativeErrorTF.setVisible(false);
        maxIterationsLab.setVisible(false);
        maxIterationsTF.setVisible(false);
        initialGuessLab.setVisible(false);
        for(JTextField TF: initialGuessTF){ TF.setVisible(false); }
        showSolutionBut.setVisible(false);

        if( e.getSource() == showSolutionBut ){
            // get function
            Function = equationTF.getText();

            // get precision
            try{ Precision = parseInt( precisionTF.getText() ); }
            catch (Exception ea) { Precision = 10; }

            // get relative error
            try{ relativeError = parseDouble( relativeErrorTF.getText() ); }
            catch (Exception ea) { relativeError = 10; }

            // get max iterations
            try{ maxIterations = parseInt( maxIterationsTF.getText() ); }
            catch (Exception ea) { maxIterations = 10; }

            // get first initial guess
            initialGuesses = new double[2];
            try{ initialGuesses[0] = parseDouble( initialGuessTF[0].getText() ); }
            catch (Exception ea) { initialGuesses[0] = 1; }

            // get second initial guess if exists
            if( wantedMethod != "Fixed Point" && wantedMethod != "Newton-Raphson" ) {
                try {
                    initialGuesses[1] = parseDouble(initialGuessTF[1].getText());
                } catch (Exception ea) {
                    initialGuesses[1] = 2;
                }
            }

            System.out.println(Function);
            System.out.println(Precision);
            System.out.println(relativeError);
            System.out.println(maxIterations);
            System.out.println(Arrays.toString(initialGuesses));

            new showAnswer();

        }
    }
}
