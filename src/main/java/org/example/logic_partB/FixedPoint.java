package org.example.logic_partB;

import java.util.ArrayList;

import javax.sound.midi.Soundbank;

import java.util.*;
import org.example.evaluate.calculate;
import org.example.logic_partA.approximation;

public class FixedPoint {
      
        double relativeError = 0.0;
        String gX;
        private long time;
        private int counter = 0;
        private double guess = 0; //default
        private int Iterations = 50;//default
         double Epsilon = 0.00001;//default
        List<double[]> arrList = new ArrayList<double[]>();
        int precision;
        double xn, xp;
        boolean epsFlag = false;

        //takes g(x) and max iterations and relative error
        public FixedPoint(double Epsilon, String gX, int Iterations, double guess, int precision){
            this.Epsilon = Epsilon;
            this.gX = gX;
            this.guess = guess;
            this.precision = precision;
            this.Iterations = Iterations;
            epsFlag = true;
            
        }
        //takes g(x) and max iterations only
        public FixedPoint(String gX, int Iterations, double guess, int precision){
            this.gX =gX;
            this.precision = precision;
            this.Iterations=Iterations;
        }

        //temp arrays containing the states of each iterations
        //this array is appended to the list to be sent to the GUI
        //order: Iterations, Guess, g(xi), approximate relative error
        private void algorithm(){
          try{
            double xp = guess;
            boolean valid = true;

            while(valid){

                double[] temp = new double[4];
                counter++;

                xn = approximation.sigFig(calculate.getFunctionOutput(gX, xp), precision);
                
                //Max Iterations reached
                if(counter == Iterations){
                   valid = false;
                }
                
                relativeError = ((xn - xp)/xn);

                //relative error achieved

                if( epsFlag && Math.abs(relativeError) < Epsilon  ){
                    valid = false;
                }
                //continue another iteration
                temp[0] = counter;
                temp[1] = xn;
                temp[2] = approximation.sigFig(calculate.getFunctionOutput(gX, xn), precision); //sent to evaluate new X
                temp[3] = Math.abs(relativeError);
                arrList.add(temp);

                xp = xn;
              }
            } catch(Exception e){

         }
      }


        public List<double[]> solve(){
            time = System.nanoTime();
            
            algorithm();
            
            time = System.nanoTime() - time;
            
        //     System.out.println(arrList.size());
        // for (int i = 0; i < arrList.size(); i++) {
        //     for (int j = 0; j < 4; j++){
              
        //         System.out.print(arrList.get(i)[j] + " ");}
        //         System.out.println();
        // }
       
            return arrList;
        }

        public long getTime(){
            return time;
        }  
}
