#include <TinyGPS++.h>
#include <SoftwareSerial.h>

//gps
int RXPin = 11;  //inverser RX TX au montage
int TXPin = 12;

//trig = 2; echo = 3;trig2 = 4;echo2 = 5; control = 11; (talk) : 13/12
long lecture_echo;
long cm;
int const nb_echantillon = 5;
int const nb_capteur = 2;
int distanceD[nb_echantillon];
int distanceG[nb_echantillon];
int num_distanceD=0;
int num_distanceG=0;


//creation objet gps
TinyGPSPlus gps;
//SIM900
SoftwareSerial mySerial(7, 8); 
//gps
SoftwareSerial gpsSerial(RXPin, TXPin);

double latitude=0;
double longitude=0;

void setup() {
  for(byte i=2;i<=nb_capteur*2;i=i+2){  //ouverture de 2 et 4 | 3 et 5
    pinMode(i, OUTPUT);
    digitalWrite(i, LOW);
    pinMode(i+1, INPUT);
  }
  pinMode(6,OUTPUT);
  Serial.begin(9600);
  delay(10);
}

void loop(){
  scan_distance();
  delay(1);
  test_distance();
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

void test_distance(){
  int disD = 0;
  for(int i = 0; i < nb_echantillon; i++){
    disD = disD + (nb_echantillon-i)*distanceD[i];
  }
  disD = int(disD/15);
  int disG = 0;
  for(int i = 0; i < nb_echantillon; i++){
    disG = disG + (nb_echantillon-i)*distanceG[i];
  }
  disG = int(disG/15);
  Serial.print(disG);

  int delai = 300;
  
  //rien
  if (disD>500 && disG>500){
    Serial.println("pas d'obstacle");
    digitalWrite(6,LOW);
  }

  //droite
  if (disD<500 && disG>500){
    Serial.println("Obstacle a droite");
    int freq=int(map(disD, 0, 5000, 1200, 100));
    tone(6,freq,100);
    delay(delai);
    tone(6,freq,100);
    delay(delai);
    tone(6,freq,100);
  }

  //gauche
  if (disD>500 && disG<500){
    Serial.println("obstacle a gauche");
    tone(6,int(map(disG, 0, 5000, 1200, 100)),0.1);
      
  }

  //milieu
  if (disD<500 && disG<500){
    Serial.println("Obstacle au milieu");
    int freq = int(map(min(disD,disG), 0, 5000, 1200, 100));
    tone(6,freq,100);
    delay(delai);
    tone(6,freq,100);
  }
  delay(500);
}

//////////////////////////////////////alerte/////////////////////////////////////////////

//bouton urgence appuyé
void alerte(){
  SIM900power();  //allumage SIM900
  Serial.println("SIM900 powered");
  //init arduino/gps+gsm
  //Serial.begin(9600);
  
  
  gpsSerial.begin(9600); //com gps
  /*
  Serial.println("latitude");
  Serial.println(latitude);
  Serial.println("gps.location.isValid()");
  Serial.println(gps.location.isValid());
  Serial.println("while begin");
  */
  while(latitude == 0){
    //Serial.println("if 1");
    if (gps.encode(gpsSerial.read())){
      //Serial.println("if 2");
      if (gps.location.isValid()){
        latitude = gps.location.lat();
        Serial.println(latitude,6);
      }
    }
  }
  //Serial.println("fin while");
  //envoi sms d'urgence
  mySerial.begin(9600); //com SIM900
  mySerial.println("AT+CMGF=1"); // Configuring TEXT mode
  updateSerial();
  mySerial.println("AT+CPIN=""0000"""); // PIN
  updateSerial();
  // Give time to your GSM shield log on to network
  delay(10000); 
  mySerial.print("AT+CMGF=1\r"); 
  delay(100);
  mySerial.println("AT+CREG?"); //Check whether it has registered in the network
  updateSerial();
  mySerial.println("AT+CMGS=\"+33652709625\"");//change ZZ with country code and xxxxxxxxxxx with phone number to sms
  updateSerial();
  String text_send = "google.fr/maps/@" + String(latitude) + "," + String(longitude) + "z";
  mySerial.print(text_send); //text content
  updateSerial();
  mySerial.write(26);
}

void get_lat_lon(){
  Serial.println("get_lat_lon");
  while(gps.location.lat()==0){
    delay(1000);
    Serial.println("wait lat");
  }
  latitude = gps.location.lat();
  longitude = gps.location.lng();
  Serial.print("lat get finish");
  
}

void updateSerial()
{
  delay(500);
  while (Serial.available()) 
  {
    mySerial.write(Serial.read());//Forward what Serial received to Software Serial Port
  }
  while(mySerial.available()) 
  {
    Serial.write(mySerial.read());//Forward what Software Serial received to Serial Port
  }
}

//allumage SIM900
void SIM900power(){
  pinMode(9, OUTPUT); 
  digitalWrite(9,LOW);
  delay(1000);
  digitalWrite(9,HIGH);
  delay(2000);
  digitalWrite(9,LOW);
  delay(3000);
}
