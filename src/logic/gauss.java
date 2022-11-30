package logic;

public class gauss {
    private boolean valid = true;
    private double[][] arr2;
    private int n;
    private double[] b2;

    // forward elimination function
    private void forElimination(double[][] arr, double[] b) {
        int n = arr.length;
        for (int k = 0; k < n - 1 && valid; k++) {
            for (int i = k + 1; i < n; i++) {
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
                double factor = arr[i][k] / arr[k][k];
                for (int j = k + 1; j < n; j++)
                    arr[i][j] = arr[i][j] - factor * arr[k][j];
                b[i] = b[i] - factor * b[k];
            }
        }
    }

    // backward substitution function
    private void backSubstitution(double[][] arr, double[] b, double[] ans) {
        int n = arr.length;
        ans[n - 1] = b[n - 1] / arr[n - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = sum + arr[i][j] * ans[j];
            }
            ans[i] = (b[i] - sum) / arr[i][i];
        }
    }

    // visible functions and constructor
    public double[] solve() {
        double[] ans = new double[n];
        forElimination(arr2, b2);
        if (valid)
            backSubstitution(arr2, b2, ans);
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