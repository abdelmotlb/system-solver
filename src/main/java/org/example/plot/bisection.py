import matplotlib.pyplot as plt
import numpy as np

f = open("./function2.txt", "r")
lines = f.readlines()
xStringList = lines[0].split()
yStringList = lines[1].split()
xlist = []
ylist = []
xuList = []
xlList = []
xrList = []
xAxis = []


for x in xStringList:
    xlist.append(float(x))
for y in yStringList:
    ylist.append(float(y))
for i in range(0, len(xlist)):
    xAxis.append(0)

xl = float(lines[2])
xu = float(lines[3])
xr = (xl+xu)/2
for x in xlist:
    xlList.append(xl)
    xuList.append(xu)
    xrList.append(xr)


plt.figure(num=0, dpi=120)
# for i in range(1, 20):
#     plt.subplot(10, 2, i)
#     plt.title("loop no: ")
#     plt.plot(xlist, ylist)
#     plt.plot(xuList, ylist)
#     plt.plot(xlList, ylist)
#     plt.plot(xrList, ylist)
#     plt.grid()
# plt.subplot(10, 2, 2)
plt.title("Bisection method")
plt.plot(xlist, ylist, color='r')
plt.plot(xuList, ylist)
plt.plot(xlList, ylist)
plt.plot(xrList, ylist)
plt.plot(xlist, xAxis, color='black')
plt.grid()
plt.show()
