package WordSearch;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 * @author Naomi Plasterer, Brandon Bosso, Jason Steinberg, Austin Diviness
 */

public class Game extends JFrame{
	private String playerName;
	private Timer timer;
	private String category;
	public static Game currentGame;
	//private ArrayList<String> words;
	private int wordsLeft;
	private Point start;
	private Point end;
	private Board board;
	private WordBank wordBank;
	private SplashScreen splashScreen;
	private JMenuBar menu;
	private JMenu file;
	private JMenuItem play, exit, newGame, highScores;
	private JLabel timeDisplay;
	
	public Game() {
		currentGame = this;
		//initialize frame
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("WordSearch: The Bestest Ever");
		
		//initialize variables
		start = null;
		end = null;
		timer = new Timer();

		
		//create JMenuBar
		menu = new JMenuBar();
		timeDisplay = new JLabel();
		file = new JMenu("File");
		play = new JMenuItem("Play/Pause");
		exit = new JMenuItem("Exit");
		newGame = new JMenuItem("New Game");
		highScores = new JMenuItem("High Scores");
		highScores.addActionListener(new fileListener());
		newGame.addActionListener(new fileListener());
		play.addActionListener(new fileListener());
		exit.addActionListener(new fileListener());
		file.add(play);
		file.add(newGame);
		file.add(highScores);
		file.add(exit);
		menu.add(file);
		menu.add(Box.createHorizontalGlue());
		menu.add(timeDisplay);
		setJMenuBar(menu);
		
		//create splash screen and get player name and category file
		splashScreen = new SplashScreen(FileUtilities.getListOfFiles("SecretFiles", ".txt", true));
		
		
	}
	

	
	private class fileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == play) {
				//start/stop time
				timer.pauseTime();
				//hide/show board
				if(board.isVisible())
					board.setVisible(false);
				else
					board.setVisible(true);
			}
			else if(e.getSource() == newGame) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to start a new game?", "Please choose an option", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					dispose();
					startNewGame();
				}
			}
			else if(e.getSource() == highScores) {
				timer.stopTime();
				board.setVisible(false);
				HighScore scores = new HighScore(category);
				scores.setVisible(true);
			}
			else if(e.getSource() == exit) 
				System.exit(0);
		}
		
	}
	
	
	public void loadConfigFile(String file){
		playerName = splashScreen.getPlayerName();
		ArrayList<String> words = FileUtilities.getListOfLinesFromFile("SecretFiles", file);
		ArrayList<String> toAdd = new ArrayList<String>();
		for(String s : words) {
			String add = "";
			for(int i = 0; i < s.length(); i ++) {
				if(i == 0)
					add += Character.toUpperCase(s.charAt(i));
				else
					add += Character.toLowerCase(s.charAt(i));
			}
			toAdd.add(add);
		}
		wordsLeft = words.size();
		wordBank = new WordBank(toAdd);
		this.add(wordBank, BorderLayout.EAST);
		board = new Board(20, 20, toAdd);
		this.add(board, BorderLayout.CENTER);
		
		
		
		this.setVisible(true);
		board.listen();
	}
	
	
	public boolean checkIfDone(){
		return (wordsLeft == 0) ? true : false;
	}
	
	public void showWinScreen(){
		timer.stopTime();
		writeScore();
		setCategory(splashScreen.getCategory());
		WinScreen screen = new WinScreen(playerName, timer.getTime(), getCategory().toLowerCase()); //"automorewdfbiles" );
		
		screen.setVisible(true);
	}
	
	public void writeScore() {
		String toWrite;
		int time = timer.getElapsedTime();
		String fileName = category + "Scores.txt";
		
		File file = new File("HighScores/" + fileName);
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Unable to create file");
			}
		}
		
		try {
			FileOutputStream out = new FileOutputStream(file, true);
			PrintStream print = new PrintStream(out);
			print.println(playerName + "," + time);
			print.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		
	}
	
	public boolean checkValidWord(String word){
		String toCheck = "";
		for(int i = 0; i < word.length(); i++) {
			if(i == 0)
				toCheck += Character.toUpperCase(word.charAt(i));
			else
				toCheck += Character.toLowerCase(word.charAt(i));
		}
		return wordBank.contains(toCheck);
	}
	
	public boolean checkValidSelection(Point start, Point end){
		// checks rows, cols, \ diagonal, / diagonal
		boolean flag = false;
		if (start.x == end.x) {
			flag = true;
		}
		else if (start.y == end.y) {
			flag = true;
		}
		else if ((start.x - start.y) == (end.x - end.y)) {
			flag = true;
		}
		else if ((start.x + start.y) == (end.x + end.y)) {
			flag = true;
		}
		return flag;
	}
	
	public void highlightWords(Point start, Point end) {
        // vertical word downwards
        if (start.x == end.x && start.y < end.y) {
            for (int i = start.y; i <= end.y; ++i) {
                board.highlight(i, start.x);
            }
        }
        // vertical word upwards
        if (start.x == end.x && start.y > end.y) {
            for (int i = start.y; i >= end.y; --i) {
                board.highlight(i, start.x);
            }
        }
        // horizontal word left->right
        else if (start.y == end.y && start.x < end.x) {
            for (int i = start.x; i <= end.x; ++i) {
                board.highlight(start.y, i);
            }
        }
        // horizontal word right->left
        else if (start.y == end.y && start.x > end.x) {
            for (int i = start.x; i >= end.x; --i) {
                board.highlight(start.y, i);
            }
        }
        // / diagonal word upper right to lower left
        else if (start.x + start.y == end.x + end.y && start.x > end.x) {
            for (int i = 0; i <= (start.x - end.x); ++i) {
                board.highlight(start.y + i, start.x - i);
            }
        }
        // / diagonal word lower left to upper right
        else if (start.x + start.y == end.x + end.y && start.x < end.x) {
            for (int i = 0; i <= (end.x - start.x); ++i) {
                board.highlight(start.y - i, start.x + i);
            }
        }
        // \ diagonal upper left to lower right
        else if (end.x - start.x == end.y - start.y && start.x < end.x) {
            for (int i = 0; i <= (end.x - start.x); ++i) {
                board.highlight(start.y + i, start.x + i);
            }
        }
        // \ diagonal lower right to upper left
        else if (end.x - start.x == end.y - start.y && start.x > end.x) {
            for (int i = 0; i <= (start.x - end.x); ++i) {
                board.highlight(start.y - i, start.x - i);
            }
        }
        board.repaint();
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setWordsLeft(int left) {
		wordsLeft = left;
	}
	
	public ArrayList<String> getWords() {
		return wordBank.getWords();
	}
	
	public WordBank getWordBank() {
		return wordBank;
	}
	
	public void setWordBank(WordBank bank) {
		// TODO should this set the words left?
		wordBank = bank;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setPlayerName(String name) {
		this.playerName = name;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public static void startNewGame() {
		Game game = new Game();

	}
	
	public void runWord() {
		Point start = board.getMousePress();
		Point end = board.getMouseRelease();
		String word;
		if (checkValidSelection(start, end)) {
			
			//check forwards
			word = board.getWordAt(start, end);
			String backwords = "";
			String toCheck = "";
			for(int i = 0; i < word.length(); i++) {
				if(i == 0) {
					toCheck += Character.toUpperCase(word.charAt(i));
					backwords += Character.toLowerCase(word.charAt(i));
				}
				else if(i == word.length() - 1) {
					backwords += Character.toUpperCase(word.charAt(i));
					toCheck += Character.toLowerCase(word.charAt(i));
				}
				else {
					toCheck += Character.toLowerCase(word.charAt(i));
					backwords += Character.toLowerCase(word.charAt(i));
				}
			}
			word = toCheck;

			if (checkValidWord(word)) {	
				if(wordBank.contains(word) && !wordBank.getWordBank().get(word).isSelected()) {
					wordBank.checkBox(word);
					--wordsLeft;
					this.highlightWords(start, end);
				}
			}
			
			//check backwords
			else if(wordBank.contains(new StringBuffer(backwords).reverse().toString()) && checkValidWord(new StringBuffer(backwords).reverse().toString())) {
				if(!wordBank.getWordBank().get(new StringBuffer(backwords).reverse().toString()).isSelected()) {
					this.highlightWords(start, end);
					wordBank.checkBox(new StringBuffer(backwords).reverse().toString());
					--wordsLeft;
				}
			}
			
			if (checkIfDone())
				showWinScreen();
		}
	}
	
	public void decreaseWordsLeft() {
		wordsLeft--;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public void setDisplayTime() {
		timeDisplay.setText(timer.getTime());
	}

	
	
	public static void main(String[] args) {
		startNewGame();
		//game.setVisible(true);
		//game.highlightWords(new Point(0,0), new Point(5,5));
		//game.splashScreen.setVisible(true);
	}
}
