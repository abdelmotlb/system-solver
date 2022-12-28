package org.example.plot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.example.evaluate.calculate;

public class testplot {
    private String function;
    private double xl = 0;
    private double xu;
    boolean twoBounds = false;

    public testplot(String function, double xl, double xu) {
        this.function = function;
        this.xl = xl;
        this.xu = xu;
        twoBounds = true;
    }

    public testplot(String function, double xu) {
        this.function = function;
        this.xu = xu;
    }

    public void plotBisection() throws IOException, InterruptedException {
        File file = new File("./function2.txt");
        PrintWriter writer = new PrintWriter(file);
        double step;
        if (!twoBounds) {
            step = 2 * (xu - xl) / 1000;
            xu += (xu - xl);
        } else {
            step = (xu - xl) / 1000;
        }

        System.out.println(xl + " " + xu);
        for (double i = xl; i <= xu; i += step) {
            // writer.print(Double.toString(i));
            writer.print(i);
            writer.print(" ");
        }
        writer.print("\n");
        for (double i = xl; i <= xu; i += step) {
            writer.print(calculate.getFunctionOutput(function, i));
            writer.print(" ");
        }

        // writer.print(function);
        // writer.print("\n");
        // writer.print(Double.toString(xl));
        // writer.print("\n");
        // writer.print(Double.toString(xu));
        writer.close();

        String Script_Path = "./src/main/java/org/example/plot/plot.py";
        ProcessBuilder Process_Builder = new ProcessBuilder("py", Script_Path).inheritIO();
        Process Demo_Process = Process_Builder.start();
        Demo_Process.waitFor();
        BufferedReader Buffered_Reader = new BufferedReader(new InputStreamReader(Demo_Process.getInputStream()));
        // end run python
    }
}
