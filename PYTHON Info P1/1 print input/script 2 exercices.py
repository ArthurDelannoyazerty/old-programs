#exercice 1 calcul de l'aire d'un disque:
"""
from math import*                           #importation de l'API math
aire = pi*eval(input('rayon = '))**2        #calcul aire
print("l'aire du disque est : ",aire)       #affichage du résultat
"""

#exercice2 prix TTC
#affichage avec n chiffres après la virgule : input("texte  %.nf" %  variable)  --> .2 = 2 chiffres après la virgule  ;  f = floattant  ;  % = je sais pas mais faut le mettre
#                                                         |         |
#                                                         !IMPORTANT!
"""
TTC = eval(input("Prix HT = "))*eval(input("TVA = "))*eval(input("quantité = "))    #quantité*HT*TVA
print("le prix TTC est : %.2f" % TTC)                                               #affichage TVA avec 2 chiffre après la virgule
"""

#exercice 3 résistances
"""
r1 = eval(input("r1 = "))
r2 = eval(input("r2 = "))
serie = r1+r2
parrallele = (1/((1/r1)+(1/r2)))
print("r1 et r2 en série = " , serie)
print("r1 et r2 en parrallele", parrallele)
"""

#exercice 4 durée d'un trajet

hbegin = eval(input("Heure du début du trajet : "))
mbegin = eval(input("Minutes début du trajet : "))
hend = eval(input("Heure d'arrivée : "))
mend = eval(input("Minutes d'arrivée : "))
hduree = hend-hbegin
mduree= mend-mbegin
if(mduree<0) :
    hduree = hduree -1
    mduree = 60+mduree
print("Le trajet à duré ",hduree," heures et ",mduree," minutes")
