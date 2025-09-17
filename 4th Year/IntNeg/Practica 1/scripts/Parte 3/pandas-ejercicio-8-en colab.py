import pandas as pd
import numpy as np
from google.colab import files
import io
from sklearn.model_selection import cross_val_score,cross_val_predict
from sklearn.linear_model import LinearRegression
from sklearn.svm import SVR
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error

uploaded = files.upload()
df = pd.read_excel(io.BytesIO(uploaded['Churn_Modelling_NANs.xlsx']), na_values='NA')


# Seguir desde aquí
