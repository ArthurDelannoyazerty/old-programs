"""
a=0
while (a<7):
    a=a+1
    print(a)
"""

#demande de saisie, chaine de caractère, boule while, condition "and"
"""
reponse = input("voulez-vous un café?")
while(reponse!="O")and(reponse !="N"):
    print("saisie erronée, réppondre O ou N")
    reponse = input(print("voulez-vous un café?"))
print("réponse acceptée : ",reponse)
"""

#prgm voir si tel nb est dans une boucle for
"""
#r stocke les entiers pair de 100 à 999
r=range(100,1000,2)
#voir si tel nb et dans r
100 in r    #true
1000 in r   #false
867 in r    #false
"""

#table de 7, boucle "for"
"""
print ("table de 7")
for k in range (1,10):
    print("7*",k," = ",k*7)
"""

# Prgm max et min de nb, boucle "for"
"""
print("combien de nb allez-vous saisir?")
nbvaleur=eval(input())
print("saisir le nombre")
nombre = eval(input())
max=nombre
min=nombre
for k in range(1,nbvaleur):
    print("saisir le nombre")
    nombre = eval(input())
    if(nombre>max):
        max=nombre
    if(nombre<min):
        min=nombre
print("Le nombre max est : ",max,". Et le nombre min est : ",min)
"""
