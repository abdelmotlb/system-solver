package org.example.GUI_part2;

import org.example.GUI.GlobalFrame;
import org.example.GUI.MyButton;
import org.example.GUI_part1.wayToSolve;
import org.example.logic_partA.Jacobi;
import org.example.logic_partB.Newton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import static org.example.GUI.GlobalFrame.Logic;
import static org.example.GUI.GlobalFrame.usedColor;
import static org.example.GUI_part2.methodToSolve.wantedMethod;
import static org.example.GUI_part2.rootsGlobalData.*;

public class showAnswer implements ActionListener {
    // data
    private double outputX;
    private double gotTime = 0.004215;

    // display elements
    private JLabel methodUsedLab, solutionLab, timeComplexityLab;
    private MyButton methodUsedBut, solutionBut, timeComplexityBut;
    private MyButton plotBut, showStepsBut, anotherMethodBut, newProblemBut;

    // edit page
    private JButton tempBut;

    public showAnswer(){

        getSolution();
        renderSolution();

    }



    public void getSolution(){
        if( wantedMethod == "Bisection" ){
            // wait friends
        } else if( wantedMethod == "False-Position" ){
            // wait friends
        } else if( wantedMethod == "Fixed Point" ){
            // wait friends
        } else if ( wantedMethod == "Newton-Raphson" ) {
            Newton newtonObj = new Newton(initialGuesses[0], Function, maxIterations, relativeError, Precision);
            if( !newtonObj.isValid() ){ System.out.println( "is not valid" ); }
            ArrayList<double[]> ans = newtonObj.solve();
            System.out.println(Arrays.toString( ans.get( ans.size() - 1 ) ));
            outputX = ans.get( ans.size() - 1 )[0];
        } else if ( wantedMethod == "Secant Method" ) {
            // wait friends
        }
    }

    public void renderSolution() {

        // update border
        Logic.setBorder(new EmptyBorder(750, 1350, 0, 0));

        // method used label
        methodUsedLab = new JLabel(" Used Method ");
        methodUsedLab.setBounds(400, 100, 600, 50); ////
        methodUsedLab.setHorizontalAlignment(JLabel.CENTER);
        methodUsedLab.setForeground(Color.white);
        methodUsedLab.setFont( new Font("Times New Roman", Font.ITALIC, 35) );
        Logic.add(methodUsedLab);

        // method used button
        methodUsedBut = new MyButton( wantedMethod );
        methodUsedBut.setBounds(400, 170, 600, 50);
        methodUsedBut.setEnabled(false);
        displayAnswerColor(methodUsedBut, 30);

        // solution label
        solutionLab = new JLabel();
        solutionLab.setBounds(400, 240, 600, 50);
        solutionLab.setText(" solution ");
        solutionLab.setFont(new Font("Times New Roman", Font.ITALIC, 35));
        solutionLab.setForeground(usedColor);
        solutionLab.setHorizontalAlignment(JLabel.CENTER);
        Logic.add(solutionLab);

        // solution button
        solutionBut = new MyButton( Double.toString(outputX) );
        solutionBut.setBounds(400, 310, 600, 50);
        solutionBut.setEnabled(false);
        displayAnswerColor(solutionBut, 30);

        // time complexity label
        timeComplexityLab = new JLabel();
        timeComplexityLab.setBounds(400, 380, 600, 50);
        timeComplexityLab.setText("  → Enter the relative error: ");
        timeComplexityLab.setFont(new Font("Times New Roman", Font.ITALIC, 35));
        timeComplexityLab.setForeground(usedColor);
        timeComplexityLab.setHorizontalAlignment(JLabel.CENTER);
        Logic.add(timeComplexityLab);

        // time complexity button
        timeComplexityBut = new MyButton( Double.toString(gotTime) + " in ms" );
        timeComplexityBut.setBounds(400, 450, 600, 50);
        timeComplexityBut.setEnabled(false);
        displayAnswerColor(timeComplexityBut, 30);

        // plot button
        plotBut = new MyButton("plot function");
        plotBut.setBounds(200, 520, 200, 200);
        plotBut.addActionListener(this);
        ButtonDisplay(plotBut);

        // steps button
        showStepsBut = new MyButton("show steps");
        showStepsBut.setBounds(450, 520, 200, 200);
        showStepsBut.addActionListener(this);
        ButtonDisplay(showStepsBut);

        // new method button
        anotherMethodBut = new MyButton("another Method");
        anotherMethodBut.setBounds(700, 520, 200, 200);
        anotherMethodBut.addActionListener(this);
        ButtonDisplay(anotherMethodBut);

        // new problem button
        newProblemBut = new MyButton("new problem");
        newProblemBut.setBounds(950, 520, 200, 200);
        newProblemBut.addActionListener(this);
        ButtonDisplay(newProblemBut);

        editButtonsDisplay();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        methodUsedLab.setVisible(false);
        methodUsedBut.setVisible(false);
        solutionLab.setVisible(false);
        solutionBut.setVisible(false);
        timeComplexityLab.setVisible(false);
        timeComplexityBut.setVisible(false);
        plotBut.setVisible(false);
        showStepsBut.setVisible(false);
        anotherMethodBut.setVisible(false);
        newProblemBut.setVisible(false);

        if( e.getSource() == plotBut ){
            // wait plot python.
        } else if( e.getSource() == showStepsBut ){
            // wait 26/12 implementation.
        } else if( e.getSource() == anotherMethodBut ) {
            new methodToSolve();
        } else if( e.getSource() == newProblemBut ) {
            new rootsGlobalData();
        }
    }

    public void displayAnswerColor(MyButton but, int size){
        but.setFont( new Font("Times New Roman", Font.PLAIN, 30) );
        but.setRadius(40);
        but.setBorder(null);
        but.setForeground(new Color(255, 255, 255));
        but.setBackground(GlobalFrame.secUsedColor);
        but.setColorClick(GlobalFrame.secUsedColor);
        but.setColor(new Color(255, 255, 255));
        but.setColorOver(GlobalFrame.secUsedColor);
        Logic.add(but);
    }

    private void ButtonDisplay(MyButton but){
        but.setRadius(200);
        but.setForeground(Color.white);
        but.setBackground(GlobalFrame.secUsedColor);
        but.setColorClick(GlobalFrame.secUsedColor);
        but.setColor(GlobalFrame.secUsedColor);
        but.setColorOver(GlobalFrame.background);
        but.setFont(new Font("MV Boli", Font.ITALIC, 20));
        but.setBorder(null);
        Logic.add(but);
    }

    public void editButtonsDisplay(){
        tempBut = new JButton();
        tempBut.setBounds(1600, 900, 0, 1);
        tempBut.setBackground(GlobalFrame.background);
        tempBut.setBorder(null);
        tempBut.setEnabled(false);
        Logic.add(tempBut);
    }


}
