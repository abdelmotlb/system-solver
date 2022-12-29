package org.example.GUI_part2;

import org.example.GUI.GlobalFrame;
import org.example.GUI.MyButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.example.GUI.GlobalFrame.Logic;

public class methodToSolve implements ActionListener {
    private String choosenMethod;
    private JLabel txt;
    private MyButton Bisection;
    private MyButton FalsePosition;
    private MyButton FixedPoint;
    private MyButton NewtonRaphson;
    private MyButton SecantMethod;
    private JButton tempBut;
    public static String wantedMethod = "";

    public methodToSolve(){
        displayChoices();
    }

    public void displayChoices(){

        // update border
        Logic.setBorder(new EmptyBorder(750, 1350, 0, 0));


        txt = new JLabel("choose the type you want");
        txt.setBounds(500, 300, 500, 100);
        txt.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        txt.setHorizontalAlignment(JLabel.CENTER);
        txt.setVerticalAlignment(JLabel.CENTER);
        txt.setBackground(GlobalFrame.background);
        txt.setForeground(new Color(255, 255, 255));
        txt.setOpaque(true);
        Logic.add(txt);

        Bisection = new MyButton("Bisection");
        Bisection.setBounds(650, 0, 200, 200);
        Bisection.addActionListener(this);
        ButtonDisplay(Bisection);

        FalsePosition = new MyButton("False-Position");
        FalsePosition.setBounds(100, 150, 200, 200);
        FalsePosition.addActionListener(this);
        ButtonDisplay(FalsePosition);

        FixedPoint = new MyButton("Fixed Point");
        FixedPoint.setBounds(1100, 150, 200, 200);
        FixedPoint.addActionListener(this);
        ButtonDisplay(FixedPoint);

        NewtonRaphson = new MyButton("Newton-Raphson");
        NewtonRaphson.setBounds(350, 450, 200, 200);
        NewtonRaphson.addActionListener(this);
        ButtonDisplay(NewtonRaphson);

        SecantMethod = new MyButton("Secant Method");
        SecantMethod.setBounds(950, 450, 200, 200);
        SecantMethod.addActionListener(this);
        ButtonDisplay(SecantMethod);

        editButtonsDisplay();

    }

    private void ButtonDisplay(MyButton but){
        but.setRadius(200);
        but.setForeground(Color.white);
        but.setBackground(GlobalFrame.secUsedColor);
        but.setColorClick(GlobalFrame.secUsedColor);
        but.setColor(GlobalFrame.secUsedColor);
        but.setColorOver(GlobalFrame.background);
        but.setFont(new Font("MV Boli", Font.ITALIC, 25));
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

    @Override
    public void actionPerformed(ActionEvent e) {

        txt.setVisible(false);
        Bisection.setVisible(false);
        FalsePosition.setVisible(false);
        FixedPoint.setVisible(false);
        NewtonRaphson.setVisible(false);
        SecantMethod.setVisible(false);
        tempBut.setVisible(false);

        if( e.getSource() == Bisection ){ wantedMethod = Bisection.getText(); }
        else if( e.getSource() == FalsePosition ){ wantedMethod = FalsePosition.getText(); }
        else if( e.getSource() == FixedPoint ){ wantedMethod = FixedPoint.getText(); }
        else if( e.getSource() == NewtonRaphson ){ wantedMethod = NewtonRaphson.getText(); }
        else if( e.getSource() == SecantMethod ){ wantedMethod = SecantMethod.getText(); }

        System.out.println( wantedMethod );

        new rootsGlobalData();


//        if( wantedMethod == "Bisection" ){
//
//            ArrayList<double[]> arrList = new ArrayList<double[]>();
//            arrList.add(new double[]{ 1, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 2, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 3, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//            arrList.add(new double[]{ 4, .0123456789, 12.23, 1250, 7889.12, 13, 9, 0.0001212 });
//
//            wantedMethod = "False-Position";
//
//            steps st = new steps(arrList);
//
//        }else {
//        }



    }
}
