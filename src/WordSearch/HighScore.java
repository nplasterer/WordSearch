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
	
	/**
	 * Constructor takes a category that was played and then updates
	 * the corresponding highscore card with that players time and name information.
	 */
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
	
	/**
	 * Reads in scores from file.
	 */
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
	

	/**
	 * Sorts scores and then picks the top 5 fastest scores to print.
	 */
	public void sortScores() {
		JPanel scorePanel = new JPanel();
		JPanel namePanel = new JPanel();
		JPanel timePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(1,2));
		namePanel.setLayout(new GridLayout(5,1));
		timePanel.setLayout(new GridLayout(5,1));
		
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
			
			JLabel nameLabel = new JLabel(name);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel timeLabel = new JLabel(timeDisplay);
			timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			namePanel.add(nameLabel);
			timePanel.add(timeLabel);
		}
		scorePanel.add(namePanel);
		scorePanel.add(timePanel);
		add(scorePanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		HighScore score = new HighScore("Automobiles");
		score.setVisible(true);
	}

}
