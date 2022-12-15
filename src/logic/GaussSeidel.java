package logic;

import GUI.GlobalFrame;

public class GaussSeidel {
    // get by constructor
    int n;

    double a[][], b[], x[], e;
    boolean error;

    // initialized to solve:
    private double[] temp;
    private double[] rel_error;
    private double max_err = 0;

    private int counter = 0;
    private boolean valid = true;
    private long time;

    public boolean getValid() {
        return valid;
    }

    public long getTime() {
        return time;
    }

    private double approx(double num, int pr) {
        int temp = pr;
        // for leading zeros
        if ((int) num == 0) {
            while (num * Math.pow(10, temp) < Math.pow(10, pr - 1))
                temp++;
            return approx(Math.round(num * Math.pow(10, temp)) / Math.pow(10.0, temp), GlobalFrame.precision);
            // length of whole number > number of presction
        } else if ((int) num > Math.pow(10, pr) - 1) {
            temp = 1;
            while (num / Math.pow(10, temp) > Math.pow(10, pr - 1)) {
                temp++;
            }
            return approx((Math.round(num / Math.pow(10, temp - 1))) * Math.pow(10, temp - 1), GlobalFrame.precision);
            // not long not leading zeros
        } else {
            temp = pr - ((int) Math.log10(num) + 1);
            return approx(Math.round(num * Math.pow(10, temp)) / Math.pow(10.0, temp), GlobalFrame.precision);
        }
    }

    public GaussSeidel(double[][] a, double[] b, double[] x, double e, boolean error) {
        this.a = a;
        this.b = b;
        this.x = x;
        this.e = e;
        this.error = error;
        this.n = b.length;
        temp = new double[n];
        rel_error = new double[n];

    }

    private void Algo() {

        try {
            while (true) {

                // counter inc
                counter++;
                if (counter == 101) {
                    break;
                }

                for (int i = 0; i < n; i++) {
                    double sum = b[i]; // b_n
                    for (int j = 0; j < n; j++)
                        if (j != i)
                            sum = approx(sum - a[i][j] * x[j], GlobalFrame.precision);

                    // checking division by zero
                    // System.out.println("matrix :" + a[i][i]);
                    if (a[i][i] == 0)
                        throw new Exception();

                    // Update xi to use in the next
                    // row calculation
                    x[i] = approx(1 / a[i][i] * sum, GlobalFrame.precision);
                }

                // check ending iterations.
                if (!error) {
                    max_err = 0;
                    for (int i = 0; i < n; i++) {
                        rel_error[i] = approx(Math.abs(temp[i] - x[i]) / Math.abs(x[i]), GlobalFrame.precision);
                        // System.out.println(x[i]);
                        if (max_err < rel_error[i]) {
                            max_err = rel_error[i];
                        }
                    }
                    if (max_err <= e) {
                        break;
                    }
                } else {
                    e--;
                    if (e == 0) {
                        break;
                    }
                }
                for (int i = 0; i < n; i++) {
                    temp[i] = x[i];
                }
            }
        } catch (Exception e) {
            valid = false;
        }

    }

    public double[] solve() {
        time = System.nanoTime();
        Algo();
        time = System.nanoTime() - time;
        return x;
    }
}
