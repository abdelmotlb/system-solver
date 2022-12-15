package logic;

import GUI.GlobalFrame;

public class Jacobi {

    // indicates
    private boolean IsValid = true;
    private long time;

    public boolean IsValid() {
        return this.IsValid;
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
            return Math.round(num * Math.pow(10, temp)) / Math.pow(10.0, temp);
            // length of whole number > number of presction
        } else if ((int) num > Math.pow(10, pr) - 1) {
            temp = 1;
            while (num / Math.pow(10, temp) > Math.pow(10, pr - 1)) {
                temp++;
            }
            return (Math.round(num / Math.pow(10, temp - 1))) * Math.pow(10, temp - 1);
            // not long not leading zeros
        } else {
            temp = pr - ((int) Math.log10(num) + 1);
            return Math.round(num * Math.pow(10, temp)) / Math.pow(10.0, temp);
        }
    }

    private void CopyArrays(double[] Old, double[] New) {
        for (int i = 0; i < New.length; i++)
            Old[i] = New[i];
    }

    // calculates the max relative error for each iteration
    private double MaxRelativeError(double[] Old, double[] New) {
        double max = -1.0;
        for (int i = 0; i < New.length; i++) {
            double error = Math.abs(New[i] - Old[i]) / Math.abs(New[i]);
            if (error > max)
                max = error;
        }

        return max;
    }

    public double[] solve(double[][] cofficient, double[] B, double[] InitialGuess, double Noi_Re, boolean isNum) {
        // number of unknowns of the system (n)
        int n = InitialGuess.length;
        double[] Old = new double[n];
        double[] New = new double[n];
        time = System.nanoTime();
        // start the old vector with the initial guess
        for (int i = 0; i < n; i++)
            Old[i] = InitialGuess[i];
        // if we will work with the number of iterations
        try {
            if (isNum) {
                int NumberOfIterations = (int) Noi_Re;
                int counter = 0;
                // Number of iterations loop
                do {
                    // loop for generating the solution that will store in new
                    for (int i = 0; i < n; i++) {

                        // loop in cofficient matrix to calc solution[i] in iteration of counter.
                        double sum = 0;
                        for (int j = 0; j < n; j++) {
                            if (j == i)
                                continue;
                            sum = approx(sum + (cofficient[i][j] * Old[j]), GlobalFrame.precision);
                        }

                        // checking division by zero
                        if (cofficient[i][i] == 0)
                            throw new Exception();

                        New[i] = approx((B[i] - sum) / cofficient[i][i], GlobalFrame.precision);
                    }
                    CopyArrays(Old, New);

                    counter++;
                } while (counter < NumberOfIterations);
                time = System.nanoTime() - time;
                return New;
            } else {
                double RelativeError = Noi_Re;
                int counter = 100;
                do {
                    // loop for generating the solution that will store in new
                    for (int i = 0; i < n; i++) {

                        // loop in cofficient matrix to calc solution[i] in iteration of counter.
                        double sum = 0;
                        for (int j = 0; j < n; j++) {
                            if (j == i)
                                continue;
                            sum = approx(sum + (cofficient[i][j] * Old[j]), GlobalFrame.precision);
                        }
                        // checking division by zero
                        if (Math.abs((B[i] - sum) / cofficient[i][i]) < Double.POSITIVE_INFINITY)
                            throw new Exception();

                        New[i] = approx((B[i] - sum) / cofficient[i][i], GlobalFrame.precision);
                    }
                    CopyArrays(Old, New);
                    // make sure of approx relative error
                } while (MaxRelativeError(Old, New) > RelativeError && counter < 100);
                time = System.nanoTime() - time;
                return New;
            }
        } catch (Exception e) {
            IsValid = false;
            return Old;
        }
    }

}
