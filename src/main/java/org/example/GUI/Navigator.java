package org.example.GUI;

import org.example.GUI_part1.LinearSystemGlobalData;
import org.example.GUI_part2.methodToSolve;
import org.example.evaluate.calculate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.example.GUI.GlobalFrame.Logic;
import static org.example.GUI.GlobalFrame.background;

public class Navigator implements ActionListener {
    private JLabel label;
    private MyButton rootBut;
    private MyButton systemOfEquationsBut;
    private JButton tempBut;

    public Navigator() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);

        Logic.setBorder(new EmptyBorder(800, 1350, 0, 0));

        labelPhoto();
        rootChoice();
        SystemOfEqnsChoice();
        tempButt();
        Logic.add(label);
        Logic.add(rootBut);
        Logic.add(systemOfEquationsBut);
        Logic.add(tempBut);
    }

    public void labelPhoto() {
        label = new JLabel(" ♫Welcome to our system solver♫");
        label.setBounds(300, 50, 900, 150);
        label.setFont(new Font("Times New Roman", Font.BOLD, 60));
        label.setForeground(new Color(0xFFFFFF));
        label.setBackground(new Color(0x0B4D67));
        label.setOpaque(true);
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
        but.setFont(new Font("MV Boli", Font.ITALIC, 30));
        but.addActionListener(this);
        but.setBorder(null);
    }

    public void tempButt() {
        tempBut = new JButton();
        tempBut.setBackground(background);
        tempBut.setEnabled(false);
        tempBut.setBorder(null);
        tempBut.setBounds(1400, 0, 1, 800);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setVisible(false);
        rootBut.setVisible(false);
        systemOfEquationsBut.setVisible(false);
        tempBut.setVisible(false);
        if (e.getSource() == rootBut) {

            new methodToSolve();

        } else if (e.getSource() == systemOfEquationsBut) {

//            // to call derivative evaluation
//            try {
//                calculate.makeDerivative("e^(5*x)");
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
//            double gotoutput1 = calculate.getDerivativeOutput(1);
//            // to call function evaluation
//            double gotoutput2 = calculate.getFunctionOutput("e^(5*x)", 1);

            // go to default system values.
            new LinearSystemGlobalData();
            // end go to default system values.

        }
    }
}
