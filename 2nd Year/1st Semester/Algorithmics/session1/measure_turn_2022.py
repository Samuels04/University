#
# Implementation in Python of the algorithm to turn a vector
#
# ALGORITHMICS: PRACTICE OF ANALYSIS OF ALGORITHMS
#
# Copyright: Raquel Cortina
# Date: 1st September 2019
#
# ----------------------------------------------------------------------------------------------------------
#
# Program to measure the execution time for the implementation in Python
#
# The module time provides a function to measure times. It is named as clock,
# and returns the number of seconds that the CPU has spent performing the program
# until the call to the function is made. The returned value is float type.
#

# The module random provides the function randrange(0,upperbound) which chooses an random integer from 0 to upperbound -1
#
# ----------------------------------------------------------------------------------------------------------

from time import time
from random import randrange

# Function to fill up randomly a vector for a specific size
def fillOut(size, n):
    values = [0] * n
    for i in range(n):
        values[i]=randrange(0,n)
    return values

# Function to turn the elements of a vector
def turn(values):
    for i in range(1,len(values)):
        for j in range(1,len(values)-i+1):
            values[j-1], values[j+1-1] = values[j+1-1], values[j-1]
            #
            # the former line is equivalent to the following three lines
            #
            # aux=values[j-1]
            # values[j-1]=values[(j+1)-1]
            # values[(j+1)-1]=aux
    return values


# Main program

# It is defined a vector with 10 sizes to be measured as follows
sizes=[1000,2000,3000,4000,5000,6000,7000,8000,9000,10000, 11000, 12000]
upperbound=1000

# Establish the number of repetitions
repetitions=100

# Present the header of the table with size vs. time
print ("\n\nTURN A VECTOR\n")
print ("Consumption time:\n")
print ("\t\tsize\t\ttime")
print ("\t\t-----\t\t------")

# Loop to go through the different sizes to be measured
for i in range(len(sizes)):
    # Fill up the vector for sizes[i]
    vector=fillOut(sizes[i], upperbound)

    # Call to the function clock before running the code to be measured
    time_start = time()

    # Loop to repeat the experiment
    for j in range(repetitions):
        # Call to the function to be measured
        turn(vector)

    # Call to the function clock after running the code to be measured
    time_final = time()

    # Present the measured size and the consumption time (consumption time/repetitions)
    print ("\t\t%d\t\t%f" % (sizes[i],(time_final-time_start)/repetitions))



