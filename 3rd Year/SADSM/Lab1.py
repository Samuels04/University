import numpy as np
import gurobipy as gp
from gurobipy import GRB
model = gp.Model["Lab1"]

x = model.addVars(6, vtype = GRB.BINARY, name = "x")

values = np.array([3,4,5,7,2,1])


