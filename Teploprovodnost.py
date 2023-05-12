import copy
from math import e, sin, cos, log, fabs


## ex1
def fi0(t):     # = мю1(t)          (граничное условие)
    return 1
def fi1(t):     # = мю2(t)          (граничное условие)
    return 1
def psi(x):     # = u0(x) в начальный момент (начальное условие)
    return 1
def g(x, t):     # = f(x,t) из условия
    return 0


a_sb, b_sb = 0, 10     #левая и правая граница
alpha1 = 1              # в общем виде в уравнении теплопроводности коэф-т перед производной старшей по х


def transpose(array):
    res = []
    n = len(array)
    m = len(array[0])
    for j in range(m):
        tmp=[]
        for i in range(n):
            tmp=tmp+[array[i][j]]
        res = res+[tmp]
    return res


def result(h):
    teta = h**2*0.5 #/(2*alpha1)    # находим по условию устойчивости решения
    t = [a_sb + i*teta for i in range(0, int(3/teta)+1)]
    x = [a_sb + i*h for i in range(0, int((b_sb-a_sb)/h+1))]
    n = len(t)
    n1 = len(x)
    u = [[0]*n for i in range(n1)]          # матрица нулевая. в дальнейшем заполняется значениями функции y(x,t) == u(x,t)
    for j in range(n1): u[j][0] = psi(x[j])     # подставляем начальное условие
    for j in range(n):                          # подставляем граничные условия
        u[0][j] = fi0(t[j])
        u[-1][j] = fi1(t[j])
    for k in range(1, n):               # считаем во всех остальных точках по явной формуле (7)
        for i in range(1, n1-1):
            u[i][k] = u[i][k-1]+teta*((u[i+1][k-1]-2*u[i][k-1]+u[i-1][k-1])/h**2 + g(x[i], t[k-1]))

    # вывод результата в txt, транспонируем чтобы в строках были значения по t. в столбцах по x
    ures = transpose(u)
    with open('res.txt', 'w') as f:
        sres = ['']*len(ures)
        tres, xres = [0]*len(t), [0]*len(x)
        s = 'x; '
        for i in range(len(x)):
            s = s + str(x[i]).replace('.', ',') + ';'
        f.write(s + '\n')
        for i in range(len(ures)):
            tres[i] = str(t[i]).replace('.', ',')
            for j in range(len(ures[0])):
                sres[i] = sres[i] + str(ures[i][j]).replace('.', ',') + ';'
            f.write(tres[i] + '; ' + sres[i]+'\n')
    return t, x, u


h = 0.1
result(h)
