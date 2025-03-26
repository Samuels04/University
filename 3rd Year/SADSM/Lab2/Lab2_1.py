import gurobipy as gp;
from gurobipy import GRB;
import numpy as np;

model = gp.Model("Lab2");

numberTriangles = 3;
numberCircles = 8;

#Which triangles are chosen
x = model.addVar(numberTriangles, vtype=GRB.BINARY, name="x");

#Which triangle goes to which circle(s)
y = model.addMVar((numberTriangles, numberCircles), vtype=GRB.BINARY, name="y");

# The objective is to choose the triangles such that the sum of distances from each triangle to
# the nearest circle is minimum.

# Matrix representing the distance from each triangle to each circle
distances = np.array([
	[1, 3, 5, 4, 6, 8, 7, 1],
	[9, 6, 5, 2, 7, 3, 9, 7],
	[1, 5, 6, 4, 3, 2, 4, 3]
]);

# Each circle only assigned to one triangle
for j in range(numberCircles):
	model.addContr(sum(y[j][i] for i in range(numberTriangles)) == 1);

# Number of triangles to choose
P = 2
model.addConstr(sum(x[i] for i in range(numberTriangles)) < P)

