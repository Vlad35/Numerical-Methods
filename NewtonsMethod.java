import java.util.*;
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final double delta = 0.000001;
    private static double[][] Jacob = new double[3][3];
    private static double[] ans = new double[3];
    public static void main(String[] args) {
        int n = sc.nextInt();

    }
    private static double f11(double x) {
        return x;
    }
    private static double f12(double y) {
        return y;
    }
    private static double f13(double z) {
        return z;
    }
    private static double f21(double x) {
        return x*x;
    }
    private static double f22(double y) {
        return y*y;
    }
    private static double f23(double z) {
        return z*z;
    }
    private static double f31(double x) {
        return x*x;
    }
    private static double f32(double y) {
        return y*y;
    }
    private static double f33(double z) {
        return z*z;
    }
    private static double getDeterminant11(double x) {
        return (f11(x + delta) - f11(x)) / delta;
    }
    private static double getDeterminant12(double x) {
        return (f12(x + delta) - f12(x)) / delta;
    }
    private static double getDeterminant13(double x) {
        return (f13(x + delta) - f13(x)) / delta;
    }
    private static double getDeterminant21(double x) {
        return (f21(x + delta) - f21(x)) / delta;
    }
    private static double getDeterminant22(double x) {
        return (f22(x + delta) - f22(x)) / delta;
    }
    private static double getDeterminant23(double x) {
        return (f23(x + delta) - f23(x)) / delta;
    }
    private static double getDeterminant31(double x) {
        return (f31(x + delta) - f31(x)) / delta;
    }
    private static double getDeterminant32(double x) {
        return (f32(x + delta) - f32(x)) / delta;
    }
    private static double getDeterminant33(double x) {
        return (f33(x + delta) - f33(x)) / delta;
    }
    private static void InitJacob(double x) {
        Jacob[0][0] = getDeterminant11(x);
        Jacob[0][1] = getDeterminant12(x);
        Jacob[0][2] = getDeterminant13(x);
        Jacob[1][0] = getDeterminant21(x);
        Jacob[1][1] = getDeterminant22(x);
        Jacob[1][2] = getDeterminant23(x);
        Jacob[2][0] = getDeterminant31(x);
        Jacob[2][1] = getDeterminant32(x);
        Jacob[2][2] = getDeterminant33(x);
    }
    private static void NewtonsMethod() {
        int k = 0;
        ans[0] = 1;
        ans[1] = 1;
        ans[2] = 1;

    }
    private static double[][] getReverseDet() {
        double[][] rev = new double[3][3];
        double k = getDet(Jacob);
        rev[0][0]
    }
    private static double getDet(double[][] matrix) {
        double res = 0;
        res += matrix[0][0] * matrix[1][1] * matrix[2][2];
        res += matrix[1][0] * matrix[2][1] * matrix[0][2];
        res += matrix[0][1] * matrix[1][2] * matrix[2][0];
        res -= matrix[2][0] * matrix[1][1] * matrix[0][2];
        res -= matrix[2][1] * matrix[1][2] * matrix[0][0];
        res -= matrix[0][1] * matrix[1][0] * matrix[2][2];
        return res;
    }
}
