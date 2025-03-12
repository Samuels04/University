import numpy as np
import gurobipy as gp
from gurobipy import GRB

#Subset sums problem

#Creation of model for this lab
model = gp.Model("Lab1")

#Vector of binary values
x = model.addVars(6, vtype = GRB.BINARY, name = "x")

#Vector of values for the model
values = np.array([4,5,11,9,7,9])

#Capacity of the basket
b = 10

#Constraint of the problem
model.addConstr(sum(values[i]*x[i] for i in range(0,6)) <= b)

model.setObjective(b - sum(x[i]*values[i] for i in range(0,6)), GRB.MINIMIZE)

model.optimize()

#Print results
for v in model.getVars():
	print(v.VarName)
print("Result: ", model.ObjVal)