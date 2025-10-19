import math
import pandas as pd
#import numpy as np
from sklearn.metrics import accuracy_score
from sklearn.model_selection import cross_val_score,cross_val_predict
from sklearn.linear_model import LinearRegression
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC, SVR
from sklearn.ensemble import RandomForestClassifier, RandomForestRegressor
#from sklearn.metrics import mean_squared_error


df = pd.read_excel("Churn_Modelling_NANs.xlsx", na_values='NA')
print(df)

# Seguir desde aquí

#Elimino las filas con algún valor perdido
df = df.dropna(axis=0, how='any', inplace=False)
#print(df)

#Accedo a las columnas indicadas en el ejercicio y las guardo en un nuevo dataframe llamado X
X = df.loc[:, ["CreditScore","Age", "Tenure", "Balance", "NumOfProducts", "HasCrCard", "IsActiveMember"]]
#print(X)

#Accedo a la columna indicada en el ejercicio y la guardo en un nuevo dataframe llamado Y
#En este caso al tratarse de una nueva columna he de crear explícitamente otro dataframe
Y = pd.DataFrame(df.loc[:, "EstimatedSalary"])
#print(Y)
Y = Y.values.ravel()


#Los tres modelos que he elegido han sido (en alarde de originalidad) los indicados en el ejercicio:
#Regresión Lineal, SVR y Random Forest
#Regresión Lineal
regressor = LinearRegression()
regressor.fit(X, Y)
score = math.sqrt(-cross_val_score(regressor, X, Y, scoring='neg_mean_squared_error', cv=10).mean())
print("Regresión Linear R^2: %.3f" % score)


#SVR
regressor = SVR(kernel='rbf', C=1e1, epsilon=1)
regressor.fit(X,Y)

score = math.sqrt(-cross_val_score(regressor, X, Y, scoring='neg_mean_squared_error',cv=10).mean())
print("SVR R^2: %.3f" % score)

#Random Forest
regressor = RandomForestRegressor()
regressor.fit(X,Y)
score = math.sqrt(-cross_val_score(regressor, X, Y, scoring='neg_mean_squared_error', cv=10).mean())
print("RandomForest %.3f" % score)

""" TODO En el informe he de tener en cuenta más estadísticos y justificar por qué estos datos
	son una mierda. También qué modelos utilizar que den mejores resultados
"""

#Los 3 modelos escogidos son SVC, KNeighbours y RandomForest

# Creo el dataframe XC con las columnas indicadas en el ejercicio
df = df.dropna(axis=0, how='any', inplace=False)
XC = df.loc[:,["CreditScore", "Age", "Tenure", "Balance", "NumOfProducts", "HasCrCard", "IsActiveMember"]]
#Creo el dataframe C (igual que antes he de explícitamente crear el dataframe) con la columna indicada
C = pd.DataFrame(df.loc[:, "Exited"])
C = C.values.ravel()

#SVC
classifier = SVC(kernel="linear", C=0.025, random_state=42)
classifier.fit(XC,C)
prediction = classifier.predict(XC)
score = accuracy_score(C, prediction)
print("Score = %.3f" % score)

#KNeighbours
classifier = KNeighborsClassifier(3)
classifier.fit(XC, C)
prediction = classifier.predict(XC)
score = accuracy_score(C, prediction)
print("Score KNeighbors = %.3f" % score)

#RandomForest
classifier = RandomForestClassifier(max_depth=5, n_estimators=10, max_features=1, random_state=42)
classifier.fit(XC, C)
prediction = classifier.predict(XC)
score = accuracy_score(C, prediction)
print("Score RandomForest = %.3f" % score)
