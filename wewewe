import os
if not os.path.exists("Output"):
    os.mkdir("Output")
for i in range(1, 121):
    input_file = open(f"CourseTasks/dataset_{i}.txt", "r")
    updated_lines = []
    values = []
    for j, line in enumerate(input_file):
        if j < 1999:
            updated_lines.append(line)
        elif j <= 13500:
            columns = line.strip().split()
            try:
                value = float(columns[2])
            except ValueError:
                updated_lines.append(line)
                continue
            values.append(value)
            updated_value = value - sum(values) / len(values)
            columns[2] = f"{updated_value:.6f}"
            updated_line = "\t".join(columns) + "\n"
            updated_lines.append(updated_line)
        else:
            break
    input_file.close()
    with open(f"CourseTasks/dataset_{i}.txt", "w") as output_file:
        output_file.writelines(updated_lines)
import scipy.signal as signal
def DITDRMS(y, x, t1, t2):
    dy = y - x
    numerator = np.trapz(dy**2, dx=1.0)
    denominator = np.trapz(x**2, dx=1.0)
    return numerator / denominator
def DIXCOR(y, x):
    rxy = signal.correlate(y, x, mode='valid')
    rxy0 = rxy[int(len(rxy)/2)]
    return 1 / rxy0
def DImaxXCOR(y, x):
    rxy = signal.correlate(y, x, mode='valid')
    return 1 / np.max(rxy)
def DIENV(y, x, t1, t2, method='hilbert'):
    if method == 'hilbert':
        Y = np.abs(signal.hilbert(y))
        X = np.abs(signal.hilbert(x))
    elif method == 'cwt':
        scales = np.arange(1, len(y))
        _, Y, _ = signal.cwt(y, signal.morlet, scales)
        _, X, _ = signal.cwt(x, signal.morlet, scales)
    else:
        raise ValueError('Invalid envelope method')
    dy = Y - X
    numerator = np.trapz(dy**2, dx=1.0)
    denominator = np.trapz(X**2, dx=1.0)
    return numerator / denominator
import numpy as np
import os
data_path = 'CourseTasks/'
output_path = 'NoDamage/'
output_file = 'DamageIndicesOutput.txt'
for i in range(1, 121):
    filename = 'dataset_' + str(i) + '.txt'
    try:
        data = np.loadtxt(os.path.join(data_path, filename), skiprows=2000)
    except ValueError:
        print(f'Skipping {filename} due to ValueError while reading data')
        continue
    dt = data[1, 0] - data[0, 0]
    t1 = int(2 / dt)
    t2 = int(4 / dt)
    x = data[:, 1]
    y = data[:, 2]
    DITDRMS = 1 - (np.trapz((y[t1:t2] - x[t1:t2]) ** 2) / np.trapz(x[t1:t2] ** 2))
    if DITDRMS < 0:
        DITDRMS *= -1
    rxy = np.correlate(x, y, mode='full') / np.sqrt(np.sum(x ** 2) * np.sum(y ** 2))
    DIXCOR = rxy[len(rxy) // 2]
    DImaxXCOR = np.max(rxy)
    from scipy.signal import hilbert
    Xhilb = np.abs(hilbert(x))
    Yhilb = np.abs(hilbert(y))
    DIENV = 1 - (np.trapz((Yhilb[t1:t2] - Xhilb[t1:t2]) ** 2) / np.trapz(Xhilb[t1:t2] ** 2))
    DIIMBAL = np.mean(np.abs(y - x)) / np.mean(np.abs(x))
    with open(os.path.join(output_path, "DamageIndicesOutput.txt"), 'a') as f:
        f.write(f'{DITDRMS:.6f} {DIXCOR:.6f} {DImaxXCOR:.6f} {DIENV:.6f} {DIIMBAL:.6f}\n')
