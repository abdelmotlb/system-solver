package logic;
public class gaussjordan {
    private boolean valid = true;
    private double[][] arr2;
    private int n;
    private double[] b2;

    private void forElimination(double[][] arr, double[] b) {
        int n = arr.length;
        for (int k = 0; k < n && valid; k++) {
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
            double factor1 = arr[k][k];
            for (int l = k; l < n; l++) {
                arr[k][l] /= factor1;
            }
            b[k] /= factor1;
            for (int i = k + 1; i < n; i++) {
                double factor = arr[i][k];
                for (int j = k + 1; j < n; j++)
                    arr[i][j] = arr[i][j] - factor * arr[k][j];
                b[i] = b[i] - factor * b[k];
            }
            for (int i = k - 1; i >= 0; i--) {
                double factor = arr[i][k];
                for (int j = k + 1; j < n; j++)
                    arr[i][j] = arr[i][j] - factor * arr[k][j];
                b[i] = b[i] - factor * b[k];
            }
        }
    }

    public double[] solve() {
        forElimination(arr2, b2);
        return b2;
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
                arr2[i][j] = arr[i][j];
            }
            b2[i] = b[i];
        }
    }

}
