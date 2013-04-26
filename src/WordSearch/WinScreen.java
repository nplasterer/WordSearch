package WordSearch;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class WinScreen extends JDialog {
	private Image thumbnail;
	private String playerName, time;
	
	public WinScreen(String playerName, String time) {
		setSize(400,400);
		setTitle("Congratulations!");
		this.playerName = playerName;
		this.time = time;
		
		add(new JLabel("Congratulations " + playerName + ", you win! Your time was " + time + System.getProperty("line.separator") + "Would you like to play again?"), BorderLayout.NORTH);
		
		MediaTracker tracker = new MediaTracker(this);
		URL url = getClass().getResource("/cat.png");
		Image original = Toolkit.getDefaultToolkit().getImage(url);
		tracker.addImage(original, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) { return;}
		
		thumbnail = original.getScaledInstance(300, 300, Image.SCALE_FAST);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int padding = 50;
		g.drawImage(thumbnail, padding, padding, 300, 300, null);
		add(new JLabel("Congratulations " + playerName + ", you win! Your time was " + time + System.getProperty("line.separator") + "Would you like to play again?"), BorderLayout.NORTH);

	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
					WinScreen screen = new WinScreen("Brandon", "0");
					screen.setVisible(true);
			}
		});
	}
}
