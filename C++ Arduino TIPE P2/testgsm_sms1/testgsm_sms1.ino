//Test_SIM800_SMS
//Ce logiciel émet un SMS vers le N° INDIQU2!!


#include <SoftwareSerial.h>
 
//SIM800 TX is connected to Arduino D10
#define SIM800_TX_PIN 7   //c'est RX pour le Nano
 
//SIM800 RX is connected to Arduino D11
#define SIM800_RX_PIN 8  //c'est TX pour le Nano et diviseur de tension
 
//Create software serial object to communicate with SIM800
SoftwareSerial serialSIM800(SIM800_TX_PIN,SIM800_RX_PIN);
 
void setup() {
  //Begin serial comunication with Arduino and Arduino IDE (Serial Monitor)
  Serial.begin(9600);
  while(!Serial);
   
  //Being serial communication witj Arduino and SIM800
  serialSIM800.begin(9600);
  delay(1000);
   
  Serial.println("Setup Complete!");
  Serial.println("Sending SMS...");
   
  //Set SMS format to ASCII
  serialSIM800.write("AT+CMGF=1\r");
 
  delay(1000);
 
  //Send new SMS command and message number
  serialSIM800.write("AT+CMGS=\"+330652709625\"\r");    //Numero  à entrer
 
  delay(1000);
   
  //Send SMS content
  serialSIM800.write("TEST3");          //  Texte à entrer
  delay(1000);
   
  //Send Ctrl+Z / ESC to denote SMS message is complete
  serialSIM800.write((char)26);
  delay(1000);
     
  Serial.println("SMS Sent!");
}
 
void loop() {
}
