import pandas as pd
from keras.models import Sequential
from keras.layers import Dense
from tensorflow import keras
import numpy as np
data = pd.read_csv('Data/damage_indices.csv')
y = np.array(y)
model = Sequential()
model.add(Dense(24, input_dim=X.shape[1], activation='relu'))
model.add(Dense(12, activation='relu'))
model.add(Dense(1, activation='sigmoid'))
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
class LossAccHistory(keras.callbacks.Callback):
    def on_train_begin(self, logs={}):
        self.losses = []
        self.accuracies = []
    def on_epoch_end(self, batch, logs={}):
        self.losses.append(logs.get('loss'))
        self.accuracies.append(logs.get('accuracy'))
history = LossAccHistory()
epsilon = 0.005
prev_loss = float('inf')
cur_loss = 0
epochs = 0
losses = []
accuracies = []
while abs(cur_loss - prev_loss) > epsilon or cur_loss >= 0.1 or prev_loss >= 0.1:
    model.fit(X, y, epochs=15, batch_size=32, verbose=1, callbacks=[history])
    epochs += 15
    prev_loss = cur_loss
    cur_loss = history.losses[-1]
    losses.extend(history.losses)
    accuracies.extend(history.accuracies)
    history.losses = []
    history.accuracies = []
with open('Data/losses.txt', 'w') as f:
    for loss in losses:
        f.write(str(loss) + '\n')
with open('Data/accuracies.txt', 'w') as f:
    for acc in accuracies:
        f.write(str(acc) + '\n')

for i in range(102, 119):
    filename = f"Data/dataset_{i}.txt"
    with open(filename, "r") as file:
        contents = file.read()
        
    contents = contents.replace(" ", ",")

    with open(filename, "w") as file:
        file.write(contents)

for i in range(101, 119):
    test_data = pd.read_csv(f'Data/dataset_{i}.txt', names=['a', 'b', 'c', 'd', 'y'])
    scaler.fit(X)
    test_data_scaled = scaler.transform(test_data.iloc[:, :5])
    predictions = model.predict(test_data_scaled)
    binary_predictions = [1 if pred > 0.7 else 0 for pred in predictions]
    print(f"Binary predictions for dataset_{i}.txt: {predictions}")
    print(f"Binary predictions for dataset_{i}.txt: {binary_predictions}")
