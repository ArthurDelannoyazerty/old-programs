from math import*

#ex préliminaire:
"""
def f(x):
    return x**5+6*x**4-7*x**3+3*x**2-8*x+2

for k in range(-10,11):
    print(f(k))
"""


#ex 1 valeur absolue
"""
def vabs(nb):
    return (nb**2)**0.5

for k in range(-10,11):
    print(vabs(k))
"""


#ex 1 alternative valeur absolue
"""
def vabs2(nb):
    if(nb>0):
        return nb
    else:
        return -nb
for k in range(-10,11):
    print(vabs2(k))
"""

# ex 2 : 3 nb a remettre dans l'ordre
"""
def ordre(x,y,z):
    if(x<=y)and(y<=z):
        reponse=True
    else:
        reponse=False
    return reponse
    print(reponse)
"""

#ex 3 : maximum de 3 nb
"""
def max2(x,y):
    if(x>=y):
        return x
    else:
        return y

def max3(x,y,z):
    return max2(z,max2(x,y))
"""

#code a recopier et a analyser: c une somme de 2 nb
"""
def somme(a,b):
    return a+b
n1=int(input(" = ?"))
n2=int(input(" = ?"))
resultat=somme(n1,n2)
print("La somme de ",n1," et de ",n2," vaut ",resultat)
#print("La somme de {} et de {} vaut {}".format(n1,n2,resultat))
"""


#ex 4 solution d'une équation du second degré
"""
def calculDelta(a,b,c):
    return (b**2)-4*a*c
def abscisseSommet(a,b):
    return (-b/(2*a))
def racine1(a,b,c):
    return ((-b-sqrt(calculDelta(a,b,c)))/2*a)
"""
