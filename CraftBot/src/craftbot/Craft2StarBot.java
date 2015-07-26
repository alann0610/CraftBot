package craftbot;

import lejos.nxt.Battery;
import lejos.nxt.LCD;

public class Craft2StarBot implements BotType {

	@Override
	public void run() throws InterruptedException {
		CraftBot.STATE = CraftBot.STATE_GETRUNCOUNT;
		int runCount = Util.getNumberSelection();
		Thread.sleep(1000);
		CraftBot.STATE = CraftBot.STATE_RUNNING;
		run2starProgram(runCount);
	}

	private void run2starProgram(int runLength) throws InterruptedException {
		for (int i = 0; i < runLength; i++) {
			LCD.clear();
			LCD.drawString("Battery: " + Battery.getVoltage(), 0, 0);
			LCD.drawString("REMAIN: " + (runLength - i - 1), 0, 1);
			Motors.getInstance().pressC();
			Thread.sleep(1000);
			Motors.getInstance().pressC();
			Thread.sleep(2000);
			Motors.getInstance().pressB();
			Thread.sleep(33000);
			Motors.getInstance().pressA();
			Thread.sleep(23000);
		}
	}
}
