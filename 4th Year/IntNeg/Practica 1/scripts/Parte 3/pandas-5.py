import pandas as pd
import numpy as np


df = pd.DataFrame({'a':np.random.rand(10),
                 'b':np.random.randint(10, size=10),
                 'c':[True,True,True,False,False,np.nan,np.nan,
                      False,True,True],
                 'b':['London','Paris','New York','Istanbul',
                      'Liverpool','Berlin',np.nan,'Madrid',
                      'Rome',np.nan],
                 'd':[3,4,5,1,5,2,2,np.nan,np.nan,0],
                 'e':[1,4,5,3,3,3,3,8,8,4]})
print(df)

# Selección mediante índices
# Seleccionar fila
print(df.iloc[1])

# Seleccionar elemento
print(df.iloc[0,1])

# Uso de los dos puntos 
print(df.iloc[:,1])
print(df.iloc[:2,1])

# Selección mediante etiquetas
print(df.loc[:2,'b'])
print(df.loc[:2,:'b'])

# Diferencias entre loc e iloc
# .loc incluye el valor final del rango, .iloc no lo incluye
print(df.loc[:2,:'b'])
print(df.iloc[:2,:2])