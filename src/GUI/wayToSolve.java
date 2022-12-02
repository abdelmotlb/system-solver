package GUI;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

public class wayToSolve implements ActionListener {
    // Mothod of solution buttons and label
    private String choosenMethod;
    private JLabel txt;
    private MyButton GaussElimination;
    private MyButton GaussJordan;
    private MyButton LUDecomposition;
    private MyButton GaussSeidel;
    private MyButton JacobiIteration;

    // LU Buttons and label
    private JLabel LUtxt;
    private MyButton Downlittle;
    private MyButton Crout;
    private MyButton Cholesky;
    private double cofficients[][];
    private double results[];
    private JPanel Logic;

    // iterative methods requirements
    private JLabel intialGuessLabel;
    private JTextField[] intialGuessTF;
    private JButton RelativeErrorB;
    private JTextField RelativeErrorTF;
    private JButton NumOfIterationsB;
    private JTextField NumOfIterationsTF;
    private JButton ShowSolAIterAddData;
    private boolean BooleanRE_NI;
    private double[] initialGuessArr;
    private double NOI_RE;


    public wayToSolve(JPanel Logic, double cofficients[][], double results[]){
        this.Logic = Logic;
        this.cofficients = cofficients;
        this.results = results;
        displayChoices();
    }

    public void displayChoices(){

        txt = new JLabel("choose the type you want");
        txt.setBounds(500, 300, 500, 100);
        txt.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        txt.setHorizontalAlignment(JLabel.CENTER);
        txt.setVerticalAlignment(JLabel.CENTER);
        txt.setBackground(new Color(0, 0, 0));
        txt.setForeground(new Color(255, 255, 255));
        txt.setOpaque(true);
        Logic.add(txt);

        GaussElimination = new MyButton("Gauss Elimination");
        GaussElimination.setBounds(650, 0, 200, 200);
        GaussElimination.addActionListener(this);
        ButtonDisplay(GaussElimination);

        GaussJordan = new MyButton("Gauss-Jordan");
        GaussJordan.setBounds(100, 150, 200, 200);
        GaussJordan.addActionListener(this);
        ButtonDisplay(GaussJordan);

        LUDecomposition = new MyButton("LU Decomposition");
        LUDecomposition.setBounds(1100, 150, 200, 200);
        LUDecomposition.addActionListener(this);
        ButtonDisplay(LUDecomposition);

        GaussSeidel = new MyButton("Gauss-Seidel");
        GaussSeidel.setBounds(350, 450, 200, 200);
        GaussSeidel.addActionListener(this);
        ButtonDisplay(GaussSeidel);

        JacobiIteration = new MyButton("Jacobi-Iteration");
        JacobiIteration.setBounds(950, 450, 200, 200);
        JacobiIteration.addActionListener(this);
        ButtonDisplay(JacobiIteration);

    }



