/*
 * Jeux esquive voiture
 * 
 *  Le but du jeu est simple, on controle une voiture en bas de l'écran LCD que l'on dirige vers la droite ou la gauche afin d'esquiver les voiture qui arrivent en face.
 *  Ce jeu peut être joué jusqu'à l'infini!
 *  
 *  Si une voiture rentre de front dans notre voiture ou si notre voiture tourne alors qu'une autre était à coté, alors la partie est perdue
 *  Le GAME OVER et le score s'affiche pendant 3 secondes. Il y a aussi un système de record afin de savoir si l'on a fait notre meilleur score.
 *  Au bout de ces 3 secondes on peut recomencer une nouvelle partie en appuyant le bouton "droite" (le record est conservé).
 *   
 *  La variable espaceVoiture indique le nombra de case avant qu'une autre voiture apparait à l'écran
 *  1 voiture apparait = 3/4 de chance
 *  2 voiture apparaissent en même temps = 1/4 de chance
 *  
 *  Il semble y avoir des légers problèmes d'affichages de l'écran lcd dans la simulation tinkercad : des points et des barres apparraissent petit a petit en haut à gauche de chaque cases. Cela n'arrive pas sur mon montage en vrai
 *  De plus, la simulation tinkercad est très lente malgré le délai de 0.1s entre chaque update des positions des voitures. 
 */

#include <LiquidCrystal.h>

LiquidCrystal lcd(4, 5, 6, 7, 11, 12);

boolean dead=false;
byte iteration=0;
const byte espaceVoiture=5;                                   //variable espaceVoiture
byte etat=0;
volatile boolean droite=false;  //indique si le bouton "droite/gauche" est appuyé
volatile boolean gauche=false;
byte BPD=2; //pin bp droit
byte BPG=3;
int score=0;
int record=0;
byte alea=0;
byte bLeft[] = {//caractère gauche
  B01110,
  B11111,
  B11111,
  B01110,
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
  B01110,
  B11111,
  B11111,
  B01110
};
byte bFull[] = {
  B01110,
  B11111,
  B11111,
  B01110,
  B01110,
  B11111,
  B11111,
  B01110
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
boolean ecran[16][4] = {0};
byte positionV=2;


void ISR_D(){
  droite=true;
}

void ISR_G(){
  gauche=true;
}

void afficherMatecran(boolean ecran[16][4]){//debug affiche la matrice ecran
  for (int y=0;y<=3;y++){
    for(int x=0;x<=15;x++){
      Serial.print(ecran[x][y]);
    }
    Serial.println();
  }
    Serial.println();
}

void generation(){
  alea=random(4);
  ecran[14][alea]=1;  //creation voiture
  ecran[15][alea]=1;
  if(alea==0){ //1/4 chance d'avoir une 2eme voiture
    byte alea2=random(4); //autre broche pour position différente de la 1ere voiture
    ecran[14][alea2]=1; //creation 2eme voiture
    ecran[15][alea2]=1;
  }
  Serial.println("gen");
}

void action(){
  if(droite==true && gauche==false && positionV!=3){
    if(ecran[1][positionV+1]==1 || ecran[2][positionV+1]==1) dead=true;
    positionV++;
    ecran[1][positionV]=1;
    ecran[2][positionV]=1;
    ecran[1][positionV-1]=0;
    ecran[2][positionV-1]=0;
  }
  if(gauche==true && droite==false && positionV!=0){
    if(ecran[1][positionV-1]==1 || ecran[2][positionV-1]==1) dead=true; //si il y a deja une voiture quand on tourne
    positionV--;
    ecran[1][positionV]=1;
    ecran[2][positionV]=1;
    ecran[1][positionV+1]=0;
    ecran[2][positionV+1]=0;
  }
  gauche=false;
  droite=false;
}

void descente(){
  for(byte x=0;x<=15;x++){
    for(byte y=0;y<=3;y++){
      if(ecran[x][y]==1){
        if(x==1 && y==positionV){
          //rien
        }
        else if(x==2 && y==positionV){
          //rien
        }
        else{
          ecran[x][y]=0;
          ecran[x-1][y]=1;
        }
      }
    }
  }
}

void checkDeath(){
  if(ecran[3][positionV]==1 && ecran[4][positionV]==1) dead=true; //cad que l'on est rentré dans la voiture et pas que l'on est juste devant la voiture
}

void impression(){
  for(byte x=0;x<=15;x++){
    for(byte y=0;y<=3;y=y+2){
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
  afficherMatecran(ecran);
}

void death(){
  delay(700);
  lcd.clear();
  lcd.print("GAME OVER");
  lcd.setCursor(0,1);
  lcd.print("Score");
  lcd.print(score);
  if(score>=record){
    lcd.print("RECORD");
    record=score;
  }
  delay(3000);
  lcd.setCursor(0,1);
  lcd.print("RETRY ? = droite");
}

void setup() {
  lcd.begin(16, 2);
  lcd.setCursor(0,0);
  lcd.clear();
  Serial.begin(9600);
  attachInterrupt(digitalPinToInterrupt(BPD), ISR_D, FALLING);
  attachInterrupt(digitalPinToInterrupt(BPG), ISR_G, FALLING);
  randomSeed(analogRead(0));
  ecran[1][positionV]=1;
  ecran[2][positionV]=1;
}

void loop() {
  switch(etat){
    case 0:{
      generation();
      impression();
      etat=1;
      break;
    }
    case 1:{
      action();
      descente();
      checkDeath();
      impression();
      iteration++;
      score++;
      if(iteration==espaceVoiture){
        etat=0;
        iteration=0;
      }
      if(dead) etat=2;
      delay(100);
      break;
    }
    case 2:{
      death();
      droite=false;
      etat=3;
      break;
    }
    case 3:{
      if(droite==true) etat=4;
      delay(500);
      break;
    }
    case 4:{
      for(byte x=0;x<=15;x++){
        for(byte y=0;y<=3;y++){
          ecran[x][y]=0;
        }
      }
      positionV=2;
      ecran[1][positionV]=1;
      ecran[2][positionV]=1;
      etat=0;
      droite=false;
      dead=false;
      score=0;
      Serial.print("record:");
      Serial.println(record);
      break;
    }
  }
}
