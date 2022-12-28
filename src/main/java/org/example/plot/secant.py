import matplotlib.pyplot as plt
import numpy as np

f = open("./function2.txt", "r")
lines = f.readlines()
xStringList = lines[0].split()
yStringList = lines[1].split()
x1 = float(lines[2])
x2 = float(lines[3])
y1 = float(lines[4])
y2 = float(lines[5])
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
xlList.append(x1)
xlList.append(x2)
xuList.append(y1)
xuList.append(y2)


plt.figure(num=0, dpi=120)
plt.title("Secant method")
plt.plot(xlist, ylist, color='r')
plt.plot(xlist, xAxis, color='black')
plt.plot(xlList, xuList)
plt.grid()
plt.show()
