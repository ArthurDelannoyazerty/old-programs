from turtle import *
#info sur turtle
"""
help("turtle")
"""
#exercice 1
#Fonction qui dessine, à partir d'un point donné, un triangle équiatéral de couleur et de taille déterminées et qui le remplit d'une autre couleur
def triangleplein(taille, couleur1, couleur2, tracé, absc, ordo):
    color(couleur1)
    fillcolor(couleur2)
    pensize(tracé)
    up()
    goto(absc,ordo)
    down()
    t=0
    begin_fill()
    while(t<3):
        forward(taille)
        left(120)
        t=t+1
    end_fill()

#fonction qui dessine un triangle pointé vers le bas
def trianglebas(taille, couleur):
    color(couleur)
    pensize(1)
    up()
    goto(0,0)
    down()
    t=0
    while(t<3):
        right(120)
        forward(taille)
        t=t+1
    end_fill()

#fonction qui dessine un carré
def carre(taille, couleur):
    color(couleur)
    pensize(1)
    goto(0,0)
    t=0
    while(t<4):
        left(90)
        forward(taille)
        t=t+1
    end_fill()

#fonction qui dessine 36 triangle (6*6) de coté 50
def triangles36(absc,ordo,couleur1,couleur2):
    color(couleur1)
    fillcolor(couleur2)
    pensize(1)
    up()
    goto(0,0)
    down()
    t=0
    x=0
    y=0
    while (y<6*50):
        begin_fill()
        down()
        while (x<6*50):
            while(t<3):
                forward(50)
                left(120)
                t=t+1
            x=x+50
            goto(x,y)
            t=0
        end_fill()
        up()
        y=y+50
        x=0
        goto(x,y)

#fonction qui dessine une étoile à 5 branches pointée vers le bas
def etoile5(taille,couleur):
    a=0
    while (a<5):
        a=a+1
        color(couleur)
        forward(taille)
        left(144)
