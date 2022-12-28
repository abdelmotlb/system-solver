import matplotlib.pyplot as plt
import numpy as np

f = open("./function2.txt", "r")
lines = f.readlines()
xStringList = lines[0].split()
yStringList = lines[1].split()
ydashString = lines[2].split()
xlist = []
ylist = []
ydashList = []
xAxis = []
for x in xStringList:
    xlist.append(float(x))
for y in yStringList:
    ylist.append(float(y))
for ydash in ydashString:
    ydashList.append(float(ydash))
for i in range(0, len(xlist)):
    xAxis.append(0)
plt.figure(num=0, dpi=120)
plt.title("Newton method")
plt.plot(xlist, ylist, color='r')
plt.plot(xlist, xAxis, color='black')
plt.plot(xlist, ydashList)
plt.grid()
plt.show()
