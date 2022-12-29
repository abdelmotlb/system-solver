import matplotlib.pyplot as plt
import numpy as np

f = open("./function2.txt", "r")
lines = f.readlines()
xStringList = lines[0].split()
yStringList = lines[1].split()
xlist = []
ylist = []
xlList = []
xuList = []
xAxis = []

for x in xStringList:
    xlist.append(float(x))
for y in yStringList:
    ylist.append(float(y))
for i in range(0, len(xlist)):
    xAxis.append(0)
xlList.append(float(lines[2]))
xlList.append(float(lines[3]))
xuList.append(float(lines[4]))
xuList.append(float(lines[5]))

plt.figure(num=1, dpi=120)
plt.title("False position method")
plt.plot(xlist, ylist, color='r')
plt.plot(xlList, xuList)
plt.plot(xlist, xAxis, color='black')
plt.grid()
plt.show()
