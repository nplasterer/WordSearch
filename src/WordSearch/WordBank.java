package WordSearch;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	
	public WordBank(ArrayList<String> words) {
		this.setLayout(new GridLayout(0, 2));
		this.setSize(new Dimension(200, 600));
		wordBank = new HashMap<String, JCheckBox>();
		//create check boxes
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
	
	public boolean contains(String word) {
		return wordBank.containsKey(word);
	}
	
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
	

}
