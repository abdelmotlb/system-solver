package org.example.logic_partB;

import org.example.evaluate.calculate;
import org.example.logic_partA.approximation;

import java.util.ArrayList;

import static org.example.evaluate.calculate.getFunctionOutput;

public class Bisection {
    private String fX;
    private double Epsilon;
    private int Iterations;
    private double Xl, Xr, Xu;
    private int precision;
    private long time;

    private boolean validVar;
    private ArrayList<double[]> arrList = new ArrayList<double[]>();

    public long getTime() {
        return time;
    }

    public boolean isValidVar() {
        return validVar;
    }

    private boolean checkIsValid() {
        return (getFunctionOutput(fX, Xl) * getFunctionOutput(fX, Xu) < 0);

    }

    public Bisection(String fX, double epsilon, int iterations, double xl, double xu, int precision) {
        this.fX = fX;
        this.Epsilon = epsilon;
        this.Iterations = iterations;
        this.Xl = xl;
        this.Xu = xu;
        this.precision = precision;
    }

    private void algorithm() {

        // first condition check
        validVar = true;
        if (!checkIsValid()) {
            validVar = false;
            return;
        }

        Xr = Double.MAX_VALUE;
        double prev_Xr;
        int iterationsEnded = 0;
        double f_Xr, f_Xl, f_Xu;
        do {

            double[] iterationValues = new double[8];

            // get prev xr
            prev_Xr = Xr;
            f_Xl = approximation.sigFig(getFunctionOutput(fX, Xl), precision);
            f_Xu = approximation.sigFig(getFunctionOutput(fX, Xu), precision);

            // iteration algorithm
            Xr = (Xl + Xu) / 2;
            Xr = approximation.sigFig(Xr, precision);
            f_Xr = approximation.sigFig(getFunctionOutput(fX, Xr), precision);
            // System.out.println("Xl : " +Xl+" Xr : "+ Xr + " Xu : "+Xu);

            if (f_Xr * f_Xl < 0) {
                Xu = Xr;
            } else if (f_Xr * f_Xl > 0) {
                Xl = Xr;
            } else {
                iterationValues[0] = iterationsEnded;
                iterationValues[1] = Xl;
                iterationValues[2] = Xu;
                iterationValues[3] = Xr;
                iterationValues[4] = f_Xl;
                iterationValues[5] = f_Xu;
                iterationValues[6] = f_Xr;
                iterationValues[7] = Xr - prev_Xr;
                arrList.add(iterationValues);
                return;
            }
            iterationsEnded++;

            // continue another iteration
            iterationValues[0] = iterationsEnded;
            iterationValues[1] = Xl;
            iterationValues[2] = Xu;
            iterationValues[3] = Xr;
            iterationValues[4] = f_Xl;
            iterationValues[5] = f_Xu;
            iterationValues[6] = f_Xr;
            iterationValues[7] = Xr - prev_Xr;
            arrList.add(iterationValues);

        } while (Math.abs(Xr - prev_Xr) > Epsilon && iterationsEnded < Iterations);
    }

    public ArrayList<double[]> solve() {
        time = System.nanoTime();

        algorithm();

        time = System.nanoTime() - time;

        return arrList;
    }
}
