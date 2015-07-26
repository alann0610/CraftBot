package craftbot;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.nxt.ButtonListener;

public class CraftBot {

	final static int ROTATE_DEG = 15;
	final static int REMOTEROTATE_DEG = 15;
	final static int ROTATE_SPEED = 130;
	final static int STATE_GETPROGRAM = 0;
	final static int STATE_GETRUNTIME = 1;
	final static int STATE_GETRUNCOUNT = 2;
	final static int STATE_RUNNING = 3;
	public static int STATE = STATE_GETPROGRAM;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Motor.A.setSpeed(ROTATE_SPEED);
		Motor.B.setSpeed(ROTATE_SPEED);
		Motor.C.setSpeed(ROTATE_SPEED);
		STATE = STATE_GETPROGRAM;

		String programType = Util.getProgramType();

		if (programType.equals("EXIT")) {
			NXT.shutDown();
		}

		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
			}

			public void buttonReleased(Button b) {
				if (STATE == STATE_RUNNING) {
					Motors.getInstance().shutdown();
					System.exit(0);
				}
			}
		});

		Thread.sleep(250);
		BotType bot = null;
		if (programType.equals("CRAFT SynthTime")) {
			bot = new CraftSynthTimeBot();
		} else if (programType.equals("CRAFT 2star")) {
			bot = new Craft2StarBot();
		} else if (programType.equals("CRAFT 3star")) {
			bot = new Craft3StarBotV2();
		} else if (programType.equals("TURNIN")) {
			bot = new Craft2StarBotTurnin();
		} else if (programType.equals("FARM")) {
			bot = new FarmBot();
		} else if (programType.equals("FISH")) {
			bot = new FishBot();
		} else {
			NXT.shutDown();
		}
		bot.run();
		NXT.shutDown();
	}
}