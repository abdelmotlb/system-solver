package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static java.lang.Double.parseDouble;

public class GridMatrix implements ActionListener {

    private JPanel Logic;
    private JLabel[][] variablesName;
    private JTextField[][] pocket;
    private double cofficients[][];
    private double results[];
    private JButton GotoType;
    private int NumofEquations;
    GridMatrix(JPanel Logic, int NumofEquations){
        this.Logic = Logic;
        this.NumofEquations = NumofEquations;
        EquationsLayout();
    }

    public void EquationsLayout(){
        int startX = 150, startY = 100;
        variablesName = new JLabel[ NumofEquations ][ NumofEquations ];
        pocket = new JTextField[ NumofEquations ][ NumofEquations+1 ];

        //labels
        for (int i = 0; i < NumofEquations; i++) {
            for (int j = 0; j < NumofEquations; j++) {
                String s = (j != NumofEquations - 1)? String.format(" x%d + ", j+1) : String.format(" x%d = ", j+1);
                variablesName[i][j] = new JLabel(s);
                variablesName[i][j].setBounds(startX+j*100 +100*(j+1) +100, startY+i*120, 80, 100);
                variablesName[i][j].setFont(new Font( "Times New Roman", Font.PLAIN, 30 ));
                variablesName[i][j].setHorizontalAlignment(JLabel.CENTER);
                Logic.add( variablesName[i][j] );
            }
        }

        for (int i = 0; i < NumofEquations; i++) {
            for (int j = 0; j <= NumofEquations; j++) {
                pocket[i][j] = new JTextField();
                pocket[i][j].setBounds(startX+j*100 + 100*(j+1), startY+i*120, 80, 80);
                pocket[i][j].setFont(new Font( "Times New Roman", Font.PLAIN, 30 ));
                pocket[i][j].setHorizontalAlignment(JLabel.CENTER);
                Logic.add( pocket[i][j] );
            }
        }

        GotoType = new JButton("GotoType");
        GotoType.setBounds(1250, 600, 100, 50);
        GotoType.setBackground(new Color(0x263D88));
        GotoType.setForeground(new Color(0xFFFFFF));
        GotoType.addActionListener(this);
        Logic.add(GotoType);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == GotoType ){

            // intialize the user arrays
            cofficients = new double[ NumofEquations ][ NumofEquations ];
            results = new double[ NumofEquations ];

            // store the values got from user and change visibility.
            for (int i = 0; i < NumofEquations; i++) {
                for (int j = 0; j <= NumofEquations; j++) {
                    if(j != NumofEquations){
                        cofficients[i][j] = parseDouble(pocket[i][j].getText());
                        variablesName[i][j].setVisible(false);
                        pocket[i][j].setVisible(false);
                    }
                    else{
                        results[i] = parseDouble(pocket[i][j].getText());
                        pocket[i][j].setVisible(false);
                    }
                }
            }
            GotoType.setVisible(false);
            System.out.println( Arrays.toString(results) );
            System.out.println( Arrays.deepToString(cofficients) );
            new wayToSolve(Logic, cofficients, results);
        }
    }
}
