import gurobipy as gp;
import numpy as np;
from gurobipy import GRB;
# import scipy;
# Decision variable will be a matrix in which each element x_ij means that a job j is asigned to machine i.

# We create the model
model = gp.Model("Lab2");


# m = number of machines; n = number of jobs
m = 2;
n = 4;

#Decision variable is a matrix
x = model.addMVar((m, n), vtype = GRB.BINARY, name = "x");

# Matrix with the values
values = np.array([3,4,5,7]);

# Each job can only be in one machine, so the sum of the values of the machines for one job must == 1
for j in range (0, n):
	model.addConstr(sum(x[i][j] for i in range(0,m)) == 1);

c = model.addVar(vtype=GRB.CONTINUOUS, name="c");

# Each machine's work cannot exceed c
for i in range(0, m):
	model.addConstr(sum(x[i][j]*values[j] for j in range(0,m)) <= c);

model.setObjective(c, GRB.MINIMIZE);

model.optimize();

#Print results
for v in model.getVars():
        print(v)
print("Result: ", model.ObjVal)