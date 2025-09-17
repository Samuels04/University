import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.feature_selection import SelectKBest, f_regression
from sklearn.linear_model import LinearRegression
from sklearn.svm import SVR
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import cross_val_score,cross_val_predict
from sklearn.metrics import mean_squared_error
# boston_dataset = datasets.load_boston()
# print(boston_dataset.feature_names)
# X_full = boston_dataset.data
# Y = boston_dataset.target

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
# Se elige la variable mas dependiente de la salida
selector = SelectKBest(f_regression, k=1)
selector.fit(X_full, Y)
X = X_full[:, selector.get_support()]

regressor = LinearRegression()
regressor.fit(X, Y)
# Error cuadratico medio de cada fold, seguido de media de folds
score = cross_val_score(regressor, X, Y, scoring='neg_mean_squared_error',cv=10).mean()
# La prediccion es la respuesta del modelo aprendido en el
# el fold para el que la instancia fue parte del conjunto de test
predicted = cross_val_predict(regressor, X, Y,cv=10)
mse = mean_squared_error(Y,predicted)
# Resultados parecidos pero no iguales
print("LIN MSE=",mse)
print("LIN score=",-score)

regressor = SVR(kernel='rbf',C=1e1,epsilon=1)
regressor.fit(X, Y)
score = cross_val_score(regressor, X, Y, scoring='neg_mean_squared_error',cv=10).mean()
predicted = cross_val_predict(regressor, X, Y,cv=10)
mse = mean_squared_error(Y,predicted)
print("SVR MSE=",mse)
print("SVR score=",-score)

regressor = RandomForestRegressor()
regressor.fit(X, Y)
score = cross_val_score(regressor, X, Y, scoring='neg_mean_squared_error',cv=10).mean()
predicted = cross_val_predict(regressor, X, Y,cv=10)
mse = mean_squared_error(Y,predicted)
print("RNF MSE=",mse)
print("RNF score=",-score)
