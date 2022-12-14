package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class DisplayAnswer implements ActionListener {
    private MyButton goToNewProblem;
    private double[] SolutionArr;
    private JPanel Logic;
    MyButton timeConsumed;

    public DisplayAnswer(JPanel Logic, double[] SolutionArr){
        this.Logic = Logic;
        this.SolutionArr = SolutionArr;
        arrRepresentation();
    }

    public void arrRepresentation(){

        // label above values
        JLabel SolutionDomain = new JLabel("↓ Solution Domain ↓");
        SolutionDomain.setBounds(600, 20, 400, 60);
        SolutionDomain.setVerticalAlignment(JLabel.CENTER);
        SolutionDomain.setForeground(GlobalFrame.usedColor);
        SolutionDomain.setFont( new Font("Times New Roman", Font.PLAIN, 40) );
        Logic.add(SolutionDomain);

        int NumOfEquations = SolutionArr.length;
        MyButton[] solutionValues = new MyButton[ NumOfEquations ];
        for(int i = 0 ; i < NumOfEquations ; i++){
            solutionValues[i] = new MyButton( String.format("x%d = %f", i+1, SolutionArr[i]) );
            solutionValues[i].setBounds(100 * (i+1) - 50, 60 * (i+1) - 50, 300, 50);
            solutionValues[i].setEnabled(false);
            displayAnswerColor(solutionValues[i], 30);
        }

        timeConsumed = new MyButton( String.format("%f in ms", wayToSolve.gotTime / 1e6) );
        timeConsumed.setBounds(20, 600, 300, 50);
        timeConsumed.setEnabled(false);
        displayAnswerColor(timeConsumed, 30);

        goToNewProblem = new MyButton("solve New problem!");
        goToNewProblem.setBounds(950, 250, 400, 80);
        displayAnswerColor(goToNewProblem, 60);
        goToNewProblem.setColor(GlobalFrame.usedColor);
        goToNewProblem.setColorOver(GlobalFrame.secUsedColor);
        goToNewProblem.addActionListener(this);
        Logic.add(goToNewProblem);

        System.out.println("in arrRepresentatioin");
    }

    public void displayAnswerColor(MyButton but, int size){
        but.setFont( new Font("Times New Roman", Font.PLAIN, 30) );
        but.setRadius(40);
        but.setBorder(null);
        but.setForeground(new Color(255, 255, 255));
        but.setBackground(GlobalFrame.usedColor);
        but.setColorClick(GlobalFrame.usedColor);
        but.setColor(new Color(255, 255, 255));
        but.setColorOver(GlobalFrame.usedColor);
        Logic.add(but);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new GlobalFrame();
    }
}
