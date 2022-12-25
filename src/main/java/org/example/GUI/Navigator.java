package org.example.GUI;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.example.evaluate.calculate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static org.example.GUI.GlobalFrame.Logic;
import static org.example.GUI.GlobalFrame.background;

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

    public void labelPhoto() {
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

    public void rootChoice() {
        rootBut = new MyButton("get equation roots");
        rootBut.setBounds(200, 300, 300, 300);
        ButtonDisplay(rootBut);
    }

    public void SystemOfEqnsChoice() {
        systemOfEquationsBut = new MyButton("get system solution");
        systemOfEquationsBut.setBounds(1000, 300, 300, 300);
        ButtonDisplay(systemOfEquationsBut);
    }

    private void ButtonDisplay(MyButton but) {
        but.setRadius(300);
        but.setForeground(Color.white);
        but.setBackground(GlobalFrame.secUsedColor);
        but.setColorClick(GlobalFrame.secUsedColor);
        but.setColor(GlobalFrame.secUsedColor);
        but.setColorOver(background);
        but.setFont(new Font("MV Boli", Font.ITALIC, 20));
        but.addActionListener(this);
        but.setBorder(null);
        Logic.add(but);
    }

    public void tempButt() {
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
        if (e.getSource() == rootBut) {
            // go to display data of roots.
        } else if (e.getSource() == systemOfEquationsBut) {

            // to call derivative evaluation
            try {
                calculate.makeDerivative("e^(5*x)");
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            double gotoutput1 = calculate.getDerivativeOutput(1);
            // to call function evaluation
            double gotoutput2 = calculate.getFunctionOutput("e^(5*x)", 1);

            // go to default system values.
            new LinearSystemGlobalData();
            // end go to default system values.

        }
    }
}
