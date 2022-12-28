import matplotlib.pyplot as plt
import numpy as np

f = open("./function2.txt", "r")
lines = f.readlines()
xStringList = lines[0].split()
yStringList = lines[1].split()
xlist = []
ylist = []
y2list = []

for x in xStringList:
    xlist.append(float(x))
    y2list.append(0)
for y in yStringList:
    ylist.append(float(y))


# print(count)
plt.figure(num=0, dpi=120)
plt.title('Two lines on same graph!')
# plt.plot(ylist, ylist, label="line 1")
plt.plot(xlist, ylist, color='r')
plt.plot(xlist, y2list)
plt.grid()
plt.show()
