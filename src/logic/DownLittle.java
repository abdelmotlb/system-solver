package logic;

public class DownLittle {
    private boolean valid = true;
    private double[][] arr2;
    private int n;
    private double[] b2;
    private double[] y;
    private double[] ans;
    private long time;

    private int scaling(int row) {
        double[] temp = new double[n - row];
        int maxIndex = row;
        for (int i = row; i < n; i++) {
            double mymax = arr2[i][i];
            for (int j = row + 1; j < n; j++) {
                if (arr2[i][j] > mymax) {
                    mymax = arr2[i][j];
                }
            }
            if (mymax == 0) {
                valid = false;
                return -1;
            }
            temp[i - row] = arr2[i][i] / mymax;
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

    private void backSubstitution() {
        int n = arr2.length;
        if (arr2[n - 1][n - 1] == 0) {
            valid = false;
            return;
        }
        ans[n - 1] = y[n - 1] / arr2[n - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = sum + arr2[i][j] * ans[j];
            }
            if (arr2[i][i] == 0) {
                valid = false;
                return;
            }
            ans[i] = (y[i] - sum) / arr2[i][i];
        }
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
                double factor = arr2[i][k] / arr2[k][k];
                arr2[i][k] = factor;
                for (int j = k + 1; j < n; j++)
                    arr2[i][j] = arr2[i][j] - factor * arr2[k][j];
            }
        }
    }

    public void forSubstitution() {
        y[0] = b2[0];
        for (int i = 1; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += (y[j] * arr2[i][j]);
            }
            y[i] = b2[i] - sum;
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
        return ans;
    }

    public DownLittle(double[][] arr, double[] b) {
        this.n = arr.length;
        arr2 = new double[n][n];
        b2 = new double[n];
        // copy equations to keep original
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr2[i][j] = arr[i][j];
            }
            b2[i] = b[i];
        }
    }

    public boolean getValid() {
        return this.valid;
    }

    public long getTime() {
        return time;
    }
}