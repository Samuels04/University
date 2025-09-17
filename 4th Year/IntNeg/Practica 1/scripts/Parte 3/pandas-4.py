import pandas as pd
import numpy as np

# Combinación de dataframes

df1 = pd.DataFrame({
'column_a':[1,2,3,4],
'column_b':['a','b','c','d'],
'column_c':[True,True,False,True]
})
df2 = pd.DataFrame({
'column_a':[1,2,9,10],
'column_b':['a','k','l','m'],
'column_c':[False,False,False,True]
})

print(df1)
print(df2)

# Concatenar dataframes
df = pd.concat([df1,df2])
print(df)

df = pd.concat([df1,df2],axis=1)
print(df)

# Reindexar el df
df = pd.concat([df1,df2], ignore_index=True)
print(df)


df1 = pd.DataFrame({
'column_a':[1,2,3,4],
'column_b':['a','b','c','d'],
'column_c':[True,True,False,True]
})
df2 = pd.DataFrame({
'column_a':[1,2,9,10],
'column_b':['a','k','l','m'],
'column_c':[False,False,False,True]
})
df2.index = [3,4,5,6]

# Combinar filas con índices coincidentes
df = pd.concat([df1,df2], axis=1, join='inner')
print(df)

# Usar todos los índices
df = pd.concat([df1,df2], axis=1, join='outer')
print(df)