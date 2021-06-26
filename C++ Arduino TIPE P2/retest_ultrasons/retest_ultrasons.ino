long lecture_echo;
long cm;
int const nb_echantillon = 10;
int const nb_capteur = 1;
int const donnee = 2*nb_capteur;
long distance[donnee][nb_echantillon];
int echantillon = 0;



void setup() {
  for(byte i=2;i<=nb_capteur*2;i=i+2){  //ouverture de 2 et 4 | 3 et 5
    pinMode(i, OUTPUT);
    digitalWrite(i, LOW);
    pinMode(i+1, INPUT);
  }
  Serial.begin(9600);
}

void loop() {
  for(byte capteur=2; capteur<=nb_capteur*2; capteur += 2){
    digitalWrite(capteur, HIGH);
    delayMicroseconds(10); // vitesse d'impulsion
    digitalWrite(capteur, LOW);
    lecture_echo = pulseIn(capteur+1,HIGH);
    cm = lecture_echo /58;
    long unsigned tps = millis();
    if(echantillon==nb_echantillon){
      if(distance[capteur-1][echantillon-1]!=0){  
        translationMat();
      }
      distance[capteur-2][echantillon-1] = cm;
      distance[capteur-1][echantillon-1] = tps;
      echantillon=nb_echantillon-1;
    }
    else{
      distance[capteur-2][echantillon] = cm;
      distance[capteur-1][echantillon] = tps;
    }

    Serial.print(cm);
    Serial.print("  ");
    delay(((cm*10*2)/340)*0.9); // attente entre 2 scans// 90% de ce temps car temps pour les calculs
  }
  if(distance[nb_capteur-1][echantillon-1]!=0){
    //Traitement des données
  }
  Serial.println();
  echantillon = echantillon+1;
}



void translationMat(){
  for(int ech=1; ech<nb_echantillon; ech++){
    for(byte donnees = 0; donnees<donnee; donnees++){
      distance[donnees][ech-1] = distance[donnees][ech];
    }
  }
  for(byte donnees = 0; donnees<donnee; donnees++){
     distance[donnees][nb_echantillon-1] = 0;
  }
}

void afficherMat(){
  for(int ech=0; ech<nb_echantillon; ech++){
    for(byte donnees = 0; donnees<donnee; donnees++){
        Serial.print(distance[donnees][ech]);
        Serial.print(" | ");
      }
      Serial.println();
    }  
}
