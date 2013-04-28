package WordSearch;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HighScore extends JDialog {
	
	private Map<Integer, String> scores;
	private String fileName;
	

	public HighScore() {
		
	}
	
	public HighScore(String category) {
		setSize(600, 400);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Game.currentGame.getTimer().startTime();
				Game.currentGame.getBoard().setVisible(true);
				dispose();
			}
		});
		
		this.fileName = category + "Scores.txt";
		JLabel header = new JLabel(category + " high scores");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		add(header, BorderLayout.NORTH);
		this.scores = new HashMap<Integer, String>();
		
		getScores(fileName);
		sortScores();
		
	}
	
	//read in scores from file
	public void getScores(String fileName) {
		ArrayList<String> scoreList = FileUtilities.getListOfLinesFromFile("HighScores", fileName);
		String name;
		int time = 0;
		
		for(String s : scoreList) {
			String inputLine = s;
			String[] parts = inputLine.split(",");

			name = parts[0];
			time = Integer.parseInt(parts[1]);
			scores.put(time, name);
			}
		}		
	
	//find top five scores and add to panel
	public void sortScores() {
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(5,1));
		
		String name;
		String timeDisplay;
		int time = 0;
		int scoresToShow = 5;
		if(scores.size() < scoresToShow) 
			scoresToShow = scores.size();
		
		for(int i = 0; i < scoresToShow; i++) {
			time = 0;
			for(Integer a : scores.keySet()) {
				if(time == 0) 
					time = a;
				else if(a < time)
					time = a;
			}
			
			name = scores.get(time);
			scores.remove(time);
			int minutes = (int) (time / 60);
			int second = (int) (time - (minutes * 60));
			String str;
			if (second > 9) 
				str = second + ""; 
			else
				str = "0" + second;
			timeDisplay = minutes + ":" + str  + "   ";
			
			JLabel scoreLabel = new JLabel(name + " ----------------------------------------------------------------------------------------------------------------------- " + timeDisplay);
			scorePanel.add(scoreLabel);
		}
		add(scorePanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		HighScore score = new HighScore("Automobiles");
		score.setVisible(true);
	}

}
