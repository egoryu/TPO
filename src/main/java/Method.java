public class Method {
    public static double arcCos(double x, int n) {
        double result = 0;

        if (Math.abs(x) > 1.0) return Double.NaN;
        if (x == 1.0) return 0.0;
        if (x == -1.0) return Math.PI;

        for (int i = 0; i < n; i++) {
            result += (factorial(2 * i) / (Math.pow(4, i) * Math.pow(factorial(i), 2) * (2.0 * i + 1.0))) * Math.pow(x, 2.0 * i + 1.0);
        }
        return Math.PI / 2.0 - result;
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
