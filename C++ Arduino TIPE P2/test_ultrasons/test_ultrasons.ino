//trig = 2; echo = 3;trig2 = 4;echo2 = 5; control = 11; (talk) : 13/12
long lecture_echo;
long cm;
int const nb_echantillon = 5;
int const nb_capteur = 2;
int distanceD[nb_echantillon];
int distanceG[nb_echantillon];
int num_distanceD=0;
int num_distanceG=0;

void setup(){
  for(byte i=2;i<=nb_capteur*2;i=i+2){  //ouverture de 2 et 4 | 3 et 5
    pinMode(i, OUTPUT);
    digitalWrite(i, LOW);
    pinMode(i+1, INPUT);
  }
  Serial.begin(9600);
}

void loop(){
  scan_distance();
}

void scan_distance(){
  digitalWrite(2, HIGH);
  delayMicroseconds(10); // vitesse d'impulsion
  digitalWrite(2, LOW);
  lecture_echo = pulseIn(3,HIGH);
  cm = lecture_echo /58;
  
  Serial.print("Distance en cm :");
  Serial.println(cm);
  Serial.print("num_distance : ");
  Serial.println(num_distanceD);
  Serial.print("size : ");
  Serial.println(sizeof(distanceD));

  if (num_distanceD<nb_echantillon){
    distanceD[num_distanceD] = cm;
    num_distanceD=num_distanceD+1;
  }
  else{
    for (int i = 0; i < nb_echantillon-1; i++){
      distanceD[i] = distanceD[i+1];
    }
    distanceD[nb_echantillon-1] = cm;
  }
  for(int i = 0; i < nb_echantillon; i++){
    Serial.println(distanceD[i]);
  }


  digitalWrite(4, HIGH);
  delayMicroseconds(10); // vitesse d'impulsion
  digitalWrite(4, LOW);
  lecture_echo = pulseIn(5,HIGH);
  cm = lecture_echo /58;
  
  if (num_distanceG<nb_echantillon){
    distanceG[num_distanceG] = cm;
    num_distanceG=num_distanceG+1;
  }
  else{
    for (int i = 0; i < nb_echantillon-1; i++){
      distanceG[i] = distanceG[i+1];
    }
    distanceG[nb_echantillon-1] = cm;
  }
  for(int i = 0; i < nb_echantillon; i++){
    Serial.println(distanceG[i]);
  }
  
}
