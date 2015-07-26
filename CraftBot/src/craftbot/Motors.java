package craftbot;

import java.io.IOException;

import lejos.nxt.Motor;
import lejos.nxt.comm.RS485;
import lejos.nxt.remote.RemoteNXT;

public class Motors {
	final static int ROTATE_DEG = 15;
	final static int REMOTEROTATE_DEG = 15;
	final static int ROTATE_SPEED = 130;

	final static int POSITION_HOME = 0;
	final static int POSITION_PRESSED = 1;

	static Motors instance = null;

	private int STATE_A = 0;
	private int STATE_B = 0;
	private int STATE_C = 0;

	// Remote NXT
	static RemoteNXT REMOTENXT = null;


	public static Motors getInstance() {
		if (instance == null) {
			instance = new Motors();
		}
		return instance;
	}
	
	private Motors() {
		Motor.A.setSpeed(ROTATE_SPEED);
		Motor.B.setSpeed(ROTATE_SPEED);
		Motor.C.setSpeed(ROTATE_SPEED);
	}
	
	public void initRemoteNXT() throws IOException{
		if (REMOTENXT == null) {
	    	REMOTENXT = new RemoteNXT("NXT", RS485.getConnector());
		}
    	REMOTENXT.A.setSpeed(ROTATE_SPEED);
    	REMOTENXT.B.setSpeed(ROTATE_SPEED);
    	REMOTENXT.C.setSpeed(ROTATE_SPEED);
	}

	public void shutdown() {
		if (REMOTENXT != null) {
			REMOTENXT.stopProgram();
		}
	}

	public RemoteNXT getRemoteNXT() throws IOException{
		if (REMOTENXT == null) {
	    	REMOTENXT = new RemoteNXT("NXT", RS485.getConnector());
		}
		return REMOTENXT;
	}

	public float getRemoteBattery(){
		return REMOTENXT.Battery.getVoltage();
	}
	
	public void pressA() {
		if (STATE_A != POSITION_HOME) {
			return;
		}
		Motor.A.rotate(ROTATE_DEG, false);
		Motor.A.rotate(-1 * ROTATE_DEG, false);
	}

	public void holdA() {
		if (STATE_A != POSITION_HOME) {
			return;
		}
		Motor.A.rotate(ROTATE_DEG, false);
		STATE_A = POSITION_PRESSED;
	}

	public void releaseA() {
		if (STATE_A != POSITION_PRESSED) {
			return;
		}
		Motor.A.rotate(-1 * ROTATE_DEG, false);
		STATE_A = POSITION_HOME;
	}

	public void pressB() {
		if (STATE_B != POSITION_HOME) {
			return;
		}
		Motor.B.rotate(ROTATE_DEG, false);
		Motor.B.rotate(-1 * ROTATE_DEG, false);
	}

	public void holdB() {
		if (STATE_B != POSITION_HOME) {
			return;
		}
		Motor.B.rotate(ROTATE_DEG, false);
		STATE_B = POSITION_PRESSED;
	}

	public void releaseB() {
		if (STATE_B != POSITION_PRESSED) {
			return;
		}
		Motor.B.rotate(-1 * ROTATE_DEG, false);
		STATE_B = POSITION_HOME;
	}

	public void pressC() {
		if (STATE_C != POSITION_HOME) {
			return;
		}
		Motor.C.rotate(ROTATE_DEG, false);
		Motor.C.rotate(-1 * ROTATE_DEG, false);
	}

	public void holdC() {
		if (STATE_C != POSITION_HOME) {
			return;
		}
		Motor.C.rotate(ROTATE_DEG, false);
		STATE_C = POSITION_PRESSED;
	}

	public void releaseC() {
		if (STATE_C != POSITION_PRESSED) {
			return;
		}
		Motor.C.rotate(-1 * ROTATE_DEG, false);
		STATE_C = POSITION_HOME;
	}

	public void pressRemoteA() {
		REMOTENXT.A.rotate(REMOTEROTATE_DEG, false);
		REMOTENXT.A.rotate(-1 * REMOTEROTATE_DEG, false);
	}

	public void pressRemoteB() {
		REMOTENXT.B.rotate(REMOTEROTATE_DEG, false);
		REMOTENXT.B.rotate(-1 * REMOTEROTATE_DEG, false);
	}

	public void pressRemoteC() {
		REMOTENXT.C.rotate(REMOTEROTATE_DEG, false);
		REMOTENXT.C.rotate(-1 * REMOTEROTATE_DEG, false);
	}

	public void press1() {
		pressC();
	}

	public void press2() {
		holdA();
		pressC();
		releaseA();
	}

	public void press3() {
		holdB();
		pressC();
		releaseB();
	}

	public void press4() {
		pressRemoteA();
	}

	public void press5() {
		pressRemoteB();
	}

	public void press6() {
		pressRemoteC();
	}

	public void press7() {
		holdA();
		pressRemoteA();
		releaseA();
	}

	public void press8() {
		holdA();
		pressRemoteB();
		releaseA();
	}

	public void press9() {
		holdA();
		pressRemoteC();
		releaseA();
	}

	public void press10() {
		holdB();
		pressRemoteA();
		releaseB();
	}

	public void press11() {
		holdB();
		pressRemoteB();
		releaseB();
	}

	public void press12() {
		holdB();
		pressRemoteC();
		releaseB();
	}
}
