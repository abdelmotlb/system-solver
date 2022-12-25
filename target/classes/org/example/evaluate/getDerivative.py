import sympy as sym
from sympy import diff, Symbol
from sympy.parsing.sympy_parser import parse_expr

# read eqaution from txt file
f = open("./function.txt", "r")
my_symbols = {'x': Symbol('x', real=True)}
my_func = parse_expr(f.read(), my_symbols)

# calc divative
derivative_f = diff(my_func, my_symbols['x'])

f = open("./derivativeFunction.txt", "w")
f.write(str(derivative_f))
f.close()