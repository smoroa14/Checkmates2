package game.bl;


import game.beans.Piece;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLabel;


public class Square extends JButton {

	private static final long serialVersionUID = 1L;
	private Piece besetzt_von;
	private Point position;
	private Board board;
	private boolean highlighted;
	private Color origColor;
	
	static Dimension size = new Dimension(48, 48);
	
	public Square(Board board, Point position) {
		this.setBoard(board);
		this.setBesetzt_von(null);
		this.setPosition(position);
		this.setPreferredSize(size);
		this.setHighlighted(false);
	}

	public Piece getBesetzt_von() {
		return besetzt_von;
	}

	public void setBesetzt_von(Piece piece) {
		this.besetzt_von = piece;
		if(besetzt_von != null) {
			setIcon(besetzt_von.getIcon());
		}
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public Color getOrigColor() {
		return origColor;
	}

	public void setOrigColor(Color origColor) {
		this.origColor = origColor;
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        Color c = new Color(origColor.getRed(), origColor.getGreen(), origColor.getBlue(), 50);
        g.setColor(c);
        
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    

        
        
}	