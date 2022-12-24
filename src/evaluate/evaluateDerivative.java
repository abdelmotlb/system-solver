package evaluate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class evaluateDerivative {
    public void calcFirstDerivative(String function, double value) throws IOException, InterruptedException {

        // equation write to file
        File file = new File("D:/university/y2.term1/Numerical/project/phase2/copy1/src/evaluate/function.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.print(function);
        writer.close();

        String evaluationValue = Double.toString(value);
        File file2 = new File("D:/university/y2.term1/Numerical/project/phase2/copy1/src/evaluate/value.txt");
        PrintWriter writer2 = new PrintWriter(file2);
        writer2.print(evaluationValue);
        writer2.close();

        // define script and call it
        String Script_Path = "D:/university/y2.term1/Numerical/project/phase2/copy1/src/evaluate/drivative_evaluate.py"; // "D:/university/y2.term1/Numerical/project/phase2 test/python/drivative_evaluate.py";
        ProcessBuilder Process_Builder = new ProcessBuilder("py", Script_Path).inheritIO();

        Process Demo_Process = Process_Builder.start();
        Demo_Process.waitFor();

        BufferedReader Buffered_Reader = new BufferedReader(new InputStreamReader(Demo_Process.getInputStream()));
        String Output_line = "";

        // get answer from python
        while ((Output_line = Buffered_Reader.readLine()) != null) {
            System.out.println("output");
            System.out.println(Output_line);
        }

//        getOutput();

    }

    public void getOutput(){
        try{
            File myObj = new File("D:/university/y2.term1/Numerical/project/phase2/copy1/src/evaluate/output.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println( "output files data: " );
                System.out.println(data);
            }
            myReader.close();
        }catch (Exception ea){
            System.out.println( "in get output error" );
        }
    }
}
