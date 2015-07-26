package craftbot;

import java.io.IOException;
import java.util.ArrayList;

import lejos.nxt.Battery;
import lejos.nxt.LCD;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;
import lejos.util.Stopwatch;

public class Craft3StarBot implements BotType {
	public static final int HTCSMODE_I2C_ADDRESS = 0x41;
	public static final int TOUCH_TIME = 3000;
	public static final int BUFF_TIME = 2500;
	ColorHTSensor cmps = null;
	int goodcounter = 0;
	boolean goodseen = false;

	public Craft3StarBot() throws IOException {
		byte buf = 1;
		cmps = new ColorHTSensor(Motors.getInstance().getRemoteNXT().S1);
		cmps.sendData(HTCSMODE_I2C_ADDRESS, buf);
	}

	@Override
	public void run() throws Exception {
		CraftBot.STATE = CraftBot.STATE_GETRUNCOUNT;
		int runCount = Util.getNumberSelection();
		Thread.sleep(1000);
		CraftBot.STATE = CraftBot.STATE_RUNNING;
		Motors.getInstance().initRemoteNXT();
		run3starProgram(runCount);

	}

	private boolean isGoodCondition() throws InterruptedException {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			values.add(cmps.getRGBRaw(Color.RED));
			Thread.sleep(100);
		}

		return calculateAverage(values) < 75 && calculateAverage(values) > 40;
	}

	private double calculateAverage(ArrayList<Integer> values) {
		Integer sum = 0;
		if (!values.isEmpty()) {
			for (Integer value : values) {
				sum += value;
			}
			return sum.doubleValue() / values.size();
		}
		return sum;
	}

	private void handleGood() throws InterruptedException {
		if (isGoodCondition()) {
			Motors.getInstance().pressB();
			Thread.sleep(BUFF_TIME);
			goodcounter++;
		}
	}

	private void handleGood2() throws InterruptedException {
		if (isGoodCondition() && !goodseen) {
			Motors.getInstance().pressB();
			Thread.sleep(BUFF_TIME);
			goodcounter++;
			goodseen = true;
		}
	}

	private void run3starProgram(int runLength) throws InterruptedException,
			IOException {
		Stopwatch foodTimer = new Stopwatch();
		foodTimer.reset();
		// press10();//eat food
		for (int i = 0; i < runLength; i++) {
			goodcounter = 0;
			if (foodTimer.elapsed() >= 1620000) {// 27 minutes
				foodTimer.reset();
				// press10();//eat food
			}
			LCD.clear();
			LCD.drawString("Battery: " + Battery.getVoltage(), 0, 0);
			LCD.drawString("BatteryR: "
					+ Motors.getInstance().getRemoteBattery(), 0, 1);
			LCD.drawString("REMAIN: " + (runLength - i - 1), 0, 2);

			// start craft from craft menu
			Motors.getInstance().pressC();
			Thread.sleep(1000);
			Motors.getInstance().pressC();
			Thread.sleep(2000);

			// stage 1
			Motors.getInstance().pressA();// comfort zone
			Thread.sleep(BUFF_TIME);
			handleGood();
			Motors.getInstance().pressRemoteC();// inner quiet
			Thread.sleep(BUFF_TIME);
			handleGood();
			Motors.getInstance().pressRemoteB();// steady hand
			Thread.sleep(BUFF_TIME);
			handleGood();
			Motors.getInstance().pressRemoteA();// go to next stage
			Thread.sleep(250);

			// stage 2
			Motors.getInstance().pressA();// piece by piece
			Thread.sleep(TOUCH_TIME);
			handleGood();
			Motors.getInstance().pressA();// piece by piece
			Thread.sleep(TOUCH_TIME);
			handleGood();
			Motors.getInstance().pressRemoteC();// steady hand 2
			Thread.sleep(BUFF_TIME);
			Motors.getInstance().pressRemoteA();// go to next stage
			Thread.sleep(250);

			// stage 3
			goodseen = false;
			for (int j = 0; j < 4; j++) {
				handleGood2();
				if (goodcounter > 0) {
					goodcounter--;
					Motors.getInstance().pressRemoteC();// basic touch
				} else {
					Motors.getInstance().pressA();// hasty touch
				}
				Thread.sleep(TOUCH_TIME);
			}
			Motors.getInstance().pressRemoteB();// go to next stage
			Thread.sleep(250);

			// stage 4
			handleGood();
			Motors.getInstance().pressA();// comfort zone
			Thread.sleep(BUFF_TIME);
			handleGood();
			Motors.getInstance().pressRemoteC();// master's mend 2
			Thread.sleep(BUFF_TIME);
			handleGood();
			Motors.getInstance().pressRemoteB();// steady hand 2
			Thread.sleep(BUFF_TIME);
			Motors.getInstance().pressRemoteA();// go to next stage
			Thread.sleep(250);

			// stage 5
			goodseen = false;
			for (int j = 0; j < 4; j++) {
				handleGood2();
				if (goodcounter > 0) {
					goodcounter--;
					Motors.getInstance().pressRemoteC();// basic touch
				} else {
					Motors.getInstance().pressA();// hasty touch
				}
				Thread.sleep(TOUCH_TIME);
			}
			Motors.getInstance().pressRemoteA();// finish
			Thread.sleep(25000);

		}
		Motors.getInstance().shutdown();

	}
}
