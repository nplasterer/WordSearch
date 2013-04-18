package WordSearch;
import java.awt.Point;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JFrame;


public class Game extends JFrame{
	private String playerName;
	private GregorianCalendar time;
	private String category;
	private ArrayList<String> words;
	private int wordsLeft;
	private Point start;
	private Point end;
	private Board board;
	private WordBank wordBank;
	private SplashScreen splashScreen;
	
	public Game() {
		// TODO create constructor
	}
	
	public void loadConfigFile(String file){
		// TODO implement function
	}
	
	
	public boolean checkIfDone(){
		// TODO implement function
		return false;
	}
	
	public void showWinScreen(){
		// TODO implement function
	}
	
	public boolean checkValidWord(String word){
		// TODO implement function
		return false;
	}
	
	public boolean checkValidSelection(Point start, Point end){
		// TODO implement function
		return false;
	}
	
	public void highlightWords(Point start, Point end) {
		
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setWordsLeft(int left) {
		wordsLeft = left;
	}
	
	public ArrayList<String> getWords() {
		return words;
	}
	
	public WordBank getWordBank() {
		return wordBank;
	}
	
	public ArrayList<String> getListofFiles(String dir, String ext, boolean includeExtension) {
		// TODO implement function
		return null;
	}
	
	public ArrayList<String> getListofLinesFromFile(String dir, String file) {
		// TODO implement function
		return null;
	}
	
	public Board getBoard() {
		return board;
	}
}
