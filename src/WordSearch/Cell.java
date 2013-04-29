package WordSearch;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author Naomi Plasterer, Brandon Bosso, Jason Steinberg, Austin Diviness
 */

public class Cell {
	private int row;
	private int column;
	private static Color color;
	private char letter;
	private static int width;
	private boolean highlighted;
	
	public Cell() {
		// TODO create constructor. should there be an additional, parameterized constructor?
	}
	
/**
* Constructor takes a row and column and makes the cell class row and column.
 */
	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
		letter = ' ';
	}

	/**
	* Constructor takes a row, column, and letter and makes the cell class row, column, and letter.
	*/
	public Cell(int row, int column, char letter) {
		this.row = row;
		this.column = column;
		this.letter = letter;
	}
	
	/**
	* This draws the squares on the board and if selected
	* updates the color to a highlighted color.
	*/	
	public void draw(Graphics g){		
		//if highlighted, fill rectangle with yellow
		if(highlighted) {
			g.setColor(Color.yellow);
			g.fillRect(column*width, row*width, width, width);
			g.setColor(Color.black);
			g.drawRect(column*width, row*width, width, width);
		}
		
		else {
			g.setColor(Color.white);
			g.fillRect(column*width, row*width, width, width);
			g.setColor(Color.black);
			g.drawRect(column*width, row*width, width, width);
		}
		
		//Place character in cell
		Font font = new Font("Verdana", Font.PLAIN, width/2);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(Character.toString(letter).toUpperCase(), (int) (column*width+(.33 * width)), (int) (width*row+(.75 * width)));
	}
	
	public static void setWidth(int width) {
		Cell.width = width;
	}
	
	public static int getWidth() {
		return Cell.width;
	}


	public char getLetter() {
		return letter;
	}
	
	public void setLetter(char c) {
		letter = c;
	}
	
	public boolean isHighlighted() {
		return highlighted;
	}
	
	public void highlight() {
		highlighted = true;
	}
	
	
	@Override
	public String toString() {
		return "Cell [row=" + row + ", column=" + column + ", highlighted="
				+ highlighted + "]";
	}
}
