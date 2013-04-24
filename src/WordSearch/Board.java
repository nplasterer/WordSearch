package WordSearch;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

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
		//sort words by descending length
		ArrayList<String> wordsInOrder = new ArrayList<String>();
		while(!words.isEmpty()) {
			int index = 0;
			for(int i = 0; i < words.size(); i++) {
				if(words.get(i).length() > words.get(index).length()) {
					index = i;
				}
			}
			wordsInOrder.add(words.get(index));
			words.remove(index);
		}
		
		//randomly place first word and then remove from wordsInOrder
		Random rand = new Random();
		boolean forward = true;
		if(rand.nextInt(1) == 0)
			forward = false;
		
		int row = 0;
		int column = 0;
		int direction = rand.nextInt(4);
		String toInsert = wordsInOrder.get(0);
		if(!forward)
			toInsert = new StringBuffer(toInsert).reverse().toString();
		
		switch(direction) {	
		//horizontal
		case 0: 
			row = rand.nextInt(rows);
			column = rand.nextInt(columns - wordsInOrder.get(0).length());
			for(int i = 0; i < toInsert.length(); i++) {
				getCellAt(row, column + i).setLetter(toInsert.charAt(i));
			}
			break;
		//vertical
		case 1:
			row = rand.nextInt(rows - wordsInOrder.get(0).length());
			column = rand.nextInt(columns);
			for(int i = 0; i < toInsert.length(); i++) {
				getCellAt(row + i, column).setLetter(toInsert.charAt(i));
			}
			break;
		// / diagonal
		case 2:
			row = rand.nextInt(rows - wordsInOrder.get(0).length()) + wordsInOrder.get(0).length();
			column = rand.nextInt(columns - wordsInOrder.get(0).length());
			for(int i = 0; i < toInsert.length(); i++) {
				getCellAt(row - i, column + i).setLetter(toInsert.charAt(i));
			}
			break;
		// \ diagonal
		case 3:
			row = rand.nextInt(rows - wordsInOrder.get(0).length());
			column = rand.nextInt(columns - wordsInOrder.get(0).length());
			for(int i = 0; i < toInsert.length(); i++) {
				getCellAt(row + i, column + i).setLetter(toInsert.charAt(i));
			}
			break;
		default:
			break;
		}
		
		wordsInOrder.remove(0);
		
		//insert each word in order
		for(String s : wordsInOrder)
			insertWord(s);
	}
	
	public void insertWord(String word) {
		/*Sort all the words by length, descending.
		Take the first word and place it on the board.
		Take the next word.
		Search through all the words that are already on the board and see if there are any possible intersections (any common letters) with this word.
		If there is a possible location for this word, loop through all the words that are on the board and check to see if the new word interferes.
		If this word doesn't break the board, then place it there and go to step 3, otherwise, continue searching for a place (step 4).
		Continue this loop until all the words are either placed or unable to be placed.*/
		// TODO implement function
		for (int i = 0; i < word.length(); ++i) {
			cells[0][i].setLetter(word.charAt(i));
		}
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
	
	public void highlight(int row, int col) {
		cells[row][col].highlight();
	}
}
