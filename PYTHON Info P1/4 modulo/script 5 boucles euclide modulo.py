#ex 4 algo euclide PGCD

print("saisir un nb premier")
nb1 = eval(input())
print("saisir un nb premier")
nb2 = eval(input())
a= nb1
b=nb2
r=a%b
while (r!=0) :
    a=b
    b=r
    r=a%b
print("Le PGCD de ",nb1," et de ",nb2," est : ", b)
