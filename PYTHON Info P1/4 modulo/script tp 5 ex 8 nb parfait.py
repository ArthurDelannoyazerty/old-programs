#ex 8 nb pafaits du tp3A
def nombreParfait1(n):
    somme=0
    for k in range(1,n):
        if(n%k==0):
            somme=somme+k
    if (somme==n):
        print("le nombre ",n," est un nombre parfait")
    else:
        print("le nombre ",n," n'est pas un nombre parfait")

def nombreParfait2(n):
    somme=0
    resultat=True
    for k in range(1,n):
        if(n%k==0):
            somme = somme+k
    if(somme==n):
        resultat=True
    else:
        resultat=False
    return resultat

input("Entrer le nombre a tester : ")
nombre = int(input())
nombreParfait1(nombre)
resultat = nombreParfait2(nombre)
print(resultat)

print("liste des nb parfaits de 1 à 100 000")
for k in range(1,100001):
    
    resultat = nombreParfait2(k);
    if(resultat):
        print("nb =         ",k)
