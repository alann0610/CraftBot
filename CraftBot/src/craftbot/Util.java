package craftbot;

import javax.microedition.lcdui.Graphics;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class Util {

	final static String[] programList = { "CRAFT SynthTime", "CRAFT 2star",
			"CRAFT 3star", "FARM", "FISH", "TURNIN" };
	final static String[] runTimeList = { "1800000", "3600000", "7200000",
			"14400000" };
	final static String[] synthTimeList = { "14000", "33000", "45000", "60000" };
	static String[] digitList = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"0" };
	static boolean registeredListeners = false;
	static int numSelect = 0;
	static String[] out = new String[15];
	static int pos1 = 1;
	static int pos2 = 2;
	static int pos3 = digitList.length - 1;
	static int pos4 = digitList.length - 2;
	static boolean numSelectisDone = false;
	static int numLeft = 3;
	static int outTrack = 0;

	static Graphics g = new Graphics();

	public static String getProgramType() {
		LCD.clear();
		TextMenu programsMenu = new TextMenu(programList, 1, "Programs");
		return programList[programsMenu.select()];
	}

	public static int getRunLength() {
		LCD.clear();
		TextMenu runLengthMenu = new TextMenu(runTimeList, 1, "Run Length");
		return Integer.parseInt(runTimeList[runLengthMenu.select()]);
	}

	public static int getSynthLength() {
		LCD.clear();
		TextMenu synthLengthMenu = new TextMenu(synthTimeList, 1,
				"Synth Length");
		return Integer.parseInt(synthTimeList[synthLengthMenu.select()]);
	}

	public static int getNumberSelection() {
		reset();
		String retVal = "0";
		numSelect = 0;
		pos1 = digitList.length - 2;
		pos2 = digitList.length - 1;
		pos3 = 1;
		pos4 = 2;
		numSelectisDone = false;
		numLeft = 3;
		displayNumbersMenu(pos1, pos2, pos3, pos4, 0, numLeft, "");
		if (!registeredListeners) {
			registerListeners();
			registeredListeners = true;
		}
		Button.ESCAPE.waitForPressAndRelease();

		for (int i = 0; i <= out.length - 1; i++) {
			if (out[i] == null)
				break;
			else
				retVal += out[i];
		}
		return Integer.parseInt(retVal);
	}
	public static void reset(){
		numSelect = 0;
		out = new String[15];
		pos1 = 1;
		pos2 = 2;
		pos3 = digitList.length - 1;
		pos4 = digitList.length - 2;
		numSelectisDone = false;
		numLeft = 3;
		outTrack = 0;
	}	
	private static void registerListeners() {
		Button.RIGHT.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
			}

			public void buttonReleased(Button b) {
				if (CraftBot.STATE == CraftBot.STATE_GETRUNCOUNT) {
					numSelect++;
					if (numSelect >= digitList.length) {
						numSelect = 0;
					}

					if (numSelect == 0) {
						pos1 = digitList.length - 2;
						pos2 = digitList.length - 1;
						pos3 = 1;
						pos4 = 2;
					} else if (numSelect == 1) {
						pos1 = digitList.length - 1;
						pos2 = numSelect - 1;
						pos3 = numSelect + 1;
						pos4 = numSelect + 2;
					} else if (numSelect == digitList.length - 2) {
						pos1 = numSelect - 2;
						pos2 = numSelect - 1;
						pos3 = numSelect + 1;
						pos4 = 0;
					} else if (numSelect == digitList.length - 1) {
						pos1 = numSelect - 2;
						pos2 = numSelect - 1;
						pos3 = 0;
						pos4 = 1;
					} else {
						pos1 = numSelect - 2;
						pos2 = numSelect - 1;
						pos3 = numSelect + 1;
						pos4 = numSelect + 2;
					}

					String currVal = "";
					for (int i = 0; i <= out.length - 1; i++) {
						if (out[i] == null)
							break;
						else
							currVal += out[i];
					}

					displayNumbersMenu(pos1, pos2, pos3, pos4, numSelect,
							numLeft, currVal);
				}
			}
		});
		Button.LEFT.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
			}
			public void buttonReleased(Button b) {
				if (CraftBot.STATE == CraftBot.STATE_GETRUNCOUNT) {
					numSelect--;
					if (numSelect < 0) {
						numSelect = digitList.length - 1;
					}

					if (numSelect == 0) {
						pos1 = digitList.length - 2;
						pos2 = digitList.length - 1;
						pos3 = 1;
						pos4 = 2;
					} else if (numSelect == 1) {
						pos1 = digitList.length - 1;
						pos2 = numSelect - 1;
						pos3 = numSelect + 1;
						pos4 = numSelect + 2;
					} else if (numSelect == digitList.length - 2) {
						pos1 = numSelect - 2;
						pos2 = numSelect - 1;
						pos3 = numSelect + 1;
						pos4 = 0;
					} else if (numSelect == digitList.length - 1) {
						pos1 = numSelect - 2;
						pos2 = numSelect - 1;
						pos3 = 0;
						pos4 = 1;
					} else {
						pos1 = numSelect - 2;
						pos2 = numSelect - 1;
						pos3 = numSelect + 1;
						pos4 = numSelect + 2;
					}

					String currVal = "";
					for (int i = 0; i <= out.length - 1; i++) {
						if (out[i] == null)
							break;
						else
							currVal += out[i];
					}
					displayNumbersMenu(pos1, pos2, pos3, pos4, numSelect,
							numLeft, currVal);
				}
			}
		});
		Button.ENTER.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
			}

			public void buttonReleased(Button b) {
				if (CraftBot.STATE == CraftBot.STATE_GETRUNCOUNT) {
					String ret = "";
					if (numLeft <= 0) {
						numSelectisDone = true;
						return;
					}
					out[outTrack] = digitList[numSelect];
					numLeft--;
					outTrack++;
					for (int i = 0; i <= out.length - 1; i++) {
						if (out[i] == null)
							break;
						else
							ret += out[i];
					}
					displayNumbersMenu(pos1, pos2, pos3, pos4, numSelect,
							numLeft, ret);
				}
			}
		});		
	}

	private static void displayNumbersMenu(int pos1, int pos2, int pos3,
			int pos4, int arrTrack, int charLeft, String currVal) {
		LCD.clear();
		g.drawLine(0, 9, 99, 9);
		LCD.drawString("Enter Number", 3, 0);
		g.drawLine(45, 32, 55, 32);
		LCD.drawString("Esc when done.", 0, 2);
		LCD.drawString("^", 8, 4);
		LCD.drawString(digitList[pos1], 2, 3);
		LCD.drawString(digitList[pos2], 5, 3);
		LCD.drawString(digitList[arrTrack], 8, 3);
		LCD.drawString(digitList[pos3], 11, 3);
		LCD.drawString(digitList[pos4], 14, 3);
		LCD.drawString(charLeft + " char. left", 0, 7);
		LCD.drawString(currVal, 0, 5);
	}
}
