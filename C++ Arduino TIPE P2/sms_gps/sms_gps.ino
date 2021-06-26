#include <TinyGPS++.h>
#include <SoftwareSerial.h>

//gps
int RXPin = 11;  //inverser RX TX au montage
int TXPin = 12;

TinyGPSPlus gps;

//SIM900
SoftwareSerial mySerial(7, 8); 
//gps
SoftwareSerial gpsSerial(RXPin, TXPin);

double latitude=0;
double longitude=0;

void setup() {
  Serial.begin(9600);
  Serial.println("test begin");
  delay(10);
  Serial.println("test");
  alerte();
}

void loop() {
}

//bouton urgence appuyé
void alerte(){
  SIM900power();  //allumage SIM900
  Serial.println("SIM900 powered");
  //init arduino/gps+gsm
  //Serial.begin(9600);
  
  
  gpsSerial.begin(9600); //com gps
  Serial.println("latitude");
  Serial.println(latitude);
  Serial.println("gps.location.isValid()");
  Serial.println(gps.location.isValid());
  Serial.println("while begin");
  while(latitude == 0){
    Serial.println("if 1");
    if (gps.encode(gpsSerial.read())){
      Serial.println("if 2");
      if (gps.location.isValid()){
        latitude = gps.location.lat();
        Serial.println(latitude,6);
      }
    }
  }
  Serial.println("fin while");
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
void SIM900power()
{
  pinMode(9, OUTPUT); 
  digitalWrite(9,LOW);
  delay(1000);
  digitalWrite(9,HIGH);
  delay(2000);
  digitalWrite(9,LOW);
  delay(3000);
}
