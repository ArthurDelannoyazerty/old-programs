void generation(){// génére la pièce de départ
  for(int j=0;j<=1;j++){//vide la matrice gen
    for (int i=0;i<=2;i++){
      gen[i][j] = 0;
    }
  }
  a =analogRead(0)%5;//choisi aléatoirement la pièce
  //L
  if(a==0){
    for(int i=0;i<=2;i++) gen[i][0]=1;
    gen[0][1]=1;
  }
  //Z
  if(a==1){
    gen[1][0]=1;
    gen[2][0]=1;
    gen[0][1]=1;
    gen[1][1]=1;
    gen[0][0]=0;
  }
  //T
  if(a==2){
    for(int i=0;i<=2;i++) gen[i][0]=1;
    gen[1][1]=1;
  }
  //cube
  if(a==3){
    gen[1][0]=1;
    gen[2][0]=1;
    gen[1][1]=1;
    gen[2][1]=1;
    gen[0][0]=0;
  }
  afficherMatgen(gen);
  // placement de gen dans ecran[][]
  cptupdate=0;
  for(int y=0;y<=1;y++){
    for(int x=0;x<=2;x++){
      if(gen[x][y]!=0){   //=si la case gen est vide, on laisse la case ecran tel quel
        ecran[x+13][y+3] = gen[x][y];
        updatingX[cptupdate] = x+13;
        updatingY[cptupdate] = y+3;
        cptupdate=cptupdate+1;
      }
    }
  }
  mainB[0]=14;
  mainB[1]=largeur-1;
  Serial.println(a);
  afficherMatecran(ecran);
}
