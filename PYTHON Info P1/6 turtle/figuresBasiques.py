from turtle import *
#fonction qui dessine un carré
def carre(taille, couleur):
    color(couleur)
    for i in range(4):
        forward(taille)
        right(90)

#fonction qui dessine un triangle pointé vers le bas
def trianglebas(taille, couleur):
    color(couleur)
    for i in range(3):
        forward(taille)
        right(120)

#fonction qui dessine une étoile à 5 branches pointée vers le bas
def etoile5(taille,couleur):
    color(couleur)
    for i in range(5):
        forward(taille)
        right(144)
        

def cercle(taille,couleur):
    color(couleur)
    circle(taille/2)
    
