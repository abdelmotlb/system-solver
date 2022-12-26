package org.example.logic_partB;

import org.example.logic_partA.approximation;

import static org.example.evaluate.calculate.getFunctionOutput;

public class falsePosition {
    private String fX;
    private double Epsilon;
    private int Iterations;
    private double Xl, Xr, Xu;
    private int precision;
    private long time;

    private boolean valid;


    public falsePosition(String fX, double epsilon, int iterations, double xl, double xu, int precision) {
        this.fX = fX;
        Epsilon = epsilon;
        Iterations = iterations;
        Xl = xl;
        Xu = xu;
        this.precision = precision;
    }


    public long getTime() {
        return time;
    }

    public boolean isValid() {
        return valid;
    }

    private boolean checkIsValid(){
        return ( getFunctionOutput(fX, Xl) - getFunctionOutput(fX, Xu) < 0 );
    }

    private void algorithm(){

        // first condition check
        if( !checkIsValid() ){
            valid = false;
            return;
        }

        Xr = Double.MAX_VALUE;
        double prev_Xr;
        int iterationsEnded = 0;
        double f_Xr, f_Xl, f_Xu;
        do{
            // get prev xr
            prev_Xr = Xr;
            f_Xl = approximation.sigFig( getFunctionOutput(fX, Xl), precision );
            f_Xu = approximation.sigFig( getFunctionOutput(fX, Xu), precision );

            // iteration algorithm
            Xr =  Xl - (Xu-Xl) * f_Xl / (f_Xu-f_Xl);
            Xr = approximation.sigFig(Xr, precision);
            f_Xr = approximation.sigFig( getFunctionOutput(fX, Xr), precision );
//            System.out.println("Xl : " +Xl+" Xr : "+ Xr + " Xu :     "+Xu);

            if( f_Xr * f_Xl < 0 ){
                Xu = Xr;
            }
            else if( f_Xr * f_Xl > 0 ){
                Xl = Xr;
            }else{
                return;
            }
            iterationsEnded++;
        }while( Math.abs( Xr - prev_Xr ) > Epsilon && iterationsEnded < Iterations );
    }

    public double solve(){
        time = System.nanoTime();

        algorithm();

        time = System.nanoTime() - time;

        return Xr;
    }

}
//#include<bits/stdc++.h>
//#define Fill(arr , val) memset(arr , val , sizeof(arr))
//#define ll long long
//using namespace std;
//        float f(float x){
//            return (3*x*x*x*x+6.1*x*x*x-2*x*x+3*x+2);
//        }
//        float bisection(float l , float u){
//            float xl = l,xu = u;
//            float xrO = 0,xrN = 0;
//            for(int i = 0 ; i < 100 ; i++){
//                xrN = (xl+xu)/2;
//                if(f(xrN)*f(xl) < 0) xu = xrN;
//                else xl = xrN;
//
//                if(i != 0)
//                if(abs(xrN - xrO) < 1e-2)
//                return xrN;
//
//                xrO = xrN;
//
//            }
//            return xrN;
//        }



//        int main(){
//            float l,u;
//            cin>>l>>u;
//            cout<<"Bisection :"<<fixed<<setprecision(5)<<bisection(l,u)<<" in interval : "<<(int)l<<","<<(int)u<<endl;
//            cout<<"Regula-falsi :"<<fixed<<setprecision(5)<<regula_falsi(l,u)<<" in interval : "<<(int)l<<","<<(int)u<<endl;
//            return 0;
//        }