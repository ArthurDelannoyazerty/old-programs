/*Nous avons décidé de recréer le célebre jeu Tetris en Arduino. Le but est le divertissement (peut-être nostalgique) du joueur. 
Les pièces tombent aléatoirement et on peut les déplacer latéralement mais pas les faire touner ni accéléer leur chute.

L'utilisateur doit appuyer sur les touches 2 et 3 afin de respectivement déplacer les pièces à gauche et à droite.
Lorsque le joueur a perdu, le message "GAME OVER" s'affiche et un simple appui sur le touche A relance une nouvelle partie.

La taille de l'écran est adaptable en modifiant la valeur de la constante 'largeur'
 */
const byte largeur=4; //largueur de l'écran = indiquer le nombre de case de largueur 

// brochage 
// C1 - C2 - C3 - C4 - L1 - L2 - L3 - L4
// 6    5    4    3    2   
         
/*A LIRE
//contrôles:
//2 gauche            nbp=2
//3 droit             nbp=1
//1 tourner gauche    nbp=3 WIP
//A tourner droite    nbp=0 WIP
//ne pas appuyer sur 1 pendant la partie(bug)
  Changer l'initialisation de l'écran (2eme ligne de code)
  Il est possible que le jeu ne marche pas avec un ecran avec un shield (initialisation de l'écran et écriture sur l'écran
*/

//3 états : 0 clear = blocs tombés,blocs supprimé(ligne)     (attention aux murs)
//          1 falling = blocs sont en train de tomber (vérifier que rien en dessous)
//          2 destroy = le bloc est tombé sur un autre ->vérifier le 4 px de hauteur pour voir si une ligne
//          3 over 

//quels bloc dispo?
// le L
//Z
//T
//cube

//▄ = droite = alt 220
//▀ = gauche = alt 223
//█ = plein = alt 219

//a faire :  tourner les pièces / game over / recommencer le jeu


#include <LiquidCrystal.h>
LiquidCrystal lcd(7, 8, 9, 10, 11, 12);
//LiquidCrystal lcd(0x27,16,largeur);

boolean ecran[16][2*largeur] = {0};   //matrice de l'écran en 16 par largeur
boolean gen[3][2] = {0};      //matrice pour stocker les blocs générésau départ
          //x, y
byte updatingX[4] = {0};    //matrice comportant les valeurs en x des 4 blocs en cours de jeu
byte updatingY[4] = {0};    //matrice comportant les valeurs en y des 4 blocs en cours de jeu
byte tempupdatingX[4] = {0};  //matrice temporairepour stocker les valeurs en x(et y) lors de la rotation des blocs
byte tempupdatingY[4] = {0};
byte etat = 0;        // état de la machine a etat
boolean goRight;    //boolean si on peut aller à gauche
boolean goLeft;     //boolean si on peut aller a droite
boolean goDown;     //boolean si on peut descendre
byte cptupdate;     //jsp
byte mainB[2];      // stocke les coordonnées du centre de la pièce en cours de jeu = celle sur lasuelle la pièce est censée rotationner
//x,y

//custom characteres
byte bLeft[] = {//caractère gauche
  B11111,
  B11111,
  B11111,
  B11111,
  B00000,
  B00000,
  B00000,
  B00000
};
byte bRight[] = {
  B00000,
  B00000,
  B00000,
  B00000,
  B11111,
  B11111,
  B11111,
  B11111
};
byte bFull[] = {
  B11111,
  B11111,
  B11111,
  B11111,
  B11111,
  B11111,
  B11111,
  B11111
};
byte bEmpty[] = {
  B00000,
  B00000,
  B00000,
  B00000,
  B00000,
  B00000,
  B00000,
  B00000
};
int a;  //variable pour la selection aléatoire de la pièce générée au début du jeu


void setup(){
  Serial.begin(9600);
  for (byte p = 3;  p <= 6; p++) pinMode(p, INPUT_PULLUP);
  pinMode(2,OUTPUT);
  lcd.begin(16,largeur);
  lcd.home();
}


