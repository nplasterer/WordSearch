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
		
	}
	
	public Board(int rows, int columns, ArrayList<String> words) {
		this.rows = rows;
		this.columns = columns;
		this.cells = new Cell[rows][columns];
		generatePuzzle(words);
	}
	
	public void generatePuzzle(ArrayList<String> words){
		// TODO implement function
	}
	
	public void insertWord(String word) {
		// TODO implement function
	}
	
	@Override
	public void paintComponent(Graphics g){
		int height = this.getHeight()/rows;
		int width = this.getWidth()/columns;
		Cell.setHeight(height);
		Cell.setWidth(width);
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				cells[i][j].draw(g);
			}
		}
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
