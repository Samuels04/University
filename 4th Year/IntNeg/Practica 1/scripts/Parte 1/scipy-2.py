# Pareto
import numpy
import matplotlib.pyplot as plt
from scipy.stats import uniform
import matplotlib.pyplot as plt
x=numpy.linspace(1.0,4.0,num=5000)
# Funcion de densidad
plt.subplot(131); plt.plot(x, uniform.pdf(x, 2,1))
# Funcion de distribucion
plt.subplot(132); plt.plot(x, uniform.cdf(x,2,1))
# Generador aleatorio
plt.subplot(133); plt.plot(uniform.rvs(5,size=1000))
plt.show()