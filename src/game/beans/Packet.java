package game.beans;

import game.beans.Command;
import java.io.Serializable;


public class Packet implements Serializable {

	private Command com;
	private boolean restart;
	private boolean restartConfirm;
	private boolean undo;
	private boolean exit;
	
	public Packet() {
		setCom(null);
		setRestart(false);
		setRestartConfirm(false);
		setUndo(false);
		setExit(false);
	}
	
	public Packet(Command command, boolean restart, boolean restartConfirm, boolean undo, boolean exit) {
		this.setCom(command);
		this.setRestart(restart);
		this.setRestartConfirm(restartConfirm);
		this.setUndo(undo);
		this.setExit(exit);
	}

	public Command getCommand() {
		return com;
	}

	public void setCom(Command com) {
		this.com = com;
	}

	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public boolean isUndo() {
		return undo;
	}

	public void setUndo(boolean undo) {
		this.undo = undo;
	}

	public boolean isRestartConfirm() {
		return restartConfirm;
	}

	public void setRestartConfirm(boolean restartConfirm) {
		this.restartConfirm = restartConfirm;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
