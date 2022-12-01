package logic;
public class gauss {
    private boolean valid = true;
    private double[][] arr2;
    private int n;
    private double[] b2;

    // bonus part
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
            temp[i - row] = arr2[i][i] / mymax;
        }
        for (int i = 0; i < n - row - 1; i++) {
            if (temp[i + 1] > temp[i])
                maxIndex = i + 1 + row;
        }
        return maxIndex;
    }

    // applicable pivoting
    private void pivoting(int row) {
        int maxIndex = scaling(row);
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
    }

    // forward elimination function
    private void forElimination() {
        int n = arr2.length;
        for (int k = 0; k < n - 1 && valid; k++) {
            for (int i = k + 1; i < n; i++) {
                pivoting(i);
                double factor = arr2[i][k] / arr2[k][k];
                for (int j = k + 1; j < n; j++)
                    arr2[i][j] = arr2[i][j] - factor * arr2[k][j];
                b2[i] = b2[i] - factor * b2[k];
            }
        }
    }

    // backward substitution function
    private void backSubstitution(double[] ans) {
        int n = arr2.length;
        ans[n - 1] = b2[n - 1] / arr2[n - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = sum + arr2[i][j] * ans[j];
            }
            ans[i] = (b2[i] - sum) / arr2[i][i];
        }
    }

    // visible functions and constructor
    public double[] solve() {
        double[] ans = new double[n];
        forElimination();
        if (valid)
            backSubstitution(ans);
        // if not valid return zeros
        return ans;
    }

    public boolean getValid() {
        return this.valid;
    }

    public gauss(double[][] arr, double[] b) {
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

}