package org.example.logic_partB;

import java.io.IOException;
import java.util.ArrayList;

import org.example.evaluate.calculate;
import org.example.logic_partA.approximation;

public class Newton {

    // main values variables
    private double x0;
    private String fx;
    private int nOfItirations, nOfItirationsEnded;
    private double es;
    private int pres;
    private boolean errorStop = false;

    // array of each step xi, f(xi), fdash(xi), fxi+1, error
    private ArrayList<double[]> ans = new ArrayList<double[]>();

    // check for errors
    private boolean valid = true;

    // constructor for number of itirations stoping criteria
    public Newton(double x0, String fx, int nOfItirations, int pres) {
        this.x0 = x0;
        this.fx = fx;
        this.nOfItirations = nOfItirations;
        this.pres = pres;
    }

    // constructor for relative error stoping criteria
    public Newton( String fx, double es, int nOfItirations, double x0, int pres) {
        this.x0 = x0;
        this.fx = fx;
        this.nOfItirations = nOfItirations;
        this.es = es;
        this.errorStop = true;
        this.pres = pres;
    }

    public boolean isValid() {
        return valid;
    }

    private void getRoot() {

        nOfItirationsEnded = 0;

        double xold = x0;
        double xnew;
        double error;

        while (nOfItirationsEnded != nOfItirations && isValid()) {
            double[] oneIteration = new double[6];
            double fxi = calculate.getFunctionOutput(fx, xold);
            double dfxi = calculate.getDerivativeOutput(xold);
            if (dfxi == 0) {
                valid = false;
                System.out.println( "in drivative = 0" );
                return;
            }
            xnew = approximation.sigFig(xold - (fxi / dfxi), pres);
            error = Math.abs(xnew - xold) / xnew * 100;
            oneIteration[0] = nOfItirationsEnded + 1;
            oneIteration[1] = approximation.sigFig(xold, pres);
            oneIteration[2] = approximation.sigFig(fxi, pres);
            oneIteration[3] = approximation.sigFig(dfxi, pres);
            oneIteration[4] = approximation.sigFig(xnew, pres);
            oneIteration[5] = error;
            xold = xnew;
            ans.add(oneIteration);
            if (errorStop && Math.abs(error) < Math.abs(es))
                return;
            nOfItirationsEnded++;
        }

    }

    public ArrayList<double[]> solve() {

        // evaluate derivative symbolicaly
        try {
            calculate.makeDerivative(fx);
        } catch (IOException | InterruptedException e) {
            System.out.println("cannot make derivaitve");
            valid = false;
            e.printStackTrace();
            return null;
        }
        getRoot();

        if (!valid) {
            System.out.println("error in newton");
            return null;
        }

        // for (int i = 0; i < ans.size(); i++) {
        // for (int j = 0; j < 5; j++)
        // System.out.print(ans.get(i)[j] + " ");
        // System.out.println();
        // }

        return ans;
    }

}
