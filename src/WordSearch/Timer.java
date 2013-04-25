package WordSearch;

import java.util.TimerTask;

public class Timer extends TimerTask {
	private long elapsedTime;
	private boolean running;
	/*
	private long startTime;
	
	public boolean running;
	*/
	public Timer() {
		elapsedTime = 0;
		running = false;
	}
	/*
	public void startTime() {
		System.out.println("Start");
		running = true;
		startTime = System.currentTimeMillis();
	}
	*/
	public void pauseTime() {
		running = !running;
	}

	public void resetTime() {
		elapsedTime = 0;
	}
	/*
	public void stopTime() {
		System.out.println("Stop");
		elapsedTime += System.currentTimeMillis() - startTime;
		running = false;
	}
	

	*/
	public String getTime() {
		/*long totalTime = (elapsedTime + (System.currentTimeMillis() - startTime));
		int minutes = (int) (totalTime/60000);
		totalTime -= totalTime/60000;
		int seconds = (int) (totalTime/1000);
		totalTime -= totalTime/1000;*/
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
	
	@Override
	public void run() {
		if (running) {
			elapsedTime++;
			Game.currentGame.setDisplayTime();
		}	
	}
}
