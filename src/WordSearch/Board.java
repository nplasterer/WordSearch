package WordSearch;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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
		
		//insert each word in order
		for(String s : wordsInOrder)
			insertWord(s, 0);
		
		//TODO fill remaining cells with random characters
	}
	
	public boolean checkValidLocation(Point start, Point end, String toPlace){
		 // vertical word
        if (start.x == end.x) {
            for (int i = start.y; i < end.y; ++i) {
                if(getCellAt(i, start.x).getLetter() != ' ' && !Character.toString(getCellAt(i, start.x).getLetter()).equalsIgnoreCase(Character.toString(toPlace.charAt(i - start.y)))) {
                	return false;
                }
            }
        }
        // horizontal word
        else if (start.y == end.y) {
            for (int i = start.x; i < end.x; ++i) {
            	if(getCellAt(start.y, i).getLetter() != ' ' && !Character.toString(getCellAt(start.y, i).getLetter()).equalsIgnoreCase(Character.toString(toPlace.charAt(i - start.x)))) {
            		return false;
            }
            }
        }
        // / diagonal word
        else if (start.x + start.y == end.x + end.y) {
            for (int i = 0; i < (end.x - start.x); ++i) {
            	if(getCellAt(start.y - i, start.x + i).getLetter() != ' ' && !Character.toString(getCellAt(start.y - i, start.x + i).getLetter()).equalsIgnoreCase(Character.toString(toPlace.charAt(i))))
                	return false;
            }
        }
        // \ diagonal
        else if (end.x - start.x == end.y - start.y) {
            for (int i = 0; i < (end.x - start.x); ++i) {
            	if(getCellAt(start.y + i, start.x + i).getLetter() != ' ' && !Character.toString(getCellAt(start.y + i, start.x + i).getLetter()).equalsIgnoreCase(Character.toString(toPlace.charAt(i))))
                	return false;
            }
        }
        return true;
	}
	
	public void insertWord(String word, int timesTried) {
		/*
		Take the next word.
		Search through all the words that are already on the board and see if there are any possible intersections (any common letters) with this word.
		If there is a possible location for this word, loop through all the words that are on the board and check to see if the new word interferes.
		If this word doesn't break the board, then place it there and go to step 3, otherwise, continue searching for a place (step 4).
		Continue this loop until all the words are either placed or unable to be placed.*/
		// TODO implement function
		/*for (int i = 0; i < word.length(); ++i) {
			cells[0][i].setLetter(word.charAt(i));
		}*/
		if(timesTried < 400) {
			Random rand = new Random();
			boolean forward = true;
			if(rand.nextInt(2) == 0)
				forward = false;

			int row = 0;
			int column = 0;
			int direction = rand.nextInt(4);
			if(!forward)
				word = new StringBuffer(word).reverse().toString();

			switch(direction) {	
			//horizontal
			case 0: 
				row = rand.nextInt(rows);
				column = rand.nextInt(columns - word.length());
				if(checkValidLocation(new Point(column, row), new Point(column + word.length(), row), word)) {
					for(int i = 0; i < word.length(); i++) {
						getCellAt(row, column + i).setLetter(word.charAt(i));
					}
				}
				else {
					insertWord(word, timesTried++);
				}
				break;
				//vertical
			case 1:
				row = rand.nextInt(rows - word.length());
				column = rand.nextInt(columns);
				if(checkValidLocation(new Point(column, row), new Point(column, row + word.length()), word)) {
					for(int i = 0; i < word.length(); i++) {
						getCellAt(row + i, column).setLetter(word.charAt(i));
					}
				}
				else {
					insertWord(word, timesTried++);
				}
				break;
				// / diagonal
			case 2:
				row = rand.nextInt(rows - word.length()) + word.length();
				column = rand.nextInt(columns - word.length());
				if(checkValidLocation(new Point(column, row), new Point(column + word.length(), row - word.length()), word)) {
					for(int i = 0; i < word.length(); i++) {
						getCellAt(row - i, column + i).setLetter(word.charAt(i));
					}
				}
				else {
					insertWord(word, timesTried++);
				}
				break;
				// \ diagonal
			case 3:
				row = rand.nextInt(rows - word.length());
				column = rand.nextInt(columns - word.length());
				if(checkValidLocation(new Point(column, row), new Point(column + word.length(), row + word.length()), word)) {
					for(int i = 0; i < word.length(); i++) {
						getCellAt(row + i, column + i).setLetter(word.charAt(i));
					}
				}
				else {
					insertWord(word, timesTried++);
				}
				break;
			default:
				break;
			}
		}
		else {
			Game.currentGame.getWordBank().removeWord(word);
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
	
	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(800,815));
		Board board = new Board(25, 25, new ArrayList<String>());
		frame.add(board);
		frame.setVisible(true);
	}*/
	
	public void highlight(int row, int col) {
		cells[row][col].highlight();
	}
}
