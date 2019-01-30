package game.beans;

import java.awt.Point;
import java.io.Serializable;

public class Command implements Serializable {
    
	private transient Piece vonPiece;
	private transient Piece nachPiece;
	private Point von;
	private Point nach;
	
	public Command(Piece vonPiece, Piece nachPiece, Point von, Point nach) {
		this.setVonPiece(vonPiece);
		this.setNachPiece(nachPiece);
		this.setVon(von);
		this.setNach(nach);
	}
	
	public Command(Point von, Point nach) {
		this.setVonPiece(null);
		this.setNachPiece(null);
		this.setVon(von);
		this.setNach(nach);
	}
	
	public Command(Command command) {
		this.setVonPiece(command.getVonPiece());
		this.setNachPiece(command.getNachPiece());
		this.setVon(command.getVon());
		this.setNach(command.getNach());
	}
	
	public Piece getVonPiece() {
		return vonPiece;
	}
	
	public void setVonPiece(Piece vonPiece) {
		this.vonPiece = vonPiece;
	}
	
	public Piece getNachPiece() {
		return nachPiece;
	}
	
	public void setNachPiece(Piece nachPiece) {
		this.nachPiece = nachPiece;
	}
	
	public Point getVon() {
		return von;
	}
	
	public void setVon(Point von) {
		this.von = von;
	}
	
	public Point getNach() {
		return nach;
	}
	
	public void setNach(Point nach) {
		this.nach = nach;
	}
}
