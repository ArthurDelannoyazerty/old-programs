from turtle import *
#fonction qui dessine un carré
def carre(taille, couleur):
    color(couleur)
    pensize(1)
    t=0
    while(t<4):
        left(90)
        forward(taille)
        t=t+1
    end_fill()

#fonction qui dessine un triangle pointé vers le bas
def trianglebas(taille, couleur):
    color(couleur)
    pensize(1)
    t=0
    while(t<3):
        right(120)
        forward(taille)
        t=t+1
    end_fill()

#fonction qui dessine une étoile à 5 branches pointée vers le bas
def etoile5(taille,couleur):
    a=0
    while (a<5):
        a=a+1
        color(couleur)
        forward(taille)
        left(144)


def cercle(taille,couleur):
    color(couleur)
    pensize(1)
    circle(taille)
    