void afficherMatgen(boolean gen[3][2]){//débug : affiche la matrice gen (pour génération(de la pièce))
  Serial.println("gen");
  for (int y=0;y<=1;y++){
    for(int x=0;x<=2;x++){
      Serial.print(gen[x][y]);
    }
    Serial.println();
  }
    Serial.println();
}


void afficherMatecran(boolean ecran[16][2*largeur]){//debug affiche la matrice ecran
  Serial.println("ecran");
  for (int y=0;y<=(2*largeur)-1;y++){
    for(int x=0;x<=15;x++){
      Serial.print(ecran[x][y]);
    }
    Serial.println();
  }
    Serial.println();
}

void descente(){// fait descendre les 4 pièces en cours de jeu
  checkgodown();
  if(goDown==true){
      for(byte cpt=0;cpt<cptupdate;cpt++){
        ecran[updatingX[cpt]-1][updatingY[cpt]]=true;
        ecran[updatingX[cpt]][updatingY[cpt]]=false;
        updatingX[cpt]=updatingX[cpt]-1;
      }
      mainB[0]=mainB[0]-1;
      etat=1;
    }
  else{
    etat=3;
  }
}

void checkline(){// vérifie si il y a une ou des lignes pleines
  byte somme=0;
  byte niveauXDescente=0;
  for(byte z=0;z<=2;z++){
  for(byte x=0;x<=15;x++){
    for(byte y=0;y<=(2*largeur)-1;y++){
      somme=somme+ecran[x][y];
    }
    if(somme==2*largeur){
      for(byte clignotement=0;clignotement<=3;clignotement++){  
        for(byte y=0;y<=(2*largeur)-1;y++){
          ecran[x][y]=0;
        }
        impression();
        delay(3);
        for(byte y=0;y<=(2*largeur)-1;y++){
          ecran[x][y]=1;
        }
        impression();
        delay(3);
      }
      for(byte y=0;y<=(2*largeur)-1;y++){
          ecran[x][y]=0;
      }
      niveauXDescente=x;
      totaldescente(niveauXDescente);
    }
    somme=0;
  }
  }
  etat=0;
}


void totaldescente(byte niveauX){// fait descnedre les pièces au dessus de la ligne suprimée
  for(byte x=niveauX+1;x<=15;x++){
    for(char y=0;y<=(2*largeur)-1;y++){
      ecran[x-1][y]=ecran[x][y];
      ecran[x][y]=0;
    }
  }
}

void impression(){//va scanner la matrice ecran 2 à 2 dans la largeur et va afficher soit une case vide, soit une a moitié remplie a droite, soit une a moitié remplie a gauche stoi une case pleine
  lcd.home();
  for(byte x=0;x<=15;x++){
    for(byte y=0;y<=(2*largeur)-1;y=y+2){
      if(ecran[x][y]==0 && ecran[x][y+1]==0){//case vide
        lcd.createChar(1,bEmpty);
        lcd.setCursor(x,y/2);
        lcd.write(char(1));
        //Serial.println("empty");
      }
      if(ecran[x][y]==0 && ecran[x][y+1]==1){//case droite
        lcd.createChar(2,bRight);
        lcd.setCursor(x,y/2);
        lcd.write(char(2));
        //Serial.println("right");
      }
      if(ecran[x][y]==1 && ecran[x][y+1]==0){// case gauche
        lcd.createChar(3,bLeft);
        lcd.setCursor(x,y/2);
        lcd.write(char(3));
        //Serial.println("left");
      }
      if(ecran[x][y]==1 && ecran[x][y+1]==1){// case pleine
        lcd.createChar(4,bFull);
        lcd.setCursor(x,y/2);
        lcd.write(char(4));
        //Serial.println("full");
      }
    }
  }
}

