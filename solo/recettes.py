import random

plats=[]

def afficherPlats():
    for x in range (taille) :
        print(plats[x])

def afficherAlea():
    print(plats[random.randint(0,taille-1)])

def afficherNbRecettes():
    print(taille)

def afficherMenuSelection():
    print()
    print("Faites votre choix:")
    print("a : Recette aléatoire")
    print("t : Toutes les recettes")
    print("c : Nombre de recettes")
    print()

def menuSelection():
    menuSelection = input()
    print()
    if (isinstance(menuSelection,str) == False):
        print("Entrez un caractère valide")
        Selection()
    elif(menuSelection == 'break'):
        print()
    elif (menuSelection != 'a') and (menuSelection != 't') and (menuSelection != 'c'):
        print("Entrez un caractère valide")
        Selection()
    else :
        if (menuSelection == 'a'):
            afficherAlea()
            input()
            Selection()
        if (menuSelection == 't'):
            afficherPlats()
            input()
            Selection()
        if (menuSelection == 'c'):
            afficherNbRecettes()
            input()
            Selection()


def Selection():
    afficherMenuSelection()
    menuSelection()

##code principal
with open("plats.txt",'r') as f:
    for ligne in f:
        plats.append(ligne.rstrip())
    taille = len(plats)

Selection()

def checkRepetition(plats):
    plats_set = set(plats)
    duplication = (len(plats_set) != len(plats))
    if(duplication == False):
        print("Pas de doublons")
    else:
        print("Duplication détectée")
        plats = list(plats_set)

        #erase txt content :
        file = open("plats.txt","w")
        file.close()

        #writing new txt:
        with open('plats.txt', 'w') as f:
            f.writelines("%s\n" % line for line in plats)