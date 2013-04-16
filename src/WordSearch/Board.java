package WordSearch;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


public class Board extends JPanel {
	private int rows;
	private int columns;
	private Cell[][] cells;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public void generatePuzzle(ArrayList<String> words){
		
	}
	
	public void insertWord(String word) {
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		
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
