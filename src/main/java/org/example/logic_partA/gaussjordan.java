package org.example.logic_partA;


import org.example.GUI.GlobalFrame;

public class gaussjordan {
    private boolean valid = true;
    private double[][] arr2;
    private int n;
    private double[] b2;
    private long time;
    private int pres = GlobalFrame.precision;

    private int scaling(int row) {
        double[] temp = new double[n - row];
        int maxIndex = row;
        for (int i = row; i < n; i++) {
            double mymax = arr2[i][row];
            if (mymax <= 0)
                mymax = -1;
            for (int j = row + 1; j < n; j++) {
                double toComp = arr2[i][j];
                if (toComp < 0) {
                    toComp= -1;
                }
                if (toComp > mymax) {
                    mymax = toComp;
                }
            }
            if (mymax == 0) {
                valid = false;
                break;
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
        int maxIndex;
        if (GlobalFrame.useScaling)
            maxIndex = scaling(row);
        else {
            maxIndex = row;
            double maxn = Math.abs(arr2[row][row]);
            for (int i = row + 1; i < n; i++) {
                if (Math.abs(arr2[i][row]) > maxn) {
                    maxn = arr2[i][row];
                    maxIndex = i;
                }
            }
        }
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
        for (int k = 0; k < n && valid; k++) {
            pivoting(k);
            double factor1 = arr2[k][k];
            if (factor1 == 0) {
                valid = false;
                return;
            }
            for (int l = k; l < n; l++) {
                arr2[k][l] = approximation.sigFig(arr2[k][l] / factor1, pres);
            }
            b2[k] = approximation.sigFig(b2[k] / factor1, pres);
            for (int i = k + 1; i < n; i++) {
                double factor = arr2[i][k];
                for (int j = k + 1; j < n; j++)
                    arr2[i][j] = approximation.sigFig(arr2[i][j] - factor * arr2[k][j], pres);
                b2[i] = approximation.sigFig(b2[i] - factor * b2[k], pres);
            }
            for (int i = k - 1; i >= 0; i--) {
                double factor = arr2[i][k];
                for (int j = k + 1; j < n; j++)
                    arr2[i][j] = approximation.sigFig(arr2[i][j] - factor * arr2[k][j], pres);
                b2[i] = approximation.sigFig(b2[i] - factor * b2[k], pres);
            }
        }
    }

    public double[] solve() {
        time = System.nanoTime();
        forElimination();
        time = System.nanoTime() - time;
        if (valid)
            return b2;
        else
            return new double[n];
    }

    public boolean getValid() {
        return this.valid;
    }

    public gaussjordan(double[][] arr, double[] b) {
        this.n = arr.length;
        // copy equations to keep original
        arr2 = new double[n][n];
        b2 = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr2[i][j] = approximation.sigFig(arr[i][j], pres);
            }
            b2[i] = approximation.sigFig(b[i], pres);
        }
    }

    public long getTime() {
        return time;
    }

}
