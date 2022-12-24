import sympy as sym
from sympy import diff, Symbol
from sympy.parsing.sympy_parser import parse_expr

# read equation from txt file
f = open("D:/university/y2.term1/Numerical/project/phase2 test/tst5/testpy/src/midway.txt", "r")
my_symbols = {'x': Symbol('x', real=True)}
my_func = parse_expr(f.read(), my_symbols)

# read value from file
x = float(open("D:/university/y2.term1/Numerical/project/phase2 test/tst5/testpy/src/value.txt", "r").read())
print(eval(str(my_func)))
