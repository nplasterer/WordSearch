import java.awt.Point;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JFrame;


public class Game extends JFrame{
	private String playerName;
	private GregorianCalendar time;
	private String categories;
	private ArrayList<String> words;
	private int wordsLeft;
	private Point start;
	private Point end;
	
	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadConfigFile(String file){
		
	}
	
	public ArrayList<String> getCategories(){
		return null;
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

}
