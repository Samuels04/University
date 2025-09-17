import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.feature_selection import SelectKBest, f_regression
from sklearn.linear_model import LinearRegression
from sklearn.svm import SVR
from sklearn.ensemble import RandomForestRegressor
import numpy as np
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
X = X_full[:,:]
orden = np.argsort(Y)
horizontal = np.arange(Y.shape[0])
plt.scatter(horizontal, Y[orden], color='black')
plt.show()
regressor = LinearRegression()
regressor.fit(X, Y)
plt.scatter(horizontal, regressor.predict(X)[orden], color='blue', linewidth=3)
plt.scatter(horizontal, Y[orden], color='black')
plt.show()
regressor = SVR(kernel='rbf',C=1e1,epsilon=1)
regressor.fit(X, Y)
plt.scatter(horizontal, regressor.predict(X)[orden], color='blue', linewidth=3)
plt.scatter(horizontal, Y[orden], color='black')
plt.show()
regressor = RandomForestRegressor()
regressor.fit(X, Y)
plt.scatter(horizontal, regressor.predict(X)[orden], color='blue', linewidth=3)
plt.scatter(horizontal, Y[orden], color='black')
plt.show()