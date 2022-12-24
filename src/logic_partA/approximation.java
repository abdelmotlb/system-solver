package logic_partA;

public class approximation {
    public static double sigFig(double num, int pr) {
        boolean flag = false;
        if (num == 0)
            return 0.0;
        if (num < 0) {
            flag = true;
            num *= -1;
        }
        int temp = pr;
        // for leading zeros
        if ((int) num == 0) {
            while (num * Math.pow(10, temp) < Math.pow(10, pr - 1))
                temp++;
            double ans = Math.round(num * Math.pow(10, temp)) / Math.pow(10.0, temp);
            if (flag)
                return ans * -1;
            else
                return ans;
            // length of whole number > number of presction
        } else if ((int) num > Math.pow(10, pr) - 1) {
            temp = 1;
            while (num / Math.pow(10, temp) > Math.pow(10, pr - 1)) {
                temp++;
            }
            double ans = (Math.round(num / Math.pow(10, temp - 1))) * Math.pow(10, temp - 1);
            if (flag)
                return ans * -1;
            else
                return ans;
            // not long not leading zeros
        } else {
            temp = pr - ((int) Math.log10(num) + 1);
            Double ans = Math.round(num * Math.pow(10, temp)) / Math.pow(10.0, temp);
            if (flag)
                return ans * -1;
            else
                return ans;
        }
    }
}
