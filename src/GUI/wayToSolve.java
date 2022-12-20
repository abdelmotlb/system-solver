package GUI;
import logic.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class wayToSolve implements ActionListener {
    public static double gotTime;
    // Mothod of solution buttons and label
    private String choosenMethod;
    private JLabel txt;
    private MyButton GaussElimination;
    private MyButton GaussJordan;
    private MyButton LUDecomposition;
    private MyButton GaussSeidel;
    private MyButton JacobiIteration;
    private JButton tempBut;
    // LU Buttons and label
    private JLabel LUtxt;
    private MyButton Downlittle;
    private MyButton Crout;
    private MyButton Cholesky;

    private JPanel Logic;

    // iterative methods requirements
    private JLabel intialGuessLabel;
    private JTextField[] intialGuessTF;
    private JButton RelativeErrorB;
    private JTextField RelativeErrorTF;
    private JLabel maxIterationsLab;
    private JTextField maxIterationTF;
    private JButton NumOfIterationsB;
    private JTextField NumOfIterationsTF;
    private JButton ShowSolAIterAddData;
    private boolean BooleanRE_NI;
    private double[] initialGuessArr;
    private double NOI_RE;

    public static int maxIterationsValue;

    // desired variables

    public static double cofficients[][];
    public static double results[];


    public wayToSolve(JPanel Logic, double cofficients[][], double results[]){
        this.Logic = Logic;
        this.cofficients = cofficients;
        this.results = results;
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

        editButtonsDisplay();

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

        editButtonsDisplay();

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

                tempBut.setVisible(false);
                choosenMethod = "GaussElimination";
                gauss GaussObj = new gauss(cofficients, results);
                System.out.println( "before solve" );
                double [] ans = GaussObj.solve();
                System.out.println( "after solve" );

                // validity
                if( GaussObj.getValid() ) {
                    gotTime = GaussObj.getTime();
                    System.out.println( Arrays.toString( ans ) );
                    conWithDisAns( ans );
                }
                else CreateExpLab();

            } else if( e.getSource() == GaussJordan ){

                tempBut.setVisible(false);
                choosenMethod = "GaussJordan";
                gaussjordan JordanObj = new gaussjordan(cofficients, results);
                double [] ans = JordanObj.solve();

                // validity
                if( JordanObj.getValid() ) {
                    gotTime = JordanObj.getTime();
                    conWithDisAns( ans );
                }
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
            tempBut.setVisible(false);

            // sensor of LU choices

            if( e.getSource() == Downlittle ){

                System.out.println( "Downlittle" );
                DownLittle LUObj = new DownLittle(cofficients, results);
                double [] ans = LUObj.solve();
                System.out.println("in Downlittle");

                // validity
                if( LUObj.getValid() ) {
                    gotTime = LUObj.getTime();
                    conWithDisAns( ans );
                }
                else CreateExpLab();

            } else if( e.getSource() == Crout ){

                System.out.println( "Crout" );
                crout croutObj = new crout(cofficients, results);
                double [] ans = croutObj.solve();

                // validity
                if( croutObj.getValid() ) {
                    gotTime = croutObj.getTime();
                    conWithDisAns( ans );
                }
                else CreateExpLab();

            } else if( e.getSource() == Cholesky ){

                System.out.println( "Cholesky" );
                chelosky cheloskyObj = new chelosky(cofficients, results);

                double[] ans = cheloskyObj.solve();

                // validity
                if( cheloskyObj.getValid() ) {
                    gotTime = cheloskyObj.getTime();
                    conWithDisAns( ans );
                }
                else CreateExpLab();

            }
        }

        else if( e.getSource() == RelativeErrorB || e.getSource() == NumOfIterationsB ){
            if( e.getSource() == RelativeErrorB ){

                BooleanRE_NI = false;
                RelativeErrorB.setEnabled(false);
                RelativeErrorB.setBackground(GlobalFrame.usedColor);
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

                maxIterationsLab = new JLabel("max iterations");
                maxIterationsLab.setFont( new Font("Times New Roman", Font.BOLD, 30) );
                maxIterationsLab.setForeground(Color.white);
                maxIterationsLab.setBounds(100, 500, 500, 50);
                Logic.add(maxIterationsLab);

                maxIterationTF = new JTextField();
                maxIterationTF.setBounds(650, 500, 500, 50);
                maxIterationTF.setHorizontalAlignment(JTextField.CENTER);
                maxIterationTF.setFont( new Font("Times New Roman", Font.BOLD, 30) );
                Logic.add(maxIterationTF);


            } else {

                BooleanRE_NI = true;
                NumOfIterationsB.setEnabled(false);
                NumOfIterationsB.setBackground(GlobalFrame.usedColor);
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
            ShowSolAIterAddData.setBounds(600, 700, 300, 50);
            ShowSolAIterAddData.setBackground(GlobalFrame.secUsedColor);
            ShowSolAIterAddData.setForeground(new Color(0xFFFFFF));
            ShowSolAIterAddData.setFont( new Font("Times New Roman", Font.BOLD, 30) );
            ShowSolAIterAddData.addActionListener(this);
            Logic.add(ShowSolAIterAddData);

            editButtonsDisplay();
        }

        else if( e.getSource() == ShowSolAIterAddData ) {

            // hide previous show
            for( JTextField ParTF: intialGuessTF){ ParTF.setVisible(false); }
            intialGuessLabel.setVisible(false);
            RelativeErrorB.setVisible(false);
            if( BooleanRE_NI == false) {
                RelativeErrorTF.setVisible(false);
                maxIterationsLab.setVisible(false);
                maxIterationTF.setVisible(false);
            }
            NumOfIterationsB.setVisible(false);
            if( BooleanRE_NI ) NumOfIterationsTF.setVisible(false);
            ShowSolAIterAddData.setVisible(false);
            tempBut.setVisible(false);

            // store user data
            int NumOfEquations = results.length;
            initialGuessArr = new double[ NumOfEquations ];
            for(int i = 0; i < NumOfEquations; i++){

                try { initialGuessArr[i] = Double.parseDouble( intialGuessTF[i].getText() ); }
                catch(NumberFormatException ea) { initialGuessArr[i] = 1; } // default initial guess

            }
            if( !BooleanRE_NI ){

                try { NOI_RE = Double.parseDouble( RelativeErrorTF.getText() ); }
                catch(NumberFormatException ea) { NOI_RE = 0.05; } // default relative error

                try { maxIterationsValue = Integer.parseInt( maxIterationTF.getText() ); }
                catch(NumberFormatException ea) { maxIterationsValue = 1000; } // default relative error

            }
            else {

                try { NOI_RE = Double.parseDouble( NumOfIterationsTF.getText() ); } // default Num of Iterations
                catch(NumberFormatException ea) { NOI_RE = 10; }

            }
            System.out.println( Arrays.toString( initialGuessArr ) );
            System.out.println(NOI_RE);

            // call Algo of jacobi or siedel
            if( choosenMethod == "JacobiIteration" ){

                // call jacobi method and get solution in SolutionArr the call connect
                Jacobi JacobiObj = new Jacobi();
                double[] ans = JacobiObj.solve(cofficients, results, initialGuessArr, NOI_RE, BooleanRE_NI);

                // validity
                if( JacobiObj.IsValid() ) {
                    gotTime = JacobiObj.getTime();
                    conWithDisAns( ans );
                    System.out.println( "in jacobi: valid" );
                }
                else CreateExpLab();

            }else if( choosenMethod == "GaussSeidel" ){

                // call GaussSeidel method and get solution in SolutionArr the call connect
                 GaussSeidel GaussSeidelObj = new GaussSeidel(cofficients, results, initialGuessArr, NOI_RE, BooleanRE_NI);
                double[] ans = GaussSeidelObj.solve();

                // validity
                if( GaussSeidelObj.getValid() ) {
                    gotTime = GaussSeidelObj.getTime();
                    conWithDisAns( ans );
                }
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

        int NumOfEquations = results.length;
        Logic.setBorder(new EmptyBorder(800,150 + (NumOfEquations)*100,0, 0));

        // initial Guess Label
        intialGuessLabel = new JLabel("intial guass you want to use");
        intialGuessLabel.setBounds(500, 20, 400, 60);
        intialGuessLabel.setHorizontalAlignment(JLabel.CENTER);
        intialGuessLabel.setForeground(Color.black);
        intialGuessLabel.setFont( new Font("Times New Roman", Font.BOLD, 30) );
        Logic.add(intialGuessLabel);

        // text fields
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
        RelativeErrorB.setBackground(GlobalFrame.secUsedColor);
        RelativeErrorB.setForeground(new Color(0xFFFFFF));
        RelativeErrorB.addActionListener(this);
        Logic.add(RelativeErrorB);

        // NOI Button
        NumOfIterationsB = new JButton("iterate by number of iterations");
        NumOfIterationsB.setFont(new Font( "Times New Roman", Font.BOLD, 30 ));
        NumOfIterationsB.setBounds(800, 300, 500, 50);
        NumOfIterationsB.setHorizontalAlignment(JButton.CENTER);
        NumOfIterationsB.setBackground(GlobalFrame.secUsedColor);
        NumOfIterationsB.setForeground(new Color(0xFFFFFF));
        NumOfIterationsB.addActionListener(this);
        Logic.add(NumOfIterationsB);

        editButtonsDisplay();

    }

    private void ButtonDisplay(MyButton but){
        but.setRadius(200);
        but.setForeground(Color.white);
        but.setBackground(GlobalFrame.secUsedColor);
        but.setColorClick(GlobalFrame.secUsedColor);
        but.setColor(GlobalFrame.secUsedColor);
        but.setColorOver(GlobalFrame.background);
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

    public void editButtonsDisplay(){
        tempBut = new JButton();
        tempBut.setBounds(1600, 900, 0, 1);
        tempBut.setBackground(GlobalFrame.background);
        tempBut.setBorder(null);
        tempBut.setEnabled(false);
        Logic.add(tempBut);
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
        editButtonsDisplay();
        System.out.println("in exception");
    }

}
