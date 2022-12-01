package logic;

import java.util.Arrays;

public class GaussSeidel {


    // get by constructor
    int n;

    double a[][],  b[], x[], e;
    boolean error;

    // initialized to solve:
    private double[] temp;
    private double[] rel_error;
    private double  max_err= 0;


    public GaussSeidel(double[][] a, double[] b, double[] x, double e, boolean error) {
        this.a = a;
        this.b = b;
        this.x = x;
        this.e = e;
        this.error = error;
        this.n = b.length;
        temp = new double [n];
        rel_error = new double [n];

    }

    int counter = 0;

    private void Algo(){

        while (true){

            // counter inc
            counter++;

//            for (int i = 0 ; i < n ; i++){
//                x[i] = b[i];
//                for (int j =0 ; j < n ; j++){
//                    if (i != j){
//                        x[i] -= a[i][j]*x[j] ;
//                    }
//                }
//                x[i] = x[i] / a[i][i];
//            }

            for (int i = 0; i < n; i++) {
                double sum = b[i]; // b_n
                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= a[i][j] * x[j];
                // Update xi to use in the next
                // row calculation
                x[i] = 1 / a[i][i] * sum;
            }

            System.out.println("counter : " + e);
            System.out.println(Arrays.toString( x ));

            // check ending iterations.
            if (error == false) {
                max_err =0;
                for (int i = 0 ; i < n ;i++) {
                    rel_error[i] = Math.abs(temp[i] - x[i])/Math.abs(x[i]);
                    if (max_err < rel_error[i]) { max_err = rel_error[i]; }
                }
                if (max_err  <= e) {
                    break;
                }
            }else {
                e--;
                if (e==0){break;}
            }
            for(int i = 0 ; i < n ; i++){
                temp[i] = x[i] ;
            }
        }

    }
    public double[] solve(){
        Algo();
        return x;
    }
}
