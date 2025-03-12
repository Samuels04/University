import numpy as np
import gurobipy as gp
from gurobipy import GRB

#Classic knapsack problem, with models and more complicated, but a knapsack.

#Creation of the model for this lab
model = gp.Model("Lab1")

#Vector of binary values
x = model.addVars(6, vtype = GRB.BINARY, name = "x")

#Vector of values for the model
values = np.array([3,4,5,7,2,1])

#Capacity of the basket
b = 10

#The sum of the weights must not excede the capacity of the basket
model.addConstr(sum(values[i]*x[i] for i in range(0,6)) <= b)

#Vector of weights that, together with the 'values' will form a pair <v,w>
weights = np.array([3,4,5,7,2,1])

#Objective function, must maximize the weights
model.setObjective(sum(weights[i]*x[i] for i in range(0, 6)), GRB.MAXIMIZE)

#Make the model work
model.optimize()

#Print results
for v in model.getVars():
	print(v.VarName)
print("Result: ", model.ObjVal)
