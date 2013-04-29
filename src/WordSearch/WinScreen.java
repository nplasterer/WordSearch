package WordSearch;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class WinScreen extends JDialog implements ActionListener {
	private Image original;
	private String playerName, time;
	private JButton closeMe;
	
	/**
	 * Constructor that takes a name, time, and category and then prints
	 * the players name with the associated score and category on the screen.
	 * Inserts a winning picture.
	 */
	public WinScreen(String playerName, String time, String category) {
		setSize(400,400);
		setTitle("Congratulations!");
		this.playerName = playerName;
		this.time = time;
		
		add(new JLabel("Congratulations " + playerName + ", you win! Your time was " + time + "!"), BorderLayout.NORTH);
		
		MediaTracker tracker = new MediaTracker(this);

		URL url = null;
		try {
			ArrayList<String> fileList = FileUtilities.getListOfFilesByCategory("Images", category);
			String fileName; 
			
			// Default Picture 
			if (fileList.size() == 0) {
				fileName = FileUtilities.getFileFullPath("Images", "mostawesome.png");
			} else { // Random picture from group				
				Random rand = new Random();
				fileName = FileUtilities.getFileFullPath("Images",fileList.get(rand.nextInt(fileList.size())));
			}
			url = new File(fileName).toURI().toURL();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		original = Toolkit.getDefaultToolkit().getImage(url);
		tracker.addImage(original, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) { return;}

		closeMe = new JButton("Close"); 
		add(closeMe, BorderLayout.SOUTH);
		closeMe.addActionListener(this);
		
	}
	
	  public void actionPerformed(ActionEvent evt) {
		  setVisible(false);
	  }
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int padding = 50;
		g.drawImage(original, padding, padding, 300, 300, null);
	}
	
	public static void main(String[] args) {
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {*/
					WinScreen screen = new WinScreen("Brandon", "0", "automobiles" );
//					screen = new WinScreen("Brandon", "0", "cats" );
					screen.setVisible(true);
			/*}
		});*/
	}
}
