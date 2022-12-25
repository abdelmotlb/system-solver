package org.example.logic_partB;

import java.io.IOException;
import java.util.ArrayList;

import org.example.evaluate.calculate;
import org.example.logic_partA.approximation;

public class Newton {

    // main values variables
    private double x0;
    private String fx;
    private int nOfItirations;
    private double es;
    private int pres;

    // array of each step xi, f(xi), fdash(xi), fxi+1, error
    public ArrayList<double[]> ans = new ArrayList<double[]>();

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
    public Newton(double x0, String fx, int nOfItirations, double es, int pres) {
        this.x0 = x0;
        this.fx = fx;
        this.nOfItirations = nOfItirations;
        this.es = es;
        this.pres = pres;
    }

    public boolean isValid() {
        return valid;
    }

    private void getRoot() {

        double xold = x0;
        double xnew;
        double error;

        while (nOfItirations-- != 0 && isValid()) {
            double[] oneIteration = new double[5];
            double fxi = calculate.getFunctionOutput(fx, xold);
            double dfxi = calculate.getDerivativeOutput(xold);
            xnew = approximation.sigFig(xold - (fxi / dfxi), pres);
            error = Math.abs(xnew - xold) / xnew * 100;
            oneIteration[0] = approximation.sigFig(xold, pres);
            oneIteration[1] = approximation.sigFig(fxi, pres);
            oneIteration[2] = approximation.sigFig(dfxi, pres);
            oneIteration[3] = approximation.sigFig(xnew, pres);
            oneIteration[4] = error;
            xold = xnew;
            ans.add(oneIteration);
            if (Math.abs(error) < Math.abs(es))
                return;
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

        // for (int i = 0; i < ans.size(); i++) {
        // for (int j = 0; j < 5; j++)
        // System.out.print(ans.get(i)[j] + " ");
        // System.out.println();
        // }

        return ans;
    }

}
