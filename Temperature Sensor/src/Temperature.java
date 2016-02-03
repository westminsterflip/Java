//Only works on Arduino
const float base = 18;
void setup(){
  Serial.begin(9600);
  for(int pinN = 2; pinN<5; pinN++){
    pinMode(pinN,OUTPUT);
    digitalWrite(pinN,LOW);
  }
}
void loop(){
  int sv = analogRead(A0);
  Serial.print(sv);
  float v = (sv / 1024.0) * 5.0;
  Serial.print("V ");
  Serial.print(v);
  Serial.print(" Deg: ");
  float temp = (v-.5)*100;
  Serial.println(temp);
  if(temp < base){
    digitalWrite(2,LOW);
    digitalWrite(3,LOW);
    digitalWrite(4,LOW);
  }else if(temp >= base+2 && temp<base+4){
    digitalWrite(2,HIGH);
    digitalWrite(3,LOW);
    digitalWrite(4,LOW);
  }else if(temp >=base+4 && temp<base+6){
    digitalWrite(2,HIGH);
    digitalWrite(3,HIGH);
    digitalWrite(4,LOW);
  }else if(temp >=base+6){
    digitalWrite(2,HIGH);
    digitalWrite(3,HIGH);
    digitalWrite(4,HIGH);
  }
  delay(1);
  //digitalWrite(4,HIGH);
}
