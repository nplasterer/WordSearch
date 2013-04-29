package WordSearch;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Naomi Plasterer, Brandon Bosso, Jason Steinberg, Austin Diviness
 */

public class WordBank extends JPanel{
	private Map<String, JCheckBox> wordBank;
	
	/**
	 * Prints all words that have been placed in puzzle on left hand side.
	 * Does not allow user to check check boxes.
	 */
	public WordBank(ArrayList<String> words) {
		this.setLayout(new GridLayout(0, 2));
		this.setSize(new Dimension(200, 600));
		wordBank = new HashMap<String, JCheckBox>();
		//create check boxes
		Collections.sort(words);
		for(String s : words) {
			JCheckBox box = new JCheckBox(s);
			//add action listener to disable checkbox
			box.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e)
			    {
			      JCheckBox box = (JCheckBox) e.getSource();
			      if(box.isSelected())
			    	  box.setSelected(false);
			      else
			    	  box.setSelected(true);
			    }
			    });
			wordBank.put(s, box);
			add(box);
		}
	}
	
	/**
	 * If selected word is in word bank updates.
	 */
	public boolean contains(String word) {
		return wordBank.containsKey(word);
	}
	
	/**
	 * Checks the check box of the correct word guessed.
	 */
	public void checkBox(String guess){
		JCheckBox box = wordBank.get(guess);
		box.setSelected(true);
	}

	public Map<String, JCheckBox> getWordBank() {
		return wordBank;
	}

	public ArrayList<String> getWords() {
		return new ArrayList<String>(wordBank.keySet());
	}
	
	/**
	 * Removes any words that could not be placed on the board
	 * from the word bank.
	 */
	public void removeWord(String word) {
		
		remove(wordBank.get(word));
		wordBank.remove(word);
		Game.currentGame.decreaseWordsLeft();
	}
	
}
