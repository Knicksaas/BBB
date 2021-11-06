// C++ code
//

const int buttonPin = 13;
const int lightPin = 12;

// 0 = Waiting for start 
// 1 = Waiting for light on
// 2 = Waiting for user press
int status = 0;

long startTime;
long waitingTime;

void setup()
{
  pinMode(A0, INPUT);
  pinMode(buttonPin, INPUT);
  pinMode(12, OUTPUT);
  Serial.begin(9600);

}

void loop()
{
  
  bool buttonPressed = readButton();
  
  switch (status){
    case 0: 
        if(buttonPressed){
          waitUntilButtonReleased();
          status = 1;
          waitingTime = 1000 * random(1, (analogRead(A0) / 100) + 1);
          Serial.println("Start to wait...");
          startTime = millis();
        }
        break;
    case 1:
      if(buttonPressed){
        Serial.println("Button pressed wrong, ending program");
        status = 0;
        waitUntilButtonReleased();
      } else {
        if(millis() - startTime >= waitingTime){
          status = 2;
          switchOnLight();
        }
      }
      break;
    case 2:
      if(buttonPressed){
        long reactionTime = millis() - startTime - waitingTime;
        status = 0;
        Serial.print("The reaction time was: ");
        Serial.print(reactionTime);
        Serial.println(" ms");
        switchOffLight();
        waitUntilButtonReleased();
      }
      break;
  }
  delay(10);
}

bool readButton() {
  return digitalRead(buttonPin) == HIGH;
}

void waitUntilButtonReleased(){
  while(readButton()){
    delay(10);
  }
}

void switchOnLight(){
  digitalWrite(lightPin, HIGH);
}

void switchOffLight(){
  digitalWrite(lightPin, LOW);
}
