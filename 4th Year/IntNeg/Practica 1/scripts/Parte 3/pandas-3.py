import pandas as pd
import numpy as np



# Creación de un dataframe con valores perdidos
df = pd.DataFrame({
'column_a':[1, 2, 4, 4, np.nan, np.nan, 6],     
'column_b':[1.2, 1.4, np.nan, 6.2 ,None, 1.1, 4.3],
'column_c':['a', '?', 'c', 'd', '--', np.nan, 'd'],
'column_d':[True, True, np.nan, None, False, True, False]
})
print(df)

# El tipo <NA> es NaN para enteros
new_column = pd.Series([1, 2, np.nan, 4, np.nan, 5], dtype=pd.Int64Dtype())
df['column_e'] = new_column
print(df)

# Comprobación de que el valor esté perdido
print(df.isna())
# Lo contrario
print(df.notna())

# Algún valor perdido en la columna
print(df.isna().any())

# Cuántos valores perdidos en la columna
print(df.isna().sum())

# Convertir símbolos diferentes de NaN
df.replace(['?','--'],np.nan, inplace=True)
print(df)

# Eliminar valores perdidos
# el parámetro inplace es para cambiar el valor de df, por defecto es False
# y se devuelve el df modificado como resultado de la llamada

# Eliminar fila si todos están perdidos (no hace nada en este df)
df.dropna(axis=0, how='all', inplace=True)
df = df.dropna(axis=0, how='all', inplace=False)
print(df)

# Eliminar columna si alguno está perdido
df.dropna(axis=1, how='any', inplace=True)
print(df)

# Eliminar fila si alguno está perdido 
df.dropna(axis=0, how='any', inplace=True)
print(df)

# Reemplazar valores perdidos
df = pd.DataFrame({
'column_a':[1, 2, 4, 4, np.nan, np.nan, 6],     
'column_b':[1.2, 1.4, np.nan, 6.2 ,None, 1.1, 4.3],
'column_c':['a', '?', 'c', 'd', '--', np.nan, 'd'],
'column_d':[True, True, np.nan, None, False, True, False]
})
df.replace(['?','--'],np.nan, inplace=True)

# Por un valor constante
df.fillna(25)

df = pd.DataFrame({
'column_a':[1, 2, 4, 4, np.nan, np.nan, 6],     
'column_b':[1.2, 1.4, np.nan, 6.2 ,None, 1.1, 4.3],
'column_c':['a', '?', 'c', 'd', '--', np.nan, 'd'],
'column_d':[True, True, np.nan, None, False, True, False]
})
df.replace(['?','--'],np.nan, inplace=True)

# Por la media de la columna
mean = df['column_a'].mean()
df['column_a'].fillna(mean)

# Las medias de todas las columnas numéricas
mean = df.mean(numeric_only=True)
df.fillna(mean, inplace=True)

df = pd.DataFrame({
'column_a':[1, 2, 4, 4, np.nan, np.nan, 6],     
'column_b':[1.2, 1.4, np.nan, 6.2 ,None, 1.1, 4.3],
'column_c':['a', '?', 'c', 'd', '--', np.nan, 'd'],
'column_d':[True, True, np.nan, None, False, True, False]
})
df.replace(['?','--'],np.nan, inplace=True)

# Por el valor anterior o siguiente

df.fillna(axis=0,method='ffill')
df.fillna(axis=0,method='bfill')
df.fillna(axis=0,method='ffill',limit=1)
