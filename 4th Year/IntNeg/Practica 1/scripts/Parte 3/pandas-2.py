import pandas as pd
import numpy as np



# Creación de un dataframe a partir de un diccionario
df = pd.DataFrame({'a':np.random.rand(10),
                 'b':np.random.randint(10, size=10),
                 'c':[True,True,True,False,False,np.nan,np.nan,
                      False,True,True],
                 'b':['London','Paris','New York','Istanbul',
                      'Liverpool','Berlin',np.nan,'Madrid',
                      'Rome',np.nan],
                 'd':[3,4,5,1,5,2,2,np.nan,np.nan,0],
                 'e':[1,4,5,3,3,3,3,8,8,4]})

# Descripción estadística de datos numéricos 
# Los NaN no se cuentan
df.describe()

# Columna c
print(df.c)

# Cuenta de valores en la columna c
print(df.c.value_counts())

# Tipos de datos
# Los NaN son floats por defecto
print(df.dtypes)