    public void choicesLUButtons(){

        LUtxt = new JLabel("Choose LU type you want");
        LUtxt.setBounds(500, 300, 500, 100);
        LUtxt.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        LUtxt.setHorizontalAlignment(JLabel.CENTER);
        LUtxt.setVerticalAlignment(JLabel.CENTER);
        LUtxt.setBackground(new Color(0, 0, 0));
        LUtxt.setForeground(new Color(255, 255, 255));
        LUtxt.setOpaque(true);
        Logic.add(LUtxt);


        Downlittle = new MyButton("Downlittle");
        Downlittle.setBounds(650, 0, 200, 200);
        Downlittle.addActionListener(this);
        ButtonDisplay(Downlittle);

        Crout = new MyButton("Crout");
        Crout.setBounds(100, 150, 200, 200);
        Crout.addActionListener(this);
        ButtonDisplay(Crout);

        Cholesky = new MyButton("Cholesky");
        Cholesky.setBounds(1100, 150, 200, 200);
        Cholesky.addActionListener(this);
        ButtonDisplay(Cholesky);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // hide the page and appear the solution
        if( e.getSource() == GaussElimination || e.getSource() == GaussJordan || e.getSource() == LUDecomposition || e.getSource() == GaussSeidel || e.getSource() == JacobiIteration ){
            txt.setVisible(false);
            GaussElimination.setVisible(false);
            GaussJordan.setVisible(false);
            LUDecomposition.setVisible(false);
            GaussSeidel.setVisible(false);
            JacobiIteration.setVisible(false);

            if( e.getSource() == GaussElimination ){

                choosenMethod = "GaussElimination";
                gauss GaussObj = new gauss(cofficients, results);
                double [] ans = GaussObj.solve();

                // validity
                if( GaussObj.getValid() ) conWithDisAns( ans );
                else CreateExpLab();

            } else if( e.getSource() == GaussJordan ){

                choosenMethod = "GaussJordan";
                gaussjordan JordanObj = new gaussjordan(cofficients, results);
                double [] ans = JordanObj.solve();

                // validity
                if( JordanObj.getValid() ) conWithDisAns( ans );
                else CreateExpLab();

            } else if( e.getSource() == LUDecomposition ){

                choosenMethod = "LUDecomposition";
                // LU Type display:
                choicesLUButtons();

            } else if( e.getSource() == GaussSeidel ){

                choosenMethod = "GaussSeidel";
                // display to get intial guess then get solution
                userIntialGuessDisplay();

            } else {

                choosenMethod = "JacobiIteration";
                // (Jacobi) display to get intial guess then get solution
                userIntialGuessDisplay();

            }

        }

        else if( e.getSource() == Downlittle || e.getSource() == Crout || e.getSource() == Cholesky ){
            // end the visualization of Lu choices
            Downlittle.setVisible(false);
            Crout.setVisible(false);
            Cholesky.setVisible(false);
            LUtxt.setVisible(false);

            // sensor of LU choices

            if( e.getSource() == Downlittle ){

                System.out.println( "Downlittle" );
                DownLittle LUObj = new DownLittle(cofficients, results);
                double [] ans = LUObj.solve();
                System.out.println("in Downlittle");

                // validity
                if( LUObj.getValid() ) conWithDisAns( ans );
                else CreateExpLab();

            } else if( e.getSource() == Crout ){

                System.out.println( "Crout" );
                crout croutObj = new crout(cofficients, results);
                double [] ans = croutObj.solve();

                // validity
                if( croutObj.getValid() ) conWithDisAns( ans );
                else CreateExpLab();

            } else if( e.getSource() == Cholesky ){

                System.out.println( "Cholesky" );
                chelosky cheloskyObj = new chelosky(cofficients, results);

                double[] ans = cheloskyObj.solve();

                // validity
                if( cheloskyObj.getValid() ) conWithDisAns( ans );
                else CreateExpLab();

            }
        }

        else if( e.getSource() == RelativeErrorB || e.getSource() == NumOfIterationsB ){
            if( e.getSource() == RelativeErrorB ){

                BooleanRE_NI = false;
                RelativeErrorB.setEnabled(false);
                RelativeErrorB.setBackground(new Color(0x263D88));
                RelativeErrorB.setForeground(new Color(0xFFFFFF));
                NumOfIterationsB.setEnabled(false);
                NumOfIterationsB.setBackground(new Color(156, 171, 178));
                NumOfIterationsB.setForeground(new Color(33, 30, 30));

                // RE TF activation
                RelativeErrorTF = new JTextField();
                RelativeErrorTF.setBounds(100, 400, 500, 50);
                RelativeErrorTF.setHorizontalAlignment(JTextField.CENTER);
                RelativeErrorTF.setFont( new Font("Times New Roman", Font.BOLD, 30) );
                Logic.add(RelativeErrorTF);
            } else {

                BooleanRE_NI = true;
                NumOfIterationsB.setEnabled(false);
                NumOfIterationsB.setBackground(new Color(0x263D88));
                NumOfIterationsB.setForeground(new Color(0xFFFFFF));
                RelativeErrorB.setEnabled(false);
                RelativeErrorB.setBackground(new Color(156, 171, 178));
                RelativeErrorB.setForeground(new Color(33, 30, 30));

                // NOI TF activation
                NumOfIterationsTF = new JTextField();
                NumOfIterationsTF.setBounds(800, 400, 500, 50);
                NumOfIterationsTF.setHorizontalAlignment(JTextField.CENTER);
                NumOfIterationsTF.setFont( new Font("Times New Roman", Font.BOLD, 30) );
                Logic.add(NumOfIterationsTF);
            }

            ShowSolAIterAddData = new JButton("Show the Solution");
            ShowSolAIterAddData.setBounds(600, 600, 300, 50);
            ShowSolAIterAddData.setBackground(new Color(0x263D88));
            ShowSolAIterAddData.setForeground(new Color(0xFFFFFF));
            ShowSolAIterAddData.setFont( new Font("Times New Roman", Font.BOLD, 30) );
            ShowSolAIterAddData.addActionListener(this);
            Logic.add(ShowSolAIterAddData);
        }

        else if( e.getSource() == ShowSolAIterAddData ) {

            // hide previous show
            for( JTextField ParTF: intialGuessTF){ ParTF.setVisible(false); }
            intialGuessLabel.setVisible(false);
            RelativeErrorB.setVisible(false);
            if( BooleanRE_NI == false) RelativeErrorTF.setVisible(false);
            NumOfIterationsB.setVisible(false);
            if( BooleanRE_NI ) NumOfIterationsTF.setVisible(false);
            ShowSolAIterAddData.setVisible(false);

            // store user data
            int NumOfEquations = results.length;
            initialGuessArr = new double[ NumOfEquations ];
            for(int i = 0; i < NumOfEquations; i++){
                initialGuessArr[i] = Double.parseDouble( intialGuessTF[i].getText() );
            }
            if( !BooleanRE_NI )NOI_RE = Double.parseDouble( RelativeErrorTF.getText() );
            else NOI_RE = Double.parseDouble( NumOfIterationsTF.getText() );
            System.out.println( Arrays.toString( initialGuessArr ) );
            System.out.println(NOI_RE);

            // call Algo of jacobi or siedel
            if( choosenMethod == "JacobiIteration" ){

                // call jacobi method and get solution in SolutionArr the call connect
                Jacobi JacobiObj = new Jacobi();
                double[] ans = JacobiObj.solve(cofficients, results, initialGuessArr, NOI_RE, BooleanRE_NI);

                // validity
                if( JacobiObj.IsValid() ) conWithDisAns( ans );
                else CreateExpLab();

            }else if( choosenMethod == "GaussSeidel" ){

                // call GaussSeidel method and get solution in SolutionArr the call connect
                 GaussSeidel GaussSeidelObj = new GaussSeidel(cofficients, results, initialGuessArr, NOI_RE, BooleanRE_NI);
                double[] ans = GaussSeidelObj.solve();

                // validity
                if( GaussSeidelObj.getValid() ) conWithDisAns( ans );
                else CreateExpLab();

            }

            System.out.println("in show solution part");
        }
        else {
            System.out.println("in else of AL");
        }
    }


