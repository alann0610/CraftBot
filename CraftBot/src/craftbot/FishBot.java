package craftbot;

import lejos.nxt.Battery;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.SensorSelector;
import lejos.robotics.Accelerometer;
import lejos.util.Stopwatch;

public class FishBot implements BotType {

	@Override
	public void run() throws Exception {
		CraftBot.STATE = CraftBot.STATE_GETRUNCOUNT;
		int runLength = 1000 * 60 * 40;//40 minutes
//		int runLength = 1000 * 30; //30 seconds demo.
		int hookSeconds = Util.getNumberSelection() * 1000;
		int iterations = Util.getNumberSelection();
		Thread.sleep(1000);
		CraftBot.STATE = CraftBot.STATE_RUNNING;
		runFish(runLength, hookSeconds, iterations);
	}

	private void runFish(int runLength, int hookSeconds, int iterations)
			throws Exception {
		Accelerometer accelerometer = SensorSelector
				.createAccelerometer(SensorPort.S1);
		Stopwatch runTimer = new Stopwatch();
		runTimer.reset();
		boolean goleft = false;
		for (int i = 0; i < iterations; i++) {
			runTimer.reset();
			while (runTimer.elapsed() < runLength) {
				LCD.clear();
				LCD.drawString("Battery: " + Battery.getVoltage(), 0, 0);
				LCD.drawString("TIME: " + (runLength - runTimer.elapsed())
						/ (1000 * 60), 0, 1);
				LCD.drawString("ITER: " + (iterations - i), 0, 2);
				Stopwatch castTimer = new Stopwatch();
				castTimer.reset();
				Motors.getInstance().pressB();
				boolean fishFound = false;
				int prevXVal = 0;
				int prevYVal = 0;
				int prevZVal = 0;
				int newXVal = accelerometer.getXAccel();
				int newYVal = accelerometer.getYAccel();
				int newZVal = accelerometer.getZAccel();
				while (castTimer.elapsed() < 40000) {
					prevXVal = newXVal;
					prevYVal = newYVal;
					prevZVal = newZVal;
					newXVal = accelerometer.getXAccel();
					newYVal = accelerometer.getYAccel();
					newZVal = accelerometer.getZAccel();
					int xDelta = Math.abs(newXVal - prevXVal);
					int yDelta = Math.abs(newYVal - prevYVal);
					int zDelta = Math.abs(newZVal - prevZVal);
					LCD.drawString("xdel: " + xDelta, 0, 3);
					LCD.drawString("ydel: " + yDelta, 0, 4);
					LCD.drawString("zdel: " + zDelta, 0, 5);
					if (xDelta >= 6 || yDelta >= 6 || zDelta >= 6) {
						fishFound = true;
						break;
					}
					Thread.sleep(250);
				}
				if (fishFound) {
					Motors.getInstance().pressB();
					Thread.sleep(hookSeconds);
				}
			}
			if (goleft) {
				Motors.getInstance().holdC();
				Thread.sleep(5 * 1000);
				Motors.getInstance().releaseC();
			} else {
				Motors.getInstance().holdA();
				Thread.sleep(5 * 1000);
				Motors.getInstance().releaseA();
			}
			goleft = !goleft;
			Thread.sleep(1 * 4000);
		}
	}
}
