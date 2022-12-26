package org.example.logic_partA;


import org.example.GUI.GlobalFrame;

public class crout {
    private double[][] arr2;
    private int n;
    private double[] b2;
    private double[] y;
    private double[] ans;
    private boolean valid = true;
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
                    toComp = -1;
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

    private void backSubstitution() {
        int n = arr2.length;
        ans[n - 1] = approximation.sigFig(y[n - 1], pres);
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = approximation.sigFig(sum + arr2[i][j] * ans[j], pres);
            }
            ans[i] = approximation.sigFig(y[i] - sum, pres);
        }
    }

    public void forSubstitution() {
        if (arr2[0][0] == 0) {
            valid = false;
            return;
        }
        y[0] = approximation.sigFig(b2[0] / arr2[0][0], pres);
        for (int i = 1; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                if (arr2[i][i] == 0) {
                    valid = false;
                    return;
                }
                sum = approximation.sigFig(sum + (y[j] * arr2[i][j]), pres);
            }
            if (arr2[i][i] == 0) {
                valid = false;
                return;
            }
            y[i] = approximation.sigFig((b2[i] - sum) / arr2[i][i], pres);
        }
    }

    private void forElimination() {
        for (int k = 0; k < n - 1 && valid; k++) {
            pivoting(k);
            for (int i = k + 1; i < n; i++) {
                if (arr2[k][k] == 0) {
                    valid = false;
                    return;
                }
                double factor = approximation.sigFig(arr2[k][i] / arr2[k][k], pres);
                arr2[k][i] = factor;
                for (int j = k + 1; j < n; j++)
                    arr2[j][i] = approximation.sigFig(arr2[j][i] - factor * arr2[j][k], pres);
            }
        }
    }

    public double[] solve() {
        ans = new double[n];
        y = new double[n];
        time = System.nanoTime();
        forElimination();
        forSubstitution();
        backSubstitution();
        time = System.nanoTime() - time;
        if (valid)
            return ans;
        else
            return new double[n];
    }

    public crout(double[][] arr, double[] b) {
        this.n = arr.length;
        arr2 = new double[n][n];
        b2 = new double[n];
        // copy equations to keep original
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr2[i][j] = approximation.sigFig(arr[i][j], pres);
            }
            b2[i] = approximation.sigFig(b[i], pres);
        }
    }

    public boolean getValid() {
        return this.valid;
    }

    public long getTime() {
        return time;
    }
}