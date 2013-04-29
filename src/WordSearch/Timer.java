package WordSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;


public class Timer extends TimerTask {
	private int elapsedTime;
	private boolean running;
	private static javax.swing.Timer ttimer;

	/**
	 * Constructor that sets timer to zero and running to false.
	 */
	public Timer() {
		elapsedTime = 0;
		running = false;
		ttimer = new javax.swing.Timer(1000, new TimerListener());
		
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			run();
			
		}
		
	}
	
	/**
	 * starts timer from stop.
	 */
	public void startTime() {
		running = true;
		ttimer.start();
	}
	
	/**
	 * stops timer from running.
	 */
	public void stopTime() {
		running = false;
		ttimer.stop();
	}

	/**
	 * stops timer until unpaused
	 */
	public void pauseTime() {
		running = !running;
	}

	/**
	 * sets time elapsed to zero
	 */
	public void resetTime() {
		elapsedTime = 0;
	}
	
	public int getElapsedTime() {
		return elapsedTime;
	}

	public String getTime() {
		int minutes = (int) (elapsedTime / 60);
		int second = (int) (elapsedTime - (minutes * 60));
		String str;
		if (second > 9) 
			str = second + ""; 
		else
			str = "0" + second;
		return minutes + ":" + str  + "   ";
	}
	
	public boolean isRunning() {
		return running;
	}
	
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		if (running) {
			elapsedTime++;
			Game.currentGame.setDisplayTime();
		}	
	}
}
