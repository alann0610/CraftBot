package craftbot;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteNXT;

public class MotorsTrack {
	final static int MOVE_DEG = -137;
	final static int MOVE_SPEED = 550;
	final static int PRESS_DEG = 15;
	final static int PRESS_SPEED = 130;

	final static int POSITION_HOME = 0;
	final static int POSITION_PRESSED = 1;
	final static int MOVE_MIN = 0;
	final static int MOVE_MAX = 10;

	static MotorsTrack instance = null;

	private int STATE_MOVED = 0;
	private int STATE_PRESSED = 0;

	// Remote NXT
	static RemoteNXT REMOTENXT = null;

	public static MotorsTrack getInstance() {
		if (instance == null) {
			instance = new MotorsTrack();
		}
		return instance;
	}

	private MotorsTrack() {
		Motor.A.setSpeed(MOVE_SPEED);
		Motor.B.setSpeed(PRESS_SPEED);
	}

	public void pressButton() {
		if (STATE_PRESSED != POSITION_HOME) {
			return;
		}
		Motor.B.rotate(PRESS_DEG, false);
		Motor.B.rotate(-1 * PRESS_DEG, false);
	}

	public void holdButton() {
		if (STATE_PRESSED != POSITION_HOME) {
			return;
		}
		Motor.B.rotate(PRESS_DEG, false);
		STATE_PRESSED = POSITION_PRESSED;
	}

	public void releaseButton() {
		if (STATE_PRESSED != POSITION_PRESSED) {
			return;
		}
		Motor.B.rotate(-1 * PRESS_DEG, false);
		STATE_PRESSED = POSITION_HOME;
	}

	public void moveArm(int units) {
		if ((STATE_MOVED + units) > MOVE_MAX
				|| (STATE_MOVED + units) < MOVE_MIN)
			return;

		STATE_MOVED += units;
		Motor.A.rotate(MOVE_DEG * units, false);
	}

	public int getPosition() {
		return STATE_MOVED;
	}
}
