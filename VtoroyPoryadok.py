import numpy as np
import matplotlib.pyplot as plt

# Определяем функцию, которую нужно решить
def p(x):
    return 1 + x/10

def f(x):
    return np.exp(-x) * np.sin(x)

# Определяем граничные условия
a = 0
b = 1
alpha = 0
beta = 1

# Определяем шаг сетки
N = 100
h = (b-a)/(N+1)

# Создаем массивы для хранения решения
x = np.linspace(a, b, N+2)
y = np.zeros(N+2)

# Задаем начальные условия
y[0] = alpha
y[N+1] = beta

# Создаем матрицу для метода простейших разностей
A = np.zeros((N+2, N+2))
A[0, 0] = 1
A[N+1, N+1] = 1

for i in range(1, N+1):
    A[i, i-1] = 1
    A[i, i] = -(2 + h**2*p(x[i]))
    A[i, i+1] = 1

# Решаем систему уравнений
y[1:N+1] = np.linalg.solve(A, h**2*f(x[1:N+1]))

# Выводим результаты
plt.plot(x, y)
plt.xlabel('x')
plt.ylabel('y')
plt.title('Решение краевой задачи для ОДУ')
plt.show()
