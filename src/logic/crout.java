package logic;

public class crout {
    private double[][] arr2;
    private int n;
    private double[] b2;
    private double[] y;
    private double[] ans;
    private boolean valid = true;

    private int scaling(int row) {
        double[] temp = new double[n - row];
        int maxIndex = row;
        for (int i = row; i < n; i++) {
            double mymax = arr2[i][row];
            if (mymax <= 0)
                mymax *= -1;

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

    private void backSubstitution() {
        int n = arr2.length;
        ans[n - 1] = y[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = sum + arr2[i][j] * ans[j];
            }
            ans[i] = (y[i] - sum);
        }
    }

    public void forSubstitution() {
        if (arr2[0][0] == 0) {
            valid = false;
            return;
        }
        y[0] = b2[0] / arr2[0][0];
        for (int i = 1; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                if (arr2[i][i] == 0) {
                    valid = false;
                    return;
                }
                sum += (y[j] * arr2[i][j]);
            }
            if (arr2[i][i] == 0) {
                valid = false;
                return;
            }
            y[i] = (b2[i] - sum) / arr2[i][i];
        }
    }

    private void forElimination() {
        for (int k = 0; k < n - 1 && valid; k++) {
            for (int i = k + 1; i < n; i++) {
                pivoting(i);
                if (arr2[k][k] == 0) {
                    valid = false;
                    return;
                }
                double factor = arr2[k][i] / arr2[k][k];
                arr2[k][i] = factor;
                for (int j = k + 1; j < n; j++)
                    arr2[j][i] = arr2[j][i] - factor * arr2[j][k];
            }
        }
    }

    public double[] solve() {
        ans = new double[n];
        y = new double[n];
        forElimination();
        forSubstitution();
        backSubstitution();
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
                arr2[i][j] = arr[i][j];
            }
            b2[i] = b[i];
        }
    }

    public boolean getValid() {
        return this.valid;
    }
}