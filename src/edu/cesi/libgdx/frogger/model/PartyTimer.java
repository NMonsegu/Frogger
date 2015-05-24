package edu.cesi.libgdx.frogger.model;

public class PartyTimer 
{
	
	private long timerRef;
	private long timer;
	private long time;
	
	private long lastSavedTimer;
	public PartyTimer()
	{
	}
	
	public void launchTimer(long time)
	{
		this.time = time;
		long tmp =System.currentTimeMillis()/1000;
		this.timerRef = tmp + this.time;
	}
	
	public void updateTimers(){
		
		long time =  System.currentTimeMillis()/1000;
		this.setTimer(timerRef - time);
		
		this.lastSavedTimer = timerRef - time;
	}
	
	public void resetTimer(){
		long tmp =System.currentTimeMillis()/1000;
		this.timerRef = tmp + time;
		
		this.lastSavedTimer = this.timerRef;
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}
}
