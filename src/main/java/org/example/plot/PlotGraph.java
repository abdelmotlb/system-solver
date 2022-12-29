package org.example.plot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.example.evaluate.calculate;

public class PlotGraph {
    private String function;
    private double xu;
    private double xl;

    public PlotGraph() {
    }

    public void plotBisection(String function, double xl, double xu, double xlnew, double xunew) {
        this.function = function;
        this.xu = xu;
        this.xl = xl;
        writeForBisection(xlnew, xunew);
        callScript call = new callScript("bisection.py");
    }

    public void plotFalsePosition(String function, double xl, double xu, double xlnew, double xunew) {
        this.function = function;
        this.xu = xu;
        this.xl = xl;
        writeForFalse(xlnew, xunew);
        callScript call = new callScript("falsePosition.py");
    }

    public void plotFixedPoint(String function, double xu, double point, double starty) {
        this.function = function;
        this.xu = xu;
        writeForFixed(point, starty);
        callScript call = new callScript("fixedPoint.py");
    }

    public void plotNewton(String function, double xu, double point) {
        this.function = function;
        this.xu = xu;
        writeForNewton(point);
        callScript call = new callScript("newton.py");
    }

    public void plotSecant(String function, double xu, double x1, double x2) {
        this.function = function;
        this.xu = xu;
        writeForSecant(x1, x2);
        callScript call = new callScript("secant.py");
    }

    private void writeForNewton(double about) {
        File file = new File("./function2.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // prepare step between 1000 points
        double step = 2 * (xu - xl) / 1000;
        xu += (xu - xl);

        // write x values to first line in the file
        System.out.println(xl + " " + xu);
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(i);
            writer.print(" ");
        }

        // write y values to second line in the file
        writer.print("\n");
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(calculate.getFunctionOutput(function, i));
            writer.print(" ");
        }
        writer.print("\n");
        try {
            calculate.makeDerivative(function);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        double drev = calculate.getDerivativeOutput(about);
        double y = calculate.getFunctionOutput(function, about);
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(drev * i + (y - drev * about));
            writer.print(" ");
        }

        writer.close();
    }

    private void writeForSecant(double x1, double x2) {
        File file = new File("./function2.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // prepare step between 1000 points
        double step = 2 * (xu - xl) / 1000;
        xu += (xu - xl);

        // write x values to first line in the file
        System.out.println(xl + " " + xu);
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(i);
            writer.print(" ");
        }

        // write y values to second line in the file
        writer.print("\n");
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(calculate.getFunctionOutput(function, i));
            writer.print(" ");
        }
        writer.print("\n");
        writer.print(x1);
        writer.print("\n");
        writer.print(x2);
        writer.print("\n");
        writer.print(calculate.getFunctionOutput(function, x1));
        writer.print("\n");
        writer.print(calculate.getFunctionOutput(function, x2));

        writer.close();
    }

    private void writeForFixed(double point, double starty) {
        File file = new File("./function2.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // prepare step between 1000 points
        double step = 2 * (xu - xl) / 1000;
        xu += (xu - xl);

        // write x values to first line in the file
        System.out.println(xl + " " + xu);
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(i);
            writer.print(" ");
        }

        // write y values to second line in the file
        writer.print("\n");
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(calculate.getFunctionOutput(function, i));
            writer.print(" ");
        }
        writer.print("\n");
        writer.print(point);
        writer.print("\n");
        writer.print(starty);
        writer.print("\n");
        writer.print(calculate.getFunctionOutput(function, point));

        writer.close();
    }

    private void writeForBisection(double xlnew, double xunew) {
        File file = new File("./function2.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // prepare step between 1000 points
        double step = (xu - xl) / 1000;

        // write x values to first line in the file
        System.out.println(xl + " " + xu);
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(i);
            writer.print(" ");
        }

        // write y values to second line in the file
        writer.print("\n");
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(calculate.getFunctionOutput(function, i));
            writer.print(" ");
        }
        writer.print("\n");
        writer.print(xlnew);
        writer.print("\n");
        writer.print(xunew);

        writer.close();
    }

    private void writeForFalse(double xlnew, double xunew) {
        File file = new File("./function2.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // prepare step between 1000 points
        double step = (xu - xl) / 1000;

        // write x values to first line in the file
        System.out.println(xl + " " + xu);
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(i);
            writer.print(" ");
        }

        // write y values to second line in the file
        writer.print("\n");
        for (double i = Double.min(xl, xu); i <= Double.max(xl, xu); i += Math.abs(step)) {
            writer.print(calculate.getFunctionOutput(function, i));
            writer.print(" ");
        }
        writer.print("\n");
        writer.print(xlnew);
        writer.print("\n");
        writer.print(xunew);
        writer.print("\n");
        writer.print(calculate.getFunctionOutput(function, xlnew));
        writer.print("\n");
        writer.print(calculate.getFunctionOutput(function, xunew));

        writer.close();
    }
}
