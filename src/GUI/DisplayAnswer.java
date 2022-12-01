package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class DisplayAnswer implements ActionListener {
    private JButton goToNewProblem;
    private double[] SolutionArr;
    private JPanel Logic;

    public DisplayAnswer(JPanel Logic, double[] SolutionArr){
        this.Logic = Logic;
        this.SolutionArr = SolutionArr;
        arrRepresentation();
    }

    public void arrRepresentation(){

        // label above values
        JLabel SolutionDomain = new JLabel("Solution Domain");
        SolutionDomain.setBounds(600, 20, 400, 60);
        SolutionDomain.setVerticalAlignment(JLabel.CENTER);
        SolutionDomain.setForeground(new Color(16, 55, 128));
        SolutionDomain.setFont( new Font("Times New Roman", Font.PLAIN, 40) );
        Logic.add(SolutionDomain);

        int NumOfEquations = SolutionArr.length;
        MyButton[] solutionValues = new MyButton[ NumOfEquations ];
        for(int i = 0 ; i < NumOfEquations ; i++){
            solutionValues[i] = new MyButton( String.format("x%d = %f", i+1, SolutionArr[i]) );
            solutionValues[i].setBounds(150 * (i+1), 100 * (i+1), 300, 80);
            solutionValues[i].setFont( new Font("Times New Roman", Font.PLAIN, 50) );
            solutionValues[i].setRadius(40);
            solutionValues[i].setEnabled(false);
            solutionValues[i].setBorder(null);
            solutionValues[i].setForeground(new Color(255, 255, 255));
            solutionValues[i].setBackground(new Color(0, 0, 0));
            solutionValues[i].setColorClick(new Color(0, 0, 0));
            solutionValues[i].setColor(new Color(16, 7, 7));
            solutionValues[i].setColorOver(new Color(0, 0, 0));
            Logic.add(solutionValues[i]);
        }

        goToNewProblem = new JButton("solve New problem!");
        goToNewProblem.setBounds(950, 550, 400, 80);
        goToNewProblem.setFont( new Font("Times New Roman", Font.PLAIN, 40) );
        goToNewProblem.setBackground(new Color(0x263D88));
        goToNewProblem.setForeground(new Color(0xFFFFFF));
        goToNewProblem.addActionListener(this);
        Logic.add(goToNewProblem);

        System.out.println("in arrRepresentatioin");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new GlobalFrame();
    }
}
