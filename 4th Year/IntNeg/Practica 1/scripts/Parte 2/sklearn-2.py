import math
import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.feature_selection import SelectKBest, f_regression
from sklearn.linear_model import LinearRegression
from sklearn.svm import SVR
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import cross_val_score,cross_val_predict
from sklearn.metrics import mean_squared_error
import pandas as pd


COLS_X = [
    "CRIM",
    "ZN",
    "INDUS",
    "CHAS",
    "NOX",
    "RM",
    "AGE",
    "DIS",
    "RAD",
    "TAX",
    "PTRATIO",
    "B",
    "LSTAT",
]
COL_Y = "MEDV"


def read_boston_statlib(path_or_url: str):
    """
    Lee el fichero 'boston' de StatLib (formato 2 líneas/registro) y devuelve:
    X : DataFrame (13 features)
    y : Series (MEDV)
    df: DataFrame completo (features + target)
    """
    # Lee todas las líneas de datos, omitiendo el encabezado largo del archivo
    raw = pd.read_csv(
        path_or_url, sep=r"\s+", header=None, skiprows=22, engine="python"
    )

    # Even rows (0,2,4,...) contienen 11 columnas: CRIM..PTRATIO
    even = raw.iloc[::2, :11].reset_index(drop=True)

    # Odd rows (1,3,5,...) contienen 3 columnas: B, LSTAT, MEDV
    odd = raw.iloc[1::2, :3].reset_index(drop=True)

    # Ensambla las 13 features y la etiqueta
    X = pd.concat([even, odd.iloc[:, :2]], axis=1)
    X.columns = COLS_X
    y = odd.iloc[:, 2].rename(COL_Y)

    df = X.copy()
    df[COL_Y] = y
    return X, y, df


data_url = "http://lib.stat.cmu.edu/datasets/boston"
X, y, raw_df = read_boston_statlib(data_url)
X_full = X.values
Y = y.values

print(raw_df.columns)

print(X_full.shape)
print(Y.shape)
# Se elige la variable mas dependiente de la salida
selector1 = SelectKBest(f_regression, k=1)
selector1.fit(X_full, Y)
X_1 = X_full[:, selector1.get_support()]

regressor = LinearRegression()
regressor.fit(X_1, Y)
# Error cuadratico medio de cada fold, seguido de media de folds
score = cross_val_score(regressor, X_1, Y, scoring='neg_mean_squared_error',cv=10).mean()
# La prediccion es la respuesta del modelo aprendido en el
# el fold para el que la instancia fue parte del conjunto de test
predicted = cross_val_predict(regressor, X_1, Y,cv=10)
mse = mean_squared_error(Y,predicted)
rmse= math.sqrt(mse)
r2 = regressor.score(X_1, Y)
# Resultados parecidos pero no iguales
#print("LIN MSE=%.2f" % mse)
print("LIN score=%.2f" % -score)
print("LIN SMSE=%.2f" % rmse)
print("LIN r^2=", r2)
print("+------------+")

regressor = SVR(kernel='rbf',C=1e1,epsilon=1)
regressor.fit(X_1, Y)
score = cross_val_score(regressor, X_1, Y, scoring='neg_mean_squared_error',cv=10).mean()
predicted = cross_val_predict(regressor, X_1, Y,cv=10)
mse = mean_squared_error(Y,predicted)
rmse= math.sqrt(mse)
r2 = regressor.score(X_1,Y)
#print("SVR MSE=%.2f" % mse)
print("SVR score=%.2f" % -score)
print("SVR SMSE=%.2f" % rmse)
print("SVR r^2=", r2)
print("+------------+")

regressor = RandomForestRegressor()
regressor.fit(X_1, Y)
score = cross_val_score(regressor, X_1, Y, scoring='neg_mean_squared_error',cv=10).mean()
predicted = cross_val_predict(regressor, X_1, Y,cv=10)
mse = mean_squared_error(Y,predicted)
rmse= math.sqrt(mse)
r2 = regressor.score(X_1,Y)
#print("RNF MSE=%.2f" % mse)
print("RNF score=%.2f" % -score)
print("RNF SMSE=%.2f" % rmse)
print("RNF r^2=", r2)
print("+------------+")
print("+---------------------------Ahora con las 13-------------------------------------+")

#Ahora con las 13
selector13 = SelectKBest(f_regression, k=13)
selector13.fit(X_full, Y)
X_13 = X_full[:, selector1.get_support()]

regressor = LinearRegression()
regressor.fit(X_13, Y)
# Error cuadratico medio de cada fold, seguido de media de folds
score = cross_val_score(regressor, X_13, Y, scoring='neg_mean_squared_error',cv=10).mean()
# La prediccion es la respuesta del modelo aprendido en el
# el fold para el que la instancia fue parte del conjunto de test
predicted = cross_val_predict(regressor, X_13, Y,cv=10)
mse = mean_squared_error(Y,predicted)
smse = math.sqrt(mse)
r2 = regressor.score(X_13, Y)

# Resultados parecidos pero no iguales
print("LIN SMSE=%.2f" % smse)
print("LIN score=%.2f" % -score)
print("LIN r^2=%f" % r2)
print("+------------+")

regressor = SVR(kernel='rbf',C=1e1,epsilon=1)
regressor.fit(X_13, Y)
score = cross_val_score(regressor, X_13, Y, scoring='neg_mean_squared_error',cv=10).mean()
predicted = cross_val_predict(regressor, X_13, Y,cv=10)
mse = mean_squared_error(Y,predicted)
smse = math.sqrt(mse)
r2 = regressor.score(X_13, Y)

print("SVR SMSE=%.2f" % smse)
print("SVR score=%.2f" % -score)
print("SVR r^2=%f" % r2)
print("+------------+")

regressor = RandomForestRegressor()
regressor.fit(X_13, Y)
score = cross_val_score(regressor, X_13, Y, scoring='neg_mean_squared_error',cv=10).mean()
predicted = cross_val_predict(regressor, X_13, Y,cv=10)
mse = mean_squared_error(Y,predicted)
r2 = regressor.score(X_13, Y)

smse = math.sqrt(mse)
print("RNF SMSE=%.2f" % smse)
print("RNF score=%.2f" % -score)
print("RNF r^2=%f" % r2)
print("+------------+")