package logic;

import GUI.GlobalFrame;

public class chelosky {

    private boolean valid = true;
    private double[][] arr2;
    private int n;
    private double[] b2;
    private double[] y;
    private double[] ans;
    private long time;
    private int pres = GlobalFrame.precision;

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

    public chelosky(double[][] arr, double[] b) {
        this.n = arr.length;
        arr2 = new double[n][n];
        b2 = new double[n];
        // copy equations to keep original
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr2[i][j] = approx(arr[i][j], pres);
            }
            b2[i] = approx(b[i], pres);
        }
    }

    private int scaling(int row) {
        double[] temp = new double[n - row];
        int maxIndex = row;
        for (int i = row; i < n; i++) {
            double mymax = arr2[i][row];
            if (mymax <= 0)
                mymax *= -1;

            if (GlobalFrame.useScaling) {
                for (int j = row + 1; j < n; j++) {
                    double toComp = arr2[i][j];
                    if (toComp < 0) {
                        toComp *= -1;
                    }
                    if (toComp > mymax) {
                        mymax = toComp;
                    }
                }
                if (mymax == 0) {
                    valid = false;
                    break;
                }
            }

            temp[i - row] = arr2[i][row] / mymax;
            if (temp[i - row] < 0)
                temp[i - row] *= -1;
        }
        for (int i = 0; i < n - row - 1; i++) {
            if (temp[i + 1] > temp[i])
                maxIndex = i + 1 + row;
        }
        return maxIndex;
    }

    private void pivoting(int row) {
        int maxIndex = scaling(row);
        if (maxIndex >= 0) {
            if (arr2[row][maxIndex] != 0) {
                if (maxIndex != row) {
                    double[] temp = new double[n];
                    temp = arr2[row];
                    arr2[row] = arr2[maxIndex];
                    arr2[maxIndex] = temp;
                    double temp2 = b2[row];
                    b2[row] = b2[maxIndex];
                    b2[maxIndex] = temp2;
                }
            } else
                valid = false;
        } else
            valid = false;
    }

    private void forElimination() {
        int n = arr2.length;
        for (int k = 0; k < n - 1 && valid; k++) {
            for (int i = k + 1; i < n; i++) {
                pivoting(i);
                if (arr2[k][k] == 0) {
                    valid = false;
                    return;
                }
                double factor = approx(arr2[i][k] / arr2[k][k], pres);
                arr2[i][k] = factor;
                for (int j = k + 1; j < n; j++)
                    arr2[i][j] = approx(arr2[i][j] - factor * arr2[k][j], pres);
            }
        }
        // New U
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                arr2[i][j] = approx(arr2[i][j] / arr2[i][i], pres);
            }
        }
    }

    // LY = B
    // DUX = Y

    public void forSubstitution() {
        y[0] = b2[0];
        for (int i = 1; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum = approx(sum + (y[j] * arr2[i][j]), pres);
            }
            y[i] = approx(b2[i] - sum, pres);
        }
    }

    public void DiagonalEvaluation() {
        for (int i = 0; i < n; i++) {
            y[i] = approx(y[i] / arr2[i][i], pres);
        }
    }

    // Next
    // DUX = Y

    private void backSubstitution() {
        int n = arr2.length;
        if (arr2[n - 1][n - 1] == 0) {
            valid = false;
            return;
        }
        ans[n - 1] = approx(y[n - 1], pres);
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = approx(sum + arr2[i][j] * ans[j], pres);
            }
            if (arr2[i][i] == 0) {
                valid = false;
                return;
            }
            ans[i] = approx(y[i] - sum, pres);
        }
    }

    public double[] solve() {
        ans = new double[n];
        y = new double[n];
        time = System.nanoTime();
        forElimination();
        forSubstitution();
        DiagonalEvaluation();
        backSubstitution();
        time = System.nanoTime() - time;
        return ans;
    }

    public long getTime() {
        return time;
    }

    public boolean getValid() {
        return valid;
    }

}
