package craftbot;

import lejos.nxt.Battery;
import lejos.nxt.LCD;
import lejos.util.Stopwatch;

public class FarmBot implements BotType {

	@Override
	public void run() throws InterruptedException {
		CraftBot.STATE = CraftBot.STATE_GETRUNTIME;
		int runLength = Util.getRunLength();
		Thread.sleep(1000);
		CraftBot.STATE = CraftBot.STATE_RUNNING;
		runFarm(runLength);
	}

	private void runFarm(int runLength) throws InterruptedException {
		Stopwatch runTimer = new Stopwatch();
		runTimer.reset();
		while (runTimer.elapsed() < runLength) {
			LCD.clear();
			LCD.drawString("Battery: " + Battery.getVoltage(), 0, 0);
			LCD.drawString("TIME: " + (runLength - runTimer.elapsed())
					/ (1000 * 60), 0, 1);
			Motors.getInstance().pressB();
			Thread.sleep(10000);
		}
	}
}
