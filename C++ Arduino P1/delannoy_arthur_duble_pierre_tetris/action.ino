void action(){// vérifie les actions du joueur
  //code appui bp
  byte nbp=0;
  for (byte c = 3; c <= 6; c++){
    byte bp = digitalRead(c);
    if (bp == LOW){
      nbp = c-2;
      Serial.println(nbp);
    }
  }
  Serial.println(nbp);
  if(nbp==3 && goRight==true){//aller à gauche(touche 2)
    for(byte y=0;y<=(2*largeur)-1;y++){
      for(byte x=0;x<=15;x++){
        for(byte cpt=0;cpt<4;cpt++){
          if(x==updatingX[cpt] && y==updatingY[cpt]){
            ecran[x][y-1]=true;
            ecran[x][y]=false;
            updatingY[cpt]=y-1;
          }
        }
      }
    }
    mainB[1]=mainB[1]-1;
  }
  if(nbp==2 && goLeft==true){//aller a droite(touche 3)
    for(byte x=0;x<=15;x++){
      for(char y=(2*largeur)-1;y>=0;y--){
        for(byte cpt=0;cpt<4;cpt++){
          if(x==updatingX[cpt] && y==updatingY[cpt]){
            ecran[x][y+1]=true;
            ecran[x][y]=false;
            updatingY[cpt]=y+1;
          }
        }
      }
    }
    mainB[1]=mainB[1]+1;
  }
  Serial.print("mainB[0] before     ");
  Serial.println(mainB[0]);
  Serial.print("mainB[1] before     ");
  Serial.println(mainB[1]);
  boolean canTurn=true;
  boolean tempEcran[3][3]={0};
  if(nbp==4){//tourner à gauche (touche 1)                  NE FONCTIONNE PAS
    
    //principe: vide la matrice écran des blocs joués, vérifie si après la rotation il n'y a aucun blocs qui gène, puis affiche les blocs après la rotation si on peut faire tourner la pièce
    
    if(a!=3){//si pas un cube
      for(byte x=mainB[0]-1;x<=mainB[0]+1;x++){
        for(byte y=mainB[1]-1;y<=mainB[1]+1;y++){
          for(byte cpt=0;cpt<4;cpt++){
            if(mainB[0]+1==updatingX[cpt] && mainB[1]==updatingY[cpt]){//case dessus
              ecran[mainB[0]+1][mainB[1]]=0;    //on retire le cube de la matrice ecran
            }
            if(mainB[0]+1==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){//case haut droite
              ecran[mainB[0]+1][mainB[1]+1]=0;
            }
            if(mainB[0]==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){//case droite
              ecran[mainB[0]][mainB[1]+1]=0;
            }
            if(mainB[0]-1==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){ //case bas droite
              ecran[mainB[0]-1][mainB[1]+1]=0;
            }
            if(mainB[0]-1==updatingX[cpt] && mainB[1]==updatingY[cpt]){//case bas
              ecran[mainB[0]-1][mainB[1]]=0;
            }
            if(mainB[0]-1==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){//case bas gauche
              ecran[mainB[0]-1][mainB[1]-1]=0;
            }
            if(mainB[0]==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){  // case gauche
              ecran[mainB[0]][mainB[1]-1]=0;
            }
            if(mainB[0]+1==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){//case gauche dessus
              ecran[mainB[0]+1][mainB[1]-1]=0;
            }
          }
        }
      }
      for(byte cpt=0;cpt<4;cpt++){
        Serial.println(mainB[0]);
        Serial.println(updatingX[cpt]);
        Serial.println(mainB[1]);
        Serial.println(updatingY[cpt]);
        if(mainB[0]+1==updatingX[cpt] && mainB[1]==updatingY[cpt]){//case dessus
          if(ecran[mainB[0]][mainB[1]+1]==1) canTurn=false;//case droite
          Serial.println("1 faux");
        }
        if(mainB[0]+1==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){//case haut droite
          if(ecran[mainB[0]-1][mainB[1]+1]==1) canTurn=false;//case bas droite
          Serial.println("2 faux");
        }
        if(mainB[0]==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){//case droite
          if(ecran[mainB[0]-1][mainB[1]]==1) canTurn=false;//case bas
          Serial.println("3 faux");
        }
        if(mainB[0]-1==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){ //case bas droite
          if(ecran[mainB[0]-1][mainB[1]-1]==1) canTurn=false;//case bas gauche 
          Serial.println("4 faux"); 
        }
        if(mainB[0]-1==updatingX[cpt] && mainB[1]==updatingY[cpt]){//case bas
          if(ecran[mainB[0]][mainB[1]-1]==1) canTurn=false;//case gauche
          Serial.println("5 faux");
        }
        if(mainB[0]-1==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){//case bas gauche
          if(ecran[mainB[0]+1][mainB[1]-1]==1) canTurn=false;  // case haut gauche
          Serial.println("6 faux");
        }
        if(mainB[0]==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){  // case gauche
          if(ecran[mainB[0]+1][mainB[1]]==1) canTurn=false; // case dessus
          Serial.println("7 faux");
        }
        if(mainB[0]+1==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){//case gauche dessus
          if(ecran[mainB[0]+1][mainB[1]+1]==1) canTurn=false; // case droite dessus
          Serial.println("8 faux");
        }
        if(canTurn==false) cpt=4; //si à 1 étape on ne peut pas tourner, alors on sort de la boucle
      }
      if(mainB[0]==0 || mainB[0]==(2*largeur)-1 || mainB[1]==15 || mainB[1]==0) canTurn=false;
      if(canTurn==true){  //si on peut tourner => on déplace les blocs
        Serial.println("canturn = true");
        for(byte cpt=0;cpt<4;cpt++){
          if(mainB[0]+1==updatingX[cpt] && mainB[1]==updatingY[cpt]){//case dessus    Si la case dessus est a update alors:
            tempEcran[mainB[0]][mainB[1]+1]=1;                            //la case projetée est vraie dans une matrice parallèle
            tempupdatingX[cpt]=mainB[0];                               //la matrice temporaire de updatingX est actualisée  => on actualisera la matrice originelle + tard
            tempupdatingY[cpt]=mainB[1]+1;                             // de meme pour updatingY
          }
          if(mainB[0]+1==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){//case haut droite
            tempEcran[mainB[0]-1][mainB[1]+1]=1;
            tempupdatingX[cpt]=mainB[0]-1;
            tempupdatingY[cpt]=mainB[1]+1;
          }
          if(mainB[0]==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){//case droite
            tempEcran[mainB[0]-1][mainB[1]]=1;
            tempupdatingX[cpt]=mainB[0]-1;
            tempupdatingY[cpt]=mainB[1];
          }
          if(mainB[0]-1==updatingX[cpt] && mainB[1]+1==updatingY[cpt]){ //case bas droite
            tempEcran[mainB[0]-1][mainB[1]-1]=1;
            tempupdatingX[cpt]=mainB[0]-1;
            tempupdatingY[cpt]=mainB[1]-1;
          }
          if(mainB[0]-1==updatingX[cpt] && mainB[1]==updatingY[cpt]){//case bas
            tempEcran[mainB[0]][mainB[1]-1]=1;
            tempupdatingX[cpt]=mainB[0];
            tempupdatingY[cpt]=mainB[1]-1;
          }
          if(mainB[0]-1==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){//case bas gauche
            tempEcran[mainB[0]+1][mainB[1]-1]=1;
            tempupdatingX[cpt]=mainB[0]+1;
            tempupdatingY[cpt]=mainB[1]-1;
          }
          if(mainB[0]==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){  // case gauche
            tempEcran[mainB[0]+1][mainB[1]]=1;
            tempupdatingX[cpt]=mainB[0]+1;
            tempupdatingY[cpt]=mainB[1];
          }
          if(mainB[0]+1==updatingX[cpt] && mainB[1]-1==updatingY[cpt]){//case gauche dessus
            tempEcran[mainB[0]+1][mainB[1]+1]=1;
            tempupdatingX[cpt]=mainB[0]+1;
            tempupdatingY[cpt]=mainB[1]+1;
          }
        }                                                                                                                       //pb reponse msg pb taille matrice
        for(byte cpt=0;cpt<4;cpt++){
          updatingX[cpt]=tempupdatingX[cpt];
          updatingY[cpt]=tempupdatingY[cpt];
        }
        for(byte y=mainB[1]-1;y<=mainB[1]+1;y++){
          for(byte x=mainB[0]-1;y<=mainB[0]+1;x++){
            if(y!=mainB[1] && x!=mainB[0]){
              ecran[x][y]=tempEcran[x-mainB[1]][y-mainB[1]];
            }
          }
        }
      }
      else{
        Serial.println("canturn = false");
      }
    }
  }
  if(nbp==1){
    //tourner à droite (touche A)   pas fait
  }
  Serial.print("goDown    ");
  Serial.println(goDown);
  if(nbp==0 && goDown==false) etat=3; //si pas d'action et bloc en dessous = voir ligne
  if(nbp==0 && goDown==true) etat=2;// si aucune action et la pièce peut descendre alors on passe à l'état 2
}
