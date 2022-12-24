package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.GlobalFrame.*;
import static java.lang.Integer.parseInt;

public class LinearSystemGlobalData implements ActionListener {
    private int NumofEquations;
    private JLabel NumEqLabel;
    private JTextField txtOfNum;
    private JLabel precisionLabel;
    private JTextField txtOfPrecision;
    private JLabel scalingLabel;
    private JButton scalingBut;
    private JButton SubmitSize;
    private JButton tempBut;

    public LinearSystemGlobalData() {

        // get number of equations
        NumEqLabel = new JLabel();
        NumEqLabel.setBounds(0, 20, 1400, 100);
        NumEqLabel.setText("        → Enter the number of variables");
        NumEqLabel.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        NumEqLabel.setForeground(usedColor);

        txtOfNum = new JTextField();
        txtOfNum.setBounds(550, 150, 300, 70);
        txtOfNum.setHorizontalAlignment(JTextField.CENTER);
        txtOfNum.setFont(new Font("Times New Roman", Font.ITALIC, 50));

        // get precision
        precisionLabel = new JLabel();
        precisionLabel.setBounds(0, 250, 1400, 100);
        precisionLabel.setText("        → Enter precision used");
        precisionLabel.setForeground(usedColor);
        precisionLabel.setFont(new Font("Times New Roman", Font.ITALIC, 50));


        txtOfPrecision = new JTextField();
        txtOfPrecision.setBounds(550, 380, 300, 70);
        txtOfPrecision.setHorizontalAlignment(JTextField.CENTER);
        txtOfPrecision.setFont(new Font("Times New Roman", Font.ITALIC, 50));

        // check scaling
        scalingLabel = new JLabel();
        scalingLabel.setBounds(0, 480, 1400, 100);
        scalingLabel.setText("        → click to toggle");
        scalingLabel.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        scalingLabel.setForeground(usedColor);

        scalingBut = new JButton("use Scaling");
        scalingBut.setBounds(550, 600, 300, 50);
        scalingBut.setBackground(secUsedColor);
        scalingBut.setForeground(new Color(0xFFFFFF));
        scalingBut.setFont(new Font("Arial", Font.BOLD, 20));
        scalingBut.addActionListener(this);


        // submission button
        SubmitSize = new JButton("GO!");
        SubmitSize.setBounds(1150, 680, 200, 50);
        SubmitSize.setBackground(secUsedColor);
        SubmitSize.setForeground(usedColor);
        SubmitSize.setFont(new Font("Arial", Font.BOLD, 20));
        SubmitSize.addActionListener(this);

        tempBut = new JButton();
        tempBut.setBackground(background);
        tempBut.setEnabled(false);
        tempBut.setBorder(null);
        tempBut.setBounds(1300, 700, 1, 1);

        Logic.add(NumEqLabel);
        Logic.add(txtOfNum);
        Logic.add(precisionLabel);
        Logic.add(txtOfPrecision);
        Logic.add(scalingLabel);
        Logic.add(scalingBut);
        Logic.add(SubmitSize);
        Logic.add(tempBut);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SubmitSize) {
            try { NumofEquations = parseInt(txtOfNum.getText()); }
            catch(NumberFormatException ea) { NumofEquations = 5; } // default NumOfEquations

            try { precision = parseInt(txtOfPrecision.getText()); }
            catch(NumberFormatException ea) { precision = 8; } // default precision

            NumEqLabel.setVisible(false);
            txtOfNum.setVisible(false);
            precisionLabel.setVisible(false);
            txtOfPrecision.setVisible(false);
            SubmitSize.setVisible(false);
            scalingLabel.setVisible(false);
            scalingBut.setVisible(false);
            tempBut.setVisible(false);


            new GridMatrix(NumofEquations);
        }
        else if( e.getSource() == scalingBut ){
            if( useScaling ){
                useScaling = false;
                scalingBut.setText("No scaling");
            }else {
                useScaling = true;
                scalingBut.setText("use scaling");
            }
        }
    }
}
