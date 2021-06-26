"""
x=19
x=x+2;y=x+2

print("somme et produit de 2 entiers")
x=5
y=7
print("La somme de ",x," et de ",y," vaut", x+y)
print("Le produit de ",x," et de ",y," vaut", x*y)
"""

#afficher des variables
"""
#options du print
print("\n")                                         #retour à la ligne
x=56;y=34;z=90
print(x,y,z)                          #écriture des valeurs x,y et z sur la meme ligne
print(x,y,z,sep=' ')                  #écriture des valeurs x,y et z sur la meme ligne
print(x,y,z,sep=' ; ')                #Séparation par un espace puis ; puis espace
print(x,y,z,sep='\n')                 #Saut de ligne après l'écriture de chaque variable
print('x =',x,sep=' ', end=' ; ')
print('y =',y,sep=' ', end=' ; ')     #3 instruction sur la meme ligne
print('z =',z,sep=' ')
"""


"""
a=input()
b=input()
print('a+b = ',a+b)                   #a et b enregistré en tant que string
"""
#input est toujours une chaine de caractères

"""
a=eval(input())
b=eval(input())                       #eval = fonction qui interprète les string pour en faire un calcul
print("a+b = ", a+b)

#on peut aussi faire :
a=int(input())
#ou :
b=float(input())
"""

#demander une variable comme ça:
"""
a=eval(input("a="))
"""
