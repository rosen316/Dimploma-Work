#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>

// WiFi Definitions
String ssid_sta = "";
String password_sta = "";
String ssid_ap = "Esp8266AP";
String password_ap = "Esp8266AP";

uint8_t Relay_Pin = 0;

IPAddress ip(192, 168, 4, 1);
IPAddress gateway(192, 168, 4, 1);
IPAddress netmask(255, 255, 255, 0);

ESP8266WebServer server(80);

void setup(void) {
   Serial.begin(115200);
   delay(10);
   pinMode(Relay_Pin, OUTPUT);
   digitalWrite(Relay_Pin, LOW);
   WiFi.mode(WIFI_AP);
   WiFi.softAPConfig(ip, gateway, netmask);
   WiFi.softAP(ssid_ap, password_ap);

  server.on("/led", LedSwitch);  
  server.on("/switch", ModeSwitch);
  server.on("/config", ConfigSwitch);
  server.begin();           

}

void loop(void) {
  server.handleClient();    
}

void LedSwitch() {
  if(server.arg("state") == "on")
  {
    digitalWrite(Relay_Pin, HIGH);
    server.send(200, "text/plain", "Led state changed to on");      
  }
  else if(server.arg("state") == "off")
  {
    digitalWrite(Relay_Pin, LOW);
    server.send(200, "text/plain", "Led state changed to off") ;   
  }
  else
  {
    server.send(404, "text/plain", "Route not found");
  } 
}

void ModeSwitch() {
  if(server.arg("mode") == "sta")
  {
    WiFi.softAPdisconnect();
    WiFi.disconnect();
    WiFi.mode(WIFI_STA);
    delay(100);
    WiFi.begin(ssid_sta, password_sta);
    server.send(200, "text/plain", "Mode set to station");      
  }
  else if(server.arg("mode") == "ap")
  {
    WiFi.mode(WIFI_AP);
    WiFi.softAPConfig(ip, ip, netmask);
    WiFi.softAP(ssid_ap, password_ap); 
    server.send(200, "text/plain", "Mode set to access point");  
  }
  else
  {
    server.send(404, "text/plain", "Route not found");
  } 
}

void ConfigSwitch()
{
   if(server.arg("ssid_ap") != "")
   {
       ssid_ap = server.arg("ssid_ap");
   }
   if(server.arg("password_ap").length() > 7)
   {
       password_ap = server.arg("password_ap");
   }
   if(server.arg("password_ap") == "none")
   {
       password_ap = "";
   }
//     if(server.arg("ip_address_ap") != "")
//   {
//       ip = ip(192, 168, 4, 1)server.arg("ip_address_ap");
//   }
      if(server.arg("ssid_sta") != "")
   {
       ssid_sta = server.arg("ssid_sta");
   }
   if(server.arg("password_sta").length() > 7)
   {
       password_ap = server.arg("password_sta");
   }
   if(server.arg("password_sta") == "none")
   {
       password_sta = "";
   }
   
}
