package WordSearch;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Naomi Plasterer, Brandon Bosso, Jason Steinberg, Austin Diviness
 */

public class SplashScreen extends JDialog {
	private JTextField playerName;
	private JComboBox categories;
	private JButton okButton;
	private JLabel nameLabel, categoryLabel;
	
	/**
	 * Default constructor creates a default splash screen with categories
	 * and name field.
	 */
	public SplashScreen() {
		// TODO This should be fleshed out better as a default case
		playerName = new JTextField();
		categories = new JComboBox();
		okButton = new JButton("OK");
		nameLabel = new JLabel("Enter Name: ");
		categoryLabel = new JLabel("Select a category: ");
		categories = null;
		JPanel panel = new JPanel();
		//instantiate frame
		setSize(new Dimension(400,300));
		panel.setLayout(new GridLayout(2,2));
		setTitle("Welcome!");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		okButton.addActionListener(new buttonListener());

		//add components
		panel.add(nameLabel);
		panel.add(playerName);
		panel.add(categoryLabel);
		panel.add(categories);
		add(panel, BorderLayout.CENTER);
		add(okButton, BorderLayout.SOUTH);
	}
	
	/**
	 * Constructor that takes a list of words and creates a field
	 * for categories with them. This also creates a listener that will not continue unless
	 * a players name has been entered.
	 */
	public SplashScreen(ArrayList<String> words) {
		//instantiate variables
		playerName = new JTextField();
		categories = new JComboBox();
		okButton = new JButton("OK");
		nameLabel = new JLabel("Enter Name: ");
		categoryLabel = new JLabel("Select a category: ");
		JPanel panel = new JPanel();
		
		//instantiate frame
		setSize(new Dimension(400,300));
		panel.setLayout(new GridLayout(2,2));
		setTitle("Welcome!");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		okButton.addActionListener(new buttonListener());
		
		for(String s : words) {
			categories.addItem(s);
		}
		
		
		//add components
		panel.add(nameLabel);
		panel.add(playerName);
		panel.add(categoryLabel);
		panel.add(categories);
		add(panel, BorderLayout.CENTER);
		add(okButton, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = playerName.getText();
			
			//if player has entered name, set name and category
			if(name.length() != 0) {
				setVisible(false);
				Game.currentGame.setCategory((String) categories.getSelectedItem());
				Game.currentGame.loadConfigFile(categories.getSelectedItem() + ".txt");
				Game.currentGame.setDisplayTime();
				Game.currentGame.getTimer().startTime();
			}
			//otherwise give error message
			else {
				JOptionPane.showMessageDialog(null, "Please enter a name");
			}
		}
		
	}

	
	public String getPlayerName() {
		return playerName.getText();
	}
	
	public String getCategory() {
		return (String) categories.getSelectedItem();
	}

	public void setCategoriesText(String category) {
		categories.setSelectedItem(category);
	}
	
	public void click() {
		okButton.doClick();
	}

}
