#include<ESP8266WiFi.h>
#include<ESP8266WebServer.h>
#include <WiFiClient.h>

// WiFi Definitions
const char* ssid_sta = "jivko";
const char* password_sta = "jivkottt";
const char* ssid_ap = "Esp8266AP";
const char* password_ap = "Esp8266AP";
const char* value = "";

IPAddress ip(192, 168, 4, 1);
IPAddress gateway(192, 168, 4, 1);
IPAddress netmask(255, 255, 255, 0);

ESP8266WebServer server;

void handleRoot() {
  server.send(200, "text/plain", "hello from esp8266!");
}

void setup() {
   Serial.begin(115200);
   delay(10);
   pinMode(LED_BUILTIN, OUTPUT);
   digitalWrite(LED_BUILTIN, LOW);
   WiFi.mode(WIFI_AP);
   WiFi.softAPConfig(ip, gateway, netmask);
   WiFi.softAP(ssid_ap, password_ap);
//    WiFi.mode(WIFI_STA);
//    delay(100);
//    WiFi.begin(ssid_sta, password_sta);
      
   server.on("/led?state=on",ledON);
   server.on("/led?state=off",ledOFF);
   server.on("/", handleRoot);
   server.begin();
     Serial.println("TEST3");
}

void loop() {
//  server.handleClient();
  // Check if client has connected
//  WiFiClient client = server.available();
//  if(!client) {
//    return;
//  }
//
//  // Read the request line
//  String request = client.readStringUntil('\r');
//  Serial.println(request);
//  client.flush();
  
//  // Match request
//  if(request.indexOf("/led?state=on") != -1) {
//     digitalWrite(LED_BUILTIN, LOW);
//     value = "on";
//  } else if (request.indexOf("/led?state=off") != -1) {
//     digitalWrite(LED_BUILTIN, HIGH);
//     value = "off";
//  } else if (request.indexOf("/switch?mode=ap") != -1) {
//    WiFi.mode(WIFI_AP);
//    WiFi.softAPConfig(ip, ip, netmask);
//    WiFi.softAP(ssid_ap, password_ap);
//  } else if (request.indexOf("/switch?mode=sta") != -1) {
//    WiFi.softAPdisconnect();
//    WiFi.disconnect();
//    WiFi.mode(WIFI_STA);
//    delay(100);
//    WiFi.begin(ssid_sta, password_sta);
//  }
  
//  client.flush();
//   
//  // create json response
//  String s = "HTTP/1.1 200 OK\r\n";
//  s += "Content-Type: application/json\r\n\r\n";
//  s += "{\"data\":{\"message\":\"success\",\"value\":\"";
//  s += value;
//  s += "\"}}\r\n";
//  s += "\n";
//
//  // Send the response
//  client.print(s);
//  delay(1);
//  Serial.println("Client disconnected");
}

void ledON()
{
  Serial.println("TEST1");
  digitalWrite(LED_BUILTIN, LOW);
  value = "on";
  server.send(200,""); 
}
void ledOFF()
{
    Serial.println("TEST2");
  digitalWrite(LED_BUILTIN, HIGH);
  value = "off";
  server.send(200,""); 
}  
//String createJsonResponce()
//{
//  String s = "HTTP/1.1 200 OK\r\n";
//  s += "Content-Type: application/json\r\n\r\n";
//  s += "{\"data\":{\"message\":\"success\",\"value\":\"";
//  s += value;
//  s += "\"}}\r\n";
//  s += "\n";
//  return s;
//}
