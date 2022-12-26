package org.example.GUI_part1;

import org.example.GUI.GlobalFrame;
import org.example.GUI.MyButton;
import org.example.GUI_part1.wayToSolve;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.GUI.GlobalFrame.Logic;


public class DisplayAnswer implements ActionListener {

    private double[] SolutionArr;

    // used references
    private JLabel SolutionDomain;
    private MyButton[] solutionValues;
    private JLabel labTimeConsumed;
    private MyButton timeConsumed;
    private MyButton goToNewProblem;
    private MyButton diffTypeBut;
    private JButton tempBut;

    public DisplayAnswer(double[] SolutionArr){
        this.SolutionArr = SolutionArr;
        arrRepresentation();
    }

    public void arrRepresentation(){

        // variable
        int startY = 100;
        int NumOfEquations = SolutionArr.length;

        // edit border
        Logic.setBorder(new EmptyBorder(100 * ( NumOfEquations +1) , 2 * 60 * ( NumOfEquations +2) ,0, 0));

        {
            // label above values
            SolutionDomain = new JLabel("↓ Solution Domain ↓");
            SolutionDomain.setBounds(20, 20, 400, 60); ////
            SolutionDomain.setVerticalAlignment(JLabel.CENTER);
            SolutionDomain.setForeground(Color.white);
            SolutionDomain.setFont( new Font("Times New Roman", Font.PLAIN, 40) );
            Logic.add(SolutionDomain);
        }

        {
            solutionValues = new MyButton[ NumOfEquations ];
            for(int i = 0 ; i < NumOfEquations ; i++){
                solutionValues[i] = new MyButton( "x" + String.format("%d", i+1) + " = " + SolutionArr[i] );
                solutionValues[i].setBounds(100 * (i+1) - 50, startY + 60 * (i+1) - 50, 450, 50);
                solutionValues[i].setEnabled(false);
                displayAnswerColor(solutionValues[i], 30);
            }
        }

        {
            labTimeConsumed = new JLabel("↓ time complexity ↓");
            labTimeConsumed.setBounds(20, 500, 400, 60); ////
            labTimeConsumed.setVerticalAlignment(JLabel.CENTER);
            labTimeConsumed.setForeground(Color.white);
            labTimeConsumed.setFont( new Font("Times New Roman", Font.PLAIN, 40) );
            Logic.add(labTimeConsumed);
        }

        {
            timeConsumed = new MyButton( String.format("%f in ms", wayToSolve.gotTime / 1e6) );
            timeConsumed.setBounds(20, 600, 300, 50);
            timeConsumed.setEnabled(false);
            displayAnswerColor(timeConsumed, 30);
        }

        {
            goToNewProblem = new MyButton("solve New problem!");
            goToNewProblem.setBounds(950, 200, 400, 80);
            displayAnswerColor(goToNewProblem, 60);
            goToNewProblem.setColor(GlobalFrame.secUsedColor);
            goToNewProblem.setColorOver(GlobalFrame.background);
            goToNewProblem.addActionListener(this);
            Logic.add(goToNewProblem);
        }

        {
            // different type
            diffTypeBut = new MyButton("solve by different method!");
            diffTypeBut.setBounds(950, 300, 400, 80);
            displayAnswerColor(diffTypeBut, 60);
            diffTypeBut.setColor(GlobalFrame.secUsedColor);
            diffTypeBut.setColorOver(GlobalFrame.background);
            diffTypeBut.addActionListener(this);
            Logic.add(diffTypeBut);
        }

        editButtonsDisplay();
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

    public void editButtonsDisplay(){
        int NumOfEquations = SolutionArr.length;
        tempBut = new JButton();
        tempBut.setBounds(100 * ( NumOfEquations +1) , 60 * ( NumOfEquations +2), 0, 1);
        tempBut.setBackground(GlobalFrame.background);
        tempBut.setBorder(null);
        tempBut.setEnabled(false);
        Logic.add(tempBut);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SolutionDomain.setVisible(false);
        for(MyButton but : solutionValues){ but.setVisible(false); }
        labTimeConsumed.setVisible(false);
        timeConsumed.setVisible(false);
        goToNewProblem.setVisible(false);
        diffTypeBut.setVisible(false);
        tempBut.setVisible(false);
        if( e.getSource() == goToNewProblem ){
            new LinearSystemGlobalData();
        }else if( e.getSource() == diffTypeBut ){
            new wayToSolve(wayToSolve.cofficients, wayToSolve.results);
        }
    }
}


/*
* [1.0, 2.0, 3.0, 0.0]
[[5.0, 0.0, 0.0, 14.0], [0.0, 33.0, 0.0, 0.0], [12.0, 0.0, -0.9, 0.0], [0.0, 0.0, 0.0, 1.1]]
*
* */