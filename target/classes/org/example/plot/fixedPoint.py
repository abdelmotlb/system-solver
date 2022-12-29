import matplotlib.pyplot as plt
import numpy as np

f = open("./function2.txt", "r")
lines = f.readlines()
xStringList = lines[0].split()
yStringList = lines[1].split()
about = float(lines[2])
starty = float(lines[3])
yabout = float(lines[4])
xlist = []
ylist = []
xAxis = []
verticalx = []
verticaly = []
horizontalx = []
horizontaly = []

for x in xStringList:
    xlist.append(float(x))
for y in yStringList:
    ylist.append(float(y))
for i in range(0, len(xlist)):
    xAxis.append(0)
verticalx.append(about)
verticalx.append(about)
verticaly.append(starty)
verticaly.append(yabout)

horizontalx.append(about)
horizontaly.append(yabout)
horizontalx.append(yabout)
horizontaly.append(yabout)

plt.figure(num=0, dpi=120)
plt.title("Fixed point method")
plt.plot(xlist, ylist, color='r')
plt.plot(xlist, xAxis, color='black')
plt.plot(xlist, xlist)
plt.plot(verticalx, verticaly)
plt.plot(horizontalx, horizontaly)
plt.grid()
plt.show()
