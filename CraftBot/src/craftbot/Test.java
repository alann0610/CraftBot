package craftbot;

import lejos.nxt.Battery;
import lejos.nxt.LCD;

public class Test implements BotType {

	@Override
	public void run() throws InterruptedException {
		CraftBot.STATE = CraftBot.STATE_GETRUNCOUNT;
		Thread.sleep(1000);
		Thread.sleep(1000);
		CraftBot.STATE = CraftBot.STATE_RUNNING;
		test();
	}

	private void test()
			throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			LCD.clear();
			LCD.drawString("Battery: " + Battery.getVoltage(), 0, 0);
			LCD.drawString("REMAIN: " + (10 - i - 1), 0, 1);
			LCD.drawString("POS: " + MotorsTrack.getInstance().getPosition(), 0, 2);
			MotorsTrack.getInstance().pressButton();
			if (i % 10 >= 5) {
				MotorsTrack.getInstance().moveArm(-1);
			} else {
				MotorsTrack.getInstance().moveArm(1);
			}
			Thread.sleep(100);
		}
	}
}
