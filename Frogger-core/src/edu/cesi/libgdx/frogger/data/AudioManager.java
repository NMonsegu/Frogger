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
	///private Sound win;
	//private Sound gameOver;
	
	private Sound click;
	private Music ambianceMusic;
	private AudioManager(){
	}
	
	private static class AudioManagerHolder{
		private final static AudioManager instance = new AudioManager();
	}
	
	public static AudioManager getInstance(){
		return AudioManagerHolder.instance;
	}
	
    public void loadSound() 
    {  

        this.hit      = loadSound("audio/hit.wav");  
        this.click    = loadSound("audio/click.wav");

    }  
    
    public void loadMusic()
    {
	    this.ambianceMusic =  loadMusic("audio/music.mp3");
    }
    
    /**
     * Play music looping.
     * @param music must be a {@link Music}
     */
    public void playMusic(Music music){
    	music.setVolume(0.5f);                 
    	music.setLooping(true); 
    	music.play();
    }
    
    /**
     * Stop music.
     * @param music must be a {@link Music}
     */
    public void stopMusic(Music music){
    	music.stop();
    }

    /**
     * Play a sound .
     * @param sound must be a {@link Sound}
     */
    public void playSound (Sound sound) {  
        sound.play(1,2f,0);  
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
    private Music loadMusic(String filename){

    		return Gdx.audio.newMusic(Gdx.files.internal(filename)); 
    }
    /***/
    private Sound loadSound (String filename) { 
    		return Gdx.audio.newSound(Gdx.files.internal(filename));  
    }

	public Sound getHit() {
		return hit;
	}

	public Music getAmbianceMusic() {
		return ambianceMusic;
	}  
}
