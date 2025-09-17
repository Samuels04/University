import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.feature_selection import SelectKBest, f_regression
from sklearn.linear_model import LinearRegression
from sklearn.svm import SVR
from sklearn.ensemble import RandomForestRegressor
import numpy as np
import pandas as pd
# boston_dataset = datasets.load_boston()
# print(boston_dataset.feature_names)
# X_full = boston_dataset.data
# Y = boston_dataset.target

data_url = "http://lib.stat.cmu.edu/datasets/boston"
raw_df = pd.read_csv(data_url, sep="\s+", skiprows=22, header=None)
X_full = np.hstack([raw_df.values[::2, :], raw_df.values[1::2, :2]])
Y = raw_df.values[1::2, 2]
print(raw_df.columns)

print(X_full.shape)
print(Y.shape)
N = Y.shape[0]
# Se elige la variable mas dependiente de la salida
# SE ESTUDIARA EN CLASE DE TEORIA MAS ADELANTE
# k es el número de variables que se eligen
selector = SelectKBest(f_regression, k=1)
selector.fit(X_full, Y)
X = X_full[:, selector.get_support()]
print(X.shape)
plt.scatter(X, Y, color='black')
plt.show()
idx = np.argsort(Y)
plt.scatter(range(N), Y[idx], color='black')
plt.show()
regressor = LinearRegression()
regressor.fit(X, Y)
plt.scatter(X, Y, color='black')
plt.plot(X, regressor.predict(X), color='blue', linewidth=3)
plt.show()
plt.plot(range(N), Y[idx], color='black')
plt.scatter(range(N), regressor.predict(X)[idx], color='blue')
plt.show()
regressor = SVR(kernel='rbf',C=1e1,epsilon=1)
regressor.fit(X, Y)
plt.scatter(X, Y, color='black')
plt.scatter(X, regressor.predict(X), color='blue', linewidth=3)
plt.show()
plt.plot(range(N), Y[idx], color='black')
plt.scatter(range(N), regressor.predict(X)[idx], color='blue')
plt.show()
regressor = RandomForestRegressor()
regressor.fit(X, Y)
plt.scatter(X, Y, color='black');
plt.scatter(X, regressor.predict(X), color='blue', linewidth=3)
plt.show()
plt.plot(range(N), Y[idx], color='black')
plt.scatter(range(N), regressor.predict(X)[idx], color='blue')
plt.show()