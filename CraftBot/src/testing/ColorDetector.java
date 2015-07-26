package testing;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;

/**
 * For testing the HiTechnic color sensor (see lejos.nxt.addon.ColorHTSensor).
 * 
 * @author BB
 */
public class ColorDetector {

	public static final int DEFAULT_I2C_ADDRESS = 0x41;
	final static int INTERVAL = 200; // milliseconds

    static ColorHTSensor cmps = new ColorHTSensor(SensorPort.S1);
	public static void main(String [] args) throws Exception {
      String color = "Color";
      String r = "R";
      String g = "G";
      String b = "B";

      String[] colorNames = {"Red", "Green", "Blue", "Yellow", "Magenta", "Orange",
                         "White", "Black", "Pink", "Gray", "Light gray", "Dark Gray", "Cyan"
      };
      
      byte buf = 1;
      cmps.sendData(DEFAULT_I2C_ADDRESS, buf);
      while(!Button.ESCAPE.isDown()) {
         //LCD.clear();
         LCD.drawString(cmps.getVendorID(), 0, 0);
         LCD.drawString(cmps.getProductID(), 0, 1);
         LCD.drawString(cmps.getVersion(), 9, 1);
         LCD.drawString(color, 0, 3);
         LCD.drawInt(cmps.getColorID(),7,3);
         LCD.drawString(colorNames[cmps.getColorID()], 0, 4);
         LCD.drawString(r, 0, 5);
         LCD.drawInt(cmps.getRGBRaw(Color.RED),1,5);
         LCD.drawString(g, 5, 5);
         LCD.drawInt(cmps.getRGBRaw(Color.GREEN),6,5);
         LCD.drawString(b, 10, 5);
         LCD.drawInt(cmps.getRGBRaw(Color.BLUE),11,5);
         LCD.drawString("Avg:" + getAverage(),0,6);
         LCD.refresh();
         Thread.sleep(INTERVAL);
      }
   }
	static private double getAverage() throws InterruptedException {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			values.add(cmps.getRGBRaw(Color.RED));
			Thread.sleep(50);
		}

		return calculateAverage(values);
	}
	
	static private double calculateAverage(ArrayList<Integer> values) {
		Integer sum = 0;
		if (!values.isEmpty()) {
			for (Integer value : values) {
				sum += value;
			}
			return sum.doubleValue() / values.size();
		}
		return sum;
	}
}