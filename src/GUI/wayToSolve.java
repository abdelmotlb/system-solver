package GUI;
import javax.swing.*;
import java.awt.*;

public class wayToSolve {
    private double cofficients[][];
    private double results[];
    private JPanel Logic;
    public wayToSolve(JPanel Logic, double cofficients[][], double results[]){
        this.Logic = Logic;
        this.cofficients = cofficients;
        this.results = results;
        displayChoices();
    }

    public void displayChoices(){

        JLabel txt = new JLabel("choose the type you want");
        txt.setBounds(550, 300, 500, 100);
        txt.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        txt.setHorizontalAlignment(JLabel.CENTER);
        txt.setVerticalAlignment(JLabel.CENTER);
        txt.setOpaque(true);
        Logic.add(txt);

        MyButton GaussElimination = new MyButton("Gauss Elimination");
        GaussElimination.setBounds(650, 0, 200, 200);
        ButtonDisplay(GaussElimination);

        MyButton GaussJordan = new MyButton("Gauss-Jordan");
        GaussJordan.setBounds(100, 150, 200, 200);
        ButtonDisplay(GaussJordan);

        MyButton LUDecomposition = new MyButton("LU Decomposition");
        LUDecomposition.setBounds(1100, 150, 200, 200);
        ButtonDisplay(LUDecomposition);

        MyButton GaussSeidel = new MyButton("Gauss-Seidel");
        GaussSeidel.setBounds(350, 450, 200, 200);
        ButtonDisplay(GaussSeidel);

        MyButton JacobiIteration = new MyButton("Jacobi-Iteration");
        JacobiIteration.setBounds(950, 450, 200, 200);
        ButtonDisplay(JacobiIteration);

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


}
