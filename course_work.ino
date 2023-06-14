#include <dht.h>
#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BMP280.h>
#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
#include <MQ135.h>

#define DHT_PIN 2
#define MQ135_PIN 35

const char* ssid = "TP-Link_0F9A";
const char* password = "81240999";
const char* temperatureAndHumiditySensorEndpoint = "http://192.168.0.104:8080/dht11/";
const char* barometerEndpoint = "http://192.168.0.104:8080/barometer/";
const char* co2Endpoint = "http://192.168.0.104:8080/co2/";

dht dht11;
Adafruit_BMP280 bmp;
MQ135 mq135(MQ135_PIN);

void setup() {
  Serial.begin(115200);
  
  if (!bmp.begin(0x76)) {
    Serial.println("Could not find a valid BMP280 sensor, check wiring!");
    while (1);
  }
  
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }
  Serial.println("Connected to WiFi");
}

void sendPostRequest(const char* endpoint, const String& payload) {
  HTTPClient http;
  http.begin(endpoint);
  http.addHeader("Content-Type", "application/json");
  int httpResponseCode = http.POST(payload);

  if (httpResponseCode > 0) {
    String response = http.getString();
    Serial.println("HTTP Response: " + response);
  } else {
    Serial.print("Error: ");
    Serial.println(httpResponseCode);
  }

  http.end();
}

void loop() {
  dht11.read11(DHT_PIN);
  DynamicJsonDocument json(128);
  json["temperature"] = dht11.temperature;
  json["humidity"] = dht11.humidity;

  String payload;
  serializeJson(json, payload);

  sendPostRequest(temperatureAndHumiditySensorEndpoint, payload);

  DynamicJsonDocument json2(128);
  json2["value"] = bmp.readPressure() / 100.0;

  String payload2;
  serializeJson(json2, payload2);

  sendPostRequest(barometerEndpoint, payload2);

  DynamicJsonDocument jsonCO2(128);
  jsonCO2["value"] = mq135.getPPM();

  String payloadCO2;
  serializeJson(jsonCO2, payloadCO2);

  sendPostRequest(co2Endpoint, payloadCO2);

  delay(10000);
}
