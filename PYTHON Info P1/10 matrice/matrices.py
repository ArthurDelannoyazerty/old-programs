import random
#exercice 1 : affichage d'une matrice
def afficheMatrice(mat):
    nbl = len(mat)
    nbc=len(mat[0])
    for i in range(0,nbl):
        print('(',end=' ')
        for j in range(0,nbc):
            print('%4d'%mat[i][j],end =' ')
        print(')')
    print('\n"')            


#exercice 2 : création d'une matrice à nbl lignes et nbc colonnes vide
def initMatVide(nbl,nbc):
    m=[None]*nbl
    for i in range(nbl):
        m[i]= [None]*nbc
    return m

#Exercice 3 : création d'une matrice à nbl lignes et nbc colonnes remplie de la valeur val
def initMatVal(nbl,nbc,val):
    m=[None]*nbl
    for i in range(nbl):
        m[i]= [None]*nbc
        for j in range(nbc):
            m[i][j] = val
    return m



#exercice 4 : création d'une matrice contenant des nombres aléatoires
def initMatAlea(nbl,nbc):
    m=[None]*nbl
    for i in range(0,nbl):
        m[i]=[None]*nbc
    for i in range(0,nbl):
        for j in range(0,nbc):
            m[i][j]=random.randint(0,20)
    return m


#Exercice 5 : saisie d'une matrice par un utilisateur
def saisirMat(nbl,nbc):
    m=[None]*nbl
    for i in range(0,nbl):
        m[i]=[None]*nbc
        for j in range(0,nbc):
            print("saisir en ligne ",i+1," colonne ",j+1," : ")
            m[i][j] = int(input())
    return m

#exercice 6 : tranposition d'une matrice
def transopseeMat(mat):
    nbl=len(mat)
    nbc=len(mat[0])
    #création d'une matrice vide au format de la transposée
    trmat = [None]*nbc
    for i in range(0,nbc):
        trmat[i]=[None]*nbl
    #initialisation de la matrice transposée
    for i in range (0,nbc):
        for j in range (0,nbl):
            trmat[i][j]=mat[j][i]
    return trmat


#programme principal ex 6
"""
matl=initMatVal(3,2,5)
afficheMatrice(matl)
trmat=transopseeMat(matl)
afficheMatrice(trmat)
"""


#Exercice 7 : maximum d'une matrice
def maxMat(mat):
    nbl=len(mat)
    nbc=len(mat[0])
    max=mat[0][0]
    for i in range(0,nbl):
        for j in range(0,nbc):
            if (mat[i][j]>max):
                max=mat[i][j]
    return max

#programme principal ex 7
"""
mat7=initMatAlea(8,10)
max = maxMat(mat7)
print ('la plus grande valeur est : ',max)
"""

#Exercice 8 :valeur et position du plus petit élément d'une matrice
def valeurMinEtPosition(mat):
    nbl=len(mat)
    nbc=len(mat[0])
    min=mat[0][0]
    imin,jmin=(0,0)
    for i in range(0,nbl):
        for j in range(0,nbc):
            if (mat[i][j]<min):
                min=mat[i][j]
                imin,jmin=i,j
    print("le plus petit élément de la matrice est le nombre : ",min)
    print("le minimum se situe sur la ligne ", imin+1,"et sur la colonne ",jmin+1)

#programme principal ex 8

mat8=initMatAlea(7,6)
afficheMatrice(mat8)
valeurMinEtPosition(mat8)
