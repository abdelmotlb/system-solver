package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAnswer implements ActionListener {
    private MyButton goToNewProblem;
    private double[] SolutionArr;
    private JPanel Logic;
    MyButton timeConsumed;
    JButton tempBut;

    public DisplayAnswer(JPanel Logic, double[] SolutionArr){
        this.Logic = Logic;
        this.SolutionArr = SolutionArr;
        arrRepresentation();
    }

    public void arrRepresentation(){

        // variable
        int startY = 100;
        int NumOfEquations = SolutionArr.length;

        // edit border
        Logic.setBorder(new EmptyBorder(100 * ( NumOfEquations +1) , 2 * 60 * ( NumOfEquations +1) ,0, 0));



        // label above values
        JLabel SolutionDomain = new JLabel("↓ Solution Domain ↓");
        SolutionDomain.setBounds(20, 20, 400, 60); ////
        SolutionDomain.setVerticalAlignment(JLabel.CENTER);
        SolutionDomain.setForeground(Color.white);
        SolutionDomain.setFont( new Font("Times New Roman", Font.PLAIN, 40) );
        Logic.add(SolutionDomain);

        MyButton[] solutionValues = new MyButton[ NumOfEquations ];
        for(int i = 0 ; i < NumOfEquations ; i++){
            solutionValues[i] = new MyButton( String.format("x%d = %f", i+1, SolutionArr[i]) );
            solutionValues[i].setBounds(100 * (i+1) - 50, startY + 60 * (i+1) - 50, 300, 50);
            solutionValues[i].setEnabled(false);
            displayAnswerColor(solutionValues[i], 30);
        }

        JLabel labTimeConsumed = new JLabel("↓ time complexity ↓");
        labTimeConsumed.setBounds(20, 500, 400, 60); ////
        labTimeConsumed.setVerticalAlignment(JLabel.CENTER);
        labTimeConsumed.setForeground(Color.white);
        labTimeConsumed.setFont( new Font("Times New Roman", Font.PLAIN, 40) );
        Logic.add(labTimeConsumed);

        timeConsumed = new MyButton( String.format("%f in ms", wayToSolve.gotTime / 1e6) );
        timeConsumed.setBounds(20, 600, 300, 50);
        timeConsumed.setEnabled(false);
        displayAnswerColor(timeConsumed, 30);

        goToNewProblem = new MyButton("solve New problem!");
        goToNewProblem.setBounds(950, 250, 400, 80);
        displayAnswerColor(goToNewProblem, 60);
        goToNewProblem.setColor(GlobalFrame.secUsedColor);
        goToNewProblem.setColorOver(GlobalFrame.background);
        goToNewProblem.addActionListener(this);
        Logic.add(goToNewProblem);

        editButtonsDisplay();

        System.out.println("in arrRepresentatioin");
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
        tempBut.setBounds(100 * ( NumOfEquations +1) , 60 * ( NumOfEquations +1), 0, 1);
        tempBut.setBackground(GlobalFrame.background);
        tempBut.setBorder(null);
        tempBut.setEnabled(false);
        Logic.add(tempBut);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new GlobalFrame();
    }
}


/*
* [1.0, 2.0, 3.0, 0.0]
[[5.0, 0.0, 0.0, 14.0], [0.0, 33.0, 0.0, 0.0], [12.0, 0.0, -0.9, 0.0], [0.0, 0.0, 0.0, 1.1]]
*
* */