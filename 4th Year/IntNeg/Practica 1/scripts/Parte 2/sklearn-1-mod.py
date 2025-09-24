import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.feature_selection import SelectKBest, f_regression
from sklearn.linear_model import LinearRegression
from sklearn.svm import SVR
from sklearn.ensemble import RandomForestRegressor
from sklearn.neural_network import MLPRegressor
import numpy as np
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

#Select k best

X_new = SelectKBest(score_func=f_regression, k=5).fit_transform(X,Y)
X_new.shape
print("SelectKBest: \n", X_new.shape)

#exit(2)

X = X_new[:,:]
orden = np.argsort(Y)
horizontal = np.arange(Y.shape[0])
#plt.scatter(horizontal, Y[orden], color='black')
#plt.show()

regressor1 = MLPRegressor()
regressor1.fit(X,Y)
#plt.subplot(234)
plt.scatter(horizontal, regressor1.predict(X)[orden], color='red', linewidth=3,s=0.5, label='MLPRegressor')
plt.scatter(horizontal, Y[orden], color='black')

regressor2 = LinearRegression()
regressor2.fit(X, Y)
#plt.subplot(231)
plt.scatter(horizontal, regressor2.predict(X)[orden], color='yellow', linewidth=3, s=0.5, label='LinearRegression')
plt.scatter(horizontal, Y[orden], color='black')

regressor3 = SVR(kernel='rbf',C=1e7,epsilon=1)
regressor3.fit(X, Y)
#plt.subplot(232)
plt.scatter(horizontal, regressor3.predict(X)[orden], color='orange', linewidth=3, s=0.5, label='SVR')
plt.scatter(horizontal, Y[orden], color='black')

regressor4 = RandomForestRegressor()
regressor4.fit(X, Y)
#plt.subplot(233)
plt.scatter(horizontal, regressor4.predict(X)[orden], color='green', linewidth=3, s=0.5, label='RandomForest')
plt.scatter(horizontal, Y[orden], color='black')
plt.legend()
plt.show()