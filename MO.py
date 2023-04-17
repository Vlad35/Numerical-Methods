import numpy as np


def gradient_descent(x, y, learning_rate, num_iterations):
    # Initialize coefficients to 0
    b0 = b1 = 0
    n = len(x)

    # Iterate for num_iterations
    for i in range(num_iterations):
        # Calculate the predicted y values
        y_pred = b0 + b1 * x

        # Calculate the gradients for each coefficient
        db0 = (1 / n) * sum(y_pred - y)
        db1 = (1 / n) * sum((y_pred - y) * x)

        # Update the coefficients
        b0 = b0 - learning_rate * db0
        b1 = b1 - learning_rate * db1

    return b0, b1

def fletcher_reeves(f, grad, x0, tol=1e-6, max_iter=1000):
    """Use the Fletcher-Reeves conjugate gradient method to minimize a function.

    Args:
        f (callable): Function to minimize.
        grad (callable): Function to compute the gradient of f.
        x0 (ndarray): Initial guess.
        tol (float, optional): Tolerance for stopping criterion. Defaults to 1e-6.
        max_iter (int, optional): Maximum number of iterations. Defaults to 1000.

    Returns:
        tuple: Solution and number of iterations.
    """
    x = x0.copy()
    g = grad(x)
    d = -g
    k = 0
    while k < max_iter and np.linalg.norm(g) > tol:
        alpha = -(g @ d) / (d @ grad(x + d))
        x = x + alpha * d
        g_new = grad(x)
        beta = (g_new @ g_new) / (g @ g)
        d = -g_new + beta * d
        g = g_new
        k += 1
    return x, k

def newton_method(f, df, x0, tol=1e-6, max_iter=100):
    """
    Newton's method for finding a root of a function.

    Args:
        f (function): A function for which we want to find a root.
        df (function): The derivative of f.
        x0 (float): The initial guess for the root.
        tol (float): The tolerance for the root. The algorithm will stop iterating when abs(f(x)) < tol.
        max_iter (int): The maximum number of iterations.

    Returns:
        float: The root of f.

    Raises:
        ValueError: If the maximum number of iterations is reached before the tolerance is met.
    """
    x = x0
    for i in range(max_iter):
        fx = f(x)
        if abs(fx) < tol:
            return x
        dfx = df(x)
        if dfx == 0:
            raise ValueError("Derivative is zero at x = {}".format(x))
        x = x - fx / dfx
    raise ValueError("Maximum number of iterations reached")