void checkgodown(){//vérifie si on peut aller en bas
  //Serial.println("debut checkgodown");
  for(byte y=0;y<=(2*largeur)-1;y++){
    for(byte x=0;x<=15;x++){
      for(byte cpt=0;cpt<4;cpt++){
        if(y==updatingY[cpt] && x==updatingX[cpt]){
          if(ecran[x-1][y]==1 || x==0){
            goDown=false;
            y=(2*largeur)-1;
            x=15;
          }
          else{
            goDown=true;
            y=y+1;
            x=0;
          }
        }
      }
    }
  }
}

void checkgoright(){//vérifie si on peut aller a droite
  //Serial.println("debut checkgoright");
  for(byte x=0;x<=15;x++){
    for(byte y=0;y<=(2*largeur)-1;y++){
      for(byte cpt=0;cpt<4;cpt++){
        if(y==updatingY[cpt] && x==updatingX[cpt]){
          if(ecran[x][y-1]==1 || y==0){
            goRight=false;
            y=(2*largeur)-1;
            x=15;
          }
          else{
            goRight=true;
            x=x+1;
            y=0;
          }
        }
      }
    }
  }
}

void checkgoleft(){//vérifie si on peut aller a gauche
  //Serial.println("debut checkgoleft");
  for(byte x=0;x<=15;x++){
    for(char y=(2*largeur)-1;y>=0;y--){
      for(byte cpt=0;cpt<4;cpt++){
        if(y==updatingY[cpt] && x==updatingX[cpt]){
          if(ecran[x][y+1]==1 || y==(2*largeur)-1){
            goLeft=false;
            y=0;
            x=15;
          }
          else{
            goLeft=true;
            x=x+1;
            y=(2*largeur)-1;
          }
        }
      }
    }
  }
}

void checkOver(){// vérifie si la partie est finie = si un bloc est en haut de l'écran
  for(byte y=0;y<=(2*largeur-1);y++){
    if(ecran[15][y]==1) etat=4;
  }
}

void over(){// affiche le game over et le recommencer
  for(byte y=0;y<=(2*largeur)-1;y++){
    for(byte x=0;x<=15;x++){
      ecran[x][y]=0;
      Serial.println("efface");   //vide la matrice ecran
    }
  }
  impression();
  lcd.setCursor(0,0);
  lcd.write("GAME OVER");   //affiche game over
  lcd.setCursor(0,1);
  lcd.write("recommencer? =>A"); // appuyer sur le A pour recommencer
}


void loop(){
  switch(etat){
    case 0:{  //débute de partie
      generation();   //génération des blocs de la première pièce
      etat = 1;     //change d'état
      break;
    }
    case 1:{//à chaque "tour"
      checkgodown();//voit si la pièce peut descendre
      checkgoright();//voit si la pièce peut aller à gauche (oui oui a gauche)
      checkgoleft();//voit si la pièce peut aller à droite
      if(goRight==false && goLeft==false && goDown==false) etat=3; // si la pièce ne peut ni descendre ni aller a droite et a gauche alors la pièce est fixée et on change d'état
      action(); //vérifie l'action possible du joueur
      afficherMatecran(ecran);//debug : affiche la matrice ecran
      impression();//affiche la matrice ecran sur l'écran lcd
      delay(10);
      break;
    }
    case 2:{//fin du "tour"
      descente();//fait descendre la pièce en cours de jeu
      impression();//imprime la matrice ecran sur lécran lcd
      break;
    }
    case 3:{//etat si la pièce jouée est fixée
      checkline();  //vérifie si une ligne est pleine
      checkOver();// vérifie si la partie est finie (bloc en heut de l'écran
      break;
    }
    case 4:{ //etat si la partie est finie
      over();   // affiche game over et recommencer
      etat=5;
      break;
    }
    case 5:{// etat recommencer : vérifie si le joueur veut recommencer une partie
      byte nbp=0;
      for (byte c = 3; c <= 6; c++){
        byte bp = digitalRead(c);
        if (bp == LOW){
        nbp = c-2;
        }
      }
      if(nbp==1){
        for(byte y=0;y<=(2*largeur)-1;y++){
          for(byte x=0;x<=15;x++){
            ecran[x][y]=0;
          }
        }
        etat=0;
      }
    }
  }
  delay(5);
}
