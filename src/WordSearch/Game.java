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
		// TODO Auto-generated constructor stub
	}
	
	public void loadConfigFile(String file){
		
	}
	
	
	public boolean checkIfDone(){
		return false;
	}
	
	public void showWinScreen(){
		
	}
	
	public boolean checkValidWord(String word){
		return false;
	}
	
	public boolean checkValidSelection(Point start, Point end){
		return false;
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
		return null;
	}
	
	public ArrayList<String> getListofLinesFromFile(String dir, String file) {
		return null;
	}
	
	public Board getBoard() {
		return board;
	}
}
