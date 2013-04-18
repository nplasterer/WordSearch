package WordSearch;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * @author Naomi Plasterer, Brandon Bosso, Jason Steinberg, Austin Diviness
 */

public class Board extends JPanel {
	private int rows;
	private int columns;
	private Cell[][] cells;
	
	public Board() {
		// TODO create constructor
	}
	
	public void generatePuzzle(ArrayList<String> words){
		// TODO implement function
	}
	
	public void insertWord(String word) {
		// TODO implement function
	}
	
	@Override
	public void paintComponent(Graphics g){
		// TODO implement function
	}

	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public Cell getCellAt(int row, int column) {
		return cells[row][column];
	}
}
