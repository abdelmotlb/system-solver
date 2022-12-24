import sympy as sym
from sympy import diff, Symbol
from sympy.parsing.sympy_parser import parse_expr

# read eqaution from txt file
f = open("D:/university/y2.term1/Numerical/project/phase2/copy1/src/evaluate/function.txt", "r")
my_symbols = {'x': Symbol('x', real=True)}
my_func = parse_expr(f.read(), my_symbols)

# calc divative
derivative_f = diff(my_func, my_symbols['x'])

# read value from file
x = float(open("D:/university/y2.term1/Numerical/project/phase2/copy1/src/evaluate/value.txt", "r").read())
print(eval(str(derivative_f)))

# store in file
f = open("D:/university/y2.term1/Numerical/project/phase2/copy1/src/evaluate/output.txt", "w")
f.write(str( eval(str(derivative_f)) ))
f.close()
