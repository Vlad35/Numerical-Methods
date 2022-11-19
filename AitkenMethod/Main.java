import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int size = 1000;
    private static double[] x_f = new double[size];
    private static double[] y_f = new double[size];
    static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:/Input.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int n = scanner.nextInt();
    private static List<Double> list = new ArrayList<>();
    private static double[] x = new double[n];
    private static double[] f = new double[n];

    public Main() throws FileNotFoundException {
    }
    private static double getMinX(double[] arr) {
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }
    private static double getMaxX(double[] arr) {
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }
    private static double getDeterminant(double y0,double x0,double y1,double x1) {
        return y0 * x1 - y1 * x0;
    }
    private static double Aitken(List<Double> new_list,double[] x,double[] y,double x_f) {
        double res = 0;
        if(new_list.size() == 1) {
            return new_list.get(0);
        }else  {
            int ind = 1;
            while (ind < new_list.size()) {
                List<Double> temp_list = new ArrayList<>();
                for (int i = 0; i < new_list.size(); i++) {
                    temp_list.add(new_list.get(i));
                }
                for (int i = ind;i < new_list.size() - 1;i ++) {
                    new_list.set(i + 1, getDeterminant(temp_list.get(i),x[0] - x_f, temp_list.get(i + 1),x[i + 1] - x_f) / (x[i + 1] - x[0]));
                }
                temp_list.clear();
                ind ++;
            }
            res = new_list.get(new_list.size() - 1);
        }
        return res;
    }
    private static double AitkenMethod(double x_f,double[] x,double[] y){
        List<Double> new_list = new ArrayList<>();
        for (int i = 0; i < x.length;i++) {
            new_list.add(0.0);
        }
        for (int i = 0; i < x.length - 1; i++) {
            new_list.set(i + 1, getDeterminant(y[i],x[i] - x_f,y[i + 1],x[i + 1] - x_f) / (x[i + 1] - x[i]));
        }
        double ans = Aitken(new_list,x,f,x_f);
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
            f[i] = scanner.nextDouble();
        }
        System.out.println();
        double xMin = getMinX(x);
        double xMax = getMaxX(x);
        for (int i = 0; i < size; i++) {
            x_f[i] = xMin + (((xMax - xMin) / size) * i);
        }
        for (int i = 0; i < size; i++) {
            y_f[i] = AitkenMethod(x_f[i],x,f);
        }
        for (int i = 0; i < size; i++) {
            System.out.println(x_f[i]);
        }
    }
}
