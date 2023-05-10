import numpy as np

def power_iteration(A, max_iter=100, tol=1e-8):
    n = A.shape[0]
    x = np.random.rand(n)  # random initial vector
    lambda_old = 0
    for k in range(max_iter):
        y = A.dot(x)
        lambda_new = np.dot(x, y) / np.dot(x, x)  # compute eigenvalue
        if np.abs(lambda_new - lambda_old) < tol:
            break
        # apply Aitken D2 process
        if k >= 2:
            lambda_star = lambda_new - (lambda_new - 2*lambda_mid + lambda_old)**2 / (lambda_new - 2*lambda_mid + lambda_old - 2*lambda_star)
        else:
            lambda_star = lambda_new
        # update eigenvalue and eigenvector
        lambda_old = lambda_new
        lambda_mid = lambda_star
        lambda_star = 0
        x = y / np.linalg.norm(y)
    return lambda_new, x

# read matrix from file
A = np.loadtxt('matrix.txt')

lambda_max, x = power_iteration(A)

# print the results
print(lambda_max)
print(x)
