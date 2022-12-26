package org.example.evaluate;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.*;
import java.util.Scanner;

public class calculate {

    public calculate() {
    }

    public static void makeDerivative(String inputEqn) throws IOException, InterruptedException {

        // handle power case
        for (int i = 0; i < inputEqn.length(); i++) {
            if (inputEqn.charAt(i) == '^') {
                inputEqn = inputEqn.substring(0, i) + "**" + inputEqn.substring(i + 1, inputEqn.length());
            }
        }
        // System.out.println( "inputEqn " + inputEqn );

        // write got string in file
        String filePath = "./function.txt";
        File file = new File(filePath);
        PrintWriter writer = new PrintWriter(file);
        writer.print(inputEqn);
        writer.close();
        // end write got string in file

        // run python....
        String Script_Path = "./src/main/java/org/example/evaluate/getDerivative.py";
        ProcessBuilder Process_Builder = new ProcessBuilder("py", Script_Path).inheritIO();
        Process Demo_Process = Process_Builder.start();
        Demo_Process.waitFor();
        BufferedReader Buffered_Reader = new BufferedReader(new InputStreamReader(Demo_Process.getInputStream()));
        // end run python
    }

    public static double getDerivativeOutput(double value) {

        String data = "-1";
        double result = -1;
        File file;

        // get derivative function from python
        // try {
        // makeDerivative(inputEqn);
        // } catch (Exception ea) {
        // }
        // end derivative function from python

        try {

            // get data
            file = new File("./derivativeFunction.txt");
            Scanner myReader = new Scanner(file);
            data = myReader.nextLine();
            // System.out.println( "output files data: " );
            // System.out.println(data);
            myReader.close();
            // end get data

            // replacing
            data = data.replaceAll("x", Double.toString(value));
            for (int i = 0; i < data.length() - 1; i++) {
                if (data.charAt(i) == '*' && data.charAt(i + 1) == '*') {
                    data = data.substring(0, i) + '^' + data.substring(i + 2, data.length());
                }
            }
            // System.out.println( "data :"+ data );
            // end replacing

            // evaluate
            ExpressionBuilder eb = new ExpressionBuilder(data);
            Expression ex = eb.build();
            result = ex.evaluate();
            // System.out.println(result);
            // end evaluate

        } catch (Exception ea) {
            System.out.println("in get output error");
        }

        return result;
    }

    public static double getFunctionOutput(String inputEqn, double value) {
        double result = -1;

        // replacing
        inputEqn = inputEqn.replaceAll("x", Double.toString(value));
        for (int i = 0; i < inputEqn.length() - 1; i++) {
            if (inputEqn.charAt(i) == '*' && inputEqn.charAt(i + 1) == '*') {
                inputEqn = inputEqn.substring(0, i) + '^' + inputEqn.substring(i + 2, inputEqn.length());
            }
        }
        // System.out.println( "data :"+ inputEqn );
        // end replacing

        // evaluate
        ExpressionBuilder eb = new ExpressionBuilder(inputEqn);
        Expression ex = eb.build();
        result = ex.evaluate();
        // System.out.println(result);
        // end evaluate

        return result;
    }

}
