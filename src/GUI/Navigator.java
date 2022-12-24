package GUI;

import evaluate.evaluateDerivative;
import evaluate.evaluateFunction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.GlobalFrame.*;

public class Navigator implements ActionListener {
    private MyButton label;
    private MyButton rootBut;
    private MyButton systemOfEquationsBut;
    private JButton tempBut;

    public Navigator() {
        Logic.setBorder(new EmptyBorder(800, 1400, 0, 0));

        labelPhoto();
        rootChoice();
        SystemOfEqnsChoice();
        tempButt();
    }

    public void labelPhoto(){
        label = new MyButton("choose and go!");
        label.setBounds(600, 50, 300, 150);
        label.setFont(new Font("MV BOLI", Font.ITALIC, 30));
        label.setEnabled(false);
        label.setColorOver(Color.white);
        label.setColorClick(Color.black);
        label.setColorOver(Color.white);
        label.setColor(Color.white);
        Logic.add(label);
    }

    public void rootChoice(){
        rootBut = new MyButton("get equation roots");
        rootBut.setBounds(200, 300, 300, 300);
        ButtonDisplay(rootBut);
    }

    public void SystemOfEqnsChoice(){
        systemOfEquationsBut = new MyButton("get system solution");
        systemOfEquationsBut.setBounds(1000, 300, 300, 300);
        ButtonDisplay(systemOfEquationsBut);
    }

    private void ButtonDisplay(MyButton but){
        but.setRadius(300);
        but.setForeground(Color.white);
        but.setBackground(GlobalFrame.secUsedColor);
        but.setColorClick(GlobalFrame.secUsedColor);
        but.setColor(GlobalFrame.secUsedColor);
        but.setColorOver(GlobalFrame.background);
        but.setFont(new Font("MV Boli", Font.ITALIC, 20));
        but.addActionListener(this);
        but.setBorder(null);
        Logic.add(but);
    }

    public void tempButt(){
        tempBut = new JButton();
        tempBut.setBackground(background);
        tempBut.setEnabled(false);
        tempBut.setBorder(null);
        tempBut.setBounds(1400, 800, 1, 1);
        Logic.add(tempBut);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setVisible(false);
        rootBut.setVisible(false);
        systemOfEquationsBut.setVisible(false);
        tempBut.setVisible(false);
        if(e.getSource() == rootBut){
            // go to display data of roots.
        }else if(e.getSource() == systemOfEquationsBut){
            evaluateDerivative obj = new evaluateDerivative();
            try { obj.calcFirstDerivative("x**2-20*x", 10);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.calcFirstDerivative("x**2-20*x", 5);
                obj.getOutput();
            }catch (Exception ea){ System.out.println( "exception in navigate" ); }
            new LinearSystemGlobalData();
        }
    }
}
