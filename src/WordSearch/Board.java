package WordSearch;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
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
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				cells[i][j] = new Cell(i,j);
			}
		}
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
		super.paintComponent(g);
		int height = this.getHeight()/rows;
		int width = this.getWidth()/columns;
		if(width <= height)
			Cell.setWidth(width);
		else
			Cell.setWidth(height);
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
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(800,815));
		Board board = new Board(25, 25, new ArrayList<String>());
		frame.add(board);
		frame.setVisible(true);
	}
}
