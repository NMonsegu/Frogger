package edu.cesi.libgdx.frogger.controler;

import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import edu.cesi.libgdx.frogger.resources.AudioManager;
import edu.cesi.libgdx.frogger.resources.ImagesManager;
import edu.cesi.libgdx.frogger.resources.SettingsManager;
import edu.cesi.libgdx.frogger.view.menu.MenuScreen;

public class MainGame extends Game {
	
	/**
	 * This class is the entry point. All screens are managed by it extending the Game class which allow to use the setScreen method. 
	 * All methods are called in a thread that has the OpenGL context current.
	 * It also load game datas
	 * */
	
	ImagesManager imageManager;
	MenuScreen menuscreen;
	public SettingsManager settingsManager;
	public AudioManager audioManager;

	/**Called when the application is created.It load all datas then show the {@link}MenuScreen */
	public void create() 
	{	
		this.settingsManager = SettingsManager.getInstance();
		this.settingsManager.getLevel();
		this.settingsManager.getResolution();
		this.settingsManager.isSoundEnable();
		
		this.audioManager = AudioManager.getInstance();
		
		this.audioManager.loadSound();
		this.audioManager.loadMusic();
		
		this.imageManager = ImagesManager.getInstance();
		this.imageManager.loadTextureAtlas();
		this.imageManager.loadTextureRegion();
		
		this.setScreen(new MenuScreen());
	}
	
	@Override
	public void setScreen (Screen screen) {
		try 
		{
			Screen tmp = this.screen;
			if (this.screen != null) this.screen.hide();
			this.screen = screen;
			if (this.screen != null) {
				this.screen.show();
				this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			}
			tmp.dispose();
			
		}catch(NullPointerException  ex)
		{
			System.err.println(ex + " ->  setScreen");
		}catch(Exception ex){
			System.err.println(ex + "Exception setScreen");
		}
	}

	/** Called when the application is destroyed*/
	public void dispose() {
		super.dispose();
	}
	
	/**This is the OpenGL thread. This method is called at every cycle*/
	public void render() {		
		super.render();
	}
	
	/** Called when the application is resized.
	 * @param width the new width in pixels
	 * @param height the new height in pixels */
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	/** Called when the application is paused.*/
	public void pause() {
		super.pause();
	}
	
	/** Called when the application is resumed from a paused state, usually when it regains focus (usually for mobile). */
	public void resume() {
		super.resume();
	}

}