    public void conWithDisAns(double[] SolutionArr){
        DisplayAnswer DA = new DisplayAnswer(Logic, SolutionArr);
    }



    private void userIntialGuessDisplay(){

        // initial Guess Label
        intialGuessLabel = new JLabel("intial guass you want to use");
        intialGuessLabel.setBounds(500, 20, 400, 60);
        intialGuessLabel.setHorizontalAlignment(JLabel.CENTER);
        intialGuessLabel.setForeground(new Color(119, 41, 41));
        intialGuessLabel.setFont( new Font("Times New Roman", Font.BOLD, 30) );
        Logic.add(intialGuessLabel);

        // text fields
        int NumOfEquations = results.length;
        intialGuessTF  = new JTextField[ NumOfEquations ];
        for(int i = 0; i < NumOfEquations; i++){
            intialGuessTF[i] = new JTextField();
            intialGuessTF[i].setFont(new Font( "Times New Roman", Font.PLAIN, 30 ));
            intialGuessTF[i].setBounds( 100 * (i+1), 120, 80, 80);
            intialGuessTF[i].setHorizontalAlignment(JLabel.CENTER);
            Placeholder(intialGuessTF[i], i);
            Logic.add(intialGuessTF[i]);
        }

        // RE Button
        RelativeErrorB = new JButton("iterate by Relative Error");
        RelativeErrorB.setFont(new Font( "Times New Roman", Font.BOLD, 30 ));
        RelativeErrorB.setBounds(100, 300, 500, 50);
        RelativeErrorB.setHorizontalAlignment(JButton.CENTER);
        RelativeErrorB.setBackground(new Color(0x263D88));
        RelativeErrorB.setForeground(new Color(0xFFFFFF));
        RelativeErrorB.addActionListener(this);
        Logic.add(RelativeErrorB);

        // NOI Button
        NumOfIterationsB = new JButton("iterate by number of iterations");
        NumOfIterationsB.setFont(new Font( "Times New Roman", Font.BOLD, 30 ));
        NumOfIterationsB.setBounds(800, 300, 500, 50);
        NumOfIterationsB.setHorizontalAlignment(JButton.CENTER);
        NumOfIterationsB.setBackground(new Color(0x263D88));
        NumOfIterationsB.setForeground(new Color(0xFFFFFF));
        NumOfIterationsB.addActionListener(this);
        Logic.add(NumOfIterationsB);

    }

    private void ButtonDisplay(MyButton but){
        but.setRadius(200);
        but.setForeground(Color.white);
        but.setBackground(new Color(16, 55, 128));
        but.setColorClick(new Color(16, 55, 128));
        but.setColor(new Color(16, 55, 128));
        but.setColorOver(new Color(103, 147, 255));
        but.setFont(new Font("MV Boli", Font.ITALIC, 20));
        but.setBorder(null);
        Logic.add(but);
    }
    private void Placeholder(JTextField txtField, int ind){
        txtField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(txtField.getText().trim().equals( String.format("g[%d]", ind+1) )) {
                    txtField.setText("");
                }

                txtField.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(txtField.getText().trim().equals("")) {
                    txtField.setText( String.format("g[%d]", ind+1) );
                }

                txtField.setForeground(Color.LIGHT_GRAY);
            }
        });
    }

    private void CreateExpLab(){
        JLabel ExceptionLabel = new JLabel("the entered values generates dividing by zero");
        ExceptionLabel.setBounds(400, 300, 600, 100);
        ExceptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        ExceptionLabel.setHorizontalAlignment(JLabel.CENTER);
        ExceptionLabel.setVerticalAlignment(JLabel.CENTER);
        ExceptionLabel.setBackground(new Color(0, 0, 0));
        ExceptionLabel.setForeground(new Color(255, 255, 255));
        ExceptionLabel.setOpaque(true);
        Logic.add(ExceptionLabel);
    }

}
