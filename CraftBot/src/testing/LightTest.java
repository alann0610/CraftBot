package testing;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LightTest {
  public static void main(String[] args) throws Exception {
    LightSensor light = new LightSensor(SensorPort.S3);
    light.setFloodlight(false);
//    Button.waitForAnyPress();
//    light.calibrateHigh();
//    Button.waitForAnyPress();
//    light.calibrateHigh();
    while (true) {
      LCD.drawInt(light.getLightValue(), 4, 0, 0);
      LCD.drawInt(light.getNormalizedLightValue(), 4, 0, 1);
      LCD.drawInt(SensorPort.S3.readRawValue(), 4, 0, 2);
      LCD.drawInt(SensorPort.S3.readValue(), 4, 0, 3);
    }
  }
}