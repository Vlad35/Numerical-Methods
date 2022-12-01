import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static double F(double x) {
        return Math.sin(x);
    }
    private static List<Double> InverseParabolicInterpolation(double a, double b) {
        List<Double> list = new ArrayList<>();
        double pogr = 1e-9;
        double eps = 0.001;
        double x1 = a;
        double x2 = a + eps;
        double x3 = a + 2 * eps;
        while (x1 < b - 2 * eps) {
            int res = 1;
            if(!list.contains(AnswerIs(x1,x2,x3)) && Math.abs(F(AnswerIs(x1,x2,x3))) < pogr) {
                for (int i = 0; i < list.size(); i++) {
                    if(Math.abs(list.get(i) - AnswerIs(x1,x2,x3)) < pogr) {
                        res *= 0;
                    }
                }
                if(res == 1) {
                    list.add(AnswerIs(x1,x2,x3));
                }
            }
            x1 += eps;
            x2 += eps;
            x3 += eps;
        }
        return list;
    }

    private static double AnswerIs(double x1, double x2, double x3) {
        double y1 = F(x1);
        double y2 = F(x2);
        double y3 = F(x3);
        return y2*y3 / (y1 - y2) / (y1 - y3) * x1 + y1 * y3 /(y2 - y1) / (y2 - y3) * x2 + y1 * y2 /(y3 - y1) / (y3 - y2) * x3;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a: ");
        double a = sc.nextDouble();
        System.out.println("Enter b: ");
        double b = sc.nextDouble();
        System.out.println("Enter step: ");
        double step = sc.nextDouble();
        double diff = (b - a) / step;
        double x1 = a;
        double x2 = a + step;
        System.out.println(
                "Answers: "
        );
        for (int i = 0; i < diff; i++) {
            if(F(x1) * F(x2) <= 0) {
                System.out.println(InverseParabolicInterpolation(x1,x2));
            }
            x1 += step;
            x2 += step;
        }
    }
}
