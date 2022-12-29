package org.example.logic_partB;

//public class SecantMethod {
//}

import org.example.logic_partA.approximation;

import java.util.ArrayList;

import static org.example.evaluate.calculate.getFunctionOutput;

public class SecantMethod{

    private String fX;
    private double Epsilon;
    private int Iterations;
    private double x1, x2, x3;
    private int precision;
    private boolean valid;
    private long time;
    private ArrayList<double[]> arrList = new ArrayList<double[]>();


    // constructor , getters.
    public SecantMethod(String fX, double epsilon, int iterations, double x1, double x2, int precision) {
        this.fX = fX;
        Epsilon = epsilon;
        Iterations = iterations;
        this.x1 = x1;
        this.x2 = x2;
        this.precision = precision;
    }

    public long getTime() {
        return time;
    }

    public boolean isValid() {
        return valid;
    }

    // algorithm and output.
    private void algorithm(){

        // update valid variable
        valid = true;

        int iterationsEnded = 0;
        double fX1, fX2, fX3, fDifference, xDifference;
        x3 = x2;
        x2 = x1;
        do{

            double[] iterationValues = new double[8];

            // update values
            x1 = x2;
            x2 = x3;
            fX1 = approximation.sigFig( getFunctionOutput(fX, x1), precision );
            fX2 = approximation.sigFig( getFunctionOutput(fX, x2), precision );

            // valid condition
            if( fX1 - fX2 == 0 ){
                valid = false;
                break;
            }

            // algorithm iteration
            fDifference = approximation.sigFig( fX1 - fX2, precision );
            xDifference = approximation.sigFig( x1 - x2 , precision);
            x3 = approximation.sigFig( x2 - fX2 * xDifference / fDifference, precision );
            fX3 = approximation.sigFig( getFunctionOutput(fX, x3), precision );
            System.out.println("xi :        " +x2+"    xi-1 :     "+ x1 + "     xi+1 :     "+x3);


            // increment
            iterationsEnded++;

            //continue another iteration
            iterationValues[0] = iterationsEnded;
            iterationValues[1] = x1;
            iterationValues[2] = x2;
            iterationValues[3] = fX1;
            iterationValues[4] = fX2;
            iterationValues[5] = x3;
            iterationValues[6] = fX3;
            iterationValues[7] = x3-x2;
            arrList.add(iterationValues);

        }while ( Math.abs(x3 - x2) >= Epsilon && iterationsEnded < Iterations );
    }

    public ArrayList<double[]> solve(){
        time = System.nanoTime();

        algorithm();

        time = System.nanoTime() - time;

        return arrList;
    }

}
