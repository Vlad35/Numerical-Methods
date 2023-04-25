#include <iostream>
#include <iomanip>
#include <stdlib.h>
#include <math.h>
#include <fstream>

using namespace std;

double f(double x, double y) {
    return 4 * y - 3 * x + exp(3 * x) / pow(x, 2 * x) + 1;
}

double exact_x(double t) {
    return exp(t) + 2 * exp(2 * t) - exp(t) * log(exp(2 * t) + 1) + 2 * exp(2 * t) * atan(exp(t));
}

double exact_y(double t) {
    return exp(t) + 3 * exp(2 * t) - exp(t) * log(exp(2 * t) + 1) + 3 * exp(2 * t) * atan(exp(t));
}

int main() {
    setlocale(LC_ALL, "rus");
    int i, j;
    double a, b, h;
    cout << "Введите границы" << endl;
    cin >> a >> b;
    cout << "Введите величину шага " << endl;
    cin >> h;
    int n = (b - a) / h + 1;
    double *x, *y, *del, *k1, *k2;
    x = new double[n + 1];
    y = new double[n + 1];
    del = new double[n + 1];
    k1 = new double[n + 1];
    k2 = new double[n + 1];
    ofstream x_file("x_loop.txt"), y_file("y_loop.txt"), exact_x_file("exact_x.txt"), exact_y_file("exact_y.txt");
    x[0] = a;
    cout << "Введите начальное значение функции " << endl;
    cin >> y[0];
    x_file << x[0] << endl;
    y_file << y[0] << endl;
    exact_x_file << exact_x(a) << endl;
    exact_y_file << exact_y(a) << endl;
    for (i = 1; i <= n - 1; i++) {
        x[i] = a + i * h;
        k1[i] = h * f(x[i - 1], y[i - 1]);
        k2[i] = h * f(x[i - 1] + h / 2, y[i - 1] + k1[i] / 2);
        y[i] = y[i - 1] + k2[i];
        x_file << x[i] << endl;
        y_file << y[i] << endl;
        exact_x_file << exact_x(x[i]) << endl;
        exact_y_file << exact_y(x[i]) << endl;
    }
    x_file.close();
    y_file.close();
    exact_x_file.close();
    exact_y_file.close();
    system("pause");
    return 0;
}

