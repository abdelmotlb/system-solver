package org.example.logic_partB;

//public class SecantMethod {
//}

import org.example.logic_partA.approximation;

import static org.example.evaluate.calculate.getFunctionOutput;

public class SecantMethod{

    private String fX;
    private double Epsilon;
    private int Iterations;
    private double x1, x2, x3;
    private int precision;
    private boolean valid;
    private long time;


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
        double fX1, fX2, fDifference, xDifference;
        x3 = x2;
        x2 = x1;
        do{

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
            System.out.println("xi :        " +x2+"    xi-1 :     "+ x1 + "     xi+1 :     "+x3);


            // increment
            iterationsEnded++;
        }while ( Math.abs(x3 - x2) >= Epsilon && iterationsEnded < Iterations );
    }

    public double solve(){
        time = System.nanoTime();

        algorithm();

        time = System.nanoTime() - time;

        return x3;
    }

}
