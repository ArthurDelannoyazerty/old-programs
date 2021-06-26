#include <SoftwareSerial.h>

//Create software serial object to communicate with SIM900
SoftwareSerial mySerial(7, 8); //SIM900 Tx & Rx is connected to Arduino #7 & #8


void setup()
{
  SIM900power();
  
  //Begin serial communication with Arduino and Arduino IDE (Serial Monitor)
  Serial.begin(9600);
  
  //Begin serial communication with Arduino and SIM900
  mySerial.begin(9600);

  Serial.println("Initializing..."); 
  delay(1000);

  mySerial.println("AT"); //Handshaking with SIM900
  updateSerial();
  mySerial.println("AT+CMGF=1"); // Configuring TEXT mode
  updateSerial();
  mySerial.println("AT+CPIN=""0000"""); // PIN
  updateSerial();
  /*mySerial.println("AT+CCLK=""21/02/15,15:28:00-00"""); // clock
  updateSerial();*/
  
  // Give time to your GSM shield log on to network
  delay(10000); 
  mySerial.println("AT+CSQ"); // dB
  updateSerial();
  mySerial.print("AT+CMGF=1\r"); 
  delay(100);
  mySerial.println("AT+CREG?"); //Check whether it has registered in the network
  updateSerial();
  
  mySerial.println("AT+CMGS=\"+33612458536\"");//change ZZ with country code and xxxxxxxxxxx with phone number to sms
  updateSerial();
  mySerial.print("test"); //text content
  updateSerial();
  mySerial.write(26);
}

void loop(){
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
