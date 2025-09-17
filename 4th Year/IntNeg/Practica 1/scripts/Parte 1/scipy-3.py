import numpy
import matplotlib.pyplot as plt
from scipy.stats import ttest_1samp
from scipy.stats import wilcoxon
data = numpy.array([[113,105,130,101,138,118,87,116,75,96, 
	122,103,116,107,118,103,111,104,111,89,78,100,89,85,88], 
	[137,105,133,108,115,170,103,145,78,107, \
	84,148,147,87,166,146,123,135,112,93,76,116,78,101,123]])
dataDiff = data[1,:]-data[0,:]
print(dataDiff.mean(), dataDiff.std())
# Histograma
plt.hist(dataDiff)
#plt.show()
# Test t
t_stat,p_value=ttest_1samp(dataDiff,0)
print("El p-valor es: %02f" % p_value)
if p_value<0.05:
	print("Los tiempos son significativamente diferentes")
else:
	print("No hay evidencia para rechazar que sean iguales: los dos actuadores son indistintos")

# Test wilcoxon
p_value_w=wilcoxon(dataDiff)
p_value_w=p_value_w.pvalue
print("El p-valor del test de Wilcoxon es: %02f" % p_value_w)

if p_value_w<0.05:
	print("Los tiempos son significativamente diferentes")
else:
	print("No hay evidencia para rechazar que sean iguales: los dos actuadores son indistitnos")