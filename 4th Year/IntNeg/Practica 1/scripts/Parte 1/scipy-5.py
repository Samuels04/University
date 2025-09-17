import numpy as np
import matplotlib.pyplot as plt
from scipy.spatial.distance import minkowski
Square=np.meshgrid(np.linspace(-1.1,1.1,512),np.linspace(-1.1,1.1,512),indexing='ij')
X=Square[0]; Y=Square[1]
f=lambda x,y,p: minkowski([x,y],[0.0,0.0],p)<=1.0
Ball=lambda p:np.vectorize(f)(X,Y,p)
plt.subplot(231); plt.imshow(Ball(1)); plt.title('Manhattan'); plt.axis('off')
plt.subplot(232); plt.imshow(Ball(1)); plt.title('Minkowski order 1'); plt.axis('off')
plt.subplot(233); plt.imshow(Ball(2)); plt.title('Euclidean'); plt.axis('off')
plt.subplot(234); plt.imshow(Ball(3)); plt.title('minkowski order 3'); plt.axis('off')
plt.subplot(235); plt.imshow(Ball(4)); plt.title('minkowski order 4'); plt.axis('off')
plt.subplot(236); plt.imshow(Ball(np.inf)); plt.title('Chebyshev'); plt.axis('off')
plt.show()
