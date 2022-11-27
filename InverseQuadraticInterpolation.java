import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static double y1,y2,y3;
    private static double x1,x2,x3;
    private static final int size = 1000;
    private static double eps;
    private static double[] x_arr = new double[size];
    private static double[] y_arr = new double[size];
    private static void InitArrays(double x,double y) {
        double diff = (y - x) / size;
        for (int i = 0; i < x_arr.length; i++) {
            x_arr[i] = x + diff * i;
            y_arr[i] = F(x_arr[i]);
        }

    }
    private static void PrintXArr(double a,double b) {
        InitArrays(a, b);
        System.out.println("X array is: ");
        for (int i = 0; i < x_arr.length; i++) {
            System.out.println(x_arr[i]);
        }
    }
    private static void PrintYArr(double a,double b) {
        InitArrays(a, b);
        System.out.println("Y array is: ");
        for (int i = 0; i < y_arr.length; i++) {
            System.out.println(y_arr[i]);
        }
    }
    private static double AnswerIs(double x1,double x2,double x3) {
        InitY();
        return y2*y3 / (y1 - y2) / (y1 - y3) * x1 + y1 * y3 / (y2 - y1) / (y2 - y3)*x2 + y1 * y2 / (y3 - y1) / ( y3 - y2) * x3;
    }
    private static List<Double> AllAnswers(double a, double b) {
        List<Double> list = new ArrayList<>();
        double pogr = 0.000001;
        x1 = a;
        x2 = a + eps;
        x3 = a + 2 * eps;
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
    private static double F(double x) {
        return Math.pow(x, 4) - 5 * Math.pow(x, 3) + 18 * Math.pow(x, 2) + 191 * x - 97;
    }
    private static void InitY() {
        y1 = F(x1);
        y2 = F(x2);
        y3 = F(x3);
    }
    public static void main(String[] args) {
        System.out.println("Enter a:");
        double a = sc.nextDouble();
        System.out.println("Enter b:");
        double b = sc.nextDouble();
        System.out.println("Enter epsilon: ");
        eps = sc.nextDouble();
        InitArrays(a, b);
        System.out.print("Roots of Function are: ");
        System.out.println(AllAnswers(a, b));
    }
}
