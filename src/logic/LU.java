package logic;
public class LU {
    private boolean valid = true;
    private double[][] arr2;
    private int n;
    private double[] b2;
    private double[] y;

    private void forElimination(double[][] arr) {
        int n = arr.length;
        for (int k = 0; k < n - 1 && valid; k++) {
            // check division by zero and swap if possible
            if (arr[k][k] == 0) {
                valid = false;
                for (int l = k + 1; l < n; l++) {
                    if (arr[l][l] != 0) {
                        double[] temp = new double[n];
                        temp = arr[k];
                        arr[k] = arr[l];
                        arr[l] = temp;
                        valid = true;
                        break;
                    }
                }
                if (!valid)
                    break;
            }
            for (int i = k + 1; i < n; i++) {
                double factor = arr[i][k] / arr[k][k];
                arr[i][k] = factor;
                for (int j = k + 1; j < n; j++)
                    arr[i][j] = arr[i][j] - factor * arr[k][j];
            }
        }
    }

    private void backSubstitution(double[][] arr, double[] y, double[] ans) {
        int n = arr.length;
        ans[n - 1] = y[n - 1] / arr[n - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = sum + arr[i][j] * ans[j];
            }
            ans[i] = (y[i] - sum) / arr[i][i];
        }
    }

    public void forSubstitution(double[][] arr, double[] b, double[] y) {
        y[0] = b[0];
        for (int i = 1; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += (y[j] * arr[i][j]);
            }
            y[i] = b[i] - sum;
        }
    }

    public double[] solve() {
        double[] ans = new double[n];
        y = new double[n];
        forElimination(arr2);
        forSubstitution(arr2, b2, y);
        backSubstitution(arr2, y, ans);
        return ans;
    }

    public LU(double[][] arr, double[] b) {
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