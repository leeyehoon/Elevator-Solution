#include <ESP8266WiFi.h>
#include <Arduino.h>

#include <FB_Const.h>
#include <FB_Error.h>
#include <FB_Network.h>
#include <FB_Utils.h>
#include <Firebase.h>
#include <FirebaseFS.h>
#include <Firebase_ESP_Client.h>
#include <MB_NTP.h>
#include <addons/RTDBHelper.h>

#include <Adafruit_NeoPixel.h>
#include <Servo.h>

#define FIREBASE_HOST "wac-iot-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "79hd7Ng8r4a406NyBpmWLzkVSnhTOmIE8FmWbgZ6"
#define WIFI_SSID "YH"
#define WIFI_PASSWORD "34423442"
FirebaseData fbdo;  // Firebase 데이터 객체
FirebaseAuth auth;  // Firebase 인증용 객체
FirebaseConfig config;  //Firebase 설정용 객체
Servo myservo;

void setup() {
  Serial.begin(115200);
  wifi_set();
  myservo.attach(D7);
}

void loop() {
  motor();
}


/*WIFI 세팅*/
void wifi_set(){
  //String WIFI_SSID,WIFI_PASSWORD;
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(1000);
  }
  Serial.println();
  Serial.print("connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.printf("Firebase Client v%s\n\n",FIREBASE_CLIENT_VERSION);
  config.database_url = FIREBASE_HOST;
  config.signer.tokens.legacy_token = FIREBASE_AUTH;
  Firebase.reconnectWiFi(true);
  Firebase.begin(&config, &auth);
  delay(1000);
  Firebase.reconnectWiFi(true);
  }

  void motor(){
    int flag;
      if(Firebase.RTDB.getInt(&fbdo,"/Floor") == true){
      String tmp = fbdo.to<const char *>();
      int floor = tmp.toInt();
      Serial.print("floor : ");
      Serial.println(floor);
      } else Serial.println(fbdo.errorReason().c_str());
      
  }