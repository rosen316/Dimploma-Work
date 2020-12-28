#include<ESP8266WiFi.h>

// WiFi Definitions
const char* ssid = "jivko";
const char* password = "jivkottt";
const char* value = "";

WiFiServer server(80);

void setup() {
   Serial.begin(115200);
   delay(10);
   pinMode(LED_BUILTIN, OUTPUT);
   digitalWrite(LED_BUILTIN, LOW);
   WiFi.mode(WIFI_STA);
   WiFi.begin(ssid, password);

   //wait till esp is connected to wifi
   while (WiFi.status() != WL_CONNECTED) {
   delay(500);
   Serial.print(".");
   }
   Serial.println("");
   Serial.println(WiFi.localIP());
   server.begin();
}

void loop() {
  // Check if client has connected
  WiFiClient client = server.available();
  if(!client) {
    return;
  }

  // Read the request line
  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush();
  
  // Match request
  if(request.indexOf("/led?state=on") != -1) {
     digitalWrite(LED_BUILTIN, LOW);
     value = "on";
  } else if (request.indexOf("/led?state=off") != -1) {
     digitalWrite(LED_BUILTIN, HIGH);
      value = "off";
  }
  
  client.flush();
   
  // create json response
  String s = "HTTP/1.1 200 OK\r\n";
  s += "Content-Type: application/json\r\n\r\n";
  s += "{\"data\":{\"message\":\"success\",\"value\":\"";
  s += value;
  s += "\"}}\r\n";
  s += "\n";

  // Send the response
  client.print(s);
  delay(1);
  Serial.println("Client disconnected");
}
