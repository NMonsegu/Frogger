package edu.cesi.libgdx.frogger.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager 
{
	
	/**This class contain the Audio files
	 * It allow to load them into memory, play musics and sounds.
	 **/
	
	private Sound hit;  
	private Music move;
	private Sound click;
	private Music ambianceMusic;
	private Sound timerEnd;
	private Sound timeout;
	private Sound win;
	private Sound gameOver;
	
	
	private AudioManager()
	{
	}
	
	private static class AudioManagerHolder
	{
		private final static AudioManager instance = new AudioManager();
	}
	
	public static AudioManager getInstance()
	{
		return AudioManagerHolder.instance;
	}
	
    public void loadSound() 
    {  
        this.hit      = loadSound("audio/hit.wav");  
        this.click    = loadSound("audio/click.wav");
    	this.timerEnd = loadSound("audio/timerend.wav");  
    	this.timeout = loadSound("audio/timeout.wav");  
    	this.win = loadSound("audio/win.wav");
    	this.gameOver = loadSound("audio/gameover.wav");  
    }  
    
    public Sound getTimerEnd() {
		return timerEnd;
	}

	public Sound getTimeout() {
		return timeout;
	}

	public Sound getWin() {
		return win;
	}

	public Sound getGameOver() {
		return gameOver;
	}

	public void loadMusic()
    {
	    this.ambianceMusic =  loadMusic("audio/music.mp3");
	    this.move    = loadMusic("audio/move.wav");
	     
    }
    
    /**
     * Play music looping.
     * @param music must be a {@link Music}
     */
    public void playMusic(Music music)
    {
    	music.setVolume(0.5f);                 
    	music.setLooping(true); 
    	music.play();
    }
    
    /**
     * Stop music.
     * @param music must be a {@link Music}
     */
    public void stopMusic(Music music)
    {
    	music.stop();
    }

    /**
     * Play a sound .
     * @param sound must be a {@link Sound}
     */
    public void playSound (Sound sound)
    {  
        //sound.play(1,2f,0);  
    	 sound.play(); 
    }  
	
    /**Unload musics and sounds*/
	public void dispose()
	{
		hit.dispose();
		click.dispose();
	}
	
	/**
	 * Load music files 
	 * @param filename : music name
	 * */
    private Music loadMusic(String filename)
    {

    		return Gdx.audio.newMusic(Gdx.files.internal(filename)); 
    }
    /***/
    private Sound loadSound (String filename) 
    { 
    		return Gdx.audio.newSound(Gdx.files.internal(filename));  
    }

	public Sound getHit()
	{
		return hit;
	}

	public Music getAmbianceMusic() 
	{
		return ambianceMusic;
	}  
	
	public Music getMove()
	{
		return this.move;
	}
}
