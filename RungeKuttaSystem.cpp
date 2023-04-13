#include <iostream>
#include <iomanip>
#include <stdlib.h>
#include <math.h>
#include <fstream>
using namespace std;
double f1(double x, double y, double z) {
    return 2 * z - y;
    //return x*z-2*x*y + 0.8;
}
double f2(double x, double y, double z) {
    return 4 * z - 3 * y + exp(3 * x) / (exp(2 * x) + 1);
    //return z;
}
int main()
{
    setlocale(LC_ALL, "rus");
    int i, j;
    double a, b, h;
    cout << "Введите границы" << endl;
    cin >> a >> b;
    cout << "Введите величину шага " << endl;
    cin >> h;
    int n = (b - a) / h + 1;
    double* x, * y, * del, * z;
    x = new double[n + 1];
    y = new double[n + 1];
    del = new double[n + 1];
    z = new double[n + 1];
    fstream file1, file2;
    file1.open("file1.txt");
    file2.open("file2.txt");
    x[0] = a;
    cout << "Введите начальные значения функций " << endl;
    cin >> y[0] >> z[0];
    for (i = 1; i <= n - 1; i++)
    {
        x[i] = a + i * h;

        double k1 = h * f1(x[i - 1], y[i - 1], z[i - 1]);
        double l1 = h * f2(x[i - 1], y[i - 1], z[i - 1]);

        double k2 = h * f1(x[i - 1] + h / 2, y[i - 1] + k1 / 2, z[i - 1] + l1 / 2);
        double l2 = h * f2(x[i - 1] + h / 2, y[i - 1] + k1 / 2, z[i - 1] + l1 / 2);



        y[i] = y[i - 1] + (k1 + 2 * k2) / 6;
        z[i] = z[i - 1] + (l1 + 2 * l2) / 6;
        cout << x[i] << " " << y[i] << " " << z[i] << endl;

        file1 << y[i] << endl;
        file2 << z[i] << endl;
        // cout << i << " " << x[i] << " " << y[i] << " " << del[i] << " " << f(x[i], y[i]) << endl;
    };
    file1.close();
    file2.close();
    system("pause");
    return 0;
}
double f(double x, double y)
{
    return 2 * x - 3 + y;
    //return (y/x)+(x*cos(x));
    //return (4-x*y-x*x*y*y)/(x*x);
}
