package WordSearch;
import java.awt.Color;
import java.awt.Graphics;


public class Cell {
	private int row;
	private int column;
	private static Color color;
	private char letter;
	private static int width;
	private static int height;
	private boolean highlighted;
	
	public Cell() {
		// TODO create constructor. should there be an additional, parameterized constructor?
	}
	
	public void draw(Graphics g){
		// TODO implement function
	}
	
	public char getLetter() {
		return letter;
	}
	
	public boolean isHighlighted() {
		return highlighted;
	}
}
