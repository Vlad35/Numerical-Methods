import java.util.Scanner;

public class Main {
    private static final int n = 10000;
    private static double[] dots = new double[n];
    private static double f(double x) {
        //return 7/(x * x + 1);
        //return x*Math.exp(x);
        //return Math.sin(2*x);
        return 768 - 67*x;
    }
    private static void InitDots(double b,double a) {
        double diff = (b - a) / n;
        for (int i = 0; i < n; i++) {
            dots[i] = a + diff * i;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter a: ");
        double a = sc.nextDouble();
        System.out.println("Enter b: ");
        double b = sc.nextDouble();
        InitDots(a,b);
        System.out.println(
                CountIntegral(a,b)
        );
    }

    private static double CountIntegral(double a, double b) {
        double sum = 0;
        double[] x = new double[n];
        double h=(b - a) / n;
        for(int i = 0;i < n;i ++){
            x[i] = a + i * h;
        }
        for(int i = 1;i < n - 1;i++){
            sum += (f(x[i]) * (x[i + 1] - x[i - 1]))/ 2;
        }
        return sum;
    }

}
