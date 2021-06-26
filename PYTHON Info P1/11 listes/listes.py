#exercice 3
"""
def afficheListe3(liste):
    for k in range(len(liste)-1):
        print(liste[k],end=' ')
    print(liste[len(liste)-1])
    print("\n")
"""

#exercice 4
"""
def moyenne(liste):
    taille=len(liste)
    somme=0
    for k in range(taille):
        somme = somme+tab(taille)
    moy = somme/taille
    return moy
#prgm principal ex 4
liste4=initListeAlea(30)
moy=moyenne(liste4)
print('La moyenne des valeurs du tableau est : ',moy)
"""

#exercice 5
"""
def estPresent1(valeur,liste):
    reponse=False
    for k in range(len(liste)):
        if valeur==liste[k]:
            reponse == True
    return reponse
#programme princpal 5
liste5=initListeAlea(40)
afficheListe3(liste5)
valeur=int(input("Saisir une valeur entre -30 et 20 : "))
present=estPresent1(valeur,liste5)
if present:
    print('Le nombre ',valeur,' est dans le tableau')
else:
    print('Le nombre ',valeur," n'est pas dans le tableau")
"""

#exercice 6
"""
def nombreOccurence(valeur,liste):
    resultat=0
    for k in range(len(liste)):
        if valeur==liste[k]:
            resutalt=resultat+1
    return resultat

liste6=initListeAlea(40)
afficheListe3(liste6)
valeur=int(input("entrez une valeur entre -30 et 20 : "))
nombre = nombreOccurence(valeur,liste6)
print('le nombre ',valeur,' est présent ', nombre,' fois dans la liste')
"""

#exercice 8

def modifListe(liste,val):
    liste[0]=1
    liste[1]=4
    liste[2]=9
    val=100
#programme principal
    
liste1 = [10,10,10]
valeur=10
print('avant:liste1[0]',liste1[0],'liste1[1]',liste1[1],'liste1[2]',liste1[2])
print('avant:valeur',valeur)
modifListe(liste1,valeur)
print('après:liste1[0]',liste1[0],'liste1[1]',liste1[1],'liste1[2]',liste1[2])
print('après:valeur',valeur)